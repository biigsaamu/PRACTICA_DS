package basenostates.fita1;

import basenostates.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Door {
  // Basic unit of the system which Users interact to.

  final Logger logger = LoggerFactory.getLogger("basenostates.fita1.Door");
  private final String id;
  private boolean closed; // physically
  private DoorState ds; // Reference the state of the door
  private final Space fromSpace;
  private final Space toSpace;

  public Door(String id, Space fromSpace, Space toSpace) {
    this.id = id;
    closed = true;
    ds = new Unlocked(this);
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;
    this.toSpace.addDoorGivingAccess(this); // Add this door to Door's list that gives access to the toSpace Space
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    String action = request.getAction();
    if (request.isAuthorized()) {
      doAction(action);
    } else {
      //System.out.println("not authorized");
      logger.info("not authorized to do the " + action + " action to Door " + id);
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
        /* If it is closed and wants to be locked, to!. If it is opened, before locking it it must be closed. */
        ds.lock();
        break;
      case Actions.UNLOCK:
        // fall through
        ds.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        ds.unlock_shortly();
        //System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        logger.error("Unknown action " + action + " to the Door");
        System.exit(-1);
    }
  }

  public Space getToSpace() {
    return toSpace;
  }

  public Space getFromSpace() {
    return fromSpace;

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

  public void setClosed(boolean b) {
    closed = b;
  }

  public void setState(DoorState doorState){
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
