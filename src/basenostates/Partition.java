package basenostates;

import java.util.ArrayList;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Partition extends Area {
  //A Partition is an area that can be composed by a Space/s, Partition/s or the combination of
  // these two.
  //Class that applies the COMPOSITE pattern. It is the complex object that heritages and
  // implements Area's abstract methods. Delegating the work to the Leaf objects (Space).

  Logger logger = LoggerFactory.getLogger("basenostates.Area.Partition");
  private final ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, String description, Partition father) {
    super(id, description, father);
  }

  public void addChildrenArea(Area area) {
    areas.add(area);
  }


  @Override
  public String getId() {
    return id;
  }

  public ArrayList<Door> getDoorsGivingAccess() {

    ArrayList<Door> doors = new ArrayList<>();
    for (Area area : areas) {
      doors.addAll(area.getDoorsGivingAccess());
    }
    if (doors.isEmpty()) {
      logger.warn("No Doors giving access to " + id);
      return null;
    } else {
      logger.info("Doors " + doors + " giving access to Partition " + id);
      return doors;
    }

  }

  public Area findAreaById(String id) {
    //Recursive function that calls this class or Space "findAreaById" method.
    //Asserts if the id is among the areas of the ACU

    logger.debug("Is area " + id + ", " + this.id + "?");
    if (this.id.equals(id)) {
      logger.debug("Yes it is! :)");
      return this;
    } else {
      logger.debug("Is area " + id + " among " + this.id + " areas?");
      for (Area area : this.areas) {
        Area subArea = area.findAreaById(id);
        if (subArea != null) { //id found
          logger.debug("Area " + id + " is " + subArea.id);
          return subArea;
        } else {
          logger.debug("Area " + id + " is not " + area.id);
        }
      }
      logger.debug("Area " + id + " is not in " + this.id);
      return null;
    }
  }

  public ArrayList<Space> getSpaces() {
    ArrayList<Space> partitionSpaces = new ArrayList<>();
    for (Area area : areas) {
      partitionSpaces.addAll(area.getSpaces());
    }
    if (partitionSpaces.isEmpty()) {
      logger.warn("Area " + this.id + " has no Spaces to return");
      return null;
    } else {
      logger.debug("Partition " + id + " has " + partitionSpaces + " Spaces");
      return partitionSpaces;
    }
  }

  public String toString() {
    //This is real important, the method transforms to String the values
    // that the program logs with toJson and makeJsonRequest
    return "Partition{"
        + ", id='" + id + '\''
        + ", Areas contained=" + areas
        + "father" + father
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("Areas contained", areas);
    //json.put("belongs to Partition", father);
    return json;
  }

}
