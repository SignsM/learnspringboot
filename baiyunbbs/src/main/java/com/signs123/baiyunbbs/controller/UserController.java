package com.signs123.baiyunbbs.controller;

import com.alibaba.fastjson.JSON;
import com.signs123.baiyunbbs.Util.PageUtil;
import com.signs123.baiyunbbs.bean.*;
import com.signs123.baiyunbbs.service.BoardService;
import com.signs123.baiyunbbs.service.Impl.TopicServiceImpl;
import com.signs123.baiyunbbs.service.TopicService;
import com.signs123.baiyunbbs.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
@SessionAttributes("user1")
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    private TopicService topicService;
    @Autowired
    private BoardService boardService;



    @Autowired
    private Page page;

    @Test
    public void test1(){
        TopicServiceImpl topicService1=new TopicServiceImpl();

        List<Topic> allTopic = null;
        try {
            allTopic = topicService1.selectUserAllTopic(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(allTopic);
    }


    //用户部分

    @RequestMapping("/toIndex")
    public String toIndex() {

        return "index";
    }

    @RequestMapping("/toReg")
    public String toReg() {


        return "register";

    }

    @RequestMapping("/toLogin")
    public String toLogin() {


        return "login";

    }



    @RequestMapping("/toOurInfo")
    public String toOurInfo() {

        return "bbs_info";
    }

    /**
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/doLogin")
    public String login(User user, Model model) {


        try {
            if (user == null) {
                model.addAttribute("msg", "用户名或密码错误");
                return "login";
            }
            User user1 = userService.selectUserByName(user);
            if (user1 != null) {   //按照用户名和密码查询，若不为空，则登录成功
                //添加会话
                //session.setAttribute("user1", user1);
                model.addAttribute("user1", user1);
                return "index";
            } else {

                model.addAttribute("msg", "用户名或密码错误");
                return "login";

            }

        } catch (Exception e) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
    }

    /**
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/doReg")
    public String register(User user, Model model) {

        if (user != null) {
            try {
                if (userService.FindUserByName(user) == null) {
                    LocalDate regTime = LocalDate.now();
                    user.setRegTime(regTime);
                    userService.addUser(user);
                    model.addAttribute("msg", "注册成功，请登录");
                    return "login";
                } else {
                    model.addAttribute("msg", "用户名已存在");
                    return "register";
                }

            } catch (Exception ex) {
                ex.printStackTrace();

                model.addAttribute("msg", "注册失败");
                return "register";
            }
        } else {
            model.addAttribute("msg", "请填写完整的用户信息");
            return "register";
        }
    }

    /**
     * @param username
     * @return
     */

    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkUserByName(@RequestBody String username) {


        User user = new User();
        user.setuName(username);
        try {
            User user1 = userService.FindUserByName(user);

            return JSON.toJSONString("exists");


        } catch (Exception e) {
            return JSON.toJSONString("yes");
        }

    }

    /**
     * @param model
     * @return
     */

    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(Model model) {
        try {
            User user1 = (User) model.getAttribute("user1");
            if (user1 != null) {
                model.addAttribute("msg", "可以修改你的个人信息哦");
                model.addAttribute("UserInfo", user1);
                return "update_user";
            } else {
                model.addAttribute("msg", "请登录后再进行操作");
                return "index";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "请登录后再进行操作");
            return "index";
        }
    }

    /**
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(User user, Model model) {

        if (user==null){
            model.addAttribute("msg","修改失败，请稍后再试");
            return "update_user";
        }

        try {
            userService.updateUser(user);
            model.addAttribute("msg", "修改成功");

            //更新session会话
            User user1 = (User) model.getAttribute("user1");
            user1.setuName(user.getuName());
            user1.setuPass(user.getuPass());
            user1.setGender(user.getGender());
            model.addAttribute("user1", user1);
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "修改失败");
            return "update_user";
        }


    }



   /* @Test
    public void Test(){
        LocalDate regTime=LocalDate.now();
        System.out.println(regTime);

        User user = new User("Jack","123");



       try {

           System.out.println(userService.selectUserByName(user));
       }catch (Exception e){

           System.out.println("=========");
       }


    }*/

    //帖子，板块部分

    /***
     *
     * @param model
     * @param uId
     * @param pageNum
     * @return
     */

    @RequestMapping("/toMyTopic")
    public String toMyTopic(Model model, int uId, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "0") int opt) {


        if (opt == -1) {
            model.addAttribute("optMsg", "删除成功");
        }
        if (opt == 1) {
            model.addAttribute("optMsg", "修改成功");
        }

        try {
            int pageSize = 5;
            if (pageNum <= 0) {
                pageNum = 1;
            }


            List<Topic> topics1 = topicService.selectUserAllTopic(uId);
            double topicSize = topics1.size();
            Double Count = Math.ceil(topicSize / pageSize);
            int pageCount = Count.intValue();


            //System.out.println(pageCount);

            if (pageNum > pageCount) {
                pageNum = pageCount;
            }
            int curPage = pageNum;

            pageNum = (pageNum - 1) * pageSize;
            page.setPageNum(pageNum);
            page.setPageSize(pageSize);
            page.setuId(uId);


            List<Topic> topics = topicService.selectTopicById(page);

            HashMap<String, Topic> TopicHashMap = new HashMap<>();
            Iterator<Topic> iterator = topics.iterator();
            while (iterator.hasNext()) {
                Topic topic = iterator.next();
                String boardName = boardService.selectBoardByBoardId(topic.getBoardId());

                TopicHashMap.put(boardName, topic);

            }


            model.addAttribute("uId", uId);
            model.addAttribute("curPage", curPage);

            model.addAttribute("pageCount", pageCount);
            model.addAttribute("topicMap", TopicHashMap);
            return "my_topic";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "获取用户贴失败，请登录重试");
            return "index";
        }

    }


    @RequestMapping("/toUpdateTopic")
    public String UpdateTopicBy(int topicId, int uId, String type, Model model) {

        User user1 = (User) model.getAttribute("user1");
        try {
            if (user1.getuId() == uId) {
                if ("update".equals(type)) {
                    Topic topic = topicService.selectTopicByTopicId(topicId);
                    model.addAttribute("topic", topic);
                    model.addAttribute("boardId", topic.getBoardId());

                    return "update_topic";
                }

                if ("delete".equals(type)) {
                    topicService.deleteReplyByTopicId(topicId);
                    topicService.deleteTopicByTopicId(topicId);
                    return "redirect:/toMyTopic?uId=" + uId+ "&opt=1";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "操作失败");
            return "redirect:/toIndex" ;
        }


        return "index";


    }

    @RequestMapping("/toJavaPage")
    public String toJavaPage() {


        return "java_board";

    }

    /***
     *
     * @param model
     * @param boardId
     * @return
     */
    @RequestMapping("/toBoard")
    public String toBoard(Model model, int boardId, @RequestParam(defaultValue = "1") int pageNum) {
        if (pageNum <= 0) {
            pageNum = 1;
        }

        int pageSize = 5;

        String boardName = boardService.selectBoardByBoardId(boardId);

        model.addAttribute("boardName", boardName);

        Board boardByParentId = boardService.selectBoardByParentId(boardId);

        //根据父板块查询到板块号查询子版块
        List<Topic> topics = topicService.selectTopicByBoardId(boardByParentId.getBoardId());

        //查询父板块
        List<Topic> topics1 = topicService.selectTopicByBoardId(boardId);


        //topics.addAll(topics1);
        //遍历topics1
        Iterator<Topic> iterator1 = topics1.iterator();
        while (iterator1.hasNext()) {
            Topic topic = iterator1.next();

            //将topics1中的元素加到topics

            topics.add(topic);


        }

        //向上取整获取总页数
        double topicSize = topics.size();
        Double Count = Math.ceil(topicSize / pageSize);

        int pageCount = Count.intValue();
        if (pageNum > pageCount) {
            pageNum = pageCount;
        }
        //对结果进行分页
        List list = PageUtil.startPage(topics, pageNum, pageSize, pageCount);


        //以用户名为key,topic为value放到map中
        HashMap<User, Topic> boardInfoMap = new HashMap<>();
        Iterator<Topic> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            Topic topic = iterator2.next();
            List<Reply> replies = topicService.selectReplyByTopicId(topic.getTopicId());
            int replyNum = replies.size();
            topic.setReplyNum(replyNum);

            User user = userService.selectUserById(topic.getuId());
            boardInfoMap.put(user, topic);

        }


        model.addAttribute("boardTopicMap", boardInfoMap);

        model.addAttribute("boardId", boardId);

        model.addAttribute("curPage", pageNum);

        model.addAttribute("pageCount", pageCount);

        return "board_info";


    }

    @RequestMapping("/toPostTopic")
    public String toPostTopic(int boardId, Model model) {

        if (model.getAttribute("user1") != null) {
            model.addAttribute("boardId", boardId);
            return "post_topic";

        } else {
            model.addAttribute("msg", "请登录后再进行该操作");
            return "index";
        }


    }

    @RequestMapping("/doPostTopic")
    public String doPostTopic(Model model, Topic topic, @RequestParam(defaultValue = "post") String optType) {

        try {
            if (topic != null) {
                if ("post".equals(optType)) {
                    User user = (User) model.getAttribute("user1");
                    LocalDate publishTime = LocalDate.now();
                    topic.setPublishTime(publishTime);
                    topic.setModifyTime(publishTime);
                    topic.setuId(user.getuId());

                    topicService.addTopic(topic);
                    model.addAttribute("msg", "发布成功");
                    return "index";
                }
                if ("update".equals(optType)) {
                    User user = (User) model.getAttribute("user1");
                    LocalDate publishTime = LocalDate.now();
                    topic.setPublishTime(publishTime);
                    topic.setModifyTime(publishTime);
                    topic.setuId(user.getuId());
                    topicService.updateTopic(topic);
                    return "redirect:/toMyTopic?uId=" + user.getuId() + "&opt=1";

                }

            } else {
                model.addAttribute("msg", "操作失败,请稍后再试");
                return "index";
            }
        } catch (Exception e) {

            e.printStackTrace();

            model.addAttribute("msg", "操作失败,请稍后再试");

            return "index";
        }

        return "index";
    }

    @RequestMapping("/toReply")
    public String toReply(Model model, int topicId, String uName) {

        if (model.getAttribute("user1") != null) {

            model.addAttribute("topicId", topicId);
            model.addAttribute("uName", uName);
            return "reply_topic";

        } else {
            model.addAttribute("msg", "请登录后进行该操作");
            return "index";
        }


    }

    @RequestMapping("/doReply")
    public String doReply(Model model, Reply reply) {

        try {
            if (reply != null) {


                LocalDate publishTime = LocalDate.now();
                reply.setPublishTime(publishTime);
                reply.setModifyTime(publishTime);


                topicService.addReply(reply);
                model.addAttribute("msg", "回复成功");
                return "index";

            } else {
                model.addAttribute("msg", "回复失败,请稍后再试");
                return "index";
            }
        } catch (Exception e) {

            e.printStackTrace();

            model.addAttribute("msg", "回复失败,请稍后再试");

            return "index";
        }


    }

    @RequestMapping("/toShowReply")
    public String toShowReply(Model model, int topicId, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "1") int replyNum) {
        if (replyNum == 0) {
            model.addAttribute("msg", "该贴还没有回复，去写第一条回复吧");
            return "index";
        }
        try {
            //查询原帖
            Topic topic = topicService.selectTopicByTopicId(topicId);

            //查询发帖人用户名
            User user = userService.selectUserById(topic.getuId());

            //将帖子作者信息装入model
            model.addAttribute("topicUser", user);

            model.addAttribute("topic", topic);
            //查询对应帖子的回复
            List<Reply> replies = topicService.selectReplyByTopicId(topicId);


            int pageSize = 5;
            //向上取整获取总页数
            double topicSize = replies.size();
            Double Count = Math.ceil(topicSize / pageSize);

            int pageCount = Count.intValue();
            if (pageNum < 1) {
                pageNum = 1;
            }
            if (pageNum > pageCount) {
                pageNum = pageCount;
            }

            //对结果进行分页
            List list = PageUtil.startPage(replies, pageNum, pageSize, pageCount);
            HashMap<User, Reply> replyMap = new HashMap<>();
            Iterator<Reply> iterator = list.iterator();
            while (iterator.hasNext()) {
                Reply reply = iterator.next();
                User replyUser = userService.selectUserById(reply.getuId());

                replyMap.put(replyUser, reply);

            }


            model.addAttribute("replies", replyMap);

            return "topic_reply";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "获取回复信息失败，请稍后再试");
            return "index";

        }

    }

    @RequestMapping("/toShowJavaBoard")
    public String toShowJavaBoard(Model model, @RequestParam(defaultValue = "100") int boardId, @RequestParam(defaultValue = "99") int parentId) {

        //当parentId为默认值99时，就是前台没有传回parentId,即进入java基础模块
        if (parentId == 99 && boardId != 100) {
            try {
                List<Topic> javaBasicTopicsByBoard = topicService.selectTopicByBoardId(boardId);
                HashMap<User, Topic> hashMapByJavaSe = new HashMap<>();
                Iterator<Topic> iteratorByJavaSe = javaBasicTopicsByBoard.iterator();

                while (iteratorByJavaSe.hasNext()) {
                    Topic topic = iteratorByJavaSe.next();
                    User user = userService.selectUserById(topic.getuId());
                    List<Reply> replies = topicService.selectReplyByTopicId(topic.getTopicId());
                    topic.setReplyNum(replies.size());
                    hashMapByJavaSe.put(user, topic);
                }

                model.addAttribute("boardId", boardId);
                model.addAttribute("javaTopic", hashMapByJavaSe);
                return "board_java_topic";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("msg", "获取信息失败，请稍后再试");
                return "index";
            }
        }
        if (boardId == 100 && parentId != 99) {

            try {
                List<Board> boards = boardService.selectBoardIdByParentId(parentId);
                HashMap<User, Topic> hashMap = new HashMap<>();
                Iterator<Board> iterator = boards.iterator();

                while (iterator.hasNext()) {
                    Board board = iterator.next();
                    List<Topic> topics = topicService.selectTopicByBoardId(board.getBoardId());
                    //遍历所得到的list
                    Iterator<Topic> iterator1 = topics.iterator();
                    while (iterator1.hasNext()) {
                        Topic topic = iterator1.next();
                        User user = userService.selectUserById(topic.getuId());
                        List<Reply> replies = topicService.selectReplyByTopicId(topic.getTopicId());
                        topic.setReplyNum(replies.size());
                        hashMap.put(user, topic);

                    }


                }

                model.addAttribute("boardId","6");
                model.addAttribute("javaTopic", hashMap);
                return "board_java_topic";


            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("msg", "获取信息失败，请稍后再试");
                return "index";
            }

        }
        model.addAttribute("msg", "获取信息失败，请稍后再试");
        return "index";

    }


}
