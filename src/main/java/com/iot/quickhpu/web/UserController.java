package com.iot.quickhpu.web;

import com.iot.quickhpu.common.HPUResult;
import com.iot.quickhpu.common.JsonUtils;
import com.iot.quickhpu.pojo.User;
import com.iot.quickhpu.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 用户处理类
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Value("${USER_JSON_DIR_PATH}")
    private String USER_JSON_DIR_PATH;

    /**
     * 显示用户列表
     *
     * @return
     */
    @RequestMapping(value = "list/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public HPUResult listUser(@PathVariable("userId") String id) {
        try {
            String fileName = id + ".json";
            boolean exist = FileUtil.isExist(USER_JSON_DIR_PATH, fileName);
            if (exist) {
                // 读取到json字符串
                String json = FileUtil.readFromFile(USER_JSON_DIR_PATH + fileName);
                // 转为List集合
                List<User> userList = JsonUtils.jsonToList(json, User.class);
                // 放入到HPUResult
                return HPUResult.ok(userList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HPUResult(500, "读取用户列表失败", null);
    }

    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping("add/{createId}")
    @ResponseBody
    public HPUResult addUser(@PathVariable("createId") String id, @RequestBody User user) {
        try {
            String fileName = id + ".json";
            // 如果文件不存在, 则新建
            if (!FileUtil.isExist(USER_JSON_DIR_PATH, fileName)) {
                FileUtil.newFile(USER_JSON_DIR_PATH, fileName);
            }
            // 如果文件存在, 从文件中读取到json字符串
            String json = FileUtil.readFromFile(USER_JSON_DIR_PATH + fileName);
            // 转为list集合
            List<User> list = null;
            if (json != null && !"".equals(json.trim())) {
                list = JsonUtils.jsonToList(json, User.class);
            } else {
                list = new ArrayList<>();
            }
            for (int i = 0; i < list.size(); i++) {
                String studentId = list.get(i).getStudentId();
                if (studentId != null && studentId.equals(user.getStudentId())) {
                    return new HPUResult(500, "用户已存在", list);
                }
            }
            list.add(user);
            // 重新写入文件
            FileUtil.writeToFile(USER_JSON_DIR_PATH + fileName, JsonUtils.objectToJson(list));
            return HPUResult.ok(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HPUResult(500, "添加失败", null);
    }

    /**
     * 删除用户
     *
     * @param createId 文件id
     * @param id       删除的用户id
     * @return
     */
    @RequestMapping("delete/{createId}")
    @ResponseBody
    public HPUResult deleteUser(@PathVariable("createId") String createId, String id) {
        try {
            String fileName = createId + ".json";
            // 如果文件不存在, 则提示错误信息
            if (!FileUtil.isExist(USER_JSON_DIR_PATH, fileName)) {
                return new HPUResult(500, "删除用户失败, IO流异常", null);
            }
            // 读取user.json文件
            String json = FileUtil.readFromFile(USER_JSON_DIR_PATH + fileName);
            // 转化为list集合
            List<User> userList = JsonUtils.jsonToList(json, User.class);
            if (userList != null) {
                // 遍历list集合
                Iterator<User> iterator = userList.iterator();
                while (iterator.hasNext()) {
                    User next = iterator.next();
                    // 如果用户存在, 删除之
                    String studentId = next.getStudentId();
                    if (studentId != null && studentId.equals(id)) {
                        iterator.remove();
                        return HPUResult.ok(userList);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HPUResult(500, "用户不存在", null);

    }

    /**
     * 修改用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("update/{createId}")
    @ResponseBody
    public HPUResult updateUser(@PathVariable String createId, @RequestBody User user) {
        try {
            String fileName = createId + ".json";
            // 如果文件不存在, 则提示错误信息
            if (!FileUtil.isExist(USER_JSON_DIR_PATH, fileName)) {
                return new HPUResult(500, "修改用户失败, IO流异常", null);
            }
            // 读取user.json文件
            String json = FileUtil.readFromFile(USER_JSON_DIR_PATH + fileName);
            // 转化为list集合
            List<User> userList = JsonUtils.jsonToList(json, User.class);
            if (userList != null) {
                // 遍历list集合
                for (int i = 0; i < userList.size(); i++) {
                    User retainUser = userList.get(i);
                    String studentId = retainUser.getStudentId();
                    if (studentId != null && studentId.equals(user.getStudentId())) {
                        // 修改信息
                        userList.set(i,user);
                        return HPUResult.ok(userList);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HPUResult(500, "用户不存在", null);

    }

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public HPUResult searchUser() {

        return HPUResult.ok();
    }

}
