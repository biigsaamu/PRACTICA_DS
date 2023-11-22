package basenostates.fita1;


// The Actions class defines the possible actions in the door and access control system.
// It contains constants representing actions such as LOCK, UNLOCK,
// UNLOCK_SHORTLY, OPEN and CLOSE. These constants are used in
// requests related to access readers and areas, as well as in requests
// related to doors.
// A door request is can process every action, an area request only LOCK and UNLOCK.

public final class Actions {
  public static final String LOCK = "lock";
  public static final String UNLOCK = "unlock";
  public static final String UNLOCK_SHORTLY = "unlock_shortly";
  public static final String OPEN = "open";
  public static final String CLOSE = "close";
}
