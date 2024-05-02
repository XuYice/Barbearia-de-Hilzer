package Core;

import Personas.Barber;
import Personas.Custumer;
import Resources.Sofa;
import java.util.ArrayList;

public class BarberShop {
    Sofa sofa = new Sofa();
    ArrayList<Custumer> chairList;
    ArrayList<Barber> barberList;
    ArrayList<Custumer> custumerList;
    public static boolean POS;
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
        
        return cl.size() + sofa.custumerList.size() + chairList.size();
    }

    public ArrayList<Custumer> getChairList() {
        return chairList;
    }

    public void setChairList(ArrayList<Custumer> chairList) {
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

    public synchronized static boolean getPOS() {
        return POS;
    }

    public synchronized static void setPOS(boolean pOS) {
        POS = pOS;
    }

    public synchronized void Relocate(){
        sofa.custumerList.trimToSize();
        
        while((sofa.custumerList.size() < 4) && (!custumerList.isEmpty())){
            sofa.custumerList.add(custumerList.get(0));
            custumerList.remove(0);
            
            custumerList.trimToSize();
        }
        
        System.out.println("Cadeiras: " + chairList.size() + " Sofa: " + sofa.custumerList.size() + "| Espera em pe: " + custumerList.size());
    }

    public synchronized void enter(Custumer c){
        custumerList.add(c);
    }
    
    public synchronized void Payment(Barber b, Custumer c){

        /*/* Talvez migrar para dentro da tread do Barber 
        if (this.getPOS() && b.getSleeping()) {
            this.setPOS(false);
            b.setRecivingPayment(true);

            c.ConfirmPayment(c);
            b.setRecivingPayment(false);
            this.setPOS(true);

            System.out.println("OK payment");
        }else{
            System.out.println("POS not available");
        }*/

    }
    
    public void Verify(){
    }
}
