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
    }

    public void Arrive() {

        if (bS.avaliableSpace()) {

        } else {
            System.out.println("No space avaliable, custumer leaves");
            setExit(true);
        }

    }

    public void ConfirmPayment(Custumer c) {
        if (!c.getPayment()) {
            c.setPayment(true);

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

        }
    }
}
