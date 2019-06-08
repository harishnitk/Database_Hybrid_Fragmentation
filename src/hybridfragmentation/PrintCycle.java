/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hybridfragmentation;

import InputFormats.*;

/**
 *
 * @author sony
 */
public class PrintCycle {

    //constructor
    
    InputFile in;
    HorizontalFragmentation HF;
    int size = 0;
    int parent[];
    String colors[];
    
    public PrintCycle() {
       in = new InputFile();
       HF = new HorizontalFragmentation();
       start();
    }
    
    public void start(){
        
        //predicateAffMatrix 
        
        int predicate_H_Matrix[][] = {  {0,1,0,1,0,0},
                                        {1,0,0,1,0,0},
                                        {0,0,0,0,1,1},
                                        {1,1,0,0,1,0},
                                        {0,0,1,1,0,1},
                                        {0,0,1,0,1,0}
                                     };
        //HF.calculatePredicateAffMatrix();
        size = predicate_H_Matrix.length;
        parent = new int[size];
        colors = new String[size];
        
        DFS_CheckCycle(predicate_H_Matrix);
    }
    
    /*public void DFS_CheckCycle(predicateAffMatrix M[][]){
    
        int n = M.length;
        
        for(int v=0;v<n;v++){
            colors[v] = "white";
        }

        for(int u=0;u<n;u++){
            if(colors[u].equals("white")){
                parent[u] = -1;
                explore(u,M);
            }
        }
    }*/
    
    public void DFS_CheckCycle(int M[][]){
    
        int n = M.length;
        
        for(int v=0;v<n;v++){
            colors[v] = "white";
            parent[v] = -1;
        }

        for(int u=0;u<n;u++){
            if(colors[u].equals("white")){
                parent[u] = -1;
                explore(u,M);
            }
        }
    }
    
    /*public void explore(int u,predicateAffMatrix M[][]){
        boolean cycle = false;
        colors[u] = "gray";
        
        int i = 0;
        
        for(i=0;i<M.length;i++){
            
            if((M[u][i].close==true&&M[u][i].implies==true)||(M[u][i].value!=0&&M[u][i].value!=Integer.MIN_VALUE)){
                
                if(colors[i].equals("white")){
                    parent[i] = u;
                    explore(i,M);
                }
                else if(colors[i].equals("gray")){
                    cycle = true;
                    break;
                }
                   
            }
        }
        
        colors[u] = "black";
        
        if(!cycle){
            System.out.println("No cycle");
        }else{
            PrinttheCycleNode(i,u);
            System.out.println("");
        }
    }*/
    public void explore(int u,int M[][]){
        boolean cycle = false;
        colors[u] = "gray";
        int i;
        
        for(i=0;i<M.length;i++){
            
            if(M[u][i]==1){
                
                if(colors[i].equals("white")){
                    parent[i] = u;
                    explore(i,M);
                }
                else if(colors[i].equals("gray")){
                    cycle = true;
                    break;
                }
                   
            }
        }
        
        colors[u] = "black";
        
        if(!cycle){
            System.out.println("No cycle ");
        }else{
            PrinttheCycleNode(i,u);
            System.out.println("");
        }
    }
    
    public void PrinttheCycleNode(int v,int u){
        
        while(u!=v){
            System.out.print((u+1)+" ");
            u = parent[u];
        }
        System.out.print((v+1)+" ");
        
        
    }
}
