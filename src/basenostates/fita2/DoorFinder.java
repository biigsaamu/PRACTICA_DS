package basenostates.fita2;

import basenostates.fita1.Area;
import basenostates.fita1.Door;
import basenostates.fita1.Partition;
import basenostates.fita1.Space;
import basenostates.fita2.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class DoorFinder implements Visitor {

  Logger logger = LoggerFactory.getLogger("basenostates.fita2.DoorFinder");

  private ArrayList<Door> doorsGivingSpace;

  public DoorFinder() {
    doorsGivingSpace = new ArrayList<>();
  }

  public ArrayList<Door> getDoorsGivingSpace() {
    return doorsGivingSpace;
  }

  public void visitSpace(Space space) {

    if (space.getDoorsGivingAccess().isEmpty()) {
      logger.warn("[S] No Doors giving access to " + space.getId());
    } else {
      logger.debug("[S] Doors giving access to " + space.getId() + ": " + space.getDoorsGivingAccess());
      doorsGivingSpace.addAll(space.getDoorsGivingAccess());
    }

  }

  public void visitPartition(Partition partition) {

    ArrayList<Area> partitionAreas = partition.getAreas();

    for (Area area : partitionAreas) {
      area.acceptVisitor(this);
    }
    logger.info("[P] Doors giving access to " + partition.getId() + ": " + doorsGivingSpace);
  }


}
