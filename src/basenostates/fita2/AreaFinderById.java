package basenostates.fita2;
import java.util.ArrayList;
import java.util.Iterator;

import basenostates.fita1.Area;
import basenostates.fita1.Partition;
import basenostates.fita1.Space;
import basenostates.fita2.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AreaFinderById implements Visitor {

  private String id;

  private Area area;

  private boolean foundArea;

  static final Logger logger = LoggerFactory.getLogger("basenostates.fita2.AreaFinderById");

  public AreaFinderById(String id) {
    this.id = id;
    this.foundArea = false;
    this.area = null;
  }

  public String getId() {
    return id;
  }

  public Area getArea() {
    return area;
  }

  public boolean isAreaFound() {
    return foundArea;
  }

  public void visitSpace(Space space) {
    logger.info("Is area " + this.id + ", Space " + space.getId() + "?");
    if (this.id.equals(space.getId())) {
      logger.debug("Yes it is! :)");
      this.foundArea = true;
      this.area = space;
    } else {
      logger.warn("No it isn't :(");
    }
  }

  public void visitPartition(Partition partition) {
    //Recursive function that calls this class or Space "findAreaById" method.
    //Asserts if the id is among the areas of the ACU
    logger.debug("Is area " + this.id + ", Partition " + partition.getId() + "?");
    if (this.id.equals(partition.getId())) {
      logger.debug("Yes it is! :)");
      this.foundArea = true;
      this.area = partition;
    } else {
      ArrayList<Area> partitionAreas = partition.getAreas();
      logger.debug("Is area " + this.id + " among " + partition.getId() + " areas?");
      Iterator<Area> iterator = partitionAreas.iterator();
      while (iterator.hasNext() && !this.foundArea) {
        Area childrenArea = iterator.next();
        childrenArea.acceptVisitor(this);
      }
    }
  }

}

