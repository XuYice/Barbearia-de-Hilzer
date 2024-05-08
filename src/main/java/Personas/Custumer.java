package Personas;

import Core.BarberShop;

public class Custumer extends Thread {

    boolean payment;
    BarberShop bS;
    boolean exit;
    boolean assing;

    public Custumer(String threadName, BarberShop b) {
        super(threadName);
        this.bS = b;
        this.assing = false;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void Leave() {
        setExit(true);
    }

    public void Arrive() {

        if (bS.avaliableSpace()) {
            bS.enter(this);
            bS.Relocate();
        } else {
            System.out.println("Custumer - Sem espaco disponivel, cliente nao entra");
            setExit(true);
        }
    }

    public void ConfirmPayment() {
        if (!getPayment()) {
            setPayment(true);
            System.out.println("Custumer - Pagamento confirmado para o cliente " + getName());
        }
    }

    public boolean getPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public void run() {
        if(!assing){
            Arrive();
            assing = true;  
            System.out.println("Custumer - Novo cliente chegou");
        }
    }
}
