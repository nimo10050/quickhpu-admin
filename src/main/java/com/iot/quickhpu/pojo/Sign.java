package com.iot.quickhpu.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: LAL
 * @Description: 课堂点名实体类
 * @Date Created on 2018/5/6.
 */
public class Sign implements Serializable {


    /**
     * group : [{"status":"1","studentId":"1234","username":"zhangsan"},{"status":"1","studentId":"6666","username":"lisi"}]
     * signId : 404
     * title : xxx
     * total : 80
     */

    private String signId;
    private String title;
    private String total;
    private List<GroupBean> group;


    public Sign() {
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<GroupBean> getGroup() {
        return group;
    }

    public void setGroup(List<GroupBean> group) {
        this.group = group;
    }

    public static class GroupBean {
        /**
         * status : 1
         * studentId : 1234
         * username : zhangsan
         */

        private String status;
        private String studentId;
        private String username;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
