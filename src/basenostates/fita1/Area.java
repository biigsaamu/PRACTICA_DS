package basenostates.fita1;

import basenostates.fita2.Visitor;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Area {
  //An area is declared to implement the design pattern COMPOSITE.
  //This pattern composes simple objects (Space) and composite or complex ones (Partition),
  //as tree structures and allows working with them.
  //The methods declared in the class are the ones that share and must
  //be implemented by the heritage classes.

  final Logger logger = LoggerFactory.getLogger("basenostates.fita1.Area");

  protected final String id;
  protected final String description;
  protected final Partition father;

  public Area(String id, String description, Partition father) {
    this.id = id;
    this.description = description;
    if (father != null) {
      father.addChildrenArea(this);
      this.father = father;
      logger.debug(this.id + " has " + this.father.getId() + " as father;");
    } else {
      this.father = null;
    }

  }

  public abstract String getId();

  public abstract void acceptVisitor(Visitor visitor);
}
