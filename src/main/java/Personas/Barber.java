package Personas;

public class Barber extends Thread {

    boolean Working;
    boolean Sleeping;
    boolean RecivingPayment;

    public Barber(String threadName) {
        super(threadName);
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

    public boolean GetStatus() {
        return true;
    }

    public boolean SetStatus() {
        return true;
    }

    public void run() {

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
