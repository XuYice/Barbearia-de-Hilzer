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
        }
    }

    public void run() {
        while (!exit) {

            if (bS.getTotalCustumers() > 0) {

                while (Working || RecivingPayment) {
                    try {

                        int sleepTime;

                        do {
                            sleepTime = ((int) (Math.random() * 2000));
                        } while (sleepTime < 1000);

                        Thread.sleep(sleepTime);

                    } catch (Exception e) {
                    }
                }

            } else {
                System.out.println(super.getName() + " esta dormindo");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                };
            }
        }
    }
}
