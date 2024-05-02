package Personas;

import Core.BarberShop;

public class Custumer extends Thread {

    boolean payment;
    BarberShop bS;
    boolean exit;

    public Custumer(String threadName, BarberShop b) {
        super(threadName);
        this.bS = b;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void Leave() {
        this.interrupt();
    }

    public void Arrive() {

        if (bS.avaliableSpace()) {
            bS.enter(this);
            bS.Relocate();
            System.out.println("New custumer has arrive");
        } else {
            System.out.println("No space avaliable, custumer leaves");
            setExit(true);
        }
    }

    public void ConfirmPayment(Custumer c) {
        if (!c.getPayment()) {
            c.setPayment(true);
            this.payment = payment;
            /*  System.out.println(); para sinalizar pagamento*/
        }
    }

    public boolean getPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public void run() {
        while (!exit) {
            int sleepTime;

            do {
                sleepTime = ((int) (Math.random() * 3000));
            } while (sleepTime < 1000);

            try {
                super.sleep(sleepTime);
            } catch (Exception e) {
            }

            Arrive();
        }
    }
}
