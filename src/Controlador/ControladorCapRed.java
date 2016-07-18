/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BD.ECG_JDBC;
import java.util.ArrayList;
import org.jnetpcap.PcapIf;
import snifer2.CapturaRed;
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
    private Trama packet;

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
       // ArrayList<Trama> trm=capr.returnPack();
        //if(trm!=null){
         //capr.getPackets().clear();
        //}
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
    
    
    public static void inserBaseDat(int ide,byte[] ECG1,byte[] ECG2,byte[] ECG3){
    ecg_db.insertarSe(ide, ECG1, ECG2, ECG3);
    }
    
    
    public void loadPacket(){
    packet=capr.packetValida();
    }
    
    public synchronized boolean isIp(String ip){
    loadPacket();
    if(packet!= null&&packet.getClass().getSimpleName().equals("MindrayParametros")&&ip.equals(((MindrayParametros)packet).getFuente())){
        return true;
    }else{
    return false;
     }
    }
}

