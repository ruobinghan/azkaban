package azkaban.YAML.Bean;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Param {
    private String sparkParam;
    private String envParam;
    private String flowParam;
    private Map map;
    private List list;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }



    public Param(String sparkParam, String envParam, String flowParam) {
        this.sparkParam = sparkParam;
        this.envParam = envParam;
        this.flowParam = flowParam;
        map=new LinkedHashMap();
        map.put("param.sparkParam",sparkParam);
        map.put("param.envParam",envParam);
        map.put("param.flowParam",flowParam);
    }

}
