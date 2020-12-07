package azkaban.Version;

import azkaban.Base.PathParam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateVersionProject {
    public static String versionPath;

    public CreateVersionProject(){
        versionPath= PathParam.versionPath;
    }

    public void wrtieVersionProject() throws IOException {
        System.out.println("清空version.project内容");
        System.out.println(versionPath);
        File file = new File(versionPath);
        if (!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("");

        System.out.println("清空version.project内容，开始写入内容");
        fileWriter.write("azkaban-flow-version: 2.0");
        fileWriter.flush();
        System.out.println("写入内容完成");
    }

}
