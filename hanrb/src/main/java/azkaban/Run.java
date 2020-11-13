package azkaban;


import azkaban.Git.GitMethods;
import azkaban.YAML.Bean.Node;
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
//            git.Clone();
//            git.Package();
//            Node n1=yml.creatNode("a1","b1","c1","d1");
//            Node n2=yml.creatNode("a2","b2","c2","d2","e1");
//            List<Map>nodeList=new LinkedList<Map>();
//            nodeList.add(n1.getMap());
//            nodeList.add(n2.getMap());

//            yml.creatYML(nodeList);

            ZipMethods zip=new ZipMethods(projectName);
            zip.creatZipPackage();

        }catch (Exception e){

        }
    }
}
