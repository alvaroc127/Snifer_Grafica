/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.util.ArrayList;

/**
 *
 * @author ELECTRONICA
 */
public class SubtRamTemp extends SubTramaParam implements SubTraMetod{
    
    private double T1;
    private double T2;
    private double TD;
    private int val;

    public SubtRamTemp() {
    }

    
    public int runData(ArrayList data,int post){
        String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        var=var.concat(String.format("%02X",data.get(++post)));
        val=Integer.parseInt(var, 16);
        //System.out.println(var);
        return post;
    }
    
    public int loadT1(ArrayList data,int post){
     String var=null;
     var=String.format("%02X",data.get(post));
     var=var.concat(String.format("%02X",data.get(++post)));
     T1=Integer.parseInt(var, 16);
     //System.out.println(var);
     if(T1>32768){
         T1=T1-65536;
         T1=T1/100;
     }else{
    T1=T1/10;
     }
     return ++post;
    }
    
    
    
    
    public int loadT2(ArrayList data,int post){ 
     String var=null;
     var=String.format("%02X",data.get(post));
     var=var.concat(String.format("%02X",data.get(++post)));
     T2=Integer.parseInt(var, 16);
     //System.out.println(var);
     if(T2>32768){
         T2=T2-65536;
         T2=T2/100;
     }else{
    T2=T2/10;
     }
     return ++post;
    }
    
    
    public int loadT3(ArrayList data,int post){
    String var=null;
     var=String.format("%02X",data.get(post));
     var=var.concat(String.format("%02X",data.get(++post)));
     TD=Integer.parseInt(var,16);
     //System.out.println(var);
     if(TD>32768){
         TD=TD-65536;
         TD=TD/100;
     }else{
    TD=TD/10;
     }
    return ++post;
    }
    
    
    @Override
    public int clasifiSubTra(ArrayList data, int post) {
        boolean ban=true;
    for(int i=post;i<data.size()&&ban==true&&post+1<data.size();i++){
        post=runData(data, post);
        switch(val){
            case(2294026):
                loadT1(data,++post);
            break;
                
            case(2359562):
                loadT2(data,++post);
            break;
                
            case(2425098):
                loadT3(data, ++post);
            break;
                case(150281):
                 ban=false;
                break;
            }
        }
    return post;
    }
    
    
    
}
