
//package tema2_poo;

import java.util.*;


public class DataBase {
    private String name;
    private int no_nodes, nodes_cap;
    LinkedList<Entity> ent; //lista in care retinem entitatile
    LinkedList<Node> nodes;//lista cu noduri

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo_nodes() {
        return no_nodes;
    }

    public void setNo_nodes(int no_nodes) {
        this.no_nodes = no_nodes;
    }

    public int getNodes_cap() {
        return nodes_cap;
    }

    public void setNodes_cap(int nodes_cap) {
        this.nodes_cap = nodes_cap;
    }

    public LinkedList<Entity> getEnt() {
        return ent;
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }
    
    //in constructor vom forma nodurile goale in care retinem indexul initial       
    public DataBase(String name, int no_nodes, int nodes_cap) {
        this.name = name;
        this.no_nodes = no_nodes;
        this.nodes_cap = nodes_cap;
        this.ent = new LinkedList<>();
        this.nodes = new LinkedList<>();
        /*formam no_nodes noduri*/
        int k=1;
        while(k <= no_nodes)
        {
            Node n = new Node(k);
            nodes.add(n);
            k++;
        }
    }
    
    
    
}
