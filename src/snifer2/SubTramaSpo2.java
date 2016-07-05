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
public class SubTramaSpo2 extends SubTramaParam implements SubTraMetod{
    
    private int dato1;
    private int frecuencia;
    private int val;

    public SubTramaSpo2() {
    }

    public int getDato1() {
        return dato1;
    }

    public void setDato1(int dato1) {
        this.dato1 = dato1;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    
    public int runData(ArrayList data,int post){
        String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        var=var.concat(String.format("%02X",data.get(++post)));
        //System.out.println(var);
         val=Integer.parseInt(var,16);
        return post;
    }
    
    
    public int loadDato1(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println(val);
        dato1=Integer.parseInt(val,16);
        return ++post;
    }
    
    public int loadFrecquency(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println(val);
        frecuencia=Integer.parseInt(val,16);
        return ++post; 
    }
    
    @Override
    public int clasifiSubTra(ArrayList data, int post) {
        boolean ban=true;
       for(int i=post;i<data.size()&&ban==true;i=i++){
           post=runData(data, post);
           switch(val){
           case(1179653):
               loadDato1(data, ++post);
               break;
           
           case(1245189):
               loadFrecquency(data,++post); 
               break;
               
               
           case(150281):
                 ban=false;
                break;
           }
       
       }
       return post;
    }
    
    
    
    
}
