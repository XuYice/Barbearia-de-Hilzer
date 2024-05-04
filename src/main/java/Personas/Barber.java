package Personas;

import Core.BarberShop;

public class Barber extends Thread {

    BarberShop bS;
    boolean Working;
    boolean Sleeping;
    boolean RecivingPayment;
    boolean exit;

    public Barber(String threadName, BarberShop b) {
        super(threadName);
        bS = b;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean getWorking() {
        return Working;
    }

    public synchronized void setWorking(boolean working) {
        this.Working = working;
    }

    public boolean getSleeping() {
        return Sleeping;
    }

    public synchronized void setSleeping(boolean sleeping) {
        Sleeping = sleeping;
    }

    public synchronized boolean getRecivingPayment() {
        return RecivingPayment;
    }

    public synchronized void setRecivingPayment(boolean recivingPayment) {
        RecivingPayment = recivingPayment;
    }

    public synchronized void ChangeStatus() {

        if ((getWorking() == true) && (bS.POS == false)) {
            setRecivingPayment(true);
        } else if ((getWorking() == true) && (bS.POS == true)) {
            setSleeping(true);
        } else if ((getSleeping() == true) && (bS.POS == false)) {
            bS.POS = true;
            setRecivingPayment(true);
            bS.Payment(this);
        } else if ((getSleeping() == true) && (bS.POS == true)) {
            setSleeping(true);
        } else if (getRecivingPayment() == true) {
            bS.POS = false;
            setWorking(true);
            bS.NextCustumer(this);
        }
    }

    public void run() {
        while (!exit) {
            if (bS.getTotalCustumers() - bS.getChairList().size() > 0 && bS.getChairList().size() < 3) {
                setSleeping(false);
                System.out.println(super.getName() + " acordou");
    
                Custumer nextCustomer = bS.NextCustumer(this);
                if (nextCustomer != null) {
                    bS.CutHair(this, nextCustomer);
                } else {
                    System.out.println("Não há clientes para atender. " + super.getName() + " voltou a dormir.");
                    setSleeping(true);
                }

                try {
                    int sleepTime;
                    do {
                        sleepTime = ((int) (Math.random() * 2000));
                    } while (sleepTime < 1000);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
            } else {
                System.out.println(super.getName() + " está dormindo");
                setSleeping(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }    
}
