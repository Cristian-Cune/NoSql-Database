
//package tema2_poo;

import java.util.*;

public class Instance implements Cloneable{
    private String entity_type;
    private long timestamp;
    LinkedList<Object> attributes;
    private String primary_key;

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public LinkedList<Object> getAttributes() {
        return attributes;
    }

    public String getPrimary_key() {
        return primary_key;
    }

    public void setPrimary_key(String primary_key) {
        this.primary_key = primary_key;
    }
    
    
    
    public Instance(String entity_type,long timestamp,String primary_key) {
        this.entity_type = entity_type;
        this.timestamp = timestamp;
        this.primary_key=primary_key;
        attributes = new LinkedList<>();
    }
    /* folosim cloneable pentru clonarea instantelor*/
    @Override
    public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
        }
}
