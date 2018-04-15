// Insert header here
package assignment6;

import java.util.*;
import java.lang.Thread;

public class BookingClient {
    /*
     * @param office maps box office id to number of customers in line
     * @param theater the theater where the show is playing
     */
    public static void main(String[] args) {
        System.out.println("hellowworld");
        Map<String, Integer> office = new HashMap<String, Integer>();
        office.put("BX1", 15);
        office.put("BX2", 15);
        BookingClient bc = new BookingClient(office, new Theater(10, 1, "A6 Movie"));
        bc.simulate();
    }

    private Map<String, Integer> office;
    private Theater theater;
    private static int totalclients=1;
    private boolean theaterfull=false;

    public BookingClient(Map<String, Integer> office, Theater theater) {
        // TODO: Implement this constructor
        this.office = office;
        this.theater = theater;
    }

    /*
     * Starts the box office simulation by creating (and starting) threads
     * for each box office to sell tickets for the given theater
     *
     * @return list of threads used in the simulation,
     *         should have as many threads as there are box offices
     */
    public List<Thread> simulate() {
        //TODO: Implement this method
        List<Thread> threads = new ArrayList<>();
        //create new thread for each boxoffice
        for (String key : this.office.keySet()) {
            int customersperoffice = this.office.get(key);
            BoxOfficePerThread bopt = new BoxOfficePerThread(key, customersperoffice, theater);
            Thread thread = new Thread(bopt);
            threads.add(thread);
            //totalclients++;
            thread.start();

        }

        return threads;
    }

    class BoxOfficePerThread implements Runnable {
        private String boxofficename;
        private int numcustomers;
        private Theater theater;

        BoxOfficePerThread(String boxofficename, int numcustomers, Theater theater) {
            this.boxofficename = boxofficename;
            this.numcustomers = numcustomers;//customers in this office
            this.theater = theater;
        }

        @Override
        public  void run() {
            for (int i = 1; i <= numcustomers; i++) {
                Theater.Seat st = theater.bestAvailableSeat();
                if (st != null) {
                    //totalclients++;
                    theater.printTicket(boxofficename, st, totalclients++);
                }
                else {
                    System.out.println("Sorry, we are sold out!");
                    return;
                }
            }
        }
    }
}

