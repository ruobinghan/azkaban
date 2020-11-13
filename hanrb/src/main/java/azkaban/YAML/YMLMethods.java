package azkaban.YAML;

import azkaban.Base.PathParam;
import azkaban.Base.UDF;
import azkaban.YAML.Bean.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;


import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class YMLMethods {

    private  String filePath= PathParam.zipPath+ PathParam.flowName;
    private  List<Map>nodeList;
    public YMLMethods(){
        nodeList=new LinkedList<Map>();
    }

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

    public void setNodeList (Node node){
        nodeList.add(node.getMap());
    }

    public List<Map> getNodeList(){
        return  nodeList;
    }
    /**
     * 生成yaml文件
     */
    public  void creatYML(){

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        Map<String, Object> map = new LinkedHashMap();

        Param param= new Param("p1","p2","p3");
        map.put("config",param.getMap());
        map.put("nodes",nodeList);

        String yamlFile=yaml.dump(map);
        UDF.saveAsFileWriter(yamlFile,filePath);
        System.out.println(yamlFile);
    }



}

