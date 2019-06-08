/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InputFormats;

import java.net.URL;

/**
 *
 * @author sony
 */
public class InputFile {

    public InputFile() {
       //initialize
    }
   
    public static int index[];
    
    public static int[][] getAttributeUsageMatrix(){
        /*int AttributeMat[][] = {
            {1,0,0,0,1,0,1,0,0,0},
            {0,1,1,0,0,0,0,1,1,0},
            {0,0,0,1,0,1,0,0,0,1},
            {0,1,0,0,0,0,1,1,0,0},
            {1,1,1,0,1,0,1,1,1,0},
            {1,0,0,0,1,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,1,0},
            {0,0,1,1,0,1,0,0,1,1}
        };*/
        
        // columns Pno , Pname, Budget, Loc
        // rows query or transaction
        int AttributeMat[][] = {
            {1,0,1,0},
            {0,1,1,0},
            {0,1,0,1},
            {0,0,1,1}
        };
        
        return AttributeMat;
        
    }
    
    
    public static int[][] getPredicateUsageMatrix(){
        
        /*
         * T1 : D-no<10 (P1),sal>40k (p7)
         * T1 : D-no<20 (P2),sal>40k (p7)
         * T1 : D-no>20 (P3),sal>40k (p7)
         * T1 : 30<D-no<50 (P4),sal<40k (p8)
         * T1 : D-no<15 (P5),sal<40k (p8)
         * T1 : D-no>40 (P6),sal<40k (p8)
         * T1 : D-no<15 (P5),sal<40k (p8)
         * T1 : D-no>40 (P6),sal<40k (p8)
         */
        
        int PredicateMat[][]={
            {1,0,0,0,0,0,1,0},
            {0,1,0,0,0,0,1,0},
            {0,0,1,0,0,0,1,0},
            {0,0,0,1,0,0,0,1},
            {0,0,0,0,1,0,0,1},
            {0,0,0,0,0,1,0,1},
            {0,0,0,0,1,0,0,1},
            {0,0,0,0,0,1,0,1}
        };
        return PredicateMat;
    }
    
    public static int[][] getAccessFreqMatrix(){
        int AccessFreq[][]  = {
            {15,20,10},
            {5,0,0},
            {25,25,25},
            {3,0,0}
         };
        
        return AccessFreq;
    }
    
     public static int[][] getAccessFreqMatrixHori(){
        int AccessFreq[][]  = {
            {10,10,5},
            {10,10,30},
            {5,0,20},
            {10,10,15},
            {10,10,5},
            {10,5,10},
            {15,5,5},
            {5,5,5}
         };
        
        return AccessFreq;
    }
    
    public static int[][] getpredicateadjacency_matrix(){
        int adjacencymatrix[][]  = {    {0,0,0,1,0},
                                        {1,0,1,0,0},
                                        {0,0,0,0,0},
                                        {0,1,0,0,1},
                                        {0,0,1,0,0}
                                   };
        
        return adjacencymatrix;
    }
    
    
    public URL getFileName(String filename)
    {
        //return getClass().getResource("../hybrid_data.json");
        return getClass().getResource(filename);
    }
    
    public void storePridicateMinterm(){
        
    }
    
    /*
     * Applying vertical after horizontal fragmentation
     */
    
    public static int[][] getAttributeUsageMatrixVer(){
        
        // columns dno , name, dept, sal
        // rows query or transaction
        int AttributeMat[][] = {
            {1,0,1,0},
            {0,1,1,0},
            {0,1,0,1},
            {0,0,1,1}
        };
        
        return AttributeMat;
        
    }
    
    public static int[][] getAccessFreqMatrixVer(){
        int AccessFreq[][]  = {
            {10,10,5},
            {10,10,30},
            {5,0,20},
            {10,10,15}
         };
        
        return AccessFreq;
    }
    
    //static data
    public static String[] getAttributeName(){
        try
        {
            String Att_name[] = {"dno","name","dept","sal"};
            return Att_name;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void copyIndexofClusterAttr(int []cM){
        
        index = new int[cM.length];
        for(int i=0;i<cM.length;i++){
            index[i] = cM[i];
        }
    }
    
    public static int[] getIndexOfClusterAttr(){
        return index;
    }
}
