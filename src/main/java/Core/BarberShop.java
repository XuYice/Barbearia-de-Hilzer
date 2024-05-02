package Core;

import Personas.Barber;
import Personas.Custumer;
import Resources.Chair;
import Resources.Sofa;
import java.util.ArrayList;

public class BarberShop {
    Sofa sofa;
    ArrayList<Chair> chairList;
    ArrayList<Barber> barberList;
    ArrayList<Custumer> custumerList;
    boolean POS;
    int maxTotalSeats = 20;


    public synchronized boolean avaliableSpace(){
        int size = getTotalCustumers();

        if (size < getMaxTotalSeats()) {
            return true;
        }else{
            return false;
        }
        
    }

    public int getMaxTotalSeats() {
        return maxTotalSeats;
    }

    public synchronized int getTotalCustumers(){
        ArrayList<Custumer> cl = getCustumerList();     
        return cl.size();
    }

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
    
    public synchronized void Payment(BarberShop bS, Barber b, Custumer c){

        /* Talvez migrar para dentro da tread do Barber */
        if (bS.getPOS() && b.getSleeping()) {
            bS.setPOS(false);
            b.setRecivingPayment(true);

            c.ConfirmPayment(c);
            b.setRecivingPayment(false);
            bS.setPOS(true);

            System.out.println("OK payment");
            
        }else{
            System.out.println("POS not available");
        }

    }
    
    public void Verify(){
    }
}
