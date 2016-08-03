/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author ELECTRONICA
 */
public class ECG_JDBC {
    private final String INSERTASQL="INSERT INTO ECG (ID_Indicador,aVR,aVL,Frec_Cardi,I,II,III,V,ECG1,ECG2,ECG3,Voltaje_aVR,Voltaje_aVL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String InsertSQL="INSERT INTO ECG (ID_Indicador,ECG1,ECG2,ECG3,HoraSenal) values (?,?,?,?,?)";
    
   
   public int insertarSeñal(int ID_Indicador,float aVR,float aVL,int Frec_Cardi,float I,float II,float III,float V, byte[] ECG1,byte[] ECG2,byte[] ECG3,float Voltaje_aVR,float Voltaje_aVL){
       Connection cos=null;
       PreparedStatement stat=null;
       int rowns=0;
       try{
       cos=Conecxion.getConecxion();
       stat=cos.prepareStatement(INSERTASQL);
       int index=1;
       stat.setInt(index++, ID_Indicador);
       stat.setFloat(index++, aVR);
       stat.setFloat(index++, aVL);
       stat.setInt(index++, Frec_Cardi);
       stat.setFloat(index++, I);
       stat.setFloat(index++, II);
       stat.setFloat(index++, III);
       stat.setFloat(index++, V);
       stat.setBytes(index++, ECG1);
       stat.setBytes(index++, ECG2);
       stat.setBytes(index++, ECG3);
       stat.setFloat(index++, Voltaje_aVR);
       stat.setFloat(index++, Voltaje_aVL);
       rowns=stat.executeUpdate();
       }catch(SQLException ex){
           ex.printStackTrace();
       }finally{
           Conecxion.close(cos);
           Conecxion.close(stat);
           return rowns;
       }
   }
   
   public int insertarSe(int ide,byte[] ECG1,byte[] ECG2,byte[] ECG3,Time hora){
       Connection co=null;
       PreparedStatement stat=null;
       int rowns=0;
       try{
            System.out.println("conecto*-*-*/*/**746464-/-*/-/-*/-");
       co=(Connection)Conecxion.getConecxion();
       stat=co.prepareStatement(InsertSQL);
       int index=1;
       stat.setInt(index++, ide);
       stat.setBytes(index++,ECG1);
       stat.setBytes(index++, ECG2);
       stat.setBytes(index++, ECG3);
       stat.setTime(index++, hora);
       rowns=stat.executeUpdate();
       }catch(SQLException ex){
       ex.printStackTrace();
       }
       finally{
       Conecxion.close(co);
       Conecxion.close(stat);
               return rowns;
       }
   }
    
    
}
