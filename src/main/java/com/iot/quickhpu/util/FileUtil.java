package com.iot.quickhpu.util;


import org.apache.commons.io.FileUtils;

import java.io.*;


public class FileUtil {

    /**
     * 判断文件是否存在
     *
     * @param dir
     * @param path
     * @return
     */
    public static boolean isExist(String dir, String path) {
        File file = FileUtils.getFile(dir, path);
        return file.exists();
    }

    /**
     * 新建文件
     */

    public static boolean newFile(String dir,String fileName) {
        try {
            File d = new File(dir);
            if (!d.exists()){
                d.mkdirs();
            }
            File file = new File(dir,fileName);
            if (!file.exists()){
                file.createNewFile();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 读取文件
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String readFromFile(String path) throws Exception {

        return FileUtils.readFileToString(new File(path));
    }

    /**
     * 写入文件
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static void writeToFile(String path, String json) throws Exception {

        FileUtils.write(new File(path), json);
    }


}
