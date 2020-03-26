package com.wisefashion.noticeboard.DAO;

import com.wisefashion.noticeboard.VO.MatchVO;
import com.wisefashion.noticeboard.VO.SignInVO;
import com.wisefashion.noticeboard.VO.WriteVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    @Autowired
    SqlSession sqlSession;

    public boolean postLogIn(SignInVO signInVO) {
        int value = (int)sqlSession.selectOne("postLogIn", signInVO);
        if ((int)sqlSession.selectOne("postLogIn", signInVO) == 0) {
            return false;
        }
        else {
            return true;
        }
    }
    public boolean postUserMatch(MatchVO matchVO) {
        int value = (int)sqlSession.selectOne("postUserMatch", matchVO);
        if (value == 0) return false;
        else return true;
    }
    public int postBoardUpdate(WriteVO writeVO) {
        sqlSession.update("postBoardUpdate", writeVO);
        return 1;
    }
}
