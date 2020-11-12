package azkaban.YAML.Bean;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Node {
    private String name;
    private String type;
    private Config config;
    private DependsOn dependsOn;
    private Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    public Node(String name, String type, Config config) {
        this.name = name;
        this.type = type;
        this.config = config;
        map=new LinkedHashMap();
        map.put("name",name);
        map.put("type",type);
        map.put("config",config.getMap());

    }
    public Node(String name, String type, Config config,DependsOn dependsOn) {
        this.name = name;
        this.type = type;
        this.config = config;
        map=new LinkedHashMap();
        map.put("name",name);
        map.put("type",type);
        map.put("config",config.getMap());
        map.put("dependsOn",dependsOn.getList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
