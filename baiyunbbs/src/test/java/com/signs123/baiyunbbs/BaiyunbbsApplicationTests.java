package com.signs123.baiyunbbs;

import com.signs123.baiyunbbs.bean.Topic;
import com.signs123.baiyunbbs.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
class BaiyunbbsApplicationTests {
    @Autowired
    TopicService topicService;

    @Test
    void contextLoads() {
    }

    @Test
    void mapperTest(){
        List<Topic> all = topicService.getAll();
        Iterator<Topic> iterator=all.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

}
