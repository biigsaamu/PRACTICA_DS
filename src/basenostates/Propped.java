package basenostates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Propped extends DoorState {
  /*DoorState type where a User can do actions:
   * - Close: The door turn into Locked state after that
   * This is the only action the User can do with this state, but very important to be there,
   * because the only way to reset the Door state into a "normal" state (Locked) is closing it.
   * */

  Logger logger = LoggerFactory.getLogger("basenostates.DoorState.Propped");
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
    /*User tries to close the Propped Door and it turn into Locked state after closing door*/
    door.setClosed(true);
    logger.debug("Door " + door.getId() + " isClosed set into" + door.isClosed());
    logger.info("Door " + door.getId() + " successfully closed");
    door.setState(new Locked(door));
    logger.debug("Door " + door.getId() + " state set into" + door.getStateName());
    logger.info("Door " + door.getId() + " successfully locked after closing");
    //System.out.println("Door " + door.getId() + " successfully closed");
  }

  public void lock() {
    /* User tries to lock a propped door */
    //System.out.println("Door " + door.getId() + " is propped. You can only close it");
    logger.info("Door " + door.getId() + " is propped, only can be closed");
  }

  public void unlock() {
    /* User tries to unlock the door */
    //System.out.println("Door " + door.getId() + " is propped. You can only close it");
    logger.info("Door " + door.getId() + " is propped, only can be closed");
  }

  public void unlock_shortly() {
    //System.out.println("Door " + door.getId() + " is propped. You can only close it");
    logger.info("Door " + door.getId() + " is propped, only can be closed");
  }
}
