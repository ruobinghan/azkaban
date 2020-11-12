package azkaban.YAML.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Command {
    private String command;
//    private Map map;

    public Command(String command) {
        this.command = command;
//        map=new LinkedHashMap();
//        map.put("command",command);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

//    public Map getMap() {
//        return map;
//    }
//
//    public void setMap(Map map) {
//        this.map = map;
//    }
}
