package basenostates.fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// The DoorState class is a representation of the current state of a door.
// Each state defines a specific set of operations and behaviors
// associated with the door. The need to have states associated with a
// door arises to manage the different operations and transitions that a
// door can perform in the access control system.
// The state of a door is set by the system or by user actions, and can
// change in response to events.
// This class applies the State design pattern, it allows the Door object to
// change its behavior based on its internal state, the door can behave
// differently depending on its current state.

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
