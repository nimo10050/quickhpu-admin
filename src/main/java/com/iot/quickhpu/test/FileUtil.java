package com.iot.quickhpu.test;


import java.io.*;


public class FileUtil {


    public static String readFile(String path) throws Exception {
        InputStream in = FileUtil.class.getClassLoader()
                .getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s = null;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }

        return sb.toString();

    }

    public static void main(String[] args) throws Exception {
        String json = "hello world";
        OutputStream fos=new FileOutputStream(FileUtil.class.getResource("/json/test.json").getPath());
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(fos));
        br.write(json);
        br.flush();
        br.close();
        String s = readFile("json/test.json");
        System.out.println(s);
    }


}
