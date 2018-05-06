package com.iot.quickhpu.web;

import com.iot.quickhpu.common.HPUResult;
import com.iot.quickhpu.pojo.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户处理类
 */
@Controller
@RequestMapping("user")
public class UserController {

    /**
     * 显示用户列表
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public HPUResult listUser() {
        // 读取到json字符串
        // 转为List集合
        // 放入到HPUResult
        return HPUResult.ok(null);
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping("add")
    public HPUResult addUser(@RequestBody User user) {
        System.out.println("index");
        // 从文件中读取到json字符串
        // 转为List集合
        // 将客户端传来的对象add到List集合
        return HPUResult.ok();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public HPUResult deleteUser(@PathVariable String id){
        // 读取user.json文件
        // 转化为list集合
        // 遍历list集合
            // 如果用户存在, 删除之
            // 如果用户不存在, 提示错误信息
        return null;
    }

    /**
     * 修改用户信息
     * @param id
     * @return
     */
    @RequestMapping("update/{id}")
    public HPUResult updateUser(@PathVariable String id){
        // 读取user.json文件
        // 转为list集合
        // 遍历list集合
            // 如果用户存在, 更新
            // 如果用户不存在, 提示错误信息
        return null;
    }

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    @RequestMapping("search/{id}")
    public HPUResult searchUser(@PathVariable String id){

        return null;
    }

}
