package basenostates.fita1;

import basenostates.fita2.AreaFinderById;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryUserGroups {

  //Class that creates and save one unique instance and of each UserGroups that exist in the system.
  //To do it the Singleton pattern is applied.

  static final Logger logger = LoggerFactory.getLogger("basenostates.fita1.DirectoryUserGroups");
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();

  private static DirectoryUserGroups uniqueDirectoryUserGroups = null;

  private DirectoryUserGroups() {
  }

  public static DirectoryUserGroups getInstance() {
    if (uniqueDirectoryUserGroups == null) {
      uniqueDirectoryUserGroups = new DirectoryUserGroups();
    }
    return uniqueDirectoryUserGroups;
  }

  public void makeUserGroups() {

    //Check areas, needed to assign to UserGroups, are initialized
    Area root = DirectoryAreas.getInstance().getRootArea();

    Area building = null;
    logger.debug("Is building in DirectoryAreas?");
    AreaFinderById buildingFinder = new AreaFinderById("building");
    root.acceptVisitor(buildingFinder);
    if (buildingFinder.isAreaFound()) {
      building = buildingFinder.getArea();
    }

    Area groundFloor = null;
    logger.debug("Is ground_floor in DirectoryAreas?");
    AreaFinderById groundFloorFinder = new AreaFinderById("ground_floor");
    root.acceptVisitor(groundFloorFinder);
    if (groundFloorFinder.isAreaFound()) {
      groundFloor = groundFloorFinder.getArea();
    }

    Area floor1 = null;
    logger.debug("Is floor1 in DirectoryAreas?");
    AreaFinderById floor1Finder = new AreaFinderById("floor1");
    root.acceptVisitor(floor1Finder);
    if (floor1Finder.isAreaFound()) {
      floor1 = floor1Finder.getArea();
    }

    Area exterior = null;
    logger.debug("Is exterior in DirectoryAreas?");
    AreaFinderById exteriorFinder = new AreaFinderById("exterior");
    root.acceptVisitor(exteriorFinder);
    if (exteriorFinder.isAreaFound()) {
      exterior = exteriorFinder.getArea();
    }

    Area stairs = null;
    logger.debug("Is stairs in DirectoryAreas?");
    AreaFinderById stairsFinder = new AreaFinderById("stairs");
    root.acceptVisitor(stairsFinder);
    if (stairsFinder.isAreaFound()) {
      stairs = stairsFinder.getArea();
    }


    //Create userGroupAreas
    ArrayList<Area> employeesAreas =
        new ArrayList<>(Arrays.asList(groundFloor, floor1, exterior, stairs));
    ArrayList<Area> managersAreas = new ArrayList<>(List.of(building));
    ArrayList<Area> adminAreas = new ArrayList<>(List.of(building));


    //Create Users.

    // employees
    User Ernest = new User("Ernest", "74984");
    User Eulalia = new User("Eulalia", "43295");

    // managers
    User Manel = new User("Manel", "95783");
    User Marta = new User("Marta", "05827");

    // admin
    User Ana = new User("Ana", "11343");


    //Create userGroupUsers
    ArrayList<User> employeesUsers = new ArrayList<>(Arrays.asList(Ernest, Eulalia));
    ArrayList<User> managersUsers = new ArrayList<>(Arrays.asList(Manel, Marta));
    ArrayList<User> adminUsers = new ArrayList<>(List.of(Ana));

    //UserGroup actions creation

    //Real important: Added open and close actions to employees as the teacher encourage to
    // implement it after Fita 1 session.
    // In order to make a more realistic ACS.

    ArrayList<String> employeesActions =
        new ArrayList<>(Arrays.asList(Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));

    ArrayList<String> managersActions =
        new ArrayList<>(Arrays.asList(
            Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));

    ArrayList<String> adminActions =
        new ArrayList<>(Arrays.asList(
            Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));

    //UserGroup Schedule's creation

    //Create work dates, work days and work times

    //employees
    LocalDate dataIniciEmployees = LocalDate.of(2023, 9, 1);
    LocalDate dataFiEmployees = LocalDate.of(2024, 3, 1);
    ArrayList<DayOfWeek> workDaysEmployees = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
    LocalTime horaIniciEmployees = LocalTime.of(9, 0);
    LocalTime horaFiEmployees = LocalTime.of(17, 0);

    //managers
    LocalDate dataIniciManagers = LocalDate.of(2023, 9, 1);
    LocalDate dataFiManagers = LocalDate.of(2024, 3, 1);
    ArrayList<DayOfWeek> workDaysManagers = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));
    LocalTime horaIniciManagers = LocalTime.of(8, 0);
    LocalTime horaFiManagers = LocalTime.of(20, 0);

    //admin
    LocalDate dataIniciAdmin = LocalDate.of(2023, 1, 1);
    LocalDate dataFiAdmin = LocalDate.of(2100, 12, 31);
    ArrayList<DayOfWeek> workDaysAdmin = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    LocalTime horaIniciAdmin = LocalTime.of(0, 0);
    LocalTime horaFiAdmin = LocalTime.of(23, 59);

    //Schedules initialization

    Schedule employeesSchedule = new Schedule(
        dataIniciEmployees, dataFiEmployees, workDaysEmployees,
        horaIniciEmployees, horaFiEmployees);

    Schedule managersSchedule = new Schedule(
        dataIniciManagers, dataFiManagers, workDaysManagers, horaIniciManagers, horaFiManagers);

    Schedule adminSchedule = new Schedule(
        dataIniciAdmin, dataFiAdmin, workDaysAdmin, horaIniciAdmin, horaFiAdmin);


    //Create UserGroups
    UserGroup employees = new UserGroup(
        "employees", employeesUsers, employeesAreas, employeesActions, employeesSchedule);
    UserGroup managers = new UserGroup(
        "managers", managersUsers, managersAreas, managersActions, managersSchedule);
    UserGroup admin = new UserGroup(
        "admin", adminUsers, adminAreas, adminActions, adminSchedule);

    //UserGroups assignment to the private attribute
    userGroups.add(employees);
    userGroups.add(managers);
    userGroups.add(admin);

  }

  public User findUserByCredential(String credential) {
    // Asserts if the credential is among the ACU users

    for (UserGroup userGroup : userGroups) {
      ArrayList<User> users = userGroup.getUsers();

      for (User user : users) {
        if (user.getCredential().equals(credential)) {
          logger.debug("user with credential " + credential + " found");
          return user;
        }
      }
    }
    logger.warn("user with credential " + credential + " not found");
    return null;
  }
}
