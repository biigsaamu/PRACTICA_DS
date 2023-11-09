package basenostates.requests;

import basenostates.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    User user = DirectoryUserGroups.findUserByCredential(credential); //Original DirectoryUsers.findUserByCredential(credential)
    Door door = DirectoryAreas.findDoorById(doorId); //Original DirectoryDoors.findDoorById(doorId)
    assert door != null : "door " + doorId + " not found";
    authorize(user, door); //Change by user priorities
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    if (user == null) {
      /*The User passed by parameter was not found in the ACU database.
      DirectoryUserGroups.findUserByCredential(credential) returned a null*/
      authorized = false;
      addReason("user doesn't exists");
    } else {
      boolean what = user.isAllowedToDoAction(action); /*Checks if the User can do the action requested*/
      boolean when = user.isInWorkTime(now); /*Checks if the User is in work time to do the action requested*/
      boolean where = (user.isAllowedToBeInSpace(door.getFromSpace()) && user.isAllowedToBeInSpace(door.getToSpace()));
      /*Checks if the User has access to the Spaces the door/doors connect*/
      //TODO: get the who, where, when and what in order to decide, and if not
      // authorized add the reason(s)
      if (what){
        if (when){
          if (where){
            authorized = true;
          }else{
            addReason("user doesn't have access to one/both area/s");
            authorized = false;
          }
        }else {
          addReason("user isn't in time to do the action");
          authorized = false;
        }
      } else {
        addReason("user can't do this action");
        authorized = false;
      }
    }
  }
}

