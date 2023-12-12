package basenostates.requests;

import basenostates.fita1.Area;
import basenostates.fita1.DirectoryAreas;
import basenostates.fita2.AreaFinderById;
import org.json.JSONObject;

public class RequestChildren implements Request {
  private final String areaId;
  private JSONObject jsonTree; // 1 level tree, root and children

  public RequestChildren(String areaId) {
    this.areaId = areaId;
  }

  public String getAreaId() {
    return areaId;
  }

  @Override
  public JSONObject answerToJson() {
    return jsonTree;
  }

  @Override
  public String toString() {
    return "RequestChildren{areaId=" + areaId + "}";
  }

  public void process() {
    Area area = null;

    AreaFinderById requestAreaFinder = new AreaFinderById(areaId);
    Area root = DirectoryAreas.getInstance().getRootArea();
    root.acceptVisitor(requestAreaFinder);
    if (requestAreaFinder.isAreaFound()) {
      area = requestAreaFinder.getArea();
    } else {
      assert false : "error Area not found";
    }

    assert area != null;
    jsonTree = area.toJson(1);
  }
}