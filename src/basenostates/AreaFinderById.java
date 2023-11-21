package basenostates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AreaFinderById implements Visitor {

  private String id;

  private boolean foundArea;

  static final Logger logger = LoggerFactory.getLogger("basenostates.AreaFinderById");
  public void visitSpace(Space space){

  }

  public void visitPartition(Partition partition){

  }
}
