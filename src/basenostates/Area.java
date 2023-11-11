package basenostates;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Area {
  /*This abstract class is declared to implement the design pattern COMPOSITE.
  This means that the methods declared in the class are the ones that share and must
  be implemented by the heritage classes.
  This design pattern composes objects, simple (Space) and composite (Partition),
  as tree structures and allows working with them. */

  //Logger
  //Logger logger = LoggerFactory.getLogger(Area.class);
  Logger logger = LoggerFactory.getLogger("basenostates.Area");

  protected String id;
  protected String description;
  protected Partition father;

  public Area(String id, String description, Partition father) {
    this.id = id;
    this.description = description;
    if (father != null) {
      father.addArea(this); //Tree construction assigning child (this area) to the father (Partition)
      this.father = father;
      logger.debug(this.id + " has " + this.father.getId() + " as father;");
    } else {
      this.father = null;
    }

  }

  public abstract String getId();

  public abstract ArrayList<Door> getDoorsGivingAccess();

  public abstract Area findAreaById(String id);

  public abstract ArrayList<Space> getSpaces();
}
