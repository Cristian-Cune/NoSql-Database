
//package tema2_poo;

import java.util.*;
public class Entity {
    
    private String name;
    private int rf,no_attr;
    LinkedList<String> attr_type;//lista in care vom retine tipul atributelor
    LinkedList<String> attr_name;//lista in care vom retine numele atributelor
    private String primary_key;

    public String getName() {
        return name;
    }
    
    
    
    public int getRf() {
        return rf;
    }

    public void setRf(int rf) {
        this.rf = rf;
    }

    public int getNo_attr() {
        return no_attr;
    }

    public void setNo_attr(int no_attr) {
        this.no_attr = no_attr;
    }

    public LinkedList<String> getAttr_type() {
        return attr_type;
    }

  

    public LinkedList<String> getAttr_name() {
        return attr_name;
    }

   

    public String getPrimary_key() {
        return primary_key;
    }

    public void setPrimary_key(String primary_key) {
        this.primary_key = primary_key;
    }
    
    public Entity(String name,int rf, int no_attr,String primary_key) {
        this.name=name;
        this.rf = rf;
        this.no_attr = no_attr;
        this.attr_type = new LinkedList<>();
        this.attr_name = new LinkedList<>();
        this.primary_key = primary_key;
    }
    
}
