/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hybridfragmentation;

import java.io.*;
import java.net.URL;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import InputFormats.*;
import java.util.*;

/**
 *
 * @author sony
 */
public class HybridFragmentation {

    /**
     * @param args the command line arguments
     */
    HorizontalFragmentation HF;
    VerticalFragmentation VF;
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
         
        try
        {  
            InputFile in = new InputFile();
            HybridFragmentation hf = new HybridFragmentation();
            /*
            //Object obj = new JSONParser().parse(new FileReader("C:\\Users\\sony\\Documents\\NetBeansProjects\\HybridFragmentation\\src\\hybrid_data.json"));
            Object obj = new JSONParser().parse(new FileReader(hf.getFileName().toURI().toString().substring(6,hf.getFileName().toURI().toString().length())));
            */
            
            //Start
            
            hf.BeginProgram();
            //
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void BeginProgram()
    {
        VF = new VerticalFragmentation();
        HF = new HorizontalFragmentation();
        int AttUsMat[][] = InputFile.getAttributeUsageMatrixVer(); //InputFile.getAttributeUsageMatrix();
        int AccFreq[][] = InputFile.getAccessFreqMatrixVer(); //InputFile.getAccessFreqMatrix();
        
        int AttAffMat[][] = VF.findAttributeAffinityMatrix(AttUsMat,AccFreq);
        
        int size = AttAffMat.length;
        
        int StoreAttIndex[] = new int[size];
        
        int clusteringAtt[][] = VF.calculateBongEnergyAlgorithm(AttAffMat);
        
        int index = 0;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(AttAffMat[j][j]==clusteringAtt[i][i])
                {
                    StoreAttIndex[index++] = j;
                }
                System.out.print(clusteringAtt[i][j]+" ");
            }
            System.out.println("");
        }
        
        System.out.println("\nAttribute Clustering");
        for(int i=0;i<size;i++)
        {
            System.out.print((StoreAttIndex[i]+1)+" ");
        }
        System.out.println("\n");
        
        //split the clustering
        String AttSplit = splitVerticalClustering(clusteringAtt,StoreAttIndex,AttAffMat,size,AttUsMat,AccFreq);
        System.out.println(AttSplit);
        String []f = AttSplit.split(" ");
        String fc = f[2].substring(1, f[2].length()-1);
        String sc = f[5].substring(1, f[5].length()-1);
        
        System.out.println("");
        //copy the data index of cluster indexing
        InputFile.copyIndexofClusterAttr(StoreAttIndex);
        
        BeginHorizontalFragmentation();
        
        /*
         * Design Hybrid fragmentation
         */
        
