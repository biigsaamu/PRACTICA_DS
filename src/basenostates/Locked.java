package basenostates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
The Locked class uses the State design pattern
because it allows an object to alter its behaviour when
its internal state changes.
*/

public class Locked extends DoorState {
  /*DoorState type where a User can do actions:
   * - Unlock
   * - UnlockShortly
   */

  Logger logger = LoggerFactory.getLogger("basenostates.DoorState.Locked");

  public Locked(Door door) {
    super(door);
    name = "locked";
  }

  public String getName() {
    return name;
  }

  public void open() { //User tries to open a locked door. Unlock first. *
    logger.info("This door " + door.getId() + " is locked. Unlock first to open it");
  }

  public void close() { //User tries to close a locked and closed door *
    logger.info("This door " + door.getId() + " is already locked and closed");
  }

  public void lock() { //User tries to lock a lacked door *
    logger.info("This door " + door.getId() + " is already locked");
  }

  public void unlock() { //User tries to unlock the door *
    door.setState(new Unlocked(door));
    //System.out.println(door.getStateName());
    logger.debug("Door " + door.getId() + " successfully unlocked");
  }

  public void unlock_shortly() {
    door.setClosed(false);
    door.setState(new UnlockedShortly(door));
    //System.out.println(door.getStateName());
    logger.debug("Door " + door.getId() + " successfully unlocked_shortly");
  }
}
