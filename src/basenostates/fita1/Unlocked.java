package basenostates.fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Unlocked extends DoorState {
  /*Unlocked is a DoorState type where a User can do the following actions (depending on the privileges):
  * - Open
  * - Close
  * - Lock
  * A State can change of State by itself.
  * */

  Logger logger = LoggerFactory.getLogger("basenostates.fita1.DoorState.Unlocked");
  private final String name;

  public Unlocked(Door door) {
    super(door);
    name = "unlocked";
  }

  public String getName() {
    return name;
  }

  public void open() {
    //User opens a door and sets boolean attribute closed to true *
    if (door.isClosed()) {
      door.setClosed(false);
      //System.out.println(door.getStateName()); //Shows the door state
      logger.debug("Door " + door.getId() + " successfully opened");
    } else {
      logger.warn("Door " + door.getId() + " already opened");
    }
  }

  public void close() {
    //User closes a door and sets boolean attribute closed to false *
    if (!door.isClosed()) {
      door.setClosed(true);
      logger.debug("Door " + door.getId() + " successfully closed");
    } else {
      logger.debug("Door " + door.getId() + " already closed");
    }
  }

  public void lock() {
    if (door.isClosed()) {
      //User tries to lock a closed door */
      door.setState(new Locked(door));
      //System.out.println(door.getStateName());
      logger.debug("Door " + door.getId() + " successfully locked");
    } else { //User tries to lock an opened door *
      logger.debug("Door " + door.getId() + " is opened. Close first to lock it");
    }
  }

  public void unlock() {
    //Can not unlock a door that is already unlocked
    logger.debug("Door " + door.getId() + " is already unlocked");
  }

  public void unlock_shortly() {
    logger.debug("Door " + door.getId() + " already unlocked. Lock first to UnlockShortly");
  }

}
