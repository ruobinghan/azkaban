package azkaban.YAML;

import azkaban.Base.PathParam;
import azkaban.YAML.Bean.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;


import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class YMLMethods {

    private  String filePath= PathParam.zipPath+ PathParam.flowName;

    public Node creatNode(String nameStr,String typeStr,String configStr ,String commandStr,String dependsOnStr){
        Command command=new Command(commandStr);
        Config config=new Config(command);
        DependsOn dependsOn=new DependsOn(dependsOnStr);
        Node node =new Node(nameStr,typeStr,config,dependsOn);
        return node;
    }
    public Node creatNode(String nameStr,String typeStr,String configStr ,String commandStr){
        Command command=new Command(commandStr);
        Config config=new Config(command);
        Node node =new Node(nameStr,typeStr,config);
        return node;
    }

    /**
     * 生成yaml文件
     */
    public void creatYML(List<Map>nodeList){

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        Map<String, Object> map = new LinkedHashMap();

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
    public  void saveAsFileWriter(String content) {
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

