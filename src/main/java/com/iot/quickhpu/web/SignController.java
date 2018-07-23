package com.iot.quickhpu.web;

import com.iot.quickhpu.common.FileUtil;
import com.iot.quickhpu.common.HPUResult;
import com.iot.quickhpu.common.JsonUtils;
import com.iot.quickhpu.pojo.Sign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LAL
 * @Description: 课堂签名控制层
 * @Date Created on 2018/5/6.
 */

@RequestMapping("sign")
@Controller
public class SignController {


    @Value("{SIGN_IN_JSON_DIR_PATH}")
    private String SIGN_IN_JSON_DIR_PATH;

    /**
     * 创建签到课堂
     * @param sign
     * @param code 主文件 id
     * @return
     */
    @RequestMapping("create/{code}")
    @ResponseBody
    public HPUResult createSign(@RequestBody Sign sign, @PathVariable String code) {

        try {
            String fileName = code + ".json";
            if (FileUtil.isExist(SIGN_IN_JSON_DIR_PATH, fileName)) {
                FileUtil.newFile(SIGN_IN_JSON_DIR_PATH, fileName);
            }
            String json = FileUtil.readFromFile(SIGN_IN_JSON_DIR_PATH + fileName);
            List<Sign> signList = JsonUtils.jsonToList(json, Sign.class);
            if (signList == null) {
                signList = new ArrayList<>();
            }
            signList.add(sign);
            FileUtil.writeToFile(SIGN_IN_JSON_DIR_PATH + fileName,
                    JsonUtils.objectToJson(signList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HPUResult.ok();
    }

    /**
     * 加入签到课堂
     * @param code 邀请码
     * @param signId 课堂码
     * @param username 签到学生姓名
     * @param studentId 签到学生学号
     * @return
     */
    @RequestMapping("join/{code}/{name}")
    @ResponseBody
    public HPUResult joinSign(@PathVariable("code") String code
            , String signId
            , String username
            , String studentId
    ) {
        try {

            String fileName = code + ".json";
            boolean exist = FileUtil.isExist(SIGN_IN_JSON_DIR_PATH, fileName);
            if (!exist) {
                return new HPUResult(500, "签到数据库异常", null);
            }
            // 如果code文件存在, 读取文件
            String json = FileUtil.readFromFile(SIGN_IN_JSON_DIR_PATH + fileName);
            // 返回List集合
            List<Sign> signList = JsonUtils.jsonToList(json, Sign.class);
            // 遍历集合, 查看班级是否存在
            for (int i = 0; i < signList.size(); i++) {
                if (signList.get(i).getSignId().equals(signId)) {
                    List<Sign.GroupBean> group = signList.get(i).getGroup();
                    Sign.GroupBean bean = new Sign.GroupBean();
                    bean.setStudentId(studentId);
                    bean.setStatus("0");
                    bean.setUsername(username);
                    group.add(bean);
                }
            }
            FileUtil.writeToFile(SIGN_IN_JSON_DIR_PATH + fileName, JsonUtils.objectToJson(signList));
            return HPUResult.ok(signList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HPUResult(500, "签到课堂不存在", null);
    }


    /**
     * 开始录入签到
     * @param code 邀请码
     * @param signId 签到课堂 id
     * @param studentId 学生号
     * @return
     */
    @RequestMapping("start/{code}/{signId}")
    @ResponseBody
    public HPUResult startSign(@PathVariable String code,
                               @PathVariable String signId,
                               String studentId) {
        try {
            // 读取sign.json文件
            String fileName = code + ".json";
            boolean exist = FileUtil.isExist(SIGN_IN_JSON_DIR_PATH, fileName);
            if (!exist) {
                return new HPUResult(500, "签到数据库异常", null);
            }
            String json = FileUtil.readFromFile(SIGN_IN_JSON_DIR_PATH + fileName);
            List<Sign> signList = JsonUtils.jsonToList(json, Sign.class);
            for (int i = 0; i < signList.size(); i++) {
                if (signList.get(i).getSignId().equals(signId)){
                    List<Sign.GroupBean> group = signList.get(i).getGroup();
                    for (int j = 0; j < group.size(); j++) {
                        Sign.GroupBean bean = group.get(i);
                        if (bean.getStudentId().equals(studentId)){
                            bean.setStatus("1");
                            return HPUResult.ok(signList);
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new HPUResult(500,"签到失败",null);
    }


    /**
     * 显示创建的签到课堂列表
     * @param code
     * @return
     */
    @RequestMapping("list/{code}")
    @ResponseBody
    public HPUResult listSign(@PathVariable String code) {
        try {
            String fileName = code + ".json";
            if (FileUtil.isExist(SIGN_IN_JSON_DIR_PATH, fileName)) {
                String json = FileUtil.readFromFile(SIGN_IN_JSON_DIR_PATH + fileName);
                if (StringUtils.isEmpty(json)) {
                    return new HPUResult(200, "签到数据列表为空", null);
                }
                List<Sign> signList = JsonUtils.jsonToList(json, Sign.class);
                return HPUResult.ok(signList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HPUResult(200, "签到数据库异常", null);
    }


}
