package diningphilosophers;

import java.util.Random;

/**
 * Philosopher.java
 *
 * @author Jasper Boyd and David Robinson
 */
public class Philosopher extends Thread {

    private static final int max_thinking_time_ms = 900;
    private static final int max_eating_time_ms = 600;
    private int number; 
    private boolean eating;
    private boolean thinking;
    private boolean waiting;
    private boolean monitor;
    private TableMonitor table;
    private Fork left;
    private Fork right;
    private String name;
    private Random r;

    //Constructor
    public Philosopher(String n, Fork l, Fork r) {
        this.startWaiting();
        this.name = n;
        this.left = l;
        this.right = r;
    }//Philosopher ( ) 

    public Philosopher(String n, Fork l, Fork r, TableMonitor tm, int num) {
        this.startWaiting();
        this.table = tm;
        this.monitor = true;
        this.name = n;
        this.left = l;
        this.right = r;
        this.number = num; 
    }//Philosopher ( ) 

    //Getter methods:
    public String name() {
        return name;
    }//name ( )

    //Methods to set the philosopher's state: 
    private void startEating() {
        eating = true;
        waiting = false;
        thinking = false;
    }//startEating ( ) 

    private void startWaiting() {
        waiting = true;
        eating = false;
        thinking = false;
    }//startWaiting ( )

    private void startThinking() {
        thinking = true;
        eating = false;
        waiting = false;
    }//startThinking ( )

    public void eat() throws InterruptedException {
        synchronized (left) {
            while (!left.free() || !right.free()) {
                try {
                    this.startWaiting();
                    left.wait();
                } catch (InterruptedException e) {
                    synchronized (right) {
                        try {
                            Thread.sleep(1);
                            if (!monitor){
                                left.pickup();
                                right.pickup();
                            } else { 
                                this.table.pickup(this.number);
                            }
                            this.startEating();
                            Thread.sleep(r.nextInt(max_thinking_time_ms));
                        } finally {
                            if (!monitor){
                                left.putdown();
                                right.putdown();
                                left.notify();
                                right.notify();
                            } else { 
                                this.table.putdown(this.number);
                            }
                        }//try / finally
                    }//synchronized ( )
                } //try / catch 
            }//while ( ) 
        }//synchronized ( )

    }//eat ( )

    public void think() throws InterruptedException {
        this.startThinking();
        Thread.sleep(r.nextInt(max_thinking_time_ms));
    }//think ( )

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(this.name + "is now eating for the " + i + "th time.");
                eat();
            } catch (InterruptedException e) {
                System.out.println(e);
            }//try / catch
        }//for
    }//run ( )
}//Philosopher 