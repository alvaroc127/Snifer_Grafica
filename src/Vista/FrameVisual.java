/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;



import Controlador.ControladorCapRed;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import snifer2.MindrayPacket;
import snifer2.MindrayParametros;
import snifer2.SubTramaArt_AP;
import snifer2.SubTramaECG;
import snifer2.SubTramaImpedancia;
import snifer2.SubTramaSpo2;
import snifer2.SubtRamTemp;
import snifer2.Trama;

/**
 *
 * @author ELECTRONICA
 */
public class FrameVisual extends JFrame implements Runnable,ActionListener{
    private final static int COLUMNAS =1;
    private final static int FILAS=8;
    private String ip;
    private ArrayList<PanelVisual> panels;
    private JButton btn;
    private ControladorCapRed cpr;
    
 

    public FrameVisual(String ip){
        this.ip=ip;
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       getContentPane().setLayout(new GridLayout(FILAS, COLUMNAS));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        panels=new ArrayList();
        for(int i=0;i<FILAS-1;i++){
          PanelVisual pa=new PanelVisual(i);
          pa.setVisible(false);
          panels.add(pa);
          getContentPane().add(pa);
        }
        btn=new JButton("Salida");
        btn.addActionListener(this);
        getContentPane().add(btn);
    }
    
    public void ClasifiData(MindrayPacket mp){
        final String h1="4353";
        final String h2="8449";
        final String h3="28929";
        final String h4="1705985";
        final String h5="102657";
        final String h6="2033669";
        final String h7="2099205";
        final String h8="475251";
        final String h9="73746";
        for(int i=0;i<mp.getSubtramas().size();i++){
            //System.out.println("\n valor subtrama :"+mp.getSubtramas().get(i).joinheader());
            switch(mp.getSubtramas().get(i).joinHeader()){
                case(h1):
                  panels.get(0).loadGrafic(mp.getSubtramas().get(i).getData());
                  panels.get(0).setVisible(true);
                  //ControladorCapRed.inserBaseDat(001,mp.getSubtramas().get(i).combertByte(), mp.getSubtramas().get(i+1).combertByte(), mp.getSubtramas().get(i+2).combertByte());
                break;
                        
                case(h2):
                    panels.get(1).loadGrafic(mp.getSubtramas().get(i).getData());
                    panels.get(1).setVisible(true);
                break;
                
                case (h3):
                    panels.get(2).loadGrafic(mp.getSubtramas().get(i).getData());    
                    panels.get(2).setVisible(true);
                break;
                    
                    
                case(h4):
                    panels.get(3).loadGrafic(mp.getSubtramas().get(i).getData());
                    panels.get(3).setVisible(true);
                break;
                    
                case(h5):
                    panels.get(4).loadGrafic(mp.getSubtramas().get(i).getData());
                    panels.get(4).setVisible(true);
                break;
            
                case(h6):
                    panels.get(5).loadGrafic(mp.getSubtramas().get(i).getData());
                  panels.get(5).setVisible(true);
                break;
                    
                case(h7):
                    panels.get(6).loadGrafic(mp.getSubtramas().get(i).getData());
                    panels.get(6).setVisible(true);
                break;
                      
                   default: 
                    break;
            }
    }
 } 

    
    public void clasifiParame(MindrayParametros mp){
     if(mp.getSubtramaParam()!=null){
    switch(mp.getSubtramaParam().getClass().getSimpleName()){
                case("SubTramaECG"):
                panels.get(0).cargaFrecuen(((SubTramaECG)mp.getSubtramaParam()).getFrecuencia());
                panels.get(0).cargarMatriz(((SubTramaECG)mp.getSubtramaParam()).getI(), ((SubTramaECG)mp.getSubtramaParam()).getII(),((SubTramaECG)mp.getSubtramaParam()).getIII(),((SubTramaECG)mp.getSubtramaParam()).getaVR(),((SubTramaECG)mp.getSubtramaParam()).getaVL(),((SubTramaECG)mp.getSubtramaParam()).getaVF(),((SubTramaECG)mp.getSubtramaParam()).getV(),((SubTramaECG)mp.getSubtramaParam()).getCVP());
                break;
                    
                case("SubTramaImpedancia"):
                panels.get(3).cargaImpe(((SubTramaImpedancia)mp.getSubtramaParam()).getImpedancia());
                break;
        
                case("SubtRamTemp"):
               
                break;
                  
                case("SubTramaSpo2"):
                  panels.get(4).cargarMay(((SubTramaSpo2)mp.getSubtramaParam()).getDato1());
                  panels.get(4).cargaFrecuen(((SubTramaSpo2)mp.getSubtramaParam()).getFrecuencia());
                break;    
                
                    
               case("SubTramaArt_AP"):
                   if(((SubTramaArt_AP)mp.getSubtramaParam()).isBand()){
                        panels.get(5).cargaAltoBajoParen(((SubTramaArt_AP)mp.getSubtramaParam()).getAlto(),((SubTramaArt_AP)mp.getSubtramaParam()).getParentesis(),((SubTramaArt_AP)mp.getSubtramaParam()).getBajo());
                   }else{
                  panels.get(6).cargaAltoBajoParen(((SubTramaArt_AP)mp.getSubtramaParam()).getAlto(),((SubTramaArt_AP)mp.getSubtramaParam()).getParentesis(),((SubTramaArt_AP)mp.getSubtramaParam()).getBajo());
                   }
                break;
                   
                   
                case("SubTramaNos"):
                    
                break;
        }
     }
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ArrayList<PanelVisual> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<PanelVisual> panels) {
        this.panels = panels;
    }

    public ControladorCapRed getCpr() {
        return cpr;
    }

    public void setCpr(ControladorCapRed cpr) {
        this.cpr = cpr;
    }
    
    @Override
    public void run() {
        do{
            //if(cpr!=null && ip!=null){
            //if(cpr.isIp(ip)){
               Trama mp=ControladorCapRed.Rpacket();
                if(mp!=null){
                // for(int i=0;i<tram.size();i++){
                //validar que tram diferente de NULL
            //System.out.println("inicio el hilo");
            //Trama mp= tram.get(i);//ControladorCapRed.Rpacket();
            if(mp!=null){
            if(mp.getClass().getSimpleName().equalsIgnoreCase("MindrayPacket")){
                //System.out.println("pertenece");
                //System.out.println(mp.getFuente());
                //System.out.println(ip+"la ip");
               ClasifiData((MindrayPacket)mp);
            }else if(mp.getClass().getSimpleName().equalsIgnoreCase("MindrayParametros")){
                //System.out.println("pertenece1123443");
                //System.out.println(mp.getFuente());
                //System.out.println(ip+"la ip");
                clasifiParame((MindrayParametros)mp);
                }
              //}
            }
           }else{
                    System.out.println("----tram---"+mp);
                }
          //}else{
            //System.out.println(" NO"   + ")(()()(Entro/*-**/--*/");
            //}
         // }else{
              //  System.out.println("cpr o ip son null/*/*/*/*/*/*/");
               // System.out.println(cpr+"%%&%&%&%&%&%&%&%&% esto es Ip "+ip);
           // }
        }while(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
            this.setVisible(false);
        }
    }
}
