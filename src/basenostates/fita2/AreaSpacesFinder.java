package basenostates.fita2;

import basenostates.fita1.Area;
import basenostates.fita1.Door;
import basenostates.fita1.Partition;
import basenostates.fita1.Space;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AreaSpacesFinder implements Visitor {

  Logger logger = LoggerFactory.getLogger("basenostates.fita2.AreaSpacesFinder");

  private ArrayList<Space> areaSpaces;

  public AreaSpacesFinder() {
    areaSpaces = new ArrayList<>();
  }



  public ArrayList<Space> getAreaSpaces() {
    return areaSpaces;
  }

  public void visitSpace(Space space) {
    areaSpaces.add(space);
  }

  public void visitPartition(Partition partition) {

    ArrayList<Area> partitionAreas = partition.getAreas();

    for (Area area : partitionAreas) {
      area.acceptVisitor(this);
    }
      logger.info("[P] Spaces of " + partition.getId() + ": " + areaSpaces);
  }


}
