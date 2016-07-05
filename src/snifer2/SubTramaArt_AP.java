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
public class SubTramaArt_AP extends SubTramaParam implements SubTraMetod{
    
    private int alto;
    private int bajo;
    private int parentesis;
    private int val;
    private boolean band;

    public SubTramaArt_AP() {
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getBajo() {
        return bajo;
    }

    public void setBajo(int bajo) {
        this.bajo = bajo;
    }

    public int getParentesis() {
        return parentesis;
    }

    public void setParentesis(int parentesis) {
        this.parentesis = parentesis;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public boolean isBand() {
        return band;
    }

    public void setBand(boolean band) {
        this.band = band;
    }
    
    
    public int runData(ArrayList data,int post){
        String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        var=var.concat(String.format("%02X",data.get(++post)));
        val=Integer.parseInt(var,16);
        return post;
    }
    
    
   public int loadHigh(ArrayList data,int post){
        String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        //System.out.println(var);
        alto=Integer.parseInt(var, 16);
        return ++post;
   }
   
   
   
   public int  loadLow(ArrayList data,int post){
    String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        //System.out.println(var);
        bajo=Integer.parseInt(var, 16);
   return ++post;
   }
   
   public int loadParentesis(ArrayList data,int post){
    String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        //System.out.println(var);
        parentesis=Integer.parseInt(var, 16);
        return ++post;
   }
   
   
    @Override
    public int clasifiSubTra(ArrayList data, int post) {
        boolean ban=true;
        for(int i=post;i<data.size()&&post+2<data.size()&&ban==true;i++){
            post=runData(data, post);
        switch(val){
                case(8454149):
                    loadHigh(data, ++post);
                    band=true;
                break;
                    
                    case(8519685):
                        loadLow(data, ++post);
                        band=true;
                    break;
                        
                        case(8585221):
                            loadParentesis(data, ++post);
                            band=true;
                        break;  
                            
                         case(8650757):
                             loadHigh(data, ++post);
                             band=false;
                             break;
                             
                              case(8716293):
                             loadLow(data, ++post);
                             band=false;
                             break;
                                  
                           case(8781829):
                              loadParentesis(data, ++post);
                              band=false;
                         break;
                            
           case(150281):
                 ban=false;
                break;
           }
        
            }
       // System.out.println("este es post "+ post);
                return post;
        }
    }
