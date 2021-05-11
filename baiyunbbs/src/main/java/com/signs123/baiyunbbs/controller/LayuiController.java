package com.signs123.baiyunbbs.controller;


import com.alibaba.fastjson.JSON;
import com.signs123.baiyunbbs.bean.Page;
import com.signs123.baiyunbbs.bean.Topic;
import com.signs123.baiyunbbs.service.BoardService;
import com.signs123.baiyunbbs.service.TopicService;
import com.signs123.baiyunbbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

@Controller
@RequestMapping("/layui")
public class LayuiController {

    @Autowired
    private TopicService topicService;

    @Resource
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private Page pageObj;

@RequestMapping("/toLayui")
    public String toLayui() {

    return "lauyui_table";
    }


    @RequestMapping("/toTopicById")
    @ResponseBody
    public String toMyTopic(Model model,  @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "8") int limits)  {


        try {

            if (page <= 0) {
                page = 1;
            }


            List<Topic> topics1 = topicService.selectUserAllTopic(1);
            double topicSize = topics1.size();
            Double Count = Math.ceil(topicSize / limits);
            int pageCount = Count.intValue();


            //System.out.println(pageCount);

            if (page > pageCount) {
                page = pageCount;
            }
            int curPage = page;

            page= (page - 1) * limits;
            pageObj.setPageNum(page);
            pageObj.setPageSize(limits);
            pageObj.setuId(1);


            List<Topic> topics = topicService.selectTopicById(pageObj);


            model.addAttribute("uId", 1);
            model.addAttribute("curPage", curPage);

            model.addAttribute("pageCount", pageCount);

            return JSON.toJSONString(topics);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "获取用户贴失败，请登录重试");
            return "index";
        }


    }


}
