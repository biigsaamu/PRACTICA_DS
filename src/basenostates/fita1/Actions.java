package basenostates.fita1;


// The Actions class defines the possible actions a User can do to a door.
// It contains constants representing actions.
// These constants are used in requests related to access readers
// and areas, as well as in requests related to doors.

public final class Actions {
  public static final String LOCK = "lock";
  public static final String UNLOCK = "unlock";
  public static final String UNLOCK_SHORTLY = "unlock_shortly";
  public static final String OPEN = "open";
  public static final String CLOSE = "close";
}
