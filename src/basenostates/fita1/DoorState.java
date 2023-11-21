package basenostates.fita1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
This class uses the State design pattern, allowing an object
to alter its behaviour when its internal state changes.
Its adds behaviours such as Locked and Unlocked in a state
subclass.
 */

public abstract class DoorState {
  /*This abstract class is declared to implement de design pattern STATE.
  * */
  Logger logger = LoggerFactory.getLogger("basenostates.fita1.DoorState");

  protected Door door;
  protected String name;

  public DoorState(Door door) {
    this.door = door;
  }

  public abstract String getName();

  public Door getDoor() {
    return door;
  }

  public abstract void open();

  public abstract void close();

  public abstract void lock();

  public abstract void unlock();

  public abstract void unlock_shortly();

}
