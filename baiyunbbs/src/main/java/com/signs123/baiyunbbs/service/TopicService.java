package com.signs123.baiyunbbs.service;

import com.signs123.baiyunbbs.bean.Page;
import com.signs123.baiyunbbs.bean.Reply;
import com.signs123.baiyunbbs.bean.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> selectTopicById(Page page);

    List<Topic> selectUserAllTopic(int uId);

    List<Topic> selectTopicByBoardId(int boardId);

    List<Topic> getAll();

    void addTopic(Topic topic);

    void addReply(Reply reply);

    List<Reply> selectReplyByTopicId(int topicId);


    Topic selectTopicByTopicId(int topicId);

    void updateTopic(Topic topic);

    void deleteTopicByTopicId(int topicId);

    void deleteReplyByTopicId(int topicId);
}
