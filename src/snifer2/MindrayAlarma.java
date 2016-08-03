/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author ELECTRONICA
 */
public class MindrayAlarma implements Trama{
    
    private Header header;
    private ArrayList<SubTramaAlarma> subTra;
    private LocalDate fecha;
    private LocalTime hora;
    private String fuente;
    private int tip;
    private int tam1;

    public MindrayAlarma() {
        subTra=new ArrayList<SubTramaAlarma>();
    }

    public MindrayAlarma(Header header, ArrayList<SubTramaAlarma> subTra) {
        this.header = header;
        this.subTra = subTra;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ArrayList<SubTramaAlarma> getSubTra() {
        return subTra;
    }

    public void setSubTra(ArrayList<SubTramaAlarma> subTra) {
        this.subTra = subTra;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getTam1() {
        return tam1;
    }

    public void setTam1(int tam1) {
        this.tam1 = tam1;
    }
    
    
    public int contarAsteris(String mensaje){
    int cont=0;
    for(int i=0;i<mensaje.length();i++){
            if(mensaje.charAt(i)=='*'){
              cont++;  
            }
        }
    return cont;
    }
    
    
    
    
    
    @Override
    public int clasifydata(ArrayList data, int post) {
        int pos=0;
        if(tip==1){
         pos=header.findStartAlarma1(post, data) ;
        }else if(tip==2){
            pos=header.findStarAlarma2(post, data);
        }
        if(pos!=-1){
            pos++;
        pos=header.Findsize(pos, data);
        pos=pos+2;
        pos=header.FindHi_zero(pos, data);
        pos=header.FindCrc(pos, data);
        ++pos;
        pos=header.Findlow_zer(pos, data);
        //header.printHeader();
        int tam=header.sizePacket();
        tam1=header.cantSize();
        do{
            System.out.println(tam+" antes esto es tam1 "+tam1);
        pos=cargarSubTram(data, pos);
         System.out.println(tam+"  despues esto es tam1 "+tam1);
        }while(tam1<tam);
        System.out.println("Salio1/*-");
        tam1=0;
        }
        return 0;
    }

    @Override
    public int cargarSubTram(ArrayList data, int pos) {
        byte start[]=new byte[2];
        byte end[]=new byte[3];
        byte size[]=new byte[2];
        byte cant[]=new byte[2];
        byte tama[]=new byte[2];
        SubTramaAlarma sba=new SubTramaAlarma(cant,tama,start,size,end,new ArrayList());
        pos=sba.findStart(pos, data);
        pos=sba.findEnd(pos, data);
        pos=sba.findSize(pos, data);
        sba.prinTSubHead();
        int tam=sba.sizePSubtram();
        switch(sba.joinHeader()){
        case("71936"):
            //System.out.println("entro12121212");
            pos=sba.findCant(pos,data);
            if(sba.canSubtra()>0){
            pos=sba.findTama(pos, data);
            //System.out.println(pos);
            //sba.prinCantTam();
            sba.cargaMensaje(pos, data,sba.canSubtra());
           // if(sba.canSubtra()>1){
            // tam=tam-(sba.canSubtra()*2);
            //}else{
                //if(sba.canSubtra()==1){
                    //tam=tam-4;
              //  }
            //}
                System.out.println(tam);
            pos=sba.addData(pos-4, tam, data);
            /**
           if(sba.canSubtra()==1){
                    tam1+=tam+sba.sizeSub();
                }else{
           tam1+=tam+sba.sizeSub();
           }
           **/
            System.out.println("tma 1:"+sba.tamSubtra()+"pos "+pos);
            System.out.printf("0x%02X",(byte)data.get(pos));
                System.out.println("+---------");
            tam1+=tam+sba.sizeSub();
           }else{
           tam1+=tam+sba.sizeSub();
           }
            //cargar mensajes
            break;
            
        case("96512"):
            System.out.println("entro343434");
           pos=sba.findCant(pos,data);
           System.out.println(sba.canSubtra()+"CANTIADDAD");
           if(sba.canSubtra()>0){
           pos=sba.findTama(pos, data);
           sba.cargaMensaje(pos, data, sba.canSubtra());
           /**
           if(sba.canSubtra()>1){
            tam=tam-(sba.canSubtra()*2);
            }else{
                if(sba.canSubtra()==1){
                    tam=tam-4;
                }
            }
           if(sba.canSubtra()==1){
                    tam1+=tam+4+sba.sizeSub();
                }else{
           tam1+=tam+sba.sizeSub();
           }
           **/
           pos=sba.addData(pos-4, tam, data);
           System.out.println("tma 2:"+sba.tamSubtra()+"pos "+pos);
           System.out.printf("0x%02X",(byte)data.get(pos));
           System.out.println("-------"+(char)data.get(pos));
           tam1+=tam+sba.sizeSub();
            }else{
           tam1+=tam+sba.sizeSub();
           }
           //si tiene y e
            //cargar mensajes
            break;
                
        default:
        System.out.println("tamaño _:"+tam);
        pos=sba.addData(pos, tam, data);
        System.out.println("h-->"+data.get(pos));
        tam1+=tam+sba.sizeSub();
        break;
        }
        subTra.add(sba);
        return ++pos;
    }
    
}
