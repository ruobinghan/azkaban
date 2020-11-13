package azkaban.Zip;


import azkaban.Base.PathParam;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class JarMethods {
    private String projectPath;
    private String jarName;
    private String jarPath;
    private String jarNameWithPath;

    public String getJarName() {
        return jarName;
    }

    public String getJarPath() {
        return jarPath;
    }

    public JarMethods (String projectName){
        projectPath=PathParam.gitProjectPath+projectName;
    }

    public boolean getJarNameAndPath(){
        File dir=new File(projectPath);
        List<File> list=fileList(dir,".jar");
        for (File file : list) {
           jarNameWithPath=file.toString();
        }
        if(jarNameWithPath!=null){
            String[]tmp =jarNameWithPath.split("\\\\");
            int len=tmp.length;
            jarName=tmp[len-1];
            System.out.println("JarName: "+jarName);
            jarPath=jarNameWithPath.split(jarName)[0];
            System.out.println("JarPath: "+jarPath);
            return true;

        }else {
            System.out.println("not exits jar……");
            return false;
        }
    }
    public boolean renameJar(){
        File file=new File(jarPath + jarName);
        if (file.renameTo(new File(jarPath+PathParam.azkabanJarName))) {
            System.out.println("重命名jar包成功! newName："+PathParam.azkabanJarName);
            return true;
        }
        else{
            System.out.println("重命名jar包失败!");
            return  false;
        }

    }


    /*
     *  获取指定文件夹下指定类型文件集合的功能
     */
    public List<File> fileList(File dir, String suffix){

        List<File> list=new ArrayList<File>();

        FileFilter fileFilter=new FileFilterBySuffix(suffix);

        getFileList(dir, list, fileFilter);

        return list;
    }

    /**
     * 递归中的每一级都是用同一个“容器”和过滤器，故“容器”和过滤器不能在递归函数中定义，要作为递归函数参数
     *
     * @param dir 被指定的文件夹
     * @param list 存储满足条件的File对象的“容器”
     * @param filter 过滤器（指定类型）
     */
    public void getFileList(File dir,List<File> list,FileFilter filter){
        /*
         * 通过listFiles()，获取dir的所有文件和文件夹对象
         */
        File[] files=dir.listFiles();
        /*
         * 获取指定文件夹中每一个文件和文件夹
         */
        for (File file : files) {
            /*
             * 是不是文件夹？是，递归。不是，用过滤器获取指定文件。
             */
            if(file.isDirectory()){
                getFileList(file, list, filter);
            }else{
                if(filter.accept(file)){
                    list.add(file);
                }
            }
        }
    }
}
class FileFilterBySuffix implements FileFilter {
    private String suffix;

    public FileFilterBySuffix(String suffix) {
        super();
        this.suffix = suffix;
    }

    public boolean accept(File pathname) {
        return pathname.getName().endsWith(suffix);
    }

}
