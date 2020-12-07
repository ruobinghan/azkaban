package azkaban;


import azkaban.Base.PathParam;
import azkaban.Base.UDF;
import azkaban.Git.GitMethods;
import azkaban.Web.Chrome;
import azkaban.YAML.Bean.Node;
import azkaban.YAML.Bean.Param;
import azkaban.YAML.YMLMethods;
import azkaban.Zip.JarMethods;
import azkaban.Zip.ZipMethods;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Run {
    public static void main(String[] args) {
        String remotePath = "https://github.com/ruobinghan/azkaban.git";
        String username="597759884@qq.com";
        String password="13904524006hrb";
        String projectName="hanrb";
        String branch="master";

        try{
            GitMethods git= new GitMethods(remotePath,username,password,projectName,branch);
            YMLMethods yml= new YMLMethods();
            ZipMethods zip=new ZipMethods();

            git.Clone();
            git.Package();
            Node n1=yml.creatNode("a1","command","d1");
            Node n2=yml.creatNode("a2","command","d2","a1");

            yml.setNodeList(n1);
            yml.setNodeList(n2);

            if(zip.prepareZip(projectName)){
                zip.creatZipPackage(yml);
            }
            Chrome chrome= new Chrome();
            chrome.Upload();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
