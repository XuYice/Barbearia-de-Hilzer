package Core;

import Personas.Barber;
import Personas.Custumer;
import Resources.Chair;
import Resources.Sofa;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author joils
 */
public class BarberShop {
    Sofa sofa;
    ArrayList<Chair> chairList;
    ArrayList<Barber> barberList;
    ArrayList<Custumer> custumerList;
    boolean POS;

    public ArrayList<Chair> getChairList() {
        return chairList;
    }

    public void setChairList(ArrayList<Chair> chairList) {
        this.chairList = chairList;
    }

    public ArrayList<Barber> getBarberList() {
        return this.barberList;
    }

    public void setBarberList(ArrayList<Barber> barberList) {
        this.barberList = barberList;
    }

    public ArrayList<Custumer> getCustumerList() {
        return custumerList;
    }

    public void setCustumerList(ArrayList<Custumer> custumerList) {
        this.custumerList = custumerList;
    }

    public boolean getPOS() {
        return POS;
    }

    public void setPOS(boolean pOS) {
        POS = pOS;
    }

    public void Relocate(){
    }
    
    public void Payment(){
    }
    
    public void Verify(){
    }
    
    public static void main(String[] args) {

        BarberShop bS = new BarberShop();
        ArrayList<Barber> barberList = new ArrayList<Barber>();
        bS.setBarberList(barberList);
        ArrayList<Chair> chairList = new ArrayList<Chair>();
        bS.setChairList(chairList);
                
        Barber b0 = new Barber("thBarber0");
        barberList.add(b0);
        Barber b1 = new Barber("thBarber1");
        barberList.add(b1);
        Barber b2 = new Barber("thBarber2");
        barberList.add(b2);
        

        Chair c0 = new Chair();
        chairList.add(c0);
        Chair c1 = new Chair();
        chairList.add(c1);
        Chair c2 = new Chair();
        chairList.add(c2);
        
        System.out.println("Hello World!");
    }
}
