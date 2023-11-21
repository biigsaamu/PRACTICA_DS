package basenostates.fita2;

import basenostates.fita1.Partition;
import basenostates.fita1.Space;

public interface Visitor {
  public void visitSpace(Space space);

  public void visitPartition(Partition partition);
}
