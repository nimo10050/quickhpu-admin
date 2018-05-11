package com.iot.quickhpu.web;

import com.alibaba.druid.sql.visitor.functions.If;
import com.iot.quickhpu.common.HPUResult;
import com.iot.quickhpu.common.JsonUtils;
import com.iot.quickhpu.pojo.Sign;
import com.iot.quickhpu.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.lang.reflect.Field;
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
     *
     * @param code
     * @return
     */
    @RequestMapping("create/{code}")
    @ResponseBody
    public HPUResult createSign(@PathVariable String code) {

        String fileName = code + ".json";
        if (FileUtil.isExist(SIGN_IN_JSON_DIR_PATH, fileName)) {
            FileUtil.newFile(SIGN_IN_JSON_DIR_PATH, fileName);
        }

        return HPUResult.ok();
    }

    /**
     * 加入课堂
     *
     * @param code
     * @return
     */
    @RequestMapping("join/{code}/{name}")
    @ResponseBody
    public HPUResult joinSign(@PathVariable("code") String code,
                              @PathVariable("name") String name) {
        String fileName = code + ".json";
        // 遍历目录
        File file = FileUtils.getFile("C:\\hpu\\", fileName);

        // 如果不存在, 响应失败
        if (!file.exists()) {
            return new HPUResult(500, "输入有误", null);
        }
        // 如果code文件存在, 读取文件
        // 返回List集合
        // 遍历集合, 查看用户是否存在
        // 如果存在, 响应已存在
        // 如果不存在, add 到集合
        //
        return null;
    }


    @RequestMapping("start/{name}")
    @ResponseBody
    public HPUResult startSign(@PathVariable String name) {
        // 读取sign.json文件
        //
        return null;
    }

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
