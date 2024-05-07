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
    public boolean POS;
    int maxTotalSeats = 20;

    public synchronized boolean avaliableSpace() {
        int size = getTotalCustumers();

        if (size < getMaxTotalSeats()) {
            return true;
        } else {
            return false;
        }
    }

    public int getMaxTotalSeats() {
        return maxTotalSeats;
    }

    public synchronized int getTotalCustumers() {
        ArrayList<Custumer> cl = getCustumerList();

        return cl.size() + sofa.custumerList.size() + chairList.size();
    }

    public ArrayList<Custumer> getChairList() {
        synchronized (this) {
            return chairList;
        }
    }

    public void setChairList(ArrayList<Custumer> chairList) {
        synchronized (this) {
            this.chairList = chairList;
        }
    }

    public ArrayList<Barber> getBarberList() {
        synchronized (barberList) {
            return this.barberList;
        }
    }

    public void setBarberList(ArrayList<Barber> barberList) {
        synchronized (barberList) {
            this.barberList = barberList;
        }
    }

    public ArrayList<Custumer> getCustumerList() {
        synchronized (custumerList) {
            return custumerList;
        }

    }

    public void setCustumerList(ArrayList<Custumer> custumerList) {

        synchronized (custumerList) {
            this.custumerList = custumerList;
        }
    }

    public synchronized boolean getPOS() {
        return POS;
    }

    public synchronized void setPOS(boolean pOS) {

        POS = pOS;
    }

    public synchronized void Relocate() {
        // Move clientes da lista de espera para o sofá
        while (sofa.custumerList.size() < 4 && !custumerList.isEmpty()) {
            sofa.custumerList.add(custumerList.remove(0));
        }
    
        // Atualiza a quantidade de clientes esperando de pé
        int standingCustomers = custumerList.size();
    
        // Imprime o status atual
        System.out.println("Cadeiras: " + chairList.size() + " Sofa: " + sofa.custumerList.size() + "| Espera em pe: " + standingCustomers);
    }
    

    public void enter(Custumer c) {
        synchronized (custumerList) {
            custumerList.add(c);
        }

    }

    public synchronized void Payment(Barber b) {
        Custumer c = getChairList().get(0);
        getChairList().remove(0);

        c.Leave();
    }

    public synchronized Custumer NextCustumer(Barber b) {
        if (!sofa.custumerList.isEmpty()) {
            Custumer c = sofa.custumerList.get(0);  
            getChairList().add(c);
            sofa.custumerList.remove(0);
            Relocate();
            return c;
        } else {
            return null;
        }
    }
    
    
    public synchronized void CutHair(Barber b, Custumer c){
        if (c != null) {
            b.setWorking(true);
            b.ChangeStatus();
            System.out.println("Barbeiro " + b.getName() + " corta cabelo de " + c.getName());
            Payment(b);
            c.ConfirmPayment(c); 
        } else {
            System.out.println("Nao ha clientes para cortar o cabelo neste momento.");
        }
    }
    
}
