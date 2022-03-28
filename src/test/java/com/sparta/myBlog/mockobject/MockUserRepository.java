package com.sparta.myBlog.mockobject;

import com.sparta.myBlog.models.User;
import java.util.ArrayList;

public class MockUserRepository {

    private ArrayList<User> users;
    private Long userId = 1L;

    public MockUserRepository (){
        users = new ArrayList<>();
    }

    //유저 정보를 DB에 저장
    public User save(User user) {
        user.setId(userId);
        ++userId;
        users.add(user);
        return user;
    }
    //DB에 동일한 아이디가 있는지 확인 있으면 true, 없으면 false
    boolean existsByUsername(String username) {
        for(User user : users) {
            if(username.equals(user.getUsername()))
                return true;
        }
        return false;
    }

}
