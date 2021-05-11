package com.signs123.baiyunbbs.service.Impl;

import com.signs123.baiyunbbs.bean.Board;
import com.signs123.baiyunbbs.mapper.BoardMapper;
import com.signs123.baiyunbbs.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
   @Autowired
   private BoardMapper boardMapper;
    @Override
    public String selectBoardByBoardId(int boardId) {
        return boardMapper.selectBoardByBoardId(boardId);
    }

    @Override
    public Board selectParentIdByBoard(int boardId) {
        return boardMapper.selectParentIdByBoard(boardId);
    }

    @Override
    public Board selectBoardByParentId(int parentId) {
        return boardMapper.selectBoardByParentId(parentId);
    }

    @Override
    public List<Board> selectBoardIdByParentId(int parentId) {
        return boardMapper.selectBoardIdByParentId(parentId);
    }
}
