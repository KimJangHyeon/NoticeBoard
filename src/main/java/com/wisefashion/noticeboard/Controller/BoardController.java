package com.wisefashion.noticeboard.Controller;

import com.wisefashion.noticeboard.Constants.EntityMessage;
import com.wisefashion.noticeboard.Constants.LogInMessage;
import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.Service.BoardService;
import com.wisefashion.noticeboard.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/api")
public class BoardController {
    @Autowired
    BoardService boardService;
//    , @RequestBody String title, @RequestBody String text

    @PostMapping("/write")
    int postContentWrite(@RequestBody WriteDTO writeDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SignInDTO sign = (SignInDTO) session.getAttribute("key");
        if (sign == null) return EntityMessage.DID_NOT_LOGIN;   //로그인이 되었는지 확인
        writeDTO.setName(sign.getName());    //글쓴이를 세션을 통해서 넘김
        if (sign.getName() == null) {   //-> 버그확인: 로그인이 되었지만 name이 없는 경우
            return EntityMessage.NAME_IS_NULL;
        }
        else return this.boardService.postBoardWrite(writeDTO);  //->쓰기성공
    }

    @PutMapping("/update")
    int putContentUpdate(@RequestBody UpdateDTO updateDTO, HttpServletRequest request) {
        boolean isSame;
        HttpSession session = request.getSession();
        SignInDTO sign = (SignInDTO) session.getAttribute("key");
        MatchDTO matchDTO;
        try {
            if (sign == null) return EntityMessage.DID_NOT_LOGIN;   //세션이 있는지 == 로그인 되었는지
            else if (sign.getName() == null) return EntityMessage.NAME_IS_NULL; //로그인은 했지만 이름이 없는 경우
            matchDTO = new MatchDTO(updateDTO.getId(), sign.getName()); //날려도 되는지 체크하는 VO -> 프론트에 id가 있기 때문에
            isSame = this.boardService.postUserMatch(matchDTO);  //해당 게시글의 id를 통해 name이 일치하는 글인지 확인하기 위함
            if (!isSame) return EntityMessage.NAME_DOES_NOT_MATCH;
            else {
                this.boardService.postBoardUpdate(updateDTO);
                return EntityMessage.SUCCESS;
            }
        } catch (Exception e) {
            return EntityMessage.UNKNOWN_ERROR;
        }
    }


    @DeleteMapping("/delete/{id}")
    int delMyBoard(@PathVariable int id, HttpServletRequest request) {
        boolean isSame;
        HttpSession session = request.getSession();
        SignInDTO sign = (SignInDTO) session.getAttribute("key");
        MatchDTO matchVO;
        try {
            if (sign == null) return EntityMessage.DID_NOT_LOGIN;
            else if (sign.getName() == null) return EntityMessage.NAME_IS_NULL;
            matchVO = new MatchDTO(id, sign.getName());
            isSame = this.boardService.postUserMatch(matchVO);
            if (!isSame) return EntityMessage.NAME_DOES_NOT_MATCH;
            else {
                this.boardService.delMyBoard(id);
                return EntityMessage.SUCCESS;
            }
        } catch(Exception e) {
            return EntityMessage.UNKNOWN_ERROR;
        }
    }

    @GetMapping("/read")
    String getTextRead(@RequestParam("id") int id) {
        return this.boardService.getBoardRead(id);
    }

    @GetMapping("/list")
    List<BoardEntity> getList(@RequestParam("page") int page, @RequestParam("filter") String filter) {
        FilterDTO filterVO = new FilterDTO(page, filter);
        return this.boardService.getListRead(filterVO);
    }

    @Transactional
    @PostMapping("/logIn")
    int postLogIn(@RequestBody SignInDTO signInDTO, HttpServletRequest request) {
        boolean isUser;
        try {
            HttpSession session = request.getSession();
            SignInDTO sign = (SignInDTO) session.getAttribute("key");
            if (sign != null) return LogInMessage.ALREADY_LOG_IN;
            isUser = this.boardService.postLogIn(signInDTO);

            if (isUser) {
                session.setAttribute("key", signInDTO);
                return LogInMessage.AUTH_SUCCESS;
            } else {
                return LogInMessage.NO_USER_DATA;
            }
        } catch(Exception e) {
            return LogInMessage.ERROR;
        }
    }

    @PostMapping("/logOut")
    int postLogOut(HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            return 1;
        } catch(Exception e) {
            return 0;
        }
    }
}
