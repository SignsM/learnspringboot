package com.signs123.baiyunbbs.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Reply {
    private int replyId;

    private String title;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate publishTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate modifyTime;

    private int uId;

    private int topicId;

    public Reply() {
    }

    public Reply(int replyId, String title, String content, LocalDate publishTime, LocalDate modifyTime, int uId, int topicId) {
        this.replyId = replyId;
        this.title = title;
        this.content = content;
        this.publishTime = publishTime;
        this.modifyTime = modifyTime;
        this.uId = uId;
        this.topicId = topicId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDate publishTime) {
        this.publishTime = publishTime;
    }

    public LocalDate getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDate modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                ", modifyTime=" + modifyTime +
                ", uId=" + uId +
                ", topicId=" + topicId +
                '}';
    }
}
