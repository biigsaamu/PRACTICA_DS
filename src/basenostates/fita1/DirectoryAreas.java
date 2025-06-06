package basenostates.fita1;


import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The DirectoryAreas class stores and manages the areas and doors of a
// building. It provides methods to create these entities, search them by
// ID and access the root area of the building.
// The class implements the Singleton pattern, managing resources such
// as areas and doors of a building, unique elements. The pattern ensures
// that these resources are controlled by a single instance, providing a
// global access point and data consistency.

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
    Partition building = new Partition("building", "Partition, the root of the building tree",
        null);
    Partition basement = new Partition("basement", "Partition, underground floor", building);
    Space parking = new Space("parking", "Space, park the employees cars(5 places max).", basement);
    Partition groundFloor = new Partition("ground_floor", "Partition, the main entrance is there",
        building);
    Space hall = new Space("hall", "Space, reception is there", groundFloor);
    Space room1 = new Space("room1", "Space, exposition room", groundFloor);
    Space room2 = new Space("room2", "Space, meeting room", groundFloor);
    Partition floor1 = new Partition("floor1", "Partition, Working area", building);
    Space room3 = new Space("room3", "Space, employee's working room", floor1);
    Space corridor = new Space("corridor", "Space, connect the floor 1 rooms", floor1);
    Space it = new Space("it", "Space, IT department", floor1);
    Space exterior = new Space("exterior", "Space, building outside", building);
    Space stairs = new Space("stairs", "Space, connects all building floors", building);

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
