package touch.forum.async;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class EventModel {
    private EventType eventType;
    private int actorId;
    private int entityType;
    private int entityId;
    private int entityOwner;
    private Map<String,String> options = new HashMap<>();

    public void setOptions(String key, String value){
        this.options.put(key,value);
    }
    public String getOptions(String key){
        return this.options.get(key);
    }
}
