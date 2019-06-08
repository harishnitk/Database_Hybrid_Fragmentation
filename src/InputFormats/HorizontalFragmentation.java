/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InputFormats;

import hybridfragmentation.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import oracle.jrockit.jfr.settings.JSONElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sony
 */
public class HorizontalFragmentation {

    //constructor
    InputFile in;
    public HorizontalFragmentation() {
        in = new InputFile();
    }
    
    public void calculateMiniterm()
    {
        /*
         * Table Proj
         * Pname Pno Budget Loc are attributes
         */
        try
        {
          
          Object obj = new JSONParser().parse(new FileReader(in.getFileName("../H_data.json").toURI().toString().substring(6,in.getFileName("../H_data.json").toURI().toString().length())));
          //passing predicates on which basis defined some miniterm predicates
          int a = 5;
          
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public predicateAffMatrix[][] calculatePredicateAffMatrix()
    {
            
        try
        {
           Object obj = new JSONParser().parse(new FileReader(in.getFileName("../H_data.json").toURI().toString().substring(6,in.getFileName("../H_data.json").toURI().toString().length())));    
           JSONObject json_obj = (JSONObject)obj;
           JSONArray array = (JSONArray)json_obj.get("minterm_Predicates");
           
           //parsingValues(array);
           
           predicateAffMatrix predicate_H_Matrix[][] = {
               {
                   new predicateAffMatrix("","",Integer.MIN_VALUE),
                   new predicateAffMatrix("=>","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",25),
                   new predicateAffMatrix("","",0)
               },
               {
                   new predicateAffMatrix("<=","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",Integer.MIN_VALUE),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",50),
                   new predicateAffMatrix("","",0)
               },
               {
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",Integer.MIN_VALUE),
                   new predicateAffMatrix("<=","",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",25),
                   new predicateAffMatrix("","",0)
               },
               {
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("=>","",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",Integer.MIN_VALUE),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",35)
               },
               {
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",Integer.MIN_VALUE),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",50)               },
               {
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","*",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",40)
               },
               {
                   new predicateAffMatrix("","",25),
                   new predicateAffMatrix("","",50),
                   new predicateAffMatrix("","",25),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",Integer.MIN_VALUE),
                   new predicateAffMatrix("","",0)
               },
               {
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",35),
                   new predicateAffMatrix("","",50),
                   new predicateAffMatrix("","",40),
                   new predicateAffMatrix("","",0),
                   new predicateAffMatrix("","",Integer.MIN_VALUE)
               }
           };
           
           return predicate_H_Matrix;
           
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    
    }
    
    public void parsingValues(JSONArray array)
    {
        
    }
            
    public List<String> generateGraph(predicateAffMatrix M[][]){
        List<String> listPair = new ArrayList<String>();
        
        int row = M.length;
        int col = M[0].length;
        
        boolean impliesrightclose = false;
        boolean impliesleftclose = false;
        boolean impliesleft = false;
        boolean impliesright = false;
        
        for(int i=0;i<row;i++){
            
            impliesrightclose = false;
            impliesleftclose = false;
            impliesleft = false;
            impliesright = false;
            
            //search for function
            for(int j=0;j<col;j++){
                if(M[i][j].implies.equals("=>")&&M[i][j].close.equals("*")){
                    impliesrightclose = true;
                    break;
                }else if(M[i][j].implies.equals("<=")&&M[i][j].close.equals("*")){
                    impliesleftclose = true;
                    break;
                }else if(M[i][j].implies.equals("<=")){
                    impliesleft = true;
                    break;
                }else if(M[i][j].implies.equals("=>")){
                    impliesright = true;
                }
            }
            
            for(int j=0;j<col;j++){
                
                String str = (i+1)+" "+(j+1);
                String comp = (j+1)+" "+(i+1);
                boolean flag = listPair.contains(comp);
                
                //System.out.println(""+flag);
                if(impliesrightclose==true){
                    if(M[i][j].implies.equals("=>")&&M[i][j].close.equals("*")){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" h "+(j+1));
                        }
                    }else if(M[i][j].value!=0&&M[i][j].value!=Integer.MIN_VALUE){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" i "+(j+1));
                        }
                    }
                }
                else if(impliesleftclose==true){
                    if(M[i][j].value!=0&&M[i][j].value!=Integer.MIN_VALUE){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" v "+(j+1));
                        }
                    }else if(M[i][j].close.equals("*")){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" gg "+(j+1));
                        }
                    }
                }
                else if(impliesleft==true){
                    if(M[i][j].implies.equals("<=")){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" k "+(j+1));
                        }
                    }
                }else if(impliesright==true){
                    if(M[i][j].implies.equals("=>")){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" s "+(j+1));
                        }
                    }else if(M[i][j].value!=0&&M[i][j].value!=Integer.MIN_VALUE){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" a "+(j+1));
                        }
                    }
                }else{
                    if(j>=i&&M[i][j].close.equals("*")){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" z "+(j+1));
                        }
                    }else if(j>=i&&M[i][j].value!=0&&M[i][j].value!=Integer.MIN_VALUE){
                        if(!flag){
                           listPair.add(str);
                            //System.out.println((i+1)+" x "+(j+1));
                        }
                    }
                }
            }
        }
        //System.out.println("size "+listPair.size());
        return listPair;
    }
    
    public void nonOverlapcluster(int size) throws IOException{
        
        List<int []> cycles = PrintAllCycle.getCycles();
        
        System.out.println("\nNonoverlapped cluster");
        
        try { 
            
            Object obj = new JSONParser().parse(new FileReader(in.getFileName("../H_data.json").toURI().toString().substring(6,in.getFileName("../H_data.json").toURI().toString().length())));
            JSONObject json_obj = (JSONObject)obj;
            JSONArray array = (JSONArray)json_obj.get("temp_M_Sample");
            String str[][] = new String[cycles.size()][2];
            long Value[][] = new long[cycles.size()][2];
            String Sign[][] = new String[cycles.size()][2];
            int count = 0;    
            for(int [] cy:cycles){
            
                boolean visited[] = new boolean[cy.length];
                Object o_a[] = new Object[cy.length];
                for(int i=0;i<cy.length;i++){
                    o_a[i] = array.get(cy[i]-1);
                }
                
                for(int i=0;i<cy.length;i++){
                    
                    for(int j=0;j<cy.length;j++){
                        JSONObject one = (JSONObject)o_a[i];
                        JSONObject two = (JSONObject)o_a[j];
                            
                        if((i!=j)){
                            if(one.get("At_name").toString().equals(two.get("At_name").toString())&&!visited[i]&&!visited[j]){
                                visited[j] = true;
                                visited[i] = true;
                                str[count][0] = one.get("At_name").toString();
                                if(one.get("Sign").toString().equals(two.get("Sign").toString())){
                                    
                                    Sign[count][0] = two.get("Sign").toString();
                                    if(one.get("Sign").toString().equals("lt")){
                                        if(Integer.parseInt(two.get("Val").toString())>Integer.parseInt(one.get("Val").toString())){
                                           Value[count][0] = Integer.parseInt(two.get("Val").toString());             
                                        }else{
                                            Value[count][0] = Integer.parseInt(one.get("Val").toString());
                                        }
                                    }else{
                                        if(Integer.parseInt(two.get("Val").toString())>Integer.parseInt(one.get("Val").toString())){
                                           Value[count][0] = Integer.parseInt(one.get("Val").toString());             
                                        }else{
                                            Value[count][0] = Integer.parseInt(two.get("Val").toString());
                                        }
                                    }
                                }else{
                                    //based on remaining cluster
                                    if(one.get("Sign").toString().equals("lt")){
                                        Sign[count][0] = "lt";
                                        Value[count][0] = Integer.parseInt(one.get("Val").toString());
                                    }else{
                                        Sign[count][0] = "gt";//lt before
                                        Value[count][0] = Integer.parseInt(two.get("Val").toString());
                                    }
                                }
                            }else if(two.get("At_name").toString().equals("Sal")){
                               str[count][1] = two.get("At_name").toString();
                               Sign[count][1] = two.get("Sign").toString();
                               Value[count][1] = Long.parseLong(two.get("Val").toString());
                               visited[j] = true;
                            }
                        }
                    }
                }
         
                count++;
            }
          
            for(int i=0;i<count;i++){
                System.out.println(str[i][0]+" "+Sign[i][0]+" "+Value[i][0]+" && "+str[i][1]+" "+Sign[i][1]+" "+Value[i][1]);
            }
            
            print_Horizontal_Fragementation(str,Sign,Value);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void print_Horizontal_Fragementation(String Str[][],String Sign[][],long Value[][]) throws IOException{
        try
        {
            Object obj = new JSONParser().parse(new FileReader(in.getFileName("../H_data.json").toURI().toString().substring(6,in.getFileName("../H_data.json").toURI().toString().length())));
            JSONObject json_obj = (JSONObject)obj;
            
            System.out.println();
            for(int i=0;i<Str.length;i++){
                System.out.println((i+1)+" Fragmentation");
                JSONArray array = (JSONArray)json_obj.get("Full_Table_Data");    
                for(int j=0;j<array.size();j++){
                    Object temp = (JSONObject)array.get(j);
                    JSONObject temp_value = (JSONObject)temp;
                    int dno = Integer.parseInt(temp_value.get("dno").toString());
                    int sal = Integer.parseInt(temp_value.get("sal").toString());
                    
                    if(Sign[i][0].equals("lt")&&Sign[i][1].equals("lt")){
                        
                       if(Value[i][0]>dno&&Value[i][1]>sal){
                           System.out.println(temp_value.toString());
                       }    
                    }else if(Sign[i][0].equals("gt")&&Sign[i][1].equals("gt")){
                        
                       if(Value[i][0]<dno&&Value[i][1]<sal){
                           System.out.println(temp_value.toString());
                       }
                       
                    }else if(Sign[i][0].equals("lt")&&Sign[i][1].equals("gt")){
                        
                       if(Value[i][0]>dno&&Value[i][1]<sal){
                           System.out.println(temp_value.toString());
                       }
                       
                    }else{
                        
                       if(Value[i][0]<dno&&Value[i][1]>sal){
                           System.out.println(temp_value.toString());
                       }
                       
                    }
                }
            }
            
            System.out.println("\nDesign of Hybrid Fragmentation");
            
            String Attname[] = InputFile.getAttributeName();
            int cI[] = InputFile.getIndexOfClusterAttr();
            for(int i=0;i<Str.length;i++){
                System.out.println((i+1)+" Fragmentation");
                JSONArray array = (JSONArray)json_obj.get("Full_Table_Data");

                for(int j=0;j<array.size();j++){
                        Object temp = (JSONObject)array.get(j);
                        JSONObject temp_value = (JSONObject)temp;
                        int dno = Integer.parseInt(temp_value.get("dno").toString());
                        int sal = Integer.parseInt(temp_value.get("sal").toString());

                        String output = "";

                        for(int cMit=0;cMit<cI.length;cMit++){
                            output+= Attname[cI[cMit]]+":"+temp_value.get(Attname[cI[cMit]]).toString()+" ";
                        }

                        if(Sign[i][0].equals("lt")&&Sign[i][1].equals("lt")){

                           if(Value[i][0]>dno&&Value[i][1]>sal){
                               System.out.println(output);
                           }    
                        }else if(Sign[i][0].equals("gt")&&Sign[i][1].equals("gt")){

                           if(Value[i][0]<dno&&Value[i][1]<sal){
                               System.out.println(output);
                           }

                        }else if(Sign[i][0].equals("lt")&&Sign[i][1].equals("gt")){

                           if(Value[i][0]>dno&&Value[i][1]<sal){
                               System.out.println(output);
                           }

                        }else{

                           if(Value[i][0]<dno&&Value[i][1]>sal){
                               System.out.println(output);
                           }

                        }
                    }
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
