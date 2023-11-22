package basenostates.fita1;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Clock extends Observable {
  // Clock is an Observable object that does a repeated task periodly, notifying his observers
  // (UnlockedShortly states) every second.
  // Furthermore, Clock class implements Singleton pattern, it means that the class will only have
  // a unique instance (uniqueClock) with a private constructor.
  // Furthermore, has a lazy initialization with public method getInstance().
  // So we can declare a Clock wherever other part of the project we want.

  static final Logger clockLogger = LoggerFactory.getLogger("basenostates.fita1.Clock");
  public static final int CLOCK_PERIOD = 1;
  private final Timer timer;
  private final int period;
  private static Clock uniqueClock = null;

  private Clock() {
    this.period = CLOCK_PERIOD;
    timer = new Timer();
    TimerTask repeatedTask = new TimerTask() {
      public void run() { //Starts running the timer
        LocalDateTime date = LocalDateTime.now();
        setChanged(); //Compulsory if we want the observers be notified
        if (countObservers() > 0) {
          notifyObservers(date);
          //System.out.println("run() executed at " + date);
          clockLogger.debug("Clock executed at" + date);
        }
      }
    };
    // Repeats a task each period times. In this case every second.
    //So observers are notified by every secon

    timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * period);
  }

  public static Clock getInstance() {
    if (uniqueClock == null) { //lazy initialization
      uniqueClock = new Clock();
      clockLogger.info("uniqueCLock created");
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
