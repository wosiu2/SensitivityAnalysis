/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.EmptyStringException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Włosek
 */
public class OpenFile {
DecimalFormat df = new DecimalFormat("#0.00");

    public int generateTableData(BOQ list,DefaultTableModel model){
    
       
       list.generateSR();
       
        for (Iterator<ComponentOfBOQ> iterator = list.getBillOfQuantity().iterator(); iterator.hasNext();) {
            ComponentOfBOQ next = iterator.next();
            System.out.println("111");
            model.addRow(new Object[]{next.getName(),next.getUnit(),df.format(next.getRate()),df.format(next.getQuantity()),next.getOverhead(),df.format(next.getAmount()),df.format(next.getSensitivityIndex())});
            //model.addRow(new Object[]{"1","2","3","1","2","3"});
            
        }
        
        return 0;
        
}
    /**
     *
     * @param filePath
     * @param list
     * @return
     */
    public int openFromFile(String filePath, BOQ list) {
        FileReader fr = null;
        String line = "";
        String[] tempTable=new String[5];

        //Otwieranie pliku
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Błąd przy otwieraniu pliku\n\n Error:" + e);
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(fr);
        //Odczyt kolejnych lini
        try {
            while ((line = br.readLine()) != null) {
                
                tempTable=refactorLine(line);
                list.getBillOfQuantity().add(new ComponentOfBOQ(tempTable[0], tempTable[1],Double.parseDouble(tempTable[2]), Double.parseDouble(tempTable[3]),Integer.parseInt(tempTable[4])));
               //System.out.println(tempTable[0]);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Błąd odczytu pliku\n\nError: " + e);
            System.exit(2);
        }catch(EmptyStringException e){
            
            JOptionPane.showMessageDialog(null, "Błąd odczytu lini\n\nError: "+e);
            System.exit(4);
        }

        //zamykanie pliku
        try {
            fr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Błąd przy zamykaniu pliku\n\nError: " + e);
        }
        return 0;

    }

    /**
     *
     * @param line ciag znaków który ma uledz podziałowi
     * @return zwraca tablicę 4 elementową z parametrami do wprowadzenia
     * @throws EmptyStringException
     */
    private String[] refactorLine(String line) throws EmptyStringException {
        String[] tableOfParameters = new String[5];
        String buffer = new String();
        int startIndex = 0;
        int stopIndex = 0;
        int parametersCounter = 0;

        if (line == null) {

            throw new EmptyStringException();

        }

        for (int i = 0; i < line.length(); i++) {

            if (checkSeparator(line, i)) {

                tableOfParameters[parametersCounter] = line.substring(startIndex, stopIndex);
                startIndex = i + 2;
                parametersCounter++;
            }

            stopIndex++;

            if (i == line.length() - 1) {
                tableOfParameters[parametersCounter] = line.substring(startIndex, stopIndex);
            }

        }

        return tableOfParameters;
    }

    /**
     *
     * @param line ciąg znaków w którym należy zbadać wystąpienie znaku podziału
     * "::"
     * @param index indeks odnalezionego znaku ":"
     * @return Zwraca wartość logiczną zależnie od tego czy wykryto znak
     * podziału
     */
    private boolean checkSeparator(String line, int index) {
        boolean test;

        if (line.length() < index + 2) {

            return false;
        }
        test = line.substring(index, index + 2).equals("::");

        // System.out.println(line.substring(index, index+2));
        return test;
    }

    /**
     *
     * @param args
     */
   /* public static void main(String[] args) {

        String[] tab = new String[4];
        OpenFile op = new OpenFile();
        String filePath = "D:/file.txt";
        try {
            tab = op.refactorLine("abc::bcd::rty::aaa");
        } catch (EmptyStringException e) {
            System.out.println(op.checkSeparator("::", 1));
        }
       
        op.openFromFile(filePath,new BOQ());
    }*/

}
