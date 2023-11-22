package basenostates.fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Locked extends DoorState {
  //DoorState type where a User can do actions:
  //- Unlock: The turn into Unlocked state after this action
  //- UnlockShortly: The Door unlocks shortly (UnlockedShortly state)
  // The Locked class defines the behaviour of a door when it is in a locked
  // state. In this state, actions such as opening or closing the door are
  // restricted, but some actions can be performed to unlock the door.
  // Locked is a concrete state from DoorState, which allows an object (Door) to change
  // its behaviour according to its internal state.
  // This structure facilitates behaviour modification without altering the
  // Door class.

  Logger logger = LoggerFactory.getLogger("basenostates.fita1.DoorState.Locked");

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

  public void unlock() { //User tries to unlock the door
    door.setState(new Unlocked(door));
    logger.debug("Door " + door.getId() + " successfully unlocked");
  }

  public void unlock_shortly() {
    door.setClosed(false);
    door.setState(new UnlockedShortly(door));
    logger.debug("Door " + door.getId() + " successfully unlocked_shortly");
  }
}
