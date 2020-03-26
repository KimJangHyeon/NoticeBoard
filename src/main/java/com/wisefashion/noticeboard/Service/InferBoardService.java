package com.wisefashion.noticeboard.Service;

import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.VO.FilterVO;
import com.wisefashion.noticeboard.VO.MatchVO;
import com.wisefashion.noticeboard.VO.SignInVO;
import com.wisefashion.noticeboard.VO.WriteVO;

import java.util.List;

public interface InferBoardService {
    String getBoardRead(int id);
    int postBoardWrite(WriteVO writeVO);
    List<BoardEntity> getListRead(FilterVO filterVO);
    boolean postLogIn(SignInVO signInVO);
    boolean postUserMatch(MatchVO matchVO);
    int postBoardUpdate(WriteVO writeVO);
    int delMyBoard(int id);
}
