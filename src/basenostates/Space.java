package basenostates;

import java.util.ArrayList;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Space extends Area {
  /*Class leaf (simple object), that heritages Area methods.
  Is the simplest object of the composite and can not have children*/

  Logger logger = LoggerFactory.getLogger("basenostates.Area.Space");

  private ArrayList<Door> doorsGivingAccess;

  public Space(String id, String description, Partition father) {
    super(id, description, father);
    doorsGivingAccess = new ArrayList<>();
    //father = pare;
  }

  public void addDoorGivingAccess(Door door) {
    //Set the door passed as parameter as a door that gives access tho this space
    this.doorsGivingAccess.add(door);
  }
  /*Used in Door constructor*/

  @Override
  public String getId() {
    return id;
  }

  public ArrayList<Door> getDoorsGivingAccess() {
    logger.debug("Doors giving access to " + id + ": " + doorsGivingAccess);
    return doorsGivingAccess;
  }
  //Returns a Door's list that gives access to this Space

  public Area findAreaById(String id) {
    /*Compares the id searched with the id of the Space.
    So if it is equals this Space is the one that was being found and the method returns this.id*/
    logger.info("[findAreaById Space class]. Is area " + id + ", " + this.id + "?");
    if (this.id.equals(id)) {
      logger.debug("[findAreaById Space class]. Yes it is!");
      return this;
    } else {
      logger.warn("[findAreaById Space class]. No it isn't :(");
      return null;
    }
  }

  public ArrayList<Space> getSpaces() { //Returns the actual space (this) in a Space ArrayList
    ArrayList<Space> spaceList = new ArrayList<>();
    spaceList.add(this);
    return spaceList;
  }

  public String toString() { //Values that the program logs with toJson and makeJsonRequest
    return "Space{"
        + ", id='" + id + '\''
        + ", doors giving access=" + doorsGivingAccess
        //+ ", belongs to Partition=" + father
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("doors giving access", doorsGivingAccess);
    //json.put("belongs to Partition", father);
    return json;
  }
}


