/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Włosek
 */
public class BOQ {

    private LinkedList<ComponentOfBOQ> billOfQuantity = new LinkedList();
    private double totalAmount;
    private ComponentOfBOQ maxElementBySR = new ComponentOfBOQ();

    /**
     * @return Zwraca listę pozycji obmiarowych
     */
    public LinkedList<ComponentOfBOQ> getBillOfQuantity() {
        return billOfQuantity;
    }

    /**
     * @return Zwraca całkowitą wartość robót
     */
    public double getTotalAmount() {
        totalAmount = 0;
        for (Iterator<ComponentOfBOQ> iterator = billOfQuantity.iterator(); iterator.hasNext();) {
            ComponentOfBOQ next = iterator.next();

            totalAmount += next.getAmount();
        }

        return totalAmount;
    }

    /**
     *
     * Metoda generuje wskaźniki wrażliwości dla kolejnych pozycji obmiarowych.
     *
     */
    public void generateSR() {
        getTotalAmount();
        maxElementBySR.setSensitivityIndex(0);
        for (Iterator<ComponentOfBOQ> iterator = billOfQuantity.iterator(); iterator.hasNext();) {
            ComponentOfBOQ next = iterator.next();

            next.getSensitivityIndex(totalAmount);
            if (getMaxElementBySR().getSensitivityIndex() < next.getSensitivityIndex(totalAmount)) {
                maxElementBySR = next;
            }

            //System.out.println(next.getSensitivityIndex(totalAmount));
        }
    }

    public void clearBOQ() {
        billOfQuantity.removeAll(billOfQuantity);
    }

    /**
     * @return the maxElementBySR
     */
    public ComponentOfBOQ getMaxElementBySR() {
        return maxElementBySR;
    }

    public void refreshMax() {

        {
            for (Iterator<ComponentOfBOQ> iterator = billOfQuantity.iterator(); iterator.hasNext();) {
                ComponentOfBOQ next = iterator.next();

                next.getSensitivityIndex(totalAmount);
                if (getMaxElementBySR().getSensitivityIndex() < next.getSensitivityIndex(totalAmount)) {
                    maxElementBySR = next;
                }
            }
        }
    }

    public void deleteElement(int index) {
        //System.out.println("jestem w delete");
        billOfQuantity.remove(index);
        refreshMax();
    }

    public int editList(JTable table, int col, int row) {

        System.out.println(col + "..........." + row);

        switch (col) {
            case 0:
                billOfQuantity.get(row).setName(String.valueOf(table.getModel().getValueAt(row, col)));
                break;
            case 1:
                billOfQuantity.get(row).setUnit(String.valueOf(table.getModel().getValueAt(row, col)));
                break;
            case 2:
                billOfQuantity.get(row).setRate(Double.parseDouble(String.valueOf(table.getModel().getValueAt(row, col))));
                break;
            case 3:
                billOfQuantity.get(row).setQuantity(Double.parseDouble(String.valueOf(table.getModel().getValueAt(row, col))));
                break;

            case 4:
                billOfQuantity.get(row).setOverhead(Integer.parseInt(String.valueOf(table.getModel().getValueAt(row, col))));
                break;
        }

        return 0;
    }

    public double getNetTotalAmount() {
        double netTotalAmount = 0;
        for (Iterator<ComponentOfBOQ> iterator = billOfQuantity.iterator(); iterator.hasNext();) {
            ComponentOfBOQ next = iterator.next();

            netTotalAmount += next.getNetAmount();
        }
        return netTotalAmount;
    }
    
    public double getGlobalOverhead(){
        
       return 100*(getTotalAmount()-getNetTotalAmount())/getNetTotalAmount(); 
    }
    
    public double getGlobalMargin(){
        
        return 100*(getTotalAmount()-getNetTotalAmount())/getTotalAmount();
    }
}
