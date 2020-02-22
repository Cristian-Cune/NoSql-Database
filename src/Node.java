
//package tema2_poo;

import java.util.*;
public class Node {
    
    private int node_number;// retinem indexul nodului initial
    LinkedList<Instance> inst;
    
    public LinkedList<Instance> getInst() {
        return inst;
    }

    public int getNode_num() {
        return node_number;
    }
    
    
    
    public Node(int node_num)
    {
        this.node_number=node_num;
        this.inst=new LinkedList<>();
    }
}
