/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BD.ECG_JDBC;
import java.sql.Time;
import java.util.ArrayList;
import org.jnetpcap.PcapIf;
import snifer2.CapturaRed;
import snifer2.MindrayAlarma;
import snifer2.MindrayPacket;
import snifer2.MindrayParametros;
import snifer2.Trama;

/**
 *
 * @author ELECTRONICA
 */
public class ControladorCapRed {
    
    private static CapturaRed capr;
    private ArrayList<String> Ips;
    private ArrayList<String> dispostiRed;
    private static ECG_JDBC ecg_db;


    public ControladorCapRed(){
        capr=new CapturaRed();
        ecg_db=new ECG_JDBC();
        //capr.obteneDispo();
        //capr.listarDispositivos();
        //capr.start();
    }

    public CapturaRed getCapr() {
        return capr;
    }

    public void setCapr(CapturaRed capr) {
        this.capr = capr;
    }

    public ArrayList<String> getIps() {
        return Ips;
    }

    public void setIps(ArrayList<String> Ips) {
        this.Ips = Ips;
    }

    
    
    public void cargarDispositivos(){
        Ips=capr.ipDisposit();
    }
    
    public String dispositivo(int indice){
        if(Ips!=null){
            return Ips.get(indice);
        }else{
        return null;
        }
    }
    
    public synchronized static Trama Rpacket(){
        /**
        ArrayList<Trama> trm=(ArrayList<Trama>) capr.returnPack().clone();
        if(trm!=null){
            System.out.println("tama ANTES"+trm.size());
         capr.getPackets().clear();
         System.out.println("tama despues"+trm.size());
        }
        **/
    return capr.returnPack();
    }
    
    public ControladorCapRed(CapturaRed capr, ArrayList<String> Ips) {
        this.capr = capr;
        this.Ips = Ips;
    }
    
    public void iniciarCapt(){
    capr.obteneDispo();
    capr.listarDispositivos();
    dispoTRed();
    }
    public void dispoTRed(){
        this.dispostiRed=capr.dispositivosDeRed();
    }

    public ArrayList<String> getDispostiRed() {
        return dispostiRed;
    }

    public void setDispostiRed(ArrayList<String> dispostiRed) {
        this.dispostiRed = dispostiRed;
    }
    
    public PcapIf buscaDisp(String descrip){
    return capr.buscaDisp(descrip);
    }
    
    public void capturararDe(PcapIf dispo){
        capr.setDispositivo(dispo);
        capr.start();
    }
    
    public static void adicionarPacket(Trama mp){
        capr.insertaPaquete(mp);
    }
    
    public void insertarDataBase(int ID_Indicador,float aVR,float aVL,int Frec_Cardi,float I,float II,float III,float V, byte[] ECG1,byte[] ECG2,byte[] ECG3,float Voltaje_aVR,float Voltaje_aVL){
    ecg_db.insertarSeñal(ID_Indicador, aVR, aVL, Frec_Cardi, I, II, III, V, ECG1, ECG2, ECG3, Voltaje_aVR, Voltaje_aVL);
    }
    
    
    public static void inserBaseDat(int ide,byte[] ECG1,byte[] ECG2,byte[] ECG3,Time hora){
    ecg_db.insertarSe(ide, ECG1, ECG2, ECG3,hora);
    }
    
    
  
    
    public synchronized static boolean isIp(String ip){
        boolean ban=false;
     Trama packet=capr.packetValida();
     if(packet!=null){
     switch(packet.getClass().getSimpleName()){
                                           case("MindrayAlarma"):
                                               //System.out.println(packet.getClass().getSimpleName()+"1");
                                               if(ip.equals(((MindrayAlarma)packet).getFuente())){
                                               ban=true;
                                               }
                                            break;
                                               
                                            case("MindrayPacket"):
                                                   //System.out.println(packet.getClass().getSimpleName()+"2");
                                                    if(ip.equals(((MindrayPacket)packet).getFuente())){
                                                    ban=true;
                                                    }
                                            break;
                                                
                                                
                                            case("MindrayParametros"):
                                                //System.out.println(packet.getClass().getSimpleName()+"3");
                                                if(ip.equals(((MindrayParametros)packet).getFuente())){
                                                    ban=true;
                                                    }
                                            break;
                                           
                                           }
     }else{
           System.out.println("destino null");  
             }
     return ban;
    }
}

