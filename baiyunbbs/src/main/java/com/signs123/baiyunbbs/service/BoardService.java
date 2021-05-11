package com.signs123.baiyunbbs.service;

import com.signs123.baiyunbbs.bean.Board;

import java.util.List;

public interface BoardService {
    String selectBoardByBoardId(int boardId);

    Board selectParentIdByBoard(int boardId);

    Board selectBoardByParentId(int parentId);

    List<Board> selectBoardIdByParentId(int parentId);
}
