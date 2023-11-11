package basenostates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DoorState{
  /*This abstract class is declared to implement de design pattern STATE.
  * */
  Logger logger = LoggerFactory.getLogger("basenostates.DoorState");

  protected Door door;
  protected String name;
  public DoorState(Door door){
    this.door = door;
  }
  public abstract String getName();
  public Door getDoor(){
    return door;
  }

  public abstract void open();
  public abstract void close();

  public abstract void lock();

  public abstract void unlock();

  public abstract void unlock_shortly();

}
