package diningphilosophers;

import java.util.Scanner;

/**
 * DiningPhilosophers.java
 *
 * @author Jasper Boyd and David Robinson
 */
public class DiningPhilosophers {

    /* setTable ( ): 
     * 
     * Private helper method that setus up the array of philosophers, 
     * and the forks between them. 
     */
    private static Philosopher[] setTable(boolean monitor) {
        
        Fork forks[] = new Fork[5];
        
        for (int i = 0; i < 5; i++) {
            //if sem_mode init as such
            forks[i] = new Fork();
        }//for 
        
        Philosopher p1, p2, p3, p4, p5; 
        
        if (!monitor) {
            p1 = new Philosopher("Rene Descartes", forks[0], forks[1]);
            p2 = new Philosopher("J. J. Rousseau", forks[1], forks[2]);
            p3 = new Philosopher("John Locke", forks[2], forks[3]);
            p4 = new Philosopher("Immanuel Kant", forks[3], forks[4]);
            p5 = new Philosopher("Desmond Hume", forks[4], forks[0]);
        } else {
            TableMonitor tm = new TableMonitor(5); 
            p1 = new Philosopher("Rene Descartes", forks[0], forks[1], tm, 0);
            p2 = new Philosopher("J. J. Rousseau", forks[1], forks[2], tm, 1);
            p3 = new Philosopher("John Locke", forks[2], forks[3], tm, 2);
            p4 = new Philosopher("Immanuel Kant", forks[3], forks[4], tm, 3);
            p5 = new Philosopher("Desmond Hume", forks[4], forks[0], tm, 4);
        }
        Philosopher philosophers[] = {p1, p2, p3, p4, p5};
        return philosophers;
    }//setTable ( ) 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        System.out.println("Type 1: for Monitor mode, or Type 2 for Semaphore Mode");

        Scanner s = new Scanner(System.in);

        int choice = s.nextInt();
        boolean mode;
        
        if (choice == 1){
            mode = true; 
        } else { 
            mode = false; 
        }//if/else
        
        Philosopher p[] = setTable(mode);

        for (int i = 0; i < p.length; i++) {
            p[i].start();
        }//for
        
    }//main ( )
}//DiningPhilosophers