package com.wisefashion.noticeboard.Service;

import com.wisefashion.noticeboard.DAO.UserDAO;
import com.wisefashion.noticeboard.DAO.BoardDAO;
import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.VO.FilterVO;
import com.wisefashion.noticeboard.VO.MatchVO;
import com.wisefashion.noticeboard.VO.SignInVO;
import com.wisefashion.noticeboard.VO.WriteVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.rmi.runtime.Log;

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
    public int postBoardWrite(WriteVO writeVO) {
        return boardDAO.postWriteBoard(writeVO);
    }

    @Override
    public List<BoardEntity> getListRead(FilterVO filterVO) {
        if(filterVO.getFilter().equals("")) {
            return boardDAO.getAllList(filterVO.getPage());
        } else {
//            return boardDAO.getAllList(filterVO.getPage());
            return boardDAO.getFilterList(filterVO);
        }
    }

    @Override
    public boolean postLogIn(SignInVO signInVO) {
        return userDAO.postLogIn(signInVO);
    }

    @Override
    public boolean postUserMatch(MatchVO matchVO) {
        return userDAO.postUserMatch(matchVO);
    }
    @Override
    public int postBoardUpdate(WriteVO writeVO) {
        return userDAO.postBoardUpdate(writeVO);
    }

    @Override
    public int delMyBoard(int id) {
        return boardDAO.delMyBoard(id);
    }
}
