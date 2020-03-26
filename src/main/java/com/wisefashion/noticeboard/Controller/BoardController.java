package com.wisefashion.noticeboard.Controller;

import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.Service.BoardService;
import com.wisefashion.noticeboard.VO.FilterVO;
import com.wisefashion.noticeboard.VO.MatchVO;
import com.wisefashion.noticeboard.VO.SignInVO;
import com.wisefashion.noticeboard.VO.WriteVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class BoardController {
    @Autowired
    BoardService boardService;
//    , @RequestBody String title, @RequestBody String text

    @PostMapping("/write")
    int postContentWrite(@RequestBody WriteVO writeVO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SignInVO sign = (SignInVO) session.getAttribute("key");
        writeVO.setName(sign.getName());
        if (sign == null) return 0;
        if (sign.getName() == null) {
            return -1;
        }
        else return this.boardService.postBoardWrite(writeVO);
    }

    @PutMapping("/update")
    int putContentUpdate(@RequestBody WriteVO writeVO, HttpServletRequest request) {
        boolean isSame;
        HttpSession session = request.getSession();
        SignInVO sign = (SignInVO) session.getAttribute("key");
        MatchVO matchVO;
        if (sign == null) return 0;
        else if (sign.getName() == null) return -1;
        matchVO = new MatchVO(writeVO.getId(), sign.getName());
        isSame = this.boardService.postUserMatch(matchVO);
        if (!isSame) return -2;
        else {
            this.boardService.postBoardUpdate(writeVO);
            return 1;
        }
    }


    @DeleteMapping("/delete/{id}")
    int delMyBoard(@PathVariable int id, HttpServletRequest request) {
        boolean isSame;
        HttpSession session = request.getSession();
        SignInVO sign = (SignInVO) session.getAttribute("key");
        MatchVO matchVO;
        if (sign == null) return 0;
        else if (sign.getName() == null) return -1;
        matchVO = new MatchVO(id, sign.getName());
        isSame = this.boardService.postUserMatch(matchVO);
        if (!isSame) return -2;
        else {
            this.boardService.delMyBoard(id);
            return 1;
        }
    }

    @GetMapping("/read")
    String getTextRead(@RequestParam("id") int id) {
        return this.boardService.getBoardRead(id);
    }

    @GetMapping("/list")
    List<BoardEntity> getList(@RequestParam("page") int page, @RequestParam("filter") String filter) {
        FilterVO filterVO = new FilterVO(page, filter);
        return this.boardService.getListRead(filterVO);
    }

    @PostMapping("/logIn")
    int postLogIn(@RequestBody SignInVO signInVO, HttpServletRequest request) {
        boolean isUser;
        try {
            HttpSession session = request.getSession();
            SignInVO sign = (SignInVO) session.getAttribute("key");
            if (sign != null) return -2;
            isUser = this.boardService.postLogIn(signInVO);

            if (isUser) {
                session.setAttribute("key", signInVO);
                return 1;
            } else {
                return -1;
            }
        } catch(Exception e) {
            return 0;
        }
    }

    @PostMapping("/logOut")
    int postLogOut(HttpServletRequest request) {
        try {
            request.getSession().invalidate();
//            request.getSession().setAttribute(key, null);
//            if (request.getSession().getAttribute(key) == null) {
//                return 1;
//            } else {
//                return -1;
//            }
            return 1;
        } catch(Exception e) {
            return 0;
        }
    }
}
