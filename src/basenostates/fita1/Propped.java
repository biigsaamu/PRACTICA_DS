package basenostates.fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Propped extends DoorState {
  // DoorState type (State pattern) where a User only can do the Close action to the Door:
  // - Close: The door turn into Locked state after this action
  // This is the only action the User can do to the door in this state,but very important
  // to be there, because the only way to reset the Door's state into a "normal" state
  // (Locked) is closing it

  Logger logger = LoggerFactory.getLogger("basenostates.fita1.DoorState.Propped");

  public Propped(Door door) {
    super(door);
    name = "propped";
  }

  public String getName() {
    return name;
  }

  public void open() {
    //System.out.println("Door " + door.getId() + " is propped. You can only close it");
    logger.info("Door " + door.getId() + " is propped, only can be closed");
  }

  public void close() {
    door.setClosed(true);
    logger.debug("Door " + door.getId() + " isClosed set into" + door.isClosed());
    logger.info("Door " + door.getId() + " successfully closed");
    door.setState(new Locked(door));
    logger.debug("Door " + door.getId() + " state set into" + door.getStateName());
    logger.info("Door " + door.getId() + " successfully locked after closing");
    //System.out.println("Door " + door.getId() + " successfully closed");
  }

  public void lock() {
    //System.out.println("Door " + door.getId() + " is propped. You can only close it");
    logger.info("Door " + door.getId() + " is propped, only can be closed");
  }

  public void unlock() {
    //System.out.println("Door " + door.getId() + " is propped. You can only close it");
    logger.info("Door " + door.getId() + " is propped, only can be closed");
  }

  public void unlock_shortly() {
    //System.out.println("Door " + door.getId() + " is propped. You can only close it");
    logger.info("Door " + door.getId() + " is propped, only can be closed");
  }
}
