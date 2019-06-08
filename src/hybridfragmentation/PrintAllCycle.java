/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hybridfragmentation;

/**
 *
 * @author sony
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.*;

public class PrintAllCycle {

    static int[][] graph;// = PrintAllCycle.convertData();
        /*{
            {1, 2}, {1, 3}, {1, 4}, {2, 3},
            {3, 4}, {2, 6}, {4, 6}, {7, 8},
            {8, 9}, {9, 7}
        };*/

    static List<int[]> cycles = new ArrayList<int[]>();
    
    Comparator<int []> integerLengthComparator = new Comparator<int []>()
    {
        @Override
        public int compare(int []o1, int []o2)
        {
            return Integer.compare(o1.length, o2.length);
        }
    };

    
    //public static void main(String[] args) {
    public static void Fragment(int size){
        
        for(int i = 0;i<graph.length;i++){
            for (int j = 0; j < graph[i].length; j++){
                findNewCycles(new int[] {graph[i][j]});
            }
        }
       
        
        
        PrintAllCycle pc = new PrintAllCycle();
        pc.printAllCycles(size);

    //}
    }

    public void printAllCycles(int size){
        
        Collections.sort(cycles, integerLengthComparator);
        boolean trace[] = new boolean[size+1];
        
        for (int[] cy : cycles){
            boolean flag = false;
            String s = "" + cy[0];
            if(trace[cy[0]]){
                flag = true;
            }
            for (int i = 1; i < cy.length; i++){
                s += "," + cy[i];
                if(trace[cy[i]]){
                    flag = true;
                    break;
                }
            }
            
            if(flag==false){
               
               for(int i = 0; i < cy.length; i++){
                   trace[cy[i]] = true;
               }
               o(s);
            }
        }
        
        System.out.println("Not create a similar cluster pick them in one cluster");
        List<Integer> l = new ArrayList<Integer>();
        
        for(int i=1;i<=size;i++){
            if(trace[i]==false){
                System.out.print(""+i+",");
                l.add(i);
            }
        }
        int Sal[] = {7,8};
        for(int it=0;it<Sal.length;it++){
            l.add(Sal[it]);
            Integer []A =  l.toArray(new Integer[l.size()]);
            int At[] = new int[l.size()];
            for(int a=0;a<l.size();a++){
                At[a] = A[a];
            }
            cycles.add(At);
            l.remove(new Integer(Sal[it]));
        }
       
        System.out.println("");
    }
    
    static void findNewCycles(int[] path){
            int n = path[0];
            int x;
            int[] sub = new int[path.length + 1];

            for (int i = 0; i < graph.length; i++){
                for (int y = 0; y <= 1; y++){
                    if (graph[i][y] == n){
                        x = graph[i][(y + 1) % 2];
                        if (!visited(x, path)){
                            sub[0] = x;
                            System.arraycopy(path, 0, sub, 1, path.length);
                            //  explore extended path
                            findNewCycles(sub);
                        }
                        else if ((path.length > 2) && (x == path[path.length - 1]))
                        //  cycle found
                        {
                            int[] p = normalize(path);
                            int[] inv = invert(p);
                            if (isNew(p) && isNew(inv))
                            {
                                cycles.add(p);
                            }
                        }
                    }
                }
            }
    }

    //  check of both arrays have same lengths and contents
    static Boolean equals(int[] a, int[] b){
        Boolean ret = (a[0] == b[0]) && (a.length == b.length);

        for (int i = 1; ret && (i < a.length); i++){
            if (a[i] != b[i])
            {
                ret = false;
            }
        }

        return ret;
    }

    //  create a path array with reversed order
    static int[] invert(int[] path){
        int[] p = new int[path.length];

        for (int i = 0; i < path.length; i++){
            p[i] = path[path.length - 1 - i];
        }

        return normalize(p);
    }

    //  rotate cycle path such that it begins with the smallest node
    static int[] normalize(int[] path){
        int[] p = new int[path.length];
        int x = smallest(path);
        int n;

        System.arraycopy(path, 0, p, 0, path.length);

        while (p[0] != x){
            n = p[0];
            System.arraycopy(p, 1, p, 0, p.length - 1);
            p[p.length - 1] = n;
        }

        return p;
    }

    //  compare path against known cycles
    //  return true, iff path is not a known cycle
    static Boolean isNew(int[] path){
        Boolean ret = true;

        for(int[] p : cycles)
        {
            if (equals(p, path))
            {
                ret = false;
                break;
            }
        }

        return ret;
    }

    static void o(String s){
        System.out.println(s);
    }

    static int smallest(int[] path){
        int min = path[0];

        for (int p : path)
        {
            if (p < min)
            {
                min = p;
            }
        }

        return min;
    }

    static Boolean visited(int n, int[] path){
        Boolean ret = false;

        for (int p : path){
            if (p == n){
                ret = true;
                break;
            }
        }

        return ret;
    }
    
    static void convertData(List<String> list){
        int size = list.size();
        graph = new int[size][2];
        int itr = 0;
        for(String x : list){
            String temp[] = x.split(" ");
            graph[itr][0] = Integer.parseInt(temp[0]);
            graph[itr][1] = Integer.parseInt(temp[1]);
            //System.out.println(""+x);
            itr++;
        }
      
    }
    
    public static List<int []> getCycles(){
        return cycles;
    }
            
}