package com.signs123.baiyunbbs.bean;

import org.springframework.stereotype.Component;

@Component(value = "page")
public class Page {
    private int uId;
    private int pageNum;
    private  int pageSize;

    public Page() {
    }

    public Page(int uId, int pageNum, int pageSize) {
        this.uId = uId;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "uId=" + uId +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
