package com.iot.quickhpu.web;

import com.alibaba.druid.sql.visitor.functions.If;
import com.iot.quickhpu.common.HPUResult;
import com.iot.quickhpu.common.JsonUtils;
import com.iot.quickhpu.pojo.Class;
import com.iot.quickhpu.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("class")
@Controller
public class ClassController {

    @Value("${CLASS_JSON_DIR_PATH}")
    private String CLASS_JSON_DIR_PATH;

    @RequestMapping("new/{code}")
    @ResponseBody
    public HPUResult newClass(@PathVariable String code, String title) throws Exception {
        // 文件不存在, 新建
        if (!FileUtil.isExist(CLASS_JSON_DIR_PATH, "class.json")) {
            FileUtil.newFile(CLASS_JSON_DIR_PATH, "class.json");
        }
        // 读取文件
        String s = FileUtil.readFromFile(CLASS_JSON_DIR_PATH + "class.json");
        List<Class> objects = JsonUtils.jsonToList(s, Class.class);
        if (objects == null) {
            objects = new ArrayList<>();
        }
        // 遍历
        for (int i = 0; i < objects.size(); i++) {
            Class c = objects.get(i);
            if (!StringUtils.isEmpty(c.getCode()) && c.getCode().equals(code)) {
                List<Class.ClassesBean> classes = c.getClasses();
                for (int j = 0; j < classes.size(); j++) {
                    if (classes.get(j).getName().equals(title)) {
                        return new HPUResult(500, "班级名已被使用", null);
                    }
                }
                // 新建班级
                Class.ClassesBean bean = new Class.ClassesBean();
                bean.setName(title);
                c.getClasses().add(bean);
                // 更新文件文件
                FileUtil.writeToFile(CLASS_JSON_DIR_PATH + "class.json"
                        , JsonUtils.objectToJson(objects));
                // 返回结果
                return HPUResult.ok(objects);
            }
        }
        // 第一次创建班级
        Class c = new Class();
        c.setCode(code);
        List<Class.ClassesBean> list = new ArrayList<>();
        Class.ClassesBean bean = new Class.ClassesBean();
        bean.setName(title);
        list.add(bean);
        c.setClasses(list);
        objects.add(c);
        // 写入文件
        FileUtil.writeToFile(CLASS_JSON_DIR_PATH + "class.json"
                , JsonUtils.objectToJson(objects));
        return HPUResult.ok(objects);
    }


    /**
     * 获取班级列表
     * @param code 创建id
     * @return
     */
    @RequestMapping("list/{code}")
    @ResponseBody
    public HPUResult listClass(@PathVariable String code) {

        try {
            if (FileUtil.isExist(CLASS_JSON_DIR_PATH, "class.json")) {
                String json = FileUtil.readFromFile(CLASS_JSON_DIR_PATH + "class.json");
                if (!StringUtils.isEmpty(json)) {
                    List<Class> list = JsonUtils.jsonToList(json, Class.class);
                    for (int i = 0; i < list.size(); i++) {
                        Class c = list.get(i);
                        String code1 = c.getCode();
                        if (!StringUtils.isEmpty(code1) && code1.equals(code)) {
                            return HPUResult.ok(c.getClasses());
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HPUResult(500, "班级列表为空", null);

    }


}
