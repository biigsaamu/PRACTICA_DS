package basenostates.fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DoorState class is an abstract class that defines the structure and behaviour
 * of the different states of a door. Each concrete state of a door, such as 'open',
 * 'closed', 'locked', etc., will inherit from this class. The purpose of having door
 * states is to handle different allowed or required actions depending on the current
 * state of the door. For example, a door cannot be locked if it is open, and such
 * scenarios are managed through these states.
 * This class is part of the State design pattern, which allows an object to alter its
 * behaviour when its internal state changes.
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
