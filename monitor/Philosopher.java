/******************************************************************************
 * Philosopher.java
 *
 * Class that emulates a philosopher at dinner
 *
 * AUTHOR:  David N Robinson
 * DATE:    03/28/2013
 */

public class Philosopher {
    String state;

    public Philosopher() {
        state = "THINKING";
    }//Philosopher

    public Philosopher(String state) {
        this.state = state;
    }//Philosopher

    public String getState() {
        return state;
    }//getState

    public void setState(String state) {
        this.state = state;
    }//setState

}//Philosopher
