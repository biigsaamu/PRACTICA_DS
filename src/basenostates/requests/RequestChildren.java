package basenostates.requests;

import basenostates.fita1.Area;
import basenostates.fita1.DirectoryAreas;
import basenostates.fita2.AreaFinderById;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestChildren implements Request {
  private final String areaId;
  private JSONObject jsonTree; // 1 level tree, root and children

  static final Logger logger = LoggerFactory.getLogger("basenostates.requests.RequestChildren");

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
      logger.info("Area: " + area);
    } else {
      assert false : "error Area not found";
    }

    assert area != null;
    jsonTree = area.toJson(1);
  }
}