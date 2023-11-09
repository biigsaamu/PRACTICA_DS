package basenostates;

import basenostates.requests.RequestReader;
import org.json.JSONObject;


public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState ds; //Reference the state of the door (locked or unlocked)
  private final Space origen;
  private final Space destination;

  public Door(String id, Space origen, Space destination) { //Ask if the constructor should receive more parameters
    this.id = id;
    closed = true;
    ds = new Unlocked(this);
    this.origen = origen;
    this.destination = destination;
   // this.origen.setDoorsGivingAccess(this); //Add this door to Door's list that gives access to the origen Space
    this.destination.addDoorGivingAccess(this); //Add this door to Door's list that gives access to the destination Space
    //System.out.println("Door " + this.id + " connects From space '" + this.origen.id + "' to space '" + this.destination.id + "'"); C
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        ds.open();
        break;
      case Actions.CLOSE:
        ds.close();
        break;
      case Actions.LOCK:
        // TODO
        /* If it is closed and wants to be locked, go!. If it is opened, before locking it it must be closed.*/
        // fall through
        ds.lock();
        break;
      case Actions.UNLOCK:
        // TODO
        // fall through
        ds.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        // TODO
        ds.unlock_shortly();
        //System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public Space getToSpace(){ //Returns the space where you land after crossing the door
    return destination;
  }

  public Space getFromSpace(){ //Returns the space where you start before crossing the door
    return origen;
  }
  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return ds.getName();
  }

  public void setClosed(boolean b){
    closed = b;
  }
  public void setState(DoorState doorState){ //Sets the door state to the string passed //Class 'DoorState' is exposed outside its defined visibility scope
    ds = doorState;
  }

  @Override
  public String toString() { //Values that the program logs with toJson and makeJsonRequest
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName() //Returns the DoorState state
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName()); //Returns the DoorState state
    json.put("closed", closed);
    return json;
  }
}
