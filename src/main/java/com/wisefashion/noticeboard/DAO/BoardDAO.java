package com.wisefashion.noticeboard.DAO;

import com.wisefashion.noticeboard.Entity.BoardEntity;
import com.wisefashion.noticeboard.VO.FilterVO;
import com.wisefashion.noticeboard.VO.WriteVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BoardDAO {
    @Autowired
    private SqlSession sqlSession;

    public int postWriteBoard(WriteVO writeVO) {
        try {
            sqlSession.insert("postUserText", writeVO);
            return 202;
        } catch (Exception exception) {
            return 404;
        }
    }

    public BoardEntity getReadText(int id) {
        try {
            return sqlSession.selectOne("getRead", id);
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

    public List<BoardEntity> getFilterList(FilterVO filterVO) {
        return sqlSession.selectList("getFilteredList", filterVO);
    }
}
