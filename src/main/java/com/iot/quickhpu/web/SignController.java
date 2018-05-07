package com.iot.quickhpu.web;

import com.iot.quickhpu.common.HPUResult;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.lang.reflect.Field;

/**
 * @Author: LAL
 * @Description: 课堂签名控制层
 * @Date Created on 2018/5/6.
 */

@RequestMapping("sign")
@Controller
public class SignController {

    /**
     * 创建签到课堂
     * @param code
     * @return
     */
    @RequestMapping("create/{code}")
    public HPUResult createSign(@PathVariable String code) {

        try {
            String fileName = code + ".json";
            File file = FileUtils.getFile("C:\\hpu\\", fileName);
            // 查看文件是否存在
                // 如果存在, 删除之
                // 如果不存在,创建json文件, 当前用户id命名
            if (file != null && file.exists()) {
                file.delete();
            } else {
                file.createNewFile();

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return HPUResult.ok();
    }

    /**
     * 加入课堂
     * @param code
     * @return
     */
    @RequestMapping("join/{code}/{name}")
    public HPUResult joinSign(@PathVariable("code") String code,
                              @PathVariable("name") String name) {
        String fileName = code +".json";
        // 遍历目录
        File file = FileUtils.getFile("C:\\hpu\\", fileName);

        // 如果不存在, 响应失败
        if (!file.exists()){
            return new HPUResult(500,"输入有误",null);
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
    public HPUResult startSign(@PathVariable String name) {
        // 读取sign.json文件
        //
        return null;
    }


    public static void main(String[] args) {
        try {
            String fileName =  "1234";
            File file = FileUtils.getFile("C:\\hpu\\", fileName + ".json");
            // 查看文件是否存在
            // 如果存在, 删除之
            // 如果不存在,创建json文件, 当前用户id命名
            if (file != null && file.exists()) {
                file.delete();
            } else {
                file.createNewFile();

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
