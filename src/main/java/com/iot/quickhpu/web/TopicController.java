package com.iot.quickhpu.web;

import com.iot.quickhpu.common.HPUResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: LAL
 * @Description: 话题控制层
 * @Date Created on 2018/5/6.
 */

@RequestMapping("topic")
@Controller
public class TopicController {


    /**
     * 显示话题列表
     * @return
     */
    @RequestMapping("list")
    public HPUResult listTopic(){
        // 读取topic.json文件
        // 转化为List集合
        // 放入响应工具
        return HPUResult.ok(null);
    }

    /**
     * 发布新的话题
     * @param id
     * @return
     */
    @RequestMapping("publish/{id}")
    public HPUResult publishTopic(@PathVariable String id){
        //
        return HPUResult.ok(null);
    }


    /**
     * 显示评论列表
     */
    @RequestMapping("list/comment/{topicId}")
    public HPUResult listComment(@PathVariable("topicId") String id){
        // 读取Comment.json文件
        // 转为list集合
        // 遍历list集合
            // 如果id存在, 拿到list
            // 如果id不存在, 返回为空
        return HPUResult.ok(null);
    }

    /**
     * 发布评论
     * @param id
     * @return
     */
    @RequestMapping("publish/comment/{topicId}")
    public HPUResult publishComment(@PathVariable("topicId") String id){
        // 读取Comment.json文件
        return null;
    }


    //



}
