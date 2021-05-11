package com.signs123.baiyunbbs.service.Impl;

import com.signs123.baiyunbbs.bean.Page;
import com.signs123.baiyunbbs.bean.Reply;
import com.signs123.baiyunbbs.bean.Topic;
import com.signs123.baiyunbbs.mapper.TopicMapper;
import com.signs123.baiyunbbs.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;
    @Override
    public List<Topic> selectTopicById(Page page) {
        return topicMapper.selectTopicById(page);
    }

    @Override
    public List<Topic> selectUserAllTopic(int uId) {
        return topicMapper.selectUserAllTopic(uId);
    }

    @Override
    public List<Topic> selectTopicByBoardId(int boardId) {
        return topicMapper.selectTopicByBoardId(boardId);
    }

    @Override
    public List<Topic> getAll() {
        return topicMapper.getAll();
    }

    @Override
    public void addTopic(Topic topic) {
        topicMapper.addTopic(topic);
    }

    @Override
    public void addReply(Reply reply) {
        topicMapper.addReply(reply);
    }

    @Override
    public List<Reply> selectReplyByTopicId(int topicId) {
        return topicMapper.selectReplyByTopicId(topicId);
    }

    @Override
    public Topic selectTopicByTopicId(int topicId) {
        return topicMapper.selectTopicByTopicId(topicId);
    }

    @Override
    public void updateTopic(Topic topic) {
        topicMapper.updateTopic(topic);
    }

    @Override
    public void deleteTopicByTopicId(int topicId) {
         topicMapper.deleteTopicByTopicId(topicId);
    }

    @Override
    public void deleteReplyByTopicId(int topicId) {
        topicMapper.deleteReplyByTopicId(topicId);
    }


}
