/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import com.sun.javafx.collections.ArrayListenerHelper;
import java.util.ArrayList;

/**
 *
 * @author ELECTRONICA
 */
public class SubTramaImpedancia extends SubTramaParam implements SubTraMetod{
    
    private int impedancia;
    private int val;
    
    public SubTramaImpedancia() {
        
    }

      public int runData(ArrayList data,int post){
        String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        var=var.concat(String.format("%02X",data.get(++post)));
          //System.out.println(var);
        val=Integer.parseInt(var, 16);
        return post;
      }
    
    public int cargaImpedancia(ArrayList data, int post){
       String var=null;
        var=String.format("%02X",data.get(post));
        var=var.concat(String.format("%02X",data.get(++post)));
        //System.out.println(var);
        impedancia=Integer.parseInt(var,16);
        return ++post;
    }
    
    @Override
    public int clasifiSubTra(ArrayList data, int post) {
        boolean ban=true;
      for(int i=post;i<data.size()&&ban==true&&post+1<data.size();i++){
          post=runData(data,post);
          if(val==1114117){
              post=cargaImpedancia(data,++post);
          }else{
              if(val==150281){
                  ban=false;
              }
          }
      }  
      return post;
    }

    public int getImpedancia() {
        return impedancia;
    }

    public void setImpedancia(int impedancia) {
        this.impedancia = impedancia;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
    
    
    
    
    
    
    
    
}
