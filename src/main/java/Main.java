import Core.BarberShop;
import Personas.Barber;
import Personas.Custumer;
import Resources.Chair;
import java.util.ArrayList;

public class Main {
    private static final Object lock = new Object();


    public static Custumer generateClient(String n,BarberShop b){

        Custumer newCustumer = new Custumer(n, b);
        return newCustumer;     
    }

     public static void main(String[] args) {

        int maxClients = 10;
        int clients = 0;

        BarberShop bS = new BarberShop();
        ArrayList<Barber> barberList = new ArrayList<Barber>();
        bS.setBarberList(barberList);
        ArrayList<Chair> chairList = new ArrayList<Chair>();
        bS.setChairList(chairList);
        ArrayList<Custumer> custumerList = new ArrayList<Custumer>();
        bS.setCustumerList(custumerList);

        Barber b0 = new Barber("thBarber0");
        barberList.add(b0);
        Barber b1 = new Barber("thBarber1");
        barberList.add(b1);
        Barber b2 = new Barber("thBarber2");
        barberList.add(b2);

        Chair c0 = new Chair();
        chairList.add(c0);
        Chair c1 = new Chair();
        chairList.add(c1);
        Chair c2 = new Chair();
        chairList.add(c2);

        b0.start();
        b1.start();
        b2.start();
        
        System.out.println("Clientes:" + clients);
        
        synchronized (lock) {
            while (clients <= maxClients) {

            Custumer newCustumer = generateClient(Integer.toString(clients), bS);
            System.out.println(clients);
            newCustumer.start();
            
            try {newCustumer.join();} catch (InterruptedException e) {e.printStackTrace();}
                            clients++;  
            }
        }

        b0.setExit(true);
        b1.setExit(true);
        b2.setExit(true);
    }
}