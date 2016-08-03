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
public class SubTramaAlarma  extends SubTramaParam {

    private byte[] cant;
    private byte[] tama;
    private ArrayList<String> mensajes;

    public SubTramaAlarma(byte[] cant, byte[] tama) {
        this.cant = cant;
        this.tama = tama;
    }
    
    
    
    public SubTramaAlarma(byte[] start, byte[] size, byte[] end) {
        super(start, size, end);
    }

    public SubTramaAlarma(ArrayList datas) {
        super(datas);
    }

    public SubTramaAlarma() {
        super();
        
    }

    public SubTramaAlarma(byte[] cant, byte[] tama, byte[] start, byte[] size, byte[] end, ArrayList datas) {
        super(start, size, end, datas);
        this.cant = cant;
        this.tama = tama;
        mensajes=new ArrayList<String>();
    }

    
    public SubTramaAlarma(byte[] start, byte[] size, byte[] end, ArrayList datas) {
        super(start, size, end, datas);
    }

    public byte[] getCant() {
        return cant;
    }

    public void setCant(byte[] cant) {
        this.cant = cant;
    }

    public byte[] getTama() {
        return tama;
    }

    public void setTama(byte[] tama) {
        this.tama = tama;
    }

    public ArrayList<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<String> mensajes) {
        this.mensajes = mensajes;
    }
    
   
    
    
    public int findCant(int post, ArrayList data){
    for(int j=0;j<cant.length;j++){
           cant[j]=(byte) data.get(post);
           post++;
       } 
       return post;
    }
    
    /**
     * 
     * 
     * @param post
     * @param data
     * @return 
     */
    public int findTama(int post, ArrayList data){
    for(int j=0;j<tama.length;j++){
           tama[j]=(byte) data.get(post);
           post++;
       } 
       return post;
    }
    
    
    /**
     * 
     * @param posI
     * @param cant
     * @param array
     * @return 
     */  
    public int addData(int posI,int cant,ArrayList array){
        int cont=0;
        for(int j=posI;j<array.size()&&cont<cant;j++){
                this.getDatas().add(array.get(j));
                System.out.printf("0x%02X",(byte)array.get(j));
               cont++;
               posI=j;
        }
        System.out.println("");
        return posI;
    }
    
    /**
     * 
     * @param pos
     * @param datas 
     */
    public int cargaMensaje(int pos,ArrayList datas,int cant){
        String mensaje="";
        char a;
        for(int i=0;i<cant;i++){ 
            //System.out.println("este es el tamaño de subtra "+tamSubtra());
            for(int j=0;j<this.tamSubtra();j++){
                a=(char)Byte.toUnsignedInt((byte)datas.get(pos));
                System.out.print(a);
                mensaje+=a;
                pos++;
            }
            System.out.println("Mensaje "+mensaje);
            if(cant>1){
                pos=findTama(pos, datas);
            }
            mensajes.add(mensaje);
            mensaje="";
        }
        return pos;
    }
    
    /**
     * 
     * @return 
     */
    public int canSubtra(){
    String var=new String();
    for(int i=0;i<cant.length;i++){
        var=var.concat(String.format("%02X",cant[i]));
    }
        //System.out.println("cantidad "+ Integer.parseInt(var, 16)+"var "+var);
    return Integer.parseInt(var, 16);
    }
    
    /**
     * Metodo que permite obtener el 
     * tamaño de la subtrama
     * @return 
     */ 
    public int tamSubtra(){
    String var=new String();
    for(int i=0;i<tama.length;i++){
        var=var.concat(String.format("%02X",tama[i]));
    }
    return Integer.parseInt(var, 16);
    }
    
    /**
     * 
     */
     public void printTam(){
        for(int j=0;j<tama.length;j++){
           System.out.printf("0x%02X",tama[j]);
        }
        System.out.println("--------------");
    }
     
     /**
      * 
      * 
      */
     public void printCant(){
        for(int j=0;j<cant.length;j++){
           System.out.printf("0x%02X",cant[j]);
        }
        System.out.println("--------------");
    }

    
     /**
      * 
      */
    public void prinCantTam(){
    printTam();
    printCant();
    }
    
    @Override
    public byte[] getEnd() {
        return super.getEnd(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getSize() {
        return super.getSize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDatas(ArrayList datas) {
        super.setDatas(datas); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEnd(byte[] end) {
        super.setEnd(end); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSize(byte[] size) {
        super.setSize(size); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStart(byte[] start) {
        super.setStart(start); //To change body of generated methods, choose Tools | Templates.
    }    
    
    @Override
    public String joinHeader() {
        return super.joinHeader(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getDatas() {
        return super.getDatas(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int sizeSub() {
        return super.sizeSub(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int sizePSubtram() {
        return super.sizePSubtram(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
