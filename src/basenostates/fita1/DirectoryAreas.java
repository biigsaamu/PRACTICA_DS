package basenostates.fita1;


import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DirectoryAreas class implements the Singleton pattern that
 * guarantees a single global instance. This class is used to define and organize partitions,
 * spaces and gates in the system. It uses the Composite pattern to represent the
 * hierarchical structure of areas and spaces, where Partition and Space are
 * treated as composite objects. The DirectoryAreas class allows the creation of
 * these objects and maintains a collection of all doors. By encapsulating the
 * directory and providing methods to find areas and gates by ID.
 */

public class DirectoryAreas {

  static final Logger logger = LoggerFactory.getLogger("basenostates.fita1.DirectoryAreas");

  private static DirectoryAreas uniqueDirectoryAreas = null;
  private static Area rootArea;
  private static ArrayList<Door> allDoors;

  private DirectoryAreas() {
  }

  public static DirectoryAreas getInstance() {
    if (uniqueDirectoryAreas == null) {
      uniqueDirectoryAreas = new DirectoryAreas();
    }
    return uniqueDirectoryAreas;
  }

  public void makeAreas() { //Add method in main once finished

    //Areas declaration
    Partition building = new Partition("building", "building, the root of the building tree", null);
    Partition basement = new Partition("basement", "", building);
    Space parking = new Space("parking", "Space, park the employees cars(5 places max).", basement);
    Partition groundFloor = new Partition("ground_floor", "", building);
    Space hall = new Space("hall", "", groundFloor);
    Space room1 = new Space("room1", "", groundFloor);
    Space room2 = new Space("room2", "", groundFloor);
    Partition floor1 = new Partition("floor1", "", building);
    Space room3 = new Space("room3", "", floor1);
    Space corridor = new Space("corridor", "", floor1);
    Space it = new Space("it", "", floor1);
    Space exterior = new Space("exterior", "", building);
    Space stairs = new Space("stairs", "", building);

    //Doors declaration
    //basement
    Door d1 = new Door("D1", exterior, parking);
    Door d2 = new Door("D2", stairs, parking);

    //Ground floor
    Door d3 = new Door("D3", exterior, hall);
    Door d4 = new Door("D4", stairs, hall);
    Door d5 = new Door("D5", hall, room1);
    Door d6 = new Door("D6", hall, room2);

    //first floor
    Door d7 = new Door("D7", stairs, corridor);
    Door d8 = new Door("D8", corridor, room3);
    Door d9 = new Door("D9", corridor, it);

    rootArea = building; //Assign the father of the tree. If not area requests do not work
    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

  }

  public Area getRootArea() {
    return rootArea;
  }

  public Area findAreaById(String id) { //Change code in request reader lately
    return rootArea.findAreaById(id);
    //Returns the id of the Area if it is found. If not returns null
  }

  public Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        logger.debug("Door with id " + id + " found");
        return door;
      }
    }
    logger.warn("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  public static ArrayList<Door> getAllDoors() {
    return allDoors;
  }
}
