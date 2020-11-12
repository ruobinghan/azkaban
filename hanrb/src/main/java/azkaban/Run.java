package azkaban;


import azkaban.Git.GitMethods;
import azkaban.YAML.YMLMethods;
import azkaban.Zip.ZipMethods;

public class Run {
    public static void main(String[] args) {
        String remotePath = "https://github.com/ruobinghan/azkaban.git";//远程库路径
        String username="597759884@qq.com";
        String password="13904524006hrb";
        String projectName="hanrb";


        try{
//            GitMethods git= new GitMethods(remotePath,username,password,projectName);
//            git.Clone();
//            git.Package();
           // YMLMethods.creatYML();

//            ZipMethods.creatZipPackage();

        }catch (Exception e){

        }
    }
}
