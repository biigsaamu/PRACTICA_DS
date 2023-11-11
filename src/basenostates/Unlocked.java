package basenostates;

public class Unlocked extends DoorState {
  /*Unlocked is a DoorState type where a User can do the following actions (depending on the privileges):
  * - Open
  * - Close
  * - Lock
  * A State can change of State by itself.
  * */
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
      System.out.println("Door " + door.getId() + " successfully opened");
    } else {
      System.out.println("Door " + door.getId() + " already opened");
    }
  }

  public void close() {
    //User closes a door and sets boolean attribute closed to false *
    if (!door.isClosed()) {
      door.setClosed(true);
      System.out.println("Door " + door.getId() + " successfully closed");
    } else {
      System.out.println("Door " + door.getId() + " already closed");
    }
  }

  public void lock() {
    if (door.isClosed()) {
      //User tries to lock a closed door */
      door.setState(new Locked(door));
      //System.out.println(door.getStateName());
      System.out.println("Door " + door.getId() + " successfully locked");
    } else { //User tries to lock an opened door *
      System.out.println("Door " + door.getId() + " is opened. Close first to lock it");
    }
  }

  public void unlock() {
    //Can not unlock a door that is already unlocked
    System.out.println("Door " + door.getId() + " is already unlocked");
  }

  public void unlock_shortly() {
    System.out.println("Door " + door.getId() + " already unlocked. Lock first to UnlockShortly");
  }

}
