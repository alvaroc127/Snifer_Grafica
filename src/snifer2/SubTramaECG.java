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
public class SubTramaECG extends SubTramaParam implements SubTraMetod {
    
    private double aVF;
    private double aVL;
    private double aVR;
    private double CVP;
    private int frecuencia;
    private double I;
    private double V;
    private double II;
    private double III;
    private int valid;

    public SubTramaECG(){
        
    }


    
    
    public double getaVF() {
        return aVF;
    }

    public void setaVF(double aVF) {
        this.aVF = aVF;
    }

    public double getaVL() {
        return aVL;
    }

    public void setaVL(double aVL) {
        this.aVL = aVL;
    }

    public double getaVR() {
        return aVR;
    }

    public void setaVR(double aVR) {
        this.aVR = aVR;
    }

    public double getCVP() {
        return CVP;
    }

    public void setCVP(double CVP) {
        this.CVP = CVP;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getI() {
        return I;
    }

    public void setI(double I) {
        this.I = I;
    }

    public double getV() {
        return V;
    }

    public void setV(double V) {
        this.V = V;
    }

    public double getII() {
        return II;
    }

    public void setII(double voltaje_aVF) {
        this.II = voltaje_aVF;
    }

    public double getIII() {
        return III;
    }

    public void setIII(double III) {
        this.III = III;
    }

    
    
    public int runData(ArrayList data,int post){
        String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        var=var.concat(String.format("%02X",data.get(++post)));
        //System.out.println(var);
        valid=Integer.parseInt(var,16);
        return post;
    }
    
    public int loadFrecuencia(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(++post));
        val=val.concat(String.format("%02X", data.get(++post)));
       // System.out.println(val);
        frecuencia=Integer.parseInt(val,16);
        return ++post;
    }
    
    
    public int loadCVP(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println("cvp"+val);
        CVP=Integer.parseInt(val,16);
        if(CVP>32768){
        CVP=(CVP-65536)/100;
        }else{
        CVP=CVP/100;
        }
        return ++post;
    }
    
    public int loadAVR(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println("avr"+val);
        aVR=Integer.parseInt(val,16);
        if(aVR>32768){
        aVR=(aVR-65536)/100;
        }else{
        aVR=aVR/100;
        }
        return ++post;
    }
    
    public int loadAVL(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println("avl"+val);
        aVL=Integer.parseInt(val,16);
        if(aVL>32768){
        aVL=(aVL-65536)/100;
        }else{
        aVL=aVL/100;
        }
        return ++post;
    }
    public int loadAVF(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
       //System.out.println("avf"+val);
        aVF=Integer.parseInt(val,16);
        if(aVF>32768){
        aVF=(aVF-65536)/100;
        }else{
        aVF=aVF/100;
        }
        return ++post;
    }
    public int  loadV(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println("V"+val);
        V=Integer.parseInt(val, 16);
         if(V>32768){
        V=(V-65536)/100;
        }else{
        V=V/100;
        }
         return ++post;
    }
    
    
     public int loadIII(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println("III"+val);
        III=Integer.parseInt(val,16);
         if(III>32768){
        III=(III-65536)/100;
        }else{
        III=III/100;
        }
         return ++post;
    }
    
     
     public int loadII(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println("II"+val);
        II=Integer.parseInt(val, 16);
         if(II>32768){
        II=(II-65536)/100;
        }else{
        II=II/100;
        }
         return ++post;
    }
     
     
     
     public int loadI(ArrayList data,int post){
        String val=null;
        val=String.format("%02X", data.get(post));
        val=val.concat(String.format("%02X", data.get(++post)));
        //System.out.println("I"+val);
        I=Integer.parseInt(val,16);
         if(I>32768){
        I=(I-65536)/100;
        }else{
        I=I/100;
        }
         return ++post;
    }

    @Override
    public int clasifiSubTra(ArrayList data, int post) {
        boolean ban=true;
        for(int j=post;j<data.size()&&post+1<data.size()&&ban==true;j++){
            post=runData(data, post);
        switch(valid){
            case(65541):
                post=loadFrecuencia(data,post);
            break;
            
            case(131077):
                post=loadCVP(data,++post);
            break;
               
                
            case(328202):
               post=loadI(data, ++post);
            break;
                
                
            case(393738):
                post=loadII(data, ++post);
           break;
                
           case(459274):
               post=loadIII(data, ++post);
           break;
                   
           case(524810):
                   post=loadAVR(data, ++post);
            break;
                   
           case(590346):
                post=loadAVL(data, ++post);
           break;
                    
           case(655882):
                 post=loadAVF(data, ++post);
            break;
        
             case(13173258):
                 post=loadV(data, ++post);
            break;
                 
            case(150281):
                ban=false;     
            break;
          }
        }
        return post;  
    }

  
    
    
    
    
}
