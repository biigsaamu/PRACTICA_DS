package basenostates;

import java.util.Observer;
import java.time.LocalDateTime;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UnlockedShortly extends DoorState implements Observer{
  /*DoorState type where a User can do all actions except itself (depending on the privileges).
  * Furthermore, this state only can have a duration of 10 seconds. After unlock shortly a door for ten seconds,
  * if it is still opened it must change of state to Propped. If it is closed, or it is closed before the 10 seconds
  * the State changes to Lock.
  *
  * We know UnlockedShortly is an Observer of Clock (Observable). This one notifies every ten seconds the
  * DoorsState*/

  Logger logger = LoggerFactory.getLogger("basenostates.DoorState.UnlockedShortly");
  private final LocalDateTime dateBeforeObserving;

  public static final int OBSERVING_TIME = 10;
  public UnlockedShortly(Door door){
    super(door);
    name = "unlocked_shortly";
    dateBeforeObserving = LocalDateTime.now();
    /*The parameter saves the exact previous time before observing the Clock. To compare then the date
    when being notified*/
    Clock.getInstance().addObserver(this);
    //Declare an instance of Clock to declare UnlockedShortly DoorState an Observer of Clock (Observable)
  }
  public String getName(){return name;}
  public void open(){ //User opens a door and sets boolean attribute closed to true *
    if (door.isClosed()) {
      door.setClosed(false);
      //System.out.println(door.getStateName()); //Shows the door state
      logger.debug("Door " + door.getId() + " successfully opened while UnlockedShortly");
    } else{
      logger.warn("Door " + door.getId() + " already opened");
    }
  }
  public void close(){ //User closes a door and sets boolean attribute closed to false *
    if (!door.isClosed()) {
      door.setClosed(true);
      //System.out.println(door.getStateName());
      logger.debug("Door " + door.getId() + " successfully closed while UnlockedShortly");
    }else {
      logger.warn("Door " + door.getId() + " already closed");
    }
  }
  public void lock(){
    if (door.isClosed()){ //User tries to lock a closed door */
      door.setState(new Locked(door));
      //System.out.println(door.getStateName());
      logger.debug("Door " + door.getId() + " successfully locked while UnlockedShortly");
    }else { //User tries to lock an opened door *
      logger.warn("Door " + door.getId() + " is opened. Close first to lock it");
    }
  }
  public void unlock(){ //User tries to unlock the door *
    door.setState(new Unlocked(door));
    //System.out.println(door.getStateName());
    logger.warn("Door " + door.getId() + " already unlocked_shortly");
  }
  public void unlock_shortly(){
    logger.warn("Door " + door.getId() + " already UnlockedShortly");
  }

  @Override
  public void update(java.util.Observable o, Object arg) {
    /*NOTE (implementation suggestion) --> Slightly change after Fita 1 session, as know if the door is closed before arriving to 10 seconds it must change
    automatically of state. We check it with the first conditional of the method*/

    /*UnlockedShortly (Observer) updates the state because gets Clock (Observable) notification*/
    LocalDateTime dateTime = (LocalDateTime) arg;

    /*Subscripts the dateBeforeObserving (saved just before the UnlockedShortly state starts to observe the Clock)
    to the dateTime send by the Observable (Clock)*/
    Duration timeBetweenDatesTime = Duration.between(dateBeforeObserving, dateTime);

    if (door.isClosed()){
      logger.info("This door " + door.getId() + " has been closed after being unlocked_shortly. Change state to Locked");
      door.setState(new Locked(door));
      Clock.getInstance().deleteObserver(this); //Removes the observer from the Observable (Clock) observer's list
    }else if (timeBetweenDatesTime.getSeconds() >= OBSERVING_TIME){
      /*Checks if the time between dates is equal or more than OBSERVING_TIME (10s).
      If it is the case, the state (UnlockedShortly) has to analyze the door characteristics and change
      to the appropriate state (Locked or Propped)*/
      logger.warn("Has passed 10 or more seconds between " + dateBeforeObserving.toLocalTime() + " and " + dateTime.toLocalTime());
      if (door.isClosed()) {
        logger.info("This door " + door.getId() + " is closed after being unlocked_shortly. Change state to Locked");
        door.setState(new Locked(door));
      } else {
        logger.warn("This door " + door.getId() + " is still open after being unlocked_shortly. Change state to Propped");
        door.setState(new Propped(door));
      }
      //System.out.println(Clock.getInstance().countObservers());
      Clock.getInstance().deleteObserver(this);
      logger.info(this.name + " state from " + door.getId() + " removed as an observer");
      //System.out.println(Clock.getInstance().countObservers());
    }
  }
}
