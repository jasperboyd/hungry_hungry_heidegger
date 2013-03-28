/**
 * DiningPhilosophers.monitor 
 *
 * A monitor solution to the dining philosophers problem.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

import java.util.concurrent.*;

monitor DiningPhilosophers
{
    enum State {THINKING, HUNGRY, EATING};

    State[] states = new State[5];
    Condition[] self = new Condition[5];

    public DiningPhilosophers {
        for (int i = 0; i < 5; i++)
            state[i] = State.THINKING;
    }//DiningPhilosophers

    public void takeChopsticks (int i) {
        state[i] = State.HUNGRY;
        test(i);
        if (state[i] != State.EATING)
            self[i].wait();
    }//takeChopsticks

    public void returnChopsticks (int i) {
        state[i] = State.THINKING;
        // test left and right neighbors
        test(i + 4 % 5);
        test(i + 1 % 5);
    }

    private test(int i) {
        if ( (state[i + 4 % 5) != State.EATING) && (state[i] == State.HUNGRY) 
                && (state[i + 1 % 5 != State.EATING) ) {
            state[i] = State.EATING;
            self[i].signal();
        }//if
    }//test
}//DiningPhilosophers
