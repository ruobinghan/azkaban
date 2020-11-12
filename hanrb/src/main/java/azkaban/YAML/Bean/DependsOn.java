package azkaban.YAML.Bean;

import java.util.LinkedList;
import java.util.List;

public class DependsOn {
    private List list;

    public DependsOn(String taskName) {
        list=new LinkedList();
        list.add(taskName);

    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }


}
