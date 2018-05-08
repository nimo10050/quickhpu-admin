package com.iot.quickhpu.pojo;

import java.io.Serializable;
import java.util.List;

public class Class implements Serializable {


    /**
     * classes : [{"name":"xxx"},{"name":"zzz"}]
     * code : 1234
     */

    private String code;
    private List<ClassesBean> classes;

    public Class() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ClassesBean> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassesBean> classes) {
        this.classes = classes;
    }

    public static class ClassesBean {
        /**
         * name : xxx
         */

        private String name;

        public ClassesBean() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
