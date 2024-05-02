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
        synchronized (chairList) {
            return chairList;
        }
    }

    public void setChairList(ArrayList<Custumer> chairList) {
        synchronized (chairList) {
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
        sofa.custumerList.trimToSize();

        while ((sofa.custumerList.size() < 4) && (!custumerList.isEmpty())) {
            sofa.custumerList.add(custumerList.get(0));
            custumerList.remove(0);

            custumerList.trimToSize();
        }

        System.out.println("Cadeiras: " + chairList.size() + " Sofa: " + sofa.custumerList.size() + "| Espera em pe: " + custumerList.size());
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

    public synchronized void NextCustumer(Barber b) {
        getChairList().add(sofa.custumerList.get(0));
        sofa.custumerList.remove(0);
    }

    public void Verify() {
    }
}
