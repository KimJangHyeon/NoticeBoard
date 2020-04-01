package com.wisefashion.noticeboard.DAO;

import com.wisefashion.noticeboard.Entity.UserEntity;
import com.wisefashion.noticeboard.DTO.SignInDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    @Autowired
    SqlSession sqlSession;

    UserEntity userEntity = new UserEntity();
    public boolean postLogIn(SignInDTO signInDTO) {
        userEntity.setVariable(signInDTO.getName(), signInDTO.getPassword());
        if ((int)sqlSession.selectOne("postLogIn", userEntity) == 0) {
            return false;
        }
        else {
            return true;
        }
    }
}
