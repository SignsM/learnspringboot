package com.signs123.baiyunbbs.mapper;

import com.signs123.baiyunbbs.bean.Page;
import com.signs123.baiyunbbs.bean.Reply;
import com.signs123.baiyunbbs.bean.Topic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "topicMapper")

public interface TopicMapper {

    List<Topic> selectTopicById(Page page);

    List<Topic> selectUserAllTopic(int uId);

    List<Topic> getAll();

    List<Topic> selectTopicByBoardId(int boardId);

    void addTopic(Topic topic);

    void addReply(Reply reply);

    List<Reply> selectReplyByTopicId(int topicId);

    Topic selectTopicByTopicId(int topicId);

    void updateTopic(Topic topic);

    void deleteTopicByTopicId(int topicId);

    void deleteReplyByTopicId(int topicId);
}
