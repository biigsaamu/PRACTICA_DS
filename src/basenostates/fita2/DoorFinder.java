package basenostates.fita2;

import basenostates.fita1.Door;
import basenostates.fita1.Partition;
import basenostates.fita1.Space;
import basenostates.fita2.Visitor;

import java.util.ArrayList;

public class DoorFinder implements Visitor {

  private ArrayList<Door> doorsGivingSpace;

  public DoorFinder() {
    doorsGivingSpace = new ArrayList<>();
  }
  public void visitSpace(Space space){

  }

  public void visitPartition(Partition partition){

  }

}
