package Personas;

import Core.BarberShop;

public class Custumer extends Thread {

    boolean payment;
    BarberShop bS;

    public Custumer(String threadName, BarberShop b) {
        super(threadName);
        this.bS = b;
    }

    public void Leave() {
    }

    public void Arrive(){
        
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

    }
}
