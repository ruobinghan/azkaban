package azkaban;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import java.io.File;
import java.io.IOException;

public class GitMethods {
    public static String remotePath = "https://github.com/ruobinghan/azkaban2.git";//远程库路径
    public static String localPath = "D:\\GitProject\\";//下载已有仓库到本地路径
    public static String initPath = "D:\\test\\";//本地路径新建
    public static String username="597759884@qq.com";
    public static String password="13904524006hrb";

    /**
     * 拉取git仓库代码
     * @throws IOException
     * @throws GitAPIException
     */
    public static void gitClone() throws IOException, GitAPIException {

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
     * 清空指定文件夹下的文件
     * @param filePath
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
            System.out.println("清空目标目录失败！！！");
            flag = false;
        }

        if(flag==true){
            System.out.println("清空目标目录成功");
        }
        return flag;
    }

    public static void execCMD(){
        Runtime runtime=Runtime.getRuntime();

        try {
            runtime.exec("cmd /k cd D:\\GitProject && mvn package");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
