package com.training.monitor.dto;

public class PageDTO {
    private int pageNumber;
    private int pageSize;


    public PageDTO() {
        this.pageNumber = 1;
        this.pageSize = 50;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
