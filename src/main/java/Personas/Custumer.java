package Personas;

public class Custumer extends Thread {

    boolean payment;


    public Custumer(String threadName) {
        super(threadName);
    }
    
    public void Leave(){
    }
    
    public void ConfirmPayment(Custumer c){
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
