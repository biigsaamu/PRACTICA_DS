package basenostates;

public interface Visitor {
  public void visitSpace(Space space);

  public void visitPartition(Partition partition);
}
