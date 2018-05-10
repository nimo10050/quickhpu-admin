package com.iot.quickhpu.web;

import com.iot.quickhpu.common.HPUResult;
import com.iot.quickhpu.common.JsonUtils;
import com.iot.quickhpu.pojo.Comment;
import com.iot.quickhpu.pojo.Topic;
import com.iot.quickhpu.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LAL
 * @Description: 话题控制层
 * @Date Created on 2018/5/6.
 */

@RequestMapping("topic")
@Controller
public class TopicController {


    @Value("{TOPIC_JSON_DIR_PATH}")
    private String TOPIC_JSON_DIR_PATH;

    private final String TOPIC_JSON_FILE_PATH = TOPIC_JSON_DIR_PATH + "topic.json";

    @Value("{COMMENT_JSON_DIR_PATH}")
    private String COMMENT_JSON_DIR_PATH;


    /**
     * 显示话题列表
     *
     * @return
     */
    @RequestMapping("list")
    public HPUResult listTopic() {

        try {
            // 文件是否存在
            if (FileUtil.isExist(TOPIC_JSON_DIR_PATH, "topic.json")) {
                // 读取topic.json文件
                String json = FileUtil.readFromFile(TOPIC_JSON_FILE_PATH);
                // 转化为List集合
                List<Topic> topicList = JsonUtils.jsonToList(json, Topic.class);
                return HPUResult.ok(topicList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HPUResult(500, "话题数据库异常", null);
    }

    /**
     * 发布新的话题
     * @param topic 话题json对象
     * @return
     */
    @RequestMapping("publish")
    public HPUResult publishTopic(@RequestBody Topic topic) {
        try {
            // 文件是否存在
            if (!FileUtil.isExist(TOPIC_JSON_DIR_PATH, "topic.json")) {
                FileUtil.newFile(TOPIC_JSON_DIR_PATH, "topic.json");
            }
            String json = FileUtil.readFromFile(TOPIC_JSON_FILE_PATH);
            List<Topic> topicList = JsonUtils.jsonToList(json, Topic.class);
            if (topicList == null) {
                topicList = new ArrayList<>();
            }
            topicList.add(topic);
            // 更新数据库
            FileUtil.writeToFile(TOPIC_JSON_FILE_PATH, JsonUtils.objectToJson(topicList));
            return HPUResult.ok(topicList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HPUResult(500, "发布新话题失败", null);
    }


    /**
     * 显示评论列表
     * @param id 话题id
     * @return
     */
    @RequestMapping("list/comment/{topicId}")
    public HPUResult listComment(@PathVariable("topicId") String id) {
        try {
            if (FileUtil.isExist(COMMENT_JSON_DIR_PATH, id + ".json")) {
                // 读取Comment.json文件
                String json = FileUtil.readFromFile(COMMENT_JSON_DIR_PATH + id + ".json");
                // 转为list集合
                List<Comment> commentList = JsonUtils.jsonToList(json, Comment.class);
                if (commentList != null && commentList.size() > 0) {

                    return HPUResult.ok(commentList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HPUResult(500, "读取评论数据库", null);
    }

    /**
     * @param comment 评论json字符串
     * @param id      评论的话题id
     * @return
     */
    @RequestMapping("publish/comment/{topicId}")
    public HPUResult publishComment(@RequestBody Comment comment, @PathVariable("topicId") String id) {
        try {
            if (!FileUtil.isExist(COMMENT_JSON_DIR_PATH, id + ".json")) {
                FileUtil.newFile(COMMENT_JSON_DIR_PATH, id + ".json");
            }
            // 读取comment.json文件
            String json = FileUtil.readFromFile(COMMENT_JSON_DIR_PATH + id + ".json");
            // 转为list集合
            List<Comment> commentList = JsonUtils.jsonToList(json, Comment.class);
            if (commentList == null) {
                commentList = new ArrayList<>();
            }
            commentList.add(comment);
            return HPUResult.ok(commentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HPUResult(500, "发布评论失败", null);
    }


}
