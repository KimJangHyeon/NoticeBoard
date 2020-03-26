package com.wisefashion.noticeboard.VO;

import com.wisefashion.noticeboard.Constant.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class FilterVO {
    String filter;
    int page;

    @Resource(name="entityPerPage")
    private int entityPerPage;

    public FilterVO(int page, String filter) {
        this.filter = filter;
        this.page = page * 10;
    }


    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
