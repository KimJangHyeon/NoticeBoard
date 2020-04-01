package com.wisefashion.noticeboard.DTO;

public class FilterDTO {
    String filter;
    int page;

    public FilterDTO(int page, String filter) {
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
