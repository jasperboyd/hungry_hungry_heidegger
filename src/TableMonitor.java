package diningphilosophers;
import java.util.concurrent.Semaphore; 

/**
 * TableMonitor
 *
 * this is the monitor solution to the dining philosopher's problem, based off
 * the books pseudocode.
 * 
 * Semaphores are also used 
 *
 * @author Jasper Boyd & David Robinson
 */
public class TableMonitor {

    private static final int THINK = 0;
    private static final int STARVE = 0;
    private static final int EAT = 0;
    private static final State THINKING = new State(THINK);
    private static final State HUNGRY = new State(STARVE);
    private static final State EATING = new State(EAT);
    private State[] states;
    private Semaphore[] self;
    private int size;

    TableMonitor(int tableSize) {

        this.size = tableSize;
        self = new Semaphore[tableSize];
        states = new State[tableSize];

        for (int i = 0; i < size; i++) {
            states[i] = THINKING;
            self[i] = new Semaphore(1);
        }
    }//tablemonitor

    synchronized void pickup(int i) throws InterruptedException {
        System.out.print("Picking up: " + i); 
        states[i] = HUNGRY;
        test(i);
        if (states[i] != EATING) {
            self[i].acquire();
        }
    }//pickup

    synchronized void putdown(int i) throws InterruptedException {
        System.out.print("Putting Down: " + i); 
        states[i] = THINKING;
        test((i + size - 1) % size);
        test((i + 1) % size);
    }//putdown

    void test(int i) {
        if ((states[(i + size - 1) % size] != EATING) &&
                 (states[i] == HUNGRY) &&
                 (states[(i + 1) % size] != EATING)){ 
			
            states[i] = new State(EAT);
            self[i].release(); 
        }
    }
}
