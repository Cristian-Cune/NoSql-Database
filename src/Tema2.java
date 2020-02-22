/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package tema2_poo;


import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Tema2 {


    public static void main(String[] args) {
        
        Date date = new  Date(18,11,22);
        DecimalFormat df = new DecimalFormat( "#.##" );
        /* luam numele fisierelor din linie de comanda*/
        File file_in=new File(args[0]);
        File file_out=new File(args[0] + "_out");
  
        try{
            
            Scanner input= new Scanner(file_in);
            PrintWriter output=new PrintWriter(file_out);
            DataBase db = null;
            /*citim linie cu linie pana la sfarsitul fisierului*/
            while(input.hasNextLine()){
                
                String operation=input.nextLine();
                StringTokenizer tok=new StringTokenizer(operation);
                String op="NULL";
                if(tok.hasMoreTokens())
                    op=tok.nextToken();
                
                if(op.equals("CREATEDB")){
                    /*formam baza de date*/
                    String db_name=tok.nextToken();
                    int no_nodes=Integer.parseInt(tok.nextToken());
                    int max_cap=Integer.parseInt(tok.nextToken());
                    
                    db= new DataBase(db_name,no_nodes,max_cap);//formam baza de date
                    
                }
           
                if(op.equals("CREATE")){
                    /*formam o entitate*/
                    String ent_name=tok.nextToken();
                    int rf=Integer.parseInt(tok.nextToken());
                    int no_attr=Integer.parseInt(tok.nextToken());

                    
                    Entity ent = new Entity(ent_name,rf,no_attr, null);
                    
                    for(int i=1 ; i <= no_attr ; i++)
                    {
                        ent.getAttr_name().add(tok.nextToken());
                        ent.getAttr_type().add(tok.nextToken());
                    }
                    ent.setPrimary_key(ent.getAttr_name().getFirst());//setam cheia primara
                    
                    if(db != null)
                        db.getEnt().add(ent);/*adaugam entitatea*/
                }
                if(op.equals("INSERT")){
                    
                    /*vom sorta nodurile dupa  numarul de elemente continute si indexul nodurilor*/
                    if(db != null)
                    {
                        db.getNodes().sort(new Comparator<Node>(){
                            
                            @Override
                            public int compare(Node n1,Node n2){
                                if(n1.getInst().size() == n2.getInst().size())
                                    return n1.getNode_num() - n2.getNode_num();
                                else
                                    return n2.getInst().size() - n1.getInst().size();
                            }
                        });
                        String entity_type = tok.nextToken();
                        Entity e = db.getEnt().getFirst();
                        /*cautam entitatea in lista cu entitati*/
                        for(Entity i : db.getEnt())
                            if(i.getName().equals(entity_type))
                            {
                                e = i;//am gasit entitatea
                                break;
                            }
                        int pos=0;// va fi pozitia nodului in care inseram
                        
                                                
                        
                        Instance in;
                             /*formam o instanta a entitatii e cu timestamp si primary key*/
                            in = new Instance(entity_type,System.nanoTime(),null);
                            int key = 0;/*introducem cheia primara doar la inceput*/
                            for(String s : e.getAttr_type())
                            {   
                                String attr = tok.nextToken();
                                if(key==0)
                                {
                                    in.setPrimary_key(attr);
                                    key=1;
                                }
                                if(s.equals("Integer"))
                                    in.getAttributes().add(Integer.parseInt(attr));
                                if(s.equals("Float"))
                                    in.getAttributes().add(df.format(Float.parseFloat(attr)));
                                if(s.equals("String"))
                                    in.getAttributes().add(attr);
                                
                            }
                            
                        for (int i=1 ; i <= e.getRf() ; i++)
                        {
                            Instance inst_copy = (Instance) in.clone();
                            
                            while( db.getNodes().get(pos).getInst().size() == db.getNodes_cap() ) //daca nodul este plin trecem mai departe
                                pos++;
                            db.getNodes().get(pos).getInst().addFirst(inst_copy);//adaugam instanta la inceput de lista
                            pos++;
                        }
                    }
                }
                
                if(op.equals("DELETE"))
                {
                    String ent_type=tok.nextToken();
                    String prim_key=tok.nextToken();
                    
                    if(db!=null)
                    {
                        boolean found=false;
                        /*cautam entitatea instantei de sters apoi instanta respectiva*/
                        for(int i=0 ; i<db.getNo_nodes() ; i++)
                        {
                            for(Instance ins: db.getNodes().get(i).getInst())
                                if( ins.getEntity_type().equals(ent_type) && ins.getPrimary_key().equals(prim_key))
                                {
                                    db.getNodes().get(i).getInst().remove(ins);//stergem entitatea
                                    found=true;
                                    break;
                                }
                        }
                        
                        if(found==false)
                            output.println("NO INSTANCE TO DELETE");
                    }
                }
                if(op.equals("UPDATE"))
                {
                    String ent_type=tok.nextToken();
                    String prim_key=tok.nextToken();
                    int node=0;//nodul in care se afla valoarea cautata
                    
                    if(db!=null)
                    {
                        Instance in = null;
                        /*vom cauta instanta dupa cheia primara si tipul entitatii*/
                        for(int i=0 ; i<db.getNo_nodes() ; i++)
                        {
                            for(Instance ins: db.getNodes().get(i).getInst())
                                if( ins.getEntity_type().equals(ent_type) && ins.getPrimary_key().equals(prim_key))
                                {
                                    in=ins;
                                    node=i;
                                    break;
                                }
                        }
                        /* cautam pozitia atributului ce trebuie schimbat si tipul acestuia*/
                        while(tok.hasMoreTokens())
                        {
                            String attr_name=tok.nextToken();
                            String attr_value=tok.nextToken();
                            String attr_type=null;
                            
                            int index=0;//index-ul atributului ce trebuie schimbat
                            
                            for(Entity e : db.getEnt())
                                if(e.getName().equals(ent_type))
                                    for(String s : e.getAttr_name())
                                        if(s.equals(attr_name))
                                        {
                                            index=e.getAttr_name().indexOf(s);
                                            attr_type=e.getAttr_type().get(index);
                                            break;
                                        }
                            /*Inlocuim atributele vechi cu cele noi*/
                            if(in !=null && attr_type!=null)
                            {
                                if(attr_type.equals("Integer"))
                                    in.getAttributes().set(index, Integer.parseInt(attr_value));
                                
                                if(attr_type.equals("Float"))
                                    in.getAttributes().set(index, df.format(Float.parseFloat(attr_value)));
                                
                                if(attr_type.equals("String"))
                                    in.getAttributes().set(index, attr_value);
                                
                            }
                        }
                        /*Modificam pozitia elementului ca fiind cea mai recenta intrare*/
                        for(int k=0 ;k <db.getNo_nodes(); k++)
                            for(Instance i : db.getNodes().get(k).getInst())
                                if( in!=null &&  in.getPrimary_key().equals(i.getPrimary_key()))
                                {
                                
                                    Instance inst_copy = (Instance) i.clone();
                           
                                    db.getNodes().get(k).getInst().remove(i);
                                    db.getNodes().get(k).getInst().addFirst(inst_copy);
                                    break;
                                }
                    }
                }
                
                if(op.equals("GET"))
                {
                    String ent_type=tok.nextToken();
                    String prim_key=tok.nextToken();
                    
                    ArrayList<Integer> array = new ArrayList<>();//vom stoca nodurile in care se gaseste instanta
                    boolean found=false;
                    if(db!=null)
                    {
                        Instance in = null;
                        /*vom cauta instanta dupa cheia primara*/
                        for(int i=0 ; i<db.getNo_nodes() ; i++)
                        {
                            for(Instance ins: db.getNodes().get(i).getInst())
                                if( ins.getEntity_type().equals(ent_type) && ins.getPrimary_key().equals(prim_key))
                                {
                                    in=ins;
                                    found=true;
                                    array.add(db.getNodes().get(i).getNode_num());
                                    break;
                                } 
                        }
                        if(found==true)
                        {/*vom gasi index-ul nodurilor ce contin instanta cautata si le sortam crescator pentru afisare*/
                            array.sort(new Comparator<Integer>(){
                                @Override
                                public int compare(Integer a, Integer b)
                                {
                                    return a-b;
                                }
                            });
                            /*afisam nodurile*/
                            for(int i=0; i<array.size();i++ )
                                output.print("Nod" + array.get(i) + " ");
                            output.print(ent_type + " ");
                            /*afisam instantele*/
                            for(Entity e : db.getEnt())
                                if(e.getName().equals(ent_type)) {
                                    if (in!=null) {
                                        for(String s : e.getAttr_name())
                                        {
                                            int index = e.getAttr_name().indexOf(s);
                                            output.print(s + ":" + in.getAttributes().get(index) );
                                            if(index != in.getAttributes().size()-1)
                                                output.print(" ");
                                        }
                                    }
                                }
                            output.print("\n");
                        }
                        else
                            output.println("NO INSTANCE FOUND");
                    }
                }
                
                if(op.equals("SNAPSHOTDB"))
                {   /*afisam continutul fiecarui nod */
                    boolean empty=true;
                    if(db!=null)
                    {
                        for(int i=0; i<=db.getNo_nodes(); i++)
                        {
                            for(Node n : db.getNodes())
                                if(n.getNode_num() == i && !n.getInst().isEmpty())
                                {
                                    empty=false;
                                    output.println("Nod" + i);
                                    for( Instance in : n.getInst())
                                    {
                                        output.print(in.getEntity_type()+ " ");
                                        
                                        for(Entity e : db.getEnt())
                                            if(e.getName().equals(in.getEntity_type()))
                                                for(String s : e.getAttr_name())
                                                {
                                                    int index=e.getAttr_name().indexOf(s);
                                                    output.print(s + ":" + in.getAttributes().get(index) );
                                                    if(index != in.getAttributes().size()-1)
                                                        output.print(" "); 
                                                }
                                        output.println();
                                            
                                    }
                                }
                        }
                        if(empty == true)
                            output.println("EMPTY DB");
                    }
                }
                if(op.equals("CLEANUP"))
                {
                    String db_name  = tok.nextToken();
                    long timestamp = Long.parseLong(tok.nextToken());
                    
                    if( db.getName().equals(db_name) && db!=null)
                    {
                        for ( Node i : db.getNodes())
                        {
                            int k=0;
                            while( k != i.getInst().size())
                            {
                                if ( i.getInst().get(k).getTimestamp() < timestamp )
                                {
                                    Instance n = i.getInst().get(k);
                                    i.getInst().remove(n);
                                }
                                else
                                    k++;
                            }
                        }
                    }
                }
            } 
            
            output.close();
        }
        /*prindem posibilele exceptii*/
        catch(FileNotFoundException e)
        {
            System.out.println("Fisier inexistent!");
        }
        catch(CloneNotSupportedException c)
        {
            System.out.println("Clone not supported");
        }
    
       
    }
    
}
