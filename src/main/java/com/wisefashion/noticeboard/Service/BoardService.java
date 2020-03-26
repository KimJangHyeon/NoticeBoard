package com.wisefashion.noticeboard.Service;

import com.wisefashion.noticeboard.DAO.UserDAO;
import com.wisefashion.noticeboard.DAO.BoardDAO;
import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.VO.FilterVO;
import com.wisefashion.noticeboard.VO.WriteVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;

@Service
public class BoardService implements InferBoardService {
    @Autowired
    BoardDAO boardDAO;

    @Autowired
    UserDAO userDAO;

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
    public int signIn() {
        return 0;
    }
}
