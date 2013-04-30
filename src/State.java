/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diningphilosophers;

/**
 *
 * @author jasperboyd
 */
public class State {
    
    
    private int currentState;
    
    State (int s) {
        currentState = s; 
    }
    
    public int getState () {
        return currentState; 
    }
    
    public void setState (int s) { 
        currentState = s; 
    }
    
    public void _wait ( ) { 
        
    }
    
    public void _signal ( ) {
            
    }   
}
