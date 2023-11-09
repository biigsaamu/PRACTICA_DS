package basenostates;

public class Propped extends DoorState{
  /*DoorState type where a User can do actions:
   * - Close
   Passes to Locked state after that*/

  public Propped(Door door){
    super(door);
    name = "propped";
  }
  public String getName(){return name;}
  public void open(){ //User tries to open a locked door. Unlock first. *
    System.out.println("Door " + door.getId() + " is propped. You can only close it");
  }
  public void close(){ //User tries to close a locked and closed door *
    door.setClosed(true);
    door.setState(new Locked(door));
    System.out.println("Door " + door.getId() + " successfully closed");
  }
  public void lock(){ //User tries to lock a lacked door *
    System.out.println("Door " + door.getId() + " is propped. You can only close it");
  }
  public void unlock(){ //User tries to unlock the door *
    System.out.println("Door " + door.getId() + " is propped. You can only close it");
  }
  public void unlock_shortly(){
    System.out.println("Door " + door.getId() + " is propped. You can only close it");
  }
}
