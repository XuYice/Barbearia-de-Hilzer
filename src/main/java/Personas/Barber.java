package Personas;

import Core.BarberShop;

public class Barber extends Thread {

    BarberShop bS;
    boolean Working;
    boolean Sleeping;
    boolean RecivingPayment;

    boolean exit;
    boolean paymentPending;
    Custumer nextCustomer;

    public Barber(String threadName, BarberShop b) {
        super(threadName);
        bS = b;
        setWorking(true);
        setSleeping(false);
        setRecivingPayment(false);
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

        if ((getWorking() == true) && (bS.POS == "Free")) {
            setRecivingPayment(true);
            setSleeping(false);
            setWorking(false);
        } else if ((getWorking() == true) && (bS.POS == "Busy")) {
            setRecivingPayment(false);
            setSleeping(true);
            setWorking(false);
        } else if ((getSleeping() == true) && (bS.POS == "Free")) {
            bS.POS = "Busy";
            setRecivingPayment(true);
            setSleeping(false);
            setWorking(false);
            bS.Payment(this);
        } else if ((getSleeping() == true) && (bS.POS == "Busy")) {
            setRecivingPayment(false);
            setSleeping(true);
            setWorking(false);
        } else if (getRecivingPayment() == true) {
            bS.POS = "Free";
            setRecivingPayment(false);
            setSleeping(false);
            setWorking(true);
            bS.NextCustumer(this);
        }
    }

    public void waitTime(int time) {
        try {
            int sleepTime;
            do {
                sleepTime = ((int) (Math.random() * 2000));
            } while (sleepTime < 1000);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while ((bS.clients <= bS.maxClients) || (bS.getTotalCustumers() > 0)) {

            if (bS.getTotalCustumers() - bS.getChairList().size() > 0 && bS.getChairList().size() < 3) {

                if (getWorking()) {
                    nextCustomer = bS.NextCustumer(this);
                    if (nextCustomer != null) {
                        bS.CutHair(this, nextCustomer);
                        waitTime(2000);
                        paymentPending = true;

                        if (bS.POS == "Free") {
                            System.out.println("Barber - Entrou no If");
                            setRecivingPayment(true);
                            setSleeping(false);
                            setWorking(false);
                        } else {
                            System.out.println("Barber - Entrou no Else");
                            setRecivingPayment(false);
                            setSleeping(true);
                            setWorking(false);
                        }
                    } else {
                        System.out.println("Barber - Nao ha clientes para atender. " + super.getName());

                        setRecivingPayment(false);
                        setSleeping(true);
                        setWorking(false);
                    }

                } else if (getSleeping()) {
                    if (paymentPending && bS.POS == "Free") {
                        bS.setPOS("Busy");
                        System.out.println("Barber - " + super.getName() + " foi receber pagamento");
                        setRecivingPayment(true);
                        setSleeping(false);
                        setWorking(false);
                    } else if (paymentPending && bS.POS == "Busy") {
                        System.out.println("Barber - " + super.getName() + " POS ocupado - aguardando...");
                        setRecivingPayment(false);
                        setSleeping(true);
                        setWorking(false);
                    } else if (!paymentPending) {
                        System.out.println("Barber - " + super.getName() + " acordou");
                        setRecivingPayment(false);
                        setSleeping(false);
                        setWorking(true);
                    }
                } else if (getRecivingPayment()) {
                    waitTime(1000);
                    setRecivingPayment(false);
                    setSleeping(true);
                    setWorking(false);
                    bS.Payment(this);
                    nextCustomer.ConfirmPayment();
                    System.out.println("--------------- Payment 1 " + nextCustomer.getName());
                    nextCustomer = null;
                    bS.setPOS("Free");
                    paymentPending = false;
                }

            } else {
                if (getSleeping()) {
                    System.out.println("Barber - Nao ha clientes para atender. " + super.getName());
                    setRecivingPayment(false);
                    setSleeping(true);
                    setWorking(false);
                } else if (getRecivingPayment()) {
                    waitTime(1000);
                    setRecivingPayment(false);
                    setSleeping(true);
                    setWorking(false);
                    bS.Payment(this);
                    nextCustomer.ConfirmPayment();
                    System.out.println("--------------- Payment 2 " + nextCustomer.getName());
                    nextCustomer = null;
                    bS.setPOS("Free");
                    paymentPending = false;
                }
            }

            waitTime(2000);
        }
    }
}
