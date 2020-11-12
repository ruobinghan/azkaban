package azkaban.YAML;

import azkaban.Param.Param;
import azkaban.YAML.Bean.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;


import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class YMLMethods {

    private static String filePath= Param.rootPath+ Param.flowName;

    /**
     * 生成yaml文件
     */
    public  static  void creatYML(){


        DumperOptions options = new DumperOptions();

        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        Map<String, Object> map = new LinkedHashMap();

        List <Map> nodeList=new LinkedList<Map>();
        //List <Map> configList=new LinkedList<Map>();
        Command command1=new Command("C1");
        Command command2=new Command("C2");
        Config config1=new Config(command1);
        Config config2=new Config(command2);
        DependsOn dependsOn1=new DependsOn("d1");
        DependsOn dependsOn2=new DependsOn("d2");
        Node node1 =new Node("A1","B1",config1);
        Node node2 =new Node("A2","B2",config2,dependsOn2);
        nodeList.add(node1.getMap());
        nodeList.add(node2.getMap());

        azkaban.YAML.Bean.Param param= new azkaban.YAML.Bean.Param("p1","p2","p3");
        map.put("config",param.getMap());

        map.put("nodes",nodeList);


        String yamlFile=yaml.dump(map);
        saveAsFileWriter(yamlFile);
        System.out.println(yamlFile);
    }

    /**
     * 将指定内容写到磁盘指定文件上
     * @param content
     */
    public static void saveAsFileWriter(String content) {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
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

