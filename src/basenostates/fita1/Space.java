package basenostates.fita1;

import basenostates.fita2.Visitor;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//A Space object contains a list of Door that give access to it.
// This class uses the Composite pattern, representing the hierarchical structure
// of areas and spaces, where the Space is a leaf of the tree structure.
//A Space always have a father (Partition), and can not have a children.


public class Space extends Area {

  Logger logger = LoggerFactory.getLogger("basenostates.fita1.Area.Space");

  private final ArrayList<Door> doorsGivingAccess;

  public Space(String id, String description, Partition father) {
    super(id, description, father);
    doorsGivingAccess = new ArrayList<>();
  }

  public void addDoorGivingAccess(Door door) {
    this.doorsGivingAccess.add(door);
  }

  public ArrayList<Door> getDoorsGivingAccess() {
    logger.debug("Doors giving access to " + id + ": " + doorsGivingAccess);
    return doorsGivingAccess;
  }

  @Override
  public String getId() {
    return id;
  }


  public void acceptVisitor(Visitor visitor) {
    visitor.visitSpace(this);
  }


  public String toString() { //Values that the program logs with toJson and makeJsonRequest
    return "Space{"
        + ", id='" + id + '\''
        + ", doors giving access=" + doorsGivingAccess
        //+ ", belongs to Partition=" + father
        + "}";
  }

  //Method required to make Flutter part
  public JSONObject toJson(int depth) { // depth not used here
    JSONObject json = new JSONObject();
    json.put("class", "space");
    json.put("id", id);
    JSONArray jsonDoors = new JSONArray();
    for (Door d : doorsGivingAccess) {
      jsonDoors.put(d.toJson());
    }
    json.put("access_doors", jsonDoors);
    return json;
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("doors giving access", doorsGivingAccess);
    //json.put("belongs to Partition", father);
    return json;
  }
}

