/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;



import Controlador.ControladorCapRed;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import snifer2.MindrayAlarma;
import snifer2.MindrayPacket;
import snifer2.MindrayParametros;
import snifer2.SubTramaArt_AP;
import snifer2.SubTramaECG;
import snifer2.SubTramaImpedancia;
import snifer2.SubTramaSpo2;
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
    //private ControladorCapRed cpr;
    private JPanel pan;
    private JLabel lb;
    private JLabel lb1;
    
    
 

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
         lb1=new JLabel();
         lb1.setFont(new Font("Tahoma",1,30));
        pan=new JPanel();
        lb=new JLabel();
        btn=new JButton("Salida");
        btn.addActionListener(this);
        lb.setFont(new Font("Tahoma",1,30));
        lb.setText("EMERGENCIA");
        pan.setLayout(new BorderLayout());
        pan.add(btn,BorderLayout.EAST);
        pan.add(lb,BorderLayout.SOUTH);
        pan.add(lb1,BorderLayout.NORTH);
        getContentPane().add(pan);
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
            //System.out.println("\n valor subtrama :"+mp.getSubtramas().get(i).joinHeader());
            switch(mp.getSubtramas().get(i).joinHeader()){
                case(h1):
                  panels.get(0).loadGrafic(mp.getSubtramas().get(i).getData());
                  panels.get(0).setVisible(true);
                  //System.out.println(mp.getHora());
                  //ControladorCapRed.inserBaseDat(001,mp.getSubtramas().get(j).combertByte(), mp.getSubtramas().get(j+1).combertByte(), mp.getSubtramas().get(j+2).combertByte(),Time.valueOf(mp.getHora()));
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
                    //System.out.println(((SubTramaECG)mp.getSubtramaParam()).getFrecuencia());
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


    
    @Override
    public void run() {
        do{
            if(ip!=null){
            if(ControladorCapRed.isIp(ip)){
                System.out.println("esta para graficar");
              // ArrayList<Trama> tram=ControladorCapRed.Rpacket();
               // if(tram!=null){
                    //System.out.println("tama"+tram.size());
                 //for(int i=0;i<tram.size();i++){
                      //System.out.println("inicio el hilo*/*/*/*/*/*/*/---+-+-+-+-+-+-"+tram.size());
                //validar que tram diferente de NULL
            Trama mp=ControladorCapRed.Rpacket();
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
                }else if(mp.getClass().getSimpleName().equalsIgnoreCase("MindrayAlarma")){
                    for(int j=0;j<((MindrayAlarma)mp).getSubTra().size();j++){
                        if(((MindrayAlarma)mp).getSubTra().get(j).getMensajes().isEmpty()==false){
                            cargarAlar(((MindrayAlarma)mp).getSubTra().get(j).getMensajes().get(0));
                            if(((MindrayAlarma)mp).getSubTra().get(j).getMensajes().get(0).charAt(0)=='*'&&((MindrayAlarma)mp).getSubTra().get(j).getMensajes().size()>=2){
                                lb1.setText(((MindrayAlarma)mp).getSubTra().get(j).getMensajes().get(1));
                               // System.out.println(((MindrayAlarma)mp).contarAsteris(((MindrayAlarma)mp).getSubTra().get(j).getMensajes().get(1)));
                            }
                        }
                    }
                }
              }
            // }
            //}else{
                    //System.out.println("----tram---"+mp);
                //}
              }
            }
        }while(true);
    }
        

    public void cargarAlar(String Amla){
        //llamar el metetodo dentro de la mismas clase
    lb.setText(Amla);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
            this.setVisible(false);
            }
        }
    }
