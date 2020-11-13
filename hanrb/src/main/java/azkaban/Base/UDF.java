package azkaban.Base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UDF {
    /**
     * 清空目录下的文件
     * @param filePath 指定所清空的目录
     * @return
     */
    public static boolean cleanDir(String filePath) {
        boolean flag = true;
        if(filePath != null) {
            File file = new File(filePath);
            if(file.exists()) {
                File[] filePaths = file.listFiles();
                for(File f : filePaths) {
                    if(f.isFile()) {
                        f.delete();
                    }
                    if(f.isDirectory()){
                        String fpath = f.getPath();
                        cleanDir(fpath);
                        f.delete();
                    }
                }
            }

        }else {
            System.out.println("清空 "+filePath+"失败！！！");
            flag = false;
        }

        if(flag==true){
            System.out.println("清空 "+filePath+"成功!");
        }
        return flag;
    }

    /**新建文件*/
    public  static  void createFile(String path,String fileName)throws IOException{
        File testFile = new File(path+fileName);
        try{
            if (!testFile.exists()) {
                testFile.createNewFile();
            }
            System.out.println("testFile:"+testFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**将指定内容写入文件*/
    public  static void saveAsFileWriter(String content,String filePath) {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            //fwriter = new FileWriter(filePath,true);
            fwriter = new FileWriter(filePath);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
