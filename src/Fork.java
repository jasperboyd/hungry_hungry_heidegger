package diningphilosophers;
import java.util.concurrent.Semaphore;

/** Fork.java
 *
 * @author Jasper Boyd and David Robinson
 */
public class Fork {
    
    private boolean free, sem_mode; 
    private Semaphore bin_sem; 

    // Fork ( )
    /* Calling the constructor with no arguments will construct a fork
     * that uses a simple boolean to determine whether it is in use or
     * not.
     */
    Fork ( ) { 
        sem_mode = false; 
        free = true; 
    }//Fork ( )
    
    // Fork ( Thread u )
    /* Calling the constructor with a boolean will indicate weather a
     * binary semaphore will be used to provide mutual exclusion. 
     */
    Fork ( boolean s_m ){
        sem_mode = s_m;
        if (sem_mode){
             bin_sem = new Semaphore(1); 
        }
    }
    
    boolean free ( ) { 
       return free; 
    }//free ( )
    
    void pickup ( ) throws InterruptedException { 
       if (sem_mode) { 
          sem_pickup(); 
       } else { 
          free = false; 
       }
    }//pickup ( ) 
    
    void sem_pickup ( ) throws InterruptedException {
        this.bin_sem.acquire();  
        free = false; 
    }//sem_pickup ( )
    
    void putdown ( ) throws InterruptedException {
        if (sem_mode){
            sem_putdown( );
        } else {
            free = true; 
        }
    }//putdown ( )
    
    void sem_putdown ( ) throws InterruptedException { 
        this.bin_sem.release(); 
        free = true;  
    }//sem_putdown ( )
    
}
