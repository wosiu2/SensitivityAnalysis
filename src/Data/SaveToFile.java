/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Włosek
 */
public class SaveToFile {
    
    /**
     *
     * @param filePath Ścieżka do pliku w którym zostanie zapisany ciąg znaków
     * @param str Ciąg znaków do zapisania w pliku
     * @param append Parametr określający tryb zamisu. True - dopisywanie, False - nadpisywanie
     * @param newLine Parametr określajacy czy zapis będzie poprzedzony przejsciem do następnej lini
     * @return
     */
    public int stringToFile(String filePath,String str,Boolean append,Boolean newLine){
        
        try{
        File file=new File(filePath);
        
        if(!file.exists())
        {
            file.createNewFile();
        }
        
            FileWriter fw=new FileWriter(file.getAbsoluteFile(),append);
            BufferedWriter bw=new BufferedWriter(fw);
            if(newLine==true){
                bw.newLine();
            }
            
            bw.write(str);
            
            bw.close();
            System.out.println("Saved");
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Błąd podczas zapisywania pliku");
            System.exit(1);
        }
        
        return 0;
    } 
    
    public int saveList(BOQ list,String filePath){
        int i=0;
        for (Iterator<ComponentOfBOQ> iterator = list.getBillOfQuantity().iterator(); iterator.hasNext();) {
            ComponentOfBOQ next = iterator.next();
            
           String str=next.getName()+"::"+next.getUnit()+"::"+next.getRate()+"::"+next.getQuantity()+"::"+next.getOverhead();
            
            if(i==0){
                stringToFile(filePath, str, false, false);
            }
            else{
                stringToFile(filePath, str, true, true);
            }
            i=1;
        }
        
        return 0;
    }
    
    
}
