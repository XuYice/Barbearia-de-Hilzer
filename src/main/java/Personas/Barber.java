package Personas;

/**
 *
 * @author joilson
 */
public class Barber extends Thread {

    boolean Working;
    boolean Sleeping;
    boolean RecivingPayment;

    public Barber(String threadName) {
        super(threadName);
    }

    public boolean GetStatus() {
        return true;
    }

    public boolean SetStatus() {
        return true;
    }

    public void run() {
        
    }
}
