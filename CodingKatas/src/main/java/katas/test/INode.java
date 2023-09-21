package katas.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class INode {

    private String name;
    private String type;
    private int date;

    private List<INode> subnodes;

    public List<INode> getNodesByName(String pName) {
        return subnodes.stream().filter(n -> n.getName().equals(pName)).collect(Collectors.toList());
    }

    public List<INode> getNodesByType(String pType) {
        return subnodes.stream().filter(n -> n.getType().equals(pType)).collect(Collectors.toList());
    }

    public int getDate(String _s) {
        return date;
    }

    public Object getPrimaryType() {
        return type;
    }
}
