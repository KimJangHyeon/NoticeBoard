package com.wisefashion.noticeboard.Service;

import com.wisefashion.noticeboard.DAO.UserDAO;
import com.wisefashion.noticeboard.DAO.BoardDAO;
import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService implements InferBoardService {
    @Autowired
    private BoardDAO boardDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public String getBoardRead(int id) {
        BoardEntity result = boardDAO.getReadText(id);
        return result.getText();
    }

    @Override
    public int postBoardWrite(WriteDTO writeDTO) {
        return boardDAO.postWriteBoard(writeDTO);
    }

    @Override
    public List<BoardEntity> getListRead(FilterDTO filterDTO) {
        if(filterDTO.getFilter().equals("")) {
            return boardDAO.getAllList(filterDTO.getPage());
        } else {
            return boardDAO.getFilterList(filterDTO);
        }
    }

    @Override
    public boolean postLogIn(SignInDTO signInDTO) {
        return userDAO.postLogIn(signInDTO);
    }

    @Override
    public boolean postUserMatch(MatchDTO matchDTO) {
        return boardDAO.postUserMatch(matchDTO);
    }
    @Override
    public int postBoardUpdate(UpdateDTO updateDTO) {
        return boardDAO.postBoardUpdate(updateDTO);
    }

    @Override
    public int delMyBoard(int id) {
        return boardDAO.delMyBoard(id);
    }
}
