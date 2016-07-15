/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.util.ArrayList;


/**
 * this class is a references to datas fron  trama
 * @author UCDIT
 * @created 17/05/2016
 * @version 1.0
 */
public class SubtramaMi implements SubTrama{
    /**
     * this are attribs fron subtrama
     */
    private ArrayList data=new ArrayList();
    private byte endH;
    private byte size[]=new byte[2];
    private byte start;

    /**
     * this is a constructor of subtrama
     */
    public SubtramaMi(){
    }

    public ArrayList getData() {
        return data;
    }
    
    public void setData(ArrayList data) {
        this.data = data;
    }

     

    public byte getEnd() {
        return endH;
    }

    public void setEnd(byte end) {
        this.endH = end;
    }

    public byte[] getSize() {
        return size;
    }

    public void setSize(byte size[]) {
        this.size = size;
    }

    public byte getStart() {
        return start;
    }
    
    public void setStart(byte start) {
        this.start = start;
    }
    /**
     * carga el inicio de la subtrama 
     * @param pos inidca la posicion dentro de la carga util
     * @param array hace referencia al array de datos
     * @return la posicion de array tras cargar los datos
     */
    public int findstart(int pos, ArrayList array){
    start=(byte)array.get(pos);
    return ++pos;
    }
    /**
     * carga el tamaño de la subtrama
     * @param pos posicion actual en el array de datos
     * @param array hace referencia al array de datos
     * @return  la posicion del array
     */
    public int findSize(int pos,ArrayList array){
    size[0]=(byte)array.get(pos);
    size[1]=(byte)array.get(++pos);
    return ++pos;
    }
    /**
     * carga el byte final de la subtrama
     * @param pos indica la posicion dentro del array de datos
     * @param array datos de datos dentro de la trama 
     * @return la posicion final del puntero dentro de array de datis
     */
    public int findEndh(int pos,ArrayList array){   
    endH=(byte)array.get(pos);
    return ++pos;
    }
    /**
     * cargar los datos de la subtrama de inicio a fin
     * @param posI posicion inicial para cargar los datos del arrau
     * @param cant cantidad de datos que se deben cargar
     * @param array de datos.
     * @return la posicion dentro del array de datos
     */
    public int addData(int posI,int cant,ArrayList array){
        int cont=0;
        for(int j=posI;j<array.size()&&cont<cant;j++){
               data.add(array.get(j));
               cont++;
               posI=j;
        }
        return posI;
    }
    
    
    /**
     * unifica los bytes que conforman la cabeza de la subtrama y los retorna
     * como un string
     * @return  cadena combertida de byte a enteros
     */
    
    public String joinHeader(){
        String cad=new String();
        cad=String.format("%01X", start);
        cad=cad.concat(String.format("%01X",size[0]));
        cad=cad.concat(String.format("%01X",size[1]));
        cad=cad.concat(String.format("%01X",endH));
        return String.valueOf(Integer.parseInt(cad, 16));
    }
    /**
     * retorna el tamaño de la subtrama 
     * @return el tmañao que indica la subtrama
     */
  
    public int sizePSubtram(){
        String var=new String();
        var=String.format("%02X",size[0]);
        var=var.concat(String.format("%02X",size[1]));
        return Integer.parseInt(var, 16);
    }
    
    /**
     * 
     * 
     * 
     */
    public void printStart(){
    System.out.printf("0x%02X",start);
    System.out.println("--------");
    }
    
    /**
     * 
     * 
     * 
     */
    public void printSize(){
        System.out.printf("0x%02X",size[0]);
        System.out.printf("0x%02X",size[1]);
        System.out.println("----------");
    }
    
    /**
     * 
     * 
     * 
     */
    public void printEnd(){
        System.out.printf("0x%02X",endH);
        System.out.println("-------------");
    }
    
    
    /**
     * 
     * 
     */
    public void printData(){
        for(int j=0;j<data.size();j++){
           System.out.printf("0x%02X",data.get(j));
        }
        System.out.println("--------------");
    }
    
    /**
     * 
     * 
     */
    public void printSub(){
        printStart();
        printSize();
        printEnd();
        printData();
    }

    /**
     * 
     * tmaaño total de la sutrama
     * @return entero con el tamaño de la subtrama
     */
    
      public int sizeSub(){
      return size.length+2;
      }
      
      
      /**
       * 
       * 
       */
      public byte[] combertByte(){
          byte[] a=new byte[data.size()];
          for(int i=0;i<data.size();i++){
            a[i]=(byte)data.get(i);
          }
          return a;
    }
    

}
