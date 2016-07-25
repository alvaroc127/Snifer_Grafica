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
public class SubTramaParam implements SubTrama{

    private byte start[];
    private byte size[];
    private byte end[];
    private ArrayList datas;

    public SubTramaParam() {
    }

    public SubTramaParam(byte[] start, byte[] size, byte[] end) {
        this.start = start;
        this.size = size;
        this.end = end;
    }

    public SubTramaParam(byte[] start, byte[] size, byte[] end, ArrayList datas) {
        this.start = start;
        this.size = size;
        this.end = end;
        this.datas = datas;
    }
    
    public SubTramaParam(ArrayList datas) {
        this.datas=datas;
    }

    public byte[] getStart() {
        return start;
    }

    public void setStart(byte[] start) {
        this.start = start;
    }

    public byte[] getSize() {
        return size;
    }

    public void setSize(byte[] size) {
        this.size = size;
    }

    public byte[] getEnd() {
        return end;
    }

    public void setEnd(byte[] end) {
        this.end = end;
    }

    public ArrayList getDatas() {
        return datas;
    }

    public void setDatas(ArrayList datas) {
        this.datas = datas;
    }
    
    
    public int findStart(int post,ArrayList datas){
       for(int j=0;j<start.length;j++){
           start[j]=(byte) datas.get(post);
           post++;
       } 
       return post++;
    }
    
    public int findSize(int post, ArrayList datas){
        size[0]=(byte)datas.get(post);
        size[1]=(byte)datas.get(++post);
        return ++post;
    }
    
     public int findEnd(int post,ArrayList datas){
       for(int j=0;j<end.length;j++){
           end[j]=(byte) datas.get(post);
           post++;
       } 
       return post;
    }
    
    public SubTraMetod selecciTram(int val){
        SubTraMetod retor=null;
        switch(val){
            case 65541:
                retor=new SubTramaECG();
                ((SubTramaECG)retor).setStart(start);
                ((SubTramaECG)retor).setSize(size);
                ((SubTramaECG)retor).setEnd(end);
                //System.out.println("ESTA EN ECG");
                break;
                
            case 1114117:
                retor=new SubTramaImpedancia();
                ((SubTramaImpedancia)retor).setStart(start);
                ((SubTramaImpedancia)retor).setSize(size);
                ((SubTramaImpedancia)retor).setEnd(end);
                //System.out.println("ESTA EN impedancia");
                break;
                
            case 2294026:
                retor=new SubtRamTemp();
                ((SubtRamTemp)retor).setStart(start);
                ((SubtRamTemp)retor).setSize(size);
                ((SubtRamTemp)retor).setEnd(end);
               // System.out.println("ESTA EN Temp");
                break;
                
            case 1179653:
                retor=new SubTramaSpo2();
                ((SubTramaSpo2)retor).setStart(start);
                ((SubTramaSpo2)retor).setSize(size);
                ((SubTramaSpo2)retor).setEnd(end);
                 // System.out.println("ESTA EN Temp");
                break;
                
            case 13107205:
                retor=new SubTramaNos();
                ((SubTramaNos)retor).setStart(start);
                ((SubTramaNos)retor).setSize(size);
                ((SubTramaNos)retor).setEnd(end);
                //System.out.println("ESTA EN nos");
                break;
                
            case  8454149:
                retor=new SubTramaArt_AP();
                ((SubTramaArt_AP)retor).setStart(start);
                ((SubTramaArt_AP)retor).setSize(size);
                ((SubTramaArt_AP)retor).setEnd(end);
                //System.out.println("ESTA EN ART");
                break;
                
            case 8650757:
                retor=new SubTramaArt_AP();
                ((SubTramaArt_AP)retor).setStart(start);
                ((SubTramaArt_AP)retor).setSize(size);
                ((SubTramaArt_AP)retor).setEnd(end);
                //System.out.println("ESTA EN AP");
                break;
                
               default:
                  // System.out.println("NADAN DAD");
                  break;
                
        }
    
    return retor;
    }
    
    
    public void mostrarStart(){
        System.out.println("");
        for(int i=0;i<start.length;i++){
        System.out.printf("0x%02X",start[i]);
        }
        System.out.println("*/*/*/*/*/*/*");
    }
    
    public void mostrarSize(){;
        System.out.printf("0x%02X",size[0]);
        System.out.printf("0x%02X",size[1]);
    }
    public void mostrarEnd(){
        System.out.println("");
        for(int i=0;i<end.length;i++){
        System.out.printf("0x%02X",end[i]);
        }
        System.out.println("*/*/*/*/*/*/*");
    }
    
    
    public void printData(){
        for(int j=0;j<datas.size();j++){
           System.out.printf("0x%02X",datas.get(j));
        }
        System.out.println("--------------");
    }
    
    
    
     
   
    
    public void prinTSubHead(){
    mostrarStart();
    mostrarSize();
    mostrarEnd();
    }
    
     @Override
    public String joinHeader() {
        String cad=new String();
        for(int i=0;i<start.length;i++){
            cad=cad.concat(String.format("%01X",start[i]));
        }
        for(int i=0;i<end.length;i++){
            cad=cad.concat(String.format("%01X",end[i]));
        }
        return String.valueOf(Integer.parseInt(cad, 16));
    }

    @Override
    public int sizePSubtram() {
         String var=new String();
         for(int i=0;i<size.length;i++){
        var=var.concat(String.format("%02X",size[i]));
         }
        return Integer.parseInt(var, 16);
    }

    /**
     * 
     * retorna la cantidad de bytes a los cuales corresponde a los bytes que
     * componen el inico de la subtrama
     * @return un entero
     */
    @Override
    public int sizeSub() {
        return start.length+end.length+size.length;
    }
}


