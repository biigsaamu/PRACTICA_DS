package basenostates.fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Unlocked extends DoorState {
  // Unlocked is a DoorState type where a User can do the following actions
  // (depending on privileges):
  //- Open
  //- Close
  //- Lock
  //A State can change of State by itself.


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
    if (door.isClosed()) {
      door.setClosed(false);
      logger.debug("Door " + door.getId() + " successfully opened");
    } else {
      logger.warn("Door " + door.getId() + " already opened");
    }
  }

  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      logger.debug("Door " + door.getId() + " successfully closed");
    } else {
      logger.debug("Door " + door.getId() + " already closed");
    }
  }

  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
      logger.debug("Door " + door.getId() + " successfully locked");
    } else {
      logger.debug("Door " + door.getId() + " is opened. Close first to lock it");
    }
  }

  public void unlock() {
    logger.debug("Door " + door.getId() + " is already unlocked");
  }

  public void unlock_shortly() {
    logger.debug("Door " + door.getId() + " already unlocked. Lock first to UnlockShortly");
  }

}
