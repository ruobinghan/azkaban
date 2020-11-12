package azkaban.Git;

import azkaban.Param.Param;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import java.io.File;
import java.io.IOException;

public class GitMethods {
    private  String remotePath;
    private  String localPath;
    private  String username;
    private  String password;
    private  String projectName;


    /**
     * 拉取git仓库代码
     * @throws IOException
     * @throws GitAPIException
     */


    public  void Clone() throws IOException, GitAPIException {

        cleanDir(localPath);
        //设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new UsernamePasswordCredentialsProvider(username,password);

        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();

        Git git = cloneCommand.setURI(remotePath) //设置远程URI
                .setBranch("master") //设置clone下来的分支
                .setDirectory(new File(localPath)) //设置下载存放路径
                .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                .call();

        System.out.print(git.tag());
    }

    /**
     * 清空指定目录下的文件
     * @param filePath 指定所清空的目录
     * @return
     */
    public  boolean cleanDir(String filePath) {
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
            System.out.println("清空目标目录失败！！！");
            flag = false;
        }

        if(flag==true){
            System.out.println("清空目标目录成功");
        }
        return flag;
    }
    //打jar包
    public  void Package(){
        Runtime runtime=Runtime.getRuntime();

        try {
            runtime.exec("cmd /k cd D:\\GitProject\\GitClone && mvn compile");
            runtime.exec("cmd /k cd D:\\GitProject\\GitClone\\hanrb && mvn clean package");
            //Thread.currentThread().sleep(5000);//毫秒   
            System.out.println("打包完成");
        } catch (Exception e) {
            System.out.println("打包失败");
            e.printStackTrace();
        }
    }
}
