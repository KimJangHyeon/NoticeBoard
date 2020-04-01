package com.wisefashion.noticeboard.Service;

import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.DTO.*;

import java.util.List;

public interface InferBoardService {
    String getBoardRead(int id);
    int postBoardWrite(WriteDTO writeDTO);
    List<BoardEntity> getListRead(FilterDTO filterVO);
    boolean postLogIn(SignInDTO signInVO);
    boolean postUserMatch(MatchDTO matchVO);
    int postBoardUpdate(UpdateDTO updateDTO);
    int delMyBoard(int id);
}
