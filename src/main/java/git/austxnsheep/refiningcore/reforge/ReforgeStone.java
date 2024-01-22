package git.austxnsheep.refiningcore.reforge;

import java.util.Map;

public class ReforgeStone {
    private Map<String, Integer> attributes;
    private String reforgeName;
    public ReforgeStone(Map<String, Integer> attributes, String reforgeName) {
        this.attributes = attributes;
        this.reforgeName = reforgeName;
    }
    public Map<String, Integer> getAttributes() {
        return this.attributes;
    }
    public String getReforgeName() {
        return this.reforgeName;
    }
}
