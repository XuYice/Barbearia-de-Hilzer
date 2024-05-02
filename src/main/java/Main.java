import Core.BarberShop;
import Personas.Barber;
import Personas.Custumer;
import Resources.Chair;
import java.util.ArrayList;

public class Main {


    public Custumer generateClient(String n,BarberShop b){

        Custumer newCustumer = new Custumer(n, b);
        System.out.println("New custumer has arrive");     
        return newCustumer;     
    }

     public static void main(String[] args) {

        int maxClients = 50;
        int clients = 0;

        BarberShop bS = new BarberShop();
        ArrayList<Barber> barberList = new ArrayList<Barber>();
        bS.setBarberList(barberList);
        ArrayList<Chair> chairList = new ArrayList<Chair>();
        bS.setChairList(chairList);

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

        while (clients <= maxClients) {
            
        }

        System.out.println("Hello World!");
    }
}