package com.signs123.baiyunbbs.mapper;


import com.signs123.baiyunbbs.bean.Board;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "boardMapper")
public interface BoardMapper {
    String selectBoardByBoardId(int boardId);


    Board selectParentIdByBoard(int boardId);

    Board selectBoardByParentId(int parentId);

    List<Board> selectBoardIdByParentId(int parentId);
}
