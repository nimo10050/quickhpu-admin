package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author: LAL
 * @Description: 课堂点名实体类
 * @Date Created on 2018/5/6.
 */
public class Sign implements Serializable {

    private String userId;
    private String name;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
