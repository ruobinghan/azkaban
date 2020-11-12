package azkaban.YAML.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Config {
    private Command command;
    private Map map;

    public Config(Command command) {
        this.command = command;
        map=new LinkedHashMap();
        map.put("command",command.getCommand());
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
