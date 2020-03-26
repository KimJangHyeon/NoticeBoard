package com.wisefashion.noticeboard.VO;

public class MatchVO {
    int id;
    String user;

    public MatchVO(int id, String user) {
        this.id = id;
        this.user = user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