        //System.out.println("Design Hybrid fragmentation");
    }
    
    /*
     * @method splitClustering[]
     * @description Split the cluster into two fragments
     */
    public String splitVerticalClustering(int clusAtt[][],int ClusIndex[],int AttAff[][],int size,int AttUsMat[][],int AccFreq[][])
    {
        String splitClusAtt = "";
        
        int itr = 1;
        long max = Long.MIN_VALUE;
        long first=0,second=0,both=0;
        
        while(itr<=size/2)
        {
            
            //case1
            first = 0;
            second = 0;
            both = 0;
            long value = 0;
            for(int j=0;j<size;j++)
            {
                int fc = 0;
                int sc = 0;
                for(int i=0;i<itr;i++)
                {
                   if(AttUsMat[j][ClusIndex[i]]==1)
                   {
                       fc++;
                   }
                }
                //System.out.println("f "+fc);
                
                for(int i = itr;i<size;i++)
                {
                   if(AttUsMat[j][ClusIndex[i]]==1)
                   {
                       sc++;
                   } 
                }
                //System.out.println("s "+sc);
                
                if(fc>0&&sc==0)
                {
                    for(int k=0;k<AccFreq[0].length;k++)
                    {
                        first = first+AccFreq[j][k];
                    }
                }
                else if(fc==0&&sc>0)
                {
                    for(int k=0;k<AccFreq[0].length;k++)
                    {
                        second = second+AccFreq[j][k];
                    }
                }
                else if(fc>0&&sc>0)
                {
                    for(int k=0;k<AccFreq[0].length;k++)
                    {
                        both = both+AccFreq[j][k];
                    }
                }
            }
            
            //System.out.println(first+" "+second+" "+both);
            if((first*second-Math.pow(both,2))>max)
            {
                splitClusAtt = "";
                max = (long)(first*second-Math.pow(both,2));
                splitClusAtt = splitClusAtt+"first cluster {";
                for(int i=0;i<itr;i++)
                {
                    //System.out.println(splitClusAtt);
                    if((i+1)<itr)
                    {
                            splitClusAtt = splitClusAtt+String.valueOf(ClusIndex[i]+1)+",";
                    }
                    else
                    {
                           splitClusAtt = splitClusAtt+String.valueOf(ClusIndex[i]+1);        
                    }
                }
                splitClusAtt = splitClusAtt+"} ";
                splitClusAtt = splitClusAtt+"second cluster {";
                for(int i=itr;i<size;i++)
                {
                    if((i+1)<size)
                    {
                            splitClusAtt = splitClusAtt+String.valueOf(ClusIndex[i]+1)+",";
                    }
                    else
                    {
                           splitClusAtt = splitClusAtt+String.valueOf(ClusIndex[i]+1);        
                    }
                }
                splitClusAtt = splitClusAtt+"}";
            }
            //case2
            first = 0;
            second = 0;
            both = 0;
            value = 0;
            for(int j=0;j<size;j++)
            {
                int fc = 0;
                int sc = 0;
                for(int i=0;i<size-itr;i++)
                {
                   if(AttUsMat[j][ClusIndex[i]]==1)
                   {
                       fc++;
                   }
                }
                //System.out.println("f "+fc);
                
                for(int i = size-itr;i<size;i++)
                {
                   if(AttUsMat[j][ClusIndex[i]]==1)
                   {
                       sc++;
                   } 
                }
                //System.out.println("s "+sc);
                
                if(fc>0&&sc==0)
                {
                    for(int k=0;k<AccFreq[0].length;k++)
                    {
                        first = first+AccFreq[j][k];
                    }
                }
                else if(fc==0&&sc>0)
                {
                    for(int k=0;k<AccFreq[0].length;k++)
                    {
                        second = second+AccFreq[j][k];
                    }
                }
                else if(fc>0&&sc>0)
                {
                    for(int k=0;k<AccFreq[0].length;k++)
                    {
                        both = both+AccFreq[j][k];
                    }
                }
            }
            
            //System.out.println(first+" "+second+" "+both);
            if((first*second-Math.pow(both,2))>max)
            {
                splitClusAtt = "";
                max = (long)(first*second-Math.pow(both,2));
                splitClusAtt = splitClusAtt+"first cluster {";
                for(int i=0;i<itr;i++)
                {
                    if((i+1)<itr)
                    {
                            splitClusAtt = splitClusAtt+String.valueOf(ClusIndex[i]+1)+",";
                    }
                    else
                    {
                           splitClusAtt = splitClusAtt+String.valueOf(ClusIndex[i]+1);        
                    }
                }
                splitClusAtt = splitClusAtt+" } ";
                splitClusAtt = splitClusAtt+"second cluster {";
                for(int i=itr;i<size;i++)
                {
                    if((i+1)<size)
                    {
                            splitClusAtt = splitClusAtt+String.valueOf(ClusIndex[i]+1)+",";
                    }
                    else
                    {
                           splitClusAtt = splitClusAtt+String.valueOf(ClusIndex[i]+1);        
                    }
                }
                splitClusAtt = splitClusAtt+"}";
            }
            
            itr++;
        }
        
        return splitClusAtt;
    }
    
    public void BeginHorizontalFragmentation()
    {
        try
        {
          //int HPredMatrix[][] = InputFile.getPredicateUsageMatrix();
             predicateAffMatrix predicate_H_Mat[][] = HF.calculatePredicateAffMatrix();
             
             List<String> listEdge = HF.generateGraph(predicate_H_Mat);
             PrintAllCycle.convertData(listEdge);
             PrintAllCycle.Fragment(predicate_H_Mat.length);
             //PrintCycle pc = new PrintCycle();
             
             /*
              * Cluster is done overlaped fragmentation
              */
             
             /*
              * find the non overlapped fragmentation
              */
             
             HF.nonOverlapcluster(predicate_H_Mat.length);
             
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
