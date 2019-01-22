/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import javax.swing.JSpinner;

/**
 *
 * @author Włosek
 */
public class ComponentOfBOQ {

    private double rate, quantity, amount, incAmount, sensitivityIndex = 0;
    private String unit, name;
    private int overhead;

    public ComponentOfBOQ() {
        this.rate = 0;
        this.quantity = 0;
        this.amount = 0;
        this.incAmount = 0;
        this.unit = "";
        this.name = "";
        this.overhead = 0;
    }

    public ComponentOfBOQ(double rate, double quantity, double amount, double incAmount, String unit, String name) {
        this.rate = rate;
        this.quantity = quantity;
        this.amount = amount;
        this.incAmount = incAmount;
        this.unit = unit;
        this.name = name;
        this.overhead = 0;
    }

    /**
     *
     * @param name Nazwa pozycji obmiarowej
     */
    public ComponentOfBOQ(String name) {
        this.name = name;
        this.overhead = 0;
    }

    /**
     *
     * @param name Nazwa pozycji obmiarowej
     * @param unit Jednostka obmiarowa
     */
    public ComponentOfBOQ(String name, String unit) {
        this(name);
        this.unit = unit;
        this.overhead = 0;
    }

    /**
     *
     * @param name Nazwa pozycji obmiarowej
     * @param unit Jednostka obmiarowa
     * @param rate Cena jednostkowa
     * @param quantity Ilość jednostek obmiarowych
     */
    public ComponentOfBOQ(String name, String unit, double rate, double quantity) {
        this(name, unit);
        this.rate = rate;
        this.quantity = quantity;
        this.overhead = 0;
    }

    public ComponentOfBOQ(String name, String unit, double rate, double quantity, int overhead) {
        this(name, unit);
        this.rate = rate;
        this.quantity = quantity;
        this.overhead = overhead;
    }

    /**
     * @return Zwraca wartość stawki jednostkowej
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate wartość ceny jednostkowej
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return Zwraca ilość danej jednoski obmiarowej
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity ilość jednostek obmiarowych
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return Zwraca wartość robót
     */
    public double getAmount() {
        amount = rate * quantity * (1 + overhead / 100.0);
        return amount;
    }

    public double getNetAmount() {
        
        return rate * quantity ;
    }

    /**
     * @return Zwraca w jednostkę obmiarową
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit jednostka obmiarowa
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return Zwraca nazwę robót objętych jednostką obmiarową
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Nazwa robót objętych jednostką obmiarową
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Zwraca wartosć zwięszkoną o 1%
     */
    public double getIncAmount() {
        incAmount = 0.01 * getAmount();
        return incAmount;
    }

    /**
     * @param totalAmount Wartość całkowita
     * @return Zwraca wskaźnik wrażliwości
     */
    public double getSensitivityIndex(double totalAmount) {
        double top, bottom;

        top = ((totalAmount + getIncAmount()) - totalAmount) / totalAmount;
        bottom = (1.01 - 1) / 1;

        setSensitivityIndex(top / bottom);
        return sensitivityIndex;
    }

    public double getSensitivityIndex() {

        return sensitivityIndex;
    }

    /**
     * @param sensitivityIndex the sensitivityIndex to set
     */
    public void setSensitivityIndex(double sensitivityIndex) {
        this.sensitivityIndex = sensitivityIndex;
    }

    /**
     * @return the overhead
     */
    public int getOverhead() {
        return overhead;
    }

    /**
     * @param overhead the overhead to set
     */
    public void setOverhead(int overhead) {
        this.overhead = overhead;
    }

}
