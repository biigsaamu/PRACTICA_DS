package basenostates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


public class Clock extends Observable {
  /*Singleton pattern applied. The class has only a uniqueClock (static) with a private constructor.
  Furthermore has a lazy initialization with public method getInstance(). So we can declare a Clock wherever other class we want
  In this case it is located in UnlockedShortly*/
  public static final int CLOCK_PERIOD = 1;
  private Timer timer;
  private int period;

  private static Clock uniqueClock = null;

  // Constructor
  private Clock() {
    this.period = CLOCK_PERIOD;
    timer = new Timer();
    TimerTask repeatedTask = new TimerTask() {
      public void run() { //Starts running the timer
        LocalDateTime date = LocalDateTime.now();
        setChanged(); //Compulsory if we want the observers be notified
        if (countObservers() > 0){ //If there are observers notify them. If not, no.
          notifyObservers(date); //calls all the observers update
          System.out.println("run() executed at " + date);
        }
      }
    };
    //Repeats a task each period times. In this case every second, So observers are notified avery second
    timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * period);
  }

  public static Clock getInstance(){
    if (uniqueClock == null){ //lazy initialization
      uniqueClock = new Clock();
    }
    return uniqueClock;
  }


  public void stop() {
    timer.cancel();
  }

  public int getPeriod() {
    return period;
  }
}
