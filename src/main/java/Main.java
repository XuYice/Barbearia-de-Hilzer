
import Core.BarberShop;
import Personas.Barber;
import Personas.Custumer;
import java.util.ArrayList;

public class Main {

    private static final Object lock = new Object();

    public static Custumer generateClient(String n, BarberShop b) {

        Custumer newCustumer = new Custumer(n, b);
        return newCustumer;
    }

    public static void main(String[] args) throws InterruptedException {

        int maxClients = 20;
        int clients = 0;

        BarberShop bS = new BarberShop();
        ArrayList<Barber> barberList = new ArrayList<Barber>();
        bS.setBarberList(barberList);
        ArrayList<Custumer> chairList = new ArrayList<Custumer>();
        bS.setChairList(chairList);
        ArrayList<Custumer> custumerList = new ArrayList<Custumer>();
        bS.setCustumerList(custumerList);

        Barber b0 = new Barber("thBarber0", bS);
        barberList.add(b0);
        Barber b1 = new Barber("thBarber1", bS);
        barberList.add(b1);
        Barber b2 = new Barber("thBarber2", bS);
        barberList.add(b2);

        b0.start();
        b1.start();
        b2.start();
        
        b0.join();
        b1.join();
        b2.join();

        synchronized (lock) {
            while (clients <= maxClients) {
                Custumer newCustumer = generateClient(Integer.toString(clients), bS);
                newCustumer.start();

                try {
                    newCustumer.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clients++;
            }
        }

        b0.setExit(true);
        b1.setExit(true);
        b2.setExit(true);
    }
}
