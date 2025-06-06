package basenostates.fita1;

import basenostates.fita2.Visitor;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Partition extends Area {
  //A Partition object applies the COMPOSITE pattern and can be composed by a Space/s,
  // Partition/s or the combination of these two.
  //It is the complex object that heritages and
  //implements Area's abstract methods. Delegating the work to the Leaf objects (Space).

  Logger logger = LoggerFactory.getLogger("basenostates.fita1.Area.Partition");
  private final ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, String description, Partition father) {
    super(id, description, father);
  }

  public void addChildrenArea(Area area) {
    areas.add(area);
  }

  public ArrayList<Area> getAreas() {
    return areas;
  }

  @Override
  public String getId() {
    return id;
  }

  public void acceptVisitor(Visitor visitor) {
    visitor.visitPartition(this);
  }

  public String toString() {
    //This is real important, the method transforms to String the values
    // that the program logs with toJson and makeJsonRequest
    return "Partition{"
        + ", id='" + id + '\''
        + ", Areas contained=" + areas
        //+ "father" + father
        + "}";
  }

  //Method required to make Flutter part
  public JSONObject toJson(int depth) {
    // for depth=1 only the root and children,
    // for recusive = all levels use Integer.MAX_VALUE
    JSONObject json = new JSONObject();
    json.put("class", "partition");
    json.put("id", id);
    JSONArray jsonAreas = new JSONArray();
    if (depth > 0) {
      for (Area a : areas) {
        jsonAreas.put(a.toJson(depth - 1));
      }
      json.put("areas", jsonAreas);
    }
    return json;
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("Areas contained", areas);
    //json.put("belongs to Partition", father);
    return json;
  }

}
