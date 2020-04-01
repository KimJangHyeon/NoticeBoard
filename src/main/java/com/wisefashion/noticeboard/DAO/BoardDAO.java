package com.wisefashion.noticeboard.DAO;

import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.Entity.JoinEntity;
import com.wisefashion.noticeboard.DTO.FilterDTO;
import com.wisefashion.noticeboard.DTO.MatchDTO;
import com.wisefashion.noticeboard.DTO.UpdateDTO;
import com.wisefashion.noticeboard.DTO.WriteDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BoardDAO {
    @Autowired
    private SqlSession sqlSession;

    private BoardEntity boardEntity = new BoardEntity();
    private JoinEntity joinEntity = new JoinEntity();

    public int postWriteBoard(WriteDTO writeDTO) {
        try {
            boardEntity.setVariable(-1, writeDTO.getName(), writeDTO.getTitle(), writeDTO.getText());
            sqlSession.insert("postUserText", boardEntity);
            return 1;
        } catch (Exception exception) {
            return 0;
        }
    }

    public BoardEntity getReadText(int id) {
        try {
            boardEntity.setVariable(id, null, null, null);
            return sqlSession.selectOne("getRead", boardEntity);
        } catch(Exception exception) {
            return null;
        }
    }

    public List<BoardEntity> getAllList(int page) {
        try{
            return sqlSession.selectList("getAllList", page);
        } catch(Exception e) {
            return null;
        }
    }

    public List<BoardEntity> getFilterList(FilterDTO filterDTO) {
        return sqlSession.selectList("getFilteredList", filterDTO);
    }

    public int delMyBoard(int id) {
        boardEntity.setVariable(id, null, null, null);
        sqlSession.delete("delMyBoard", boardEntity);
        return 1;
    }

    public int postBoardUpdate(UpdateDTO updateDTO) {
        boardEntity.setVariable(updateDTO.getId(), null, updateDTO.getTitle(), updateDTO.getText());
        sqlSession.update("postBoardUpdate", boardEntity);
        return 1;
    }

    public boolean postUserMatch(MatchDTO matchDTO) {
        joinEntity.setVariable(matchDTO.getId(), matchDTO.getName());
        int value = sqlSession.selectOne("postUserMatch", joinEntity);
        if (value == 0) return false;
        else return true;
    }
}
