package com.signs123.baiyunbbs.bean;

import java.io.Serializable;

public class Board implements Serializable {
  private  int boardId;
  private  String boardName;
  private int parentId;

    public Board() {
    }

    public Board(int boardId, String boardName, int parentId) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.parentId = parentId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", boardName='" + boardName + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
