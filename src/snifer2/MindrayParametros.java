/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author ELECTRONICA
 */
public class MindrayParametros implements Trama{
    
    private String fuente;
    private HeaderIn cabeza;
    private SubTraMetod SubtramaParam;
    private LocalDate fecha;
    private LocalTime hora;
    

    public MindrayParametros(Header cabeza, SubTraMetod SubTramaParam) {
        this.cabeza = cabeza;
        this.SubtramaParam = SubTramaParam;
        
    }

    public MindrayParametros(HeaderIn cabeza, SubTraMetod SubTramaParam) {
        this.cabeza = cabeza;
        this.SubtramaParam = SubTramaParam;
    }

    public MindrayParametros(String fuente, HeaderIn cabeza, SubTraMetod SubTramaParam) {
        this.fuente = fuente;
        this.cabeza = cabeza;
        this.SubtramaParam = SubTramaParam;
    }
    
    
   

    public MindrayParametros() 
    {
    
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }    
    
    public HeaderIn getCabeza() {
        return cabeza;
    }

    public void setCabeza(HeaderIn cabeza) {
        this.cabeza = cabeza;
    }

    public SubTraMetod getSubtramaParam() {
        return SubtramaParam;
    }

    public void setSubtramaParam(SubTraMetod SubtramaParam) {
        this.SubtramaParam = SubtramaParam;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    
    
    
    @Override
    public int clasifydata(ArrayList data, int pos) {
        int post=((Header)cabeza).findStartParam(pos,data);
        if(post!=-1){
            post++;
            post=((Header)cabeza).Findsize(post, data);
            post=post+2;
             post=((Header)cabeza).FindHi_zero(post, data);
                post=((Header)cabeza).FindCrc(post, data);
                ++post;
                post=((Header)cabeza).Findlow_zer(post, data);
                post=((Header)cabeza).FindCode1(post, data);
                post=((Header)cabeza).FindConst1(post, data);
                //post=((Header)cabeza).FindCode2(post, data);
                //((Header)cabeza).printHeader();
                byte in[]=new byte[3];
                byte ta[]=new byte[2];
                byte fin[]=new byte[5];
                SubTramaParam sin=new SubTramaParam(in,ta,fin);
                post=sin.findStart(++post, data);
                post=sin.findSize(post, data);
                post=sin.findEnd(post, data);
                //System.out.println("-----");
                //sin.prinTSubHead();
               //System.out.println("-----");
                SubtramaParam=sin.selecciTram(codeType(post, data)); 
                //debe dar a post su  respoectivo valor
                if(SubtramaParam!=null){
                post=cargarSubTram(data, post);
                }
                //System.out.println("finall");
        }
        return post;
    }

    
    
    
    
    /**
     * cargara la subtrama con base en la subtrama que se cree
     * @param data
     * @param pos
     * @return 
     */
    @Override
    public int cargarSubTram(ArrayList data, int pos) {
        //System.out.println(SubtramaParam.getClass().getSimpleName());
        switch(SubtramaParam.getClass().getSimpleName()){
            //cargar a post con su respectivo valor
            case("SubTramaECG"):
                pos=((SubTramaECG)SubtramaParam).clasifiSubTra(data, pos);
                break;
                case("SubTramaImpedancia"):
                pos=((SubTramaImpedancia)SubtramaParam).clasifiSubTra(data, pos);
                break;
        
                case("SubtRamTemp"):
                pos=((SubtRamTemp)SubtramaParam).clasifiSubTra(data, pos);
                break;
                  
                case("SubTramaSpo2"):
                pos=((SubTramaSpo2)SubtramaParam).clasifiSubTra(data, pos);
                break;    
                
                    
               case("SubTramaArt_AP"):
                pos=((SubTramaArt_AP)SubtramaParam).clasifiSubTra(data, pos);
                break;
                   
                   
                case("SubTramaNos"):
                    pos=((SubTramaNos)SubtramaParam).clasifiSubTra(data, pos);
                    break;
               default:
                   System.out.println("NO SE ASIGNO NADA");
                   break;
        
        }
        return pos;
    }
    
    
    
    /**
     * 
     * 
     * @param pos
     * @param data
     * @return 
     */
    public int codeType(int pos,ArrayList data){
        String var=null;
        var=String.format("%02X", (byte)data.get(pos));
        var=var.concat(String.format("%02X", (byte)data.get(++pos)));
        var=var.concat(String.format("%02X", (byte)data.get(++pos)));
        //System.out.println("#$$$$%%%$$########"+var);
        return Integer.parseInt(var,16);
    }
    
    
    public void printDate(){
        System.out.println(hora.toString());
        System.out.println(fecha.toString());
    }
    
}
