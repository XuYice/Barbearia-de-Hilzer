package Personas;

import Core.BarberShop;

public class Barber extends Thread {

    boolean Working;
    boolean Sleeping;
    boolean RecivingPayment;
    boolean exit;

    public Barber(String threadName) {
        super(threadName);
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean getWorking() {
        return Working;
    }

    public void setWorking(boolean working) {
        Working = working;
    }

    public boolean getSleeping() {
        return Sleeping;
    }

    public void setSleeping(boolean sleeping) {
        Sleeping = sleeping;
    }

    public boolean getRecivingPayment() {
        return RecivingPayment;
    }

    public void setRecivingPayment(boolean recivingPayment) {
        RecivingPayment = recivingPayment;
    }

    public void ChangeStatus() {

        if ((getWorking() == true) && (BarberShop.POS == false)) {
            setRecivingPayment(true);
        } else if ((getWorking() == true) && (BarberShop.POS == true)) {
            setSleeping(true);
        } else if ((getSleeping() == true) && (BarberShop.POS == false)) {
            BarberShop.POS = true;
            setRecivingPayment(true);
            BarberShop.Payment(this);
        } else if ((getSleeping() == true) && (BarberShop.POS == true)) {
            setSleeping(true);
        } else if (getRecivingPayment() == true) {
            BarberShop.POS = false;
            setWorking(true);
        }
    }

    public void run() {
        while (!exit) {
            while (Working || RecivingPayment) {
                try {

                    int sleepTime;

                    do {
                        sleepTime = ((int) (Math.random() * 5000));
                    } while (sleepTime < 1000);

                    Thread.sleep(sleepTime);

                    /* Executar a��o */
                } catch (Exception e) {
                }
            }
        }

    }
}
