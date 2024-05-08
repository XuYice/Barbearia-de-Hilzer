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
    public String POS = "Free";
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

    public synchronized ArrayList<Custumer> getChairList() {
        return chairList;
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

    public synchronized ArrayList<Custumer> getCustumerList() {
            return custumerList;

    }

    public synchronized void setCustumerList(ArrayList<Custumer> custumerList) {
        this.custumerList = custumerList;
    }

    public synchronized String getPOS() {
        return POS;
    }

    public synchronized void setPOS(String pOS) {
        POS = pOS;
    }

    public synchronized void Relocate() {
        // Move clientes da lista de espera para o sofá
        while (sofa.custumerList.size() < 4 && !custumerList.isEmpty()) {
            sofa.custumerList.add(custumerList.remove(0));
        }

        // Atualiza a quantidade de clientes esperando de pé
        int standingCustomers = custumerList.size();
        
        sofa.custumerList.trimToSize();
        chairList.trimToSize();

        // Imprime o status atual
        System.out.println("BarberShop - Cadeiras: " + chairList.size() + " Sofa: " + sofa.custumerList.size() + "| Espera em pe: " + standingCustomers);
    }

    public synchronized void enter(Custumer c) {
        custumerList.add(c);
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
            System.out.println("BarberShop - Cliente do Sofa - " + sofa.custumerList.get(0).getName());
            sofa.custumerList.remove(0);
            Relocate();

            return c;
        } else {
            return null;
        }
    }

    public synchronized void CutHair(Barber b, Custumer c) {
        if (c != null) {
            System.out.println("BarberShop - Barbeiro " + b.getName() + " corta cabelo de " + c.getName());
        } else {
            System.out.println("BarberShop - Nao ha clientes para cortar o cabelo neste momento.");
        }
    }

}
