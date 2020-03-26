package com.wisefashion.noticeboard.Controller;

import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.Service.BoardService;
import com.wisefashion.noticeboard.VO.FilterVO;
import com.wisefashion.noticeboard.VO.WriteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;
//    , @RequestBody String title, @RequestBody String text

    @PostMapping("/write")
    @ResponseBody
    int postContentWrite(@RequestBody WriteVO writeVO) {
        return this.boardService.postBoardWrite(writeVO);
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
}
