package basenostates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryUserGroups {
  /*
  * Class that creates and save the different UserGroups that exist in the system*/

  static Logger logger = LoggerFactory.getLogger("basenostates.DirectoryUserGroups");
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();

  public static void makeUserGroups() {
    //TODO: make user groups according to the specifications in the comments, because
    // now all are the same


    //Check areas needed to assign to UserGroups are initialized
    logger.debug("Is building in DirectoryAreas?");
    Area building = DirectoryAreas.findAreaById("building");

    logger.debug("Is ground_floor in DirectoryAreas?");
    Area ground_floor = DirectoryAreas.findAreaById("ground_floor");

    logger.debug("Is floor1 in DirectoryAreas?");
    Area floor1 = DirectoryAreas.findAreaById("floor1");

    logger.debug("Is exterior in DirectoryAreas?");
    Area exterior = DirectoryAreas.findAreaById("exterior");

    logger.debug("Is stairs in DirectoryAreas?");
    Area stairs = DirectoryAreas.findAreaById("stairs");

    //Create userGroupAreas
    ArrayList<Area> employeesAreas = new ArrayList<>(Arrays.asList(ground_floor, floor1, exterior, stairs));
    ArrayList<Area> managersAreas = new ArrayList<>(List.of(building));
    ArrayList<Area> adminAreas = new ArrayList<>(List.of(building));

    //Create Users.
    //User constructor assigns the User to the UserGroup passed by parameter
    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    //new User("Bernat", "12345", null);
    //new User("Blai", "77532", null);

    // employees :
    // Sep. 1 2023 to Mar. 1 2024
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    User Ernest = new User("Ernest", "74984");
    User Eulalia = new User("Eulalia", "43295");

    // managers :
    // Sep. 1 2023 to Mar. 1 2024
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    User Manel = new User("Manel", "95783");
    User Marta = new User("Marta", "05827");

    // admin :
    // always=2023 to 2100
    // all days of the week
    // all actions
    // all spaces
    User Ana = new User("Ana", "11343");

    //Initialize
    //Create userGroupUsers
    ArrayList<User> employeesUsers = new ArrayList<>(Arrays.asList(Ernest, Eulalia));
    ArrayList<User> managersUsers = new ArrayList<>(Arrays.asList(Manel, Marta));
    ArrayList<User> adminUsers = new ArrayList<>(List.of(Ana));

    //UserGroup actions creation
    ArrayList<String> employeesActions = new ArrayList<>(Arrays.asList(Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));
    /*NOTE (implementation suggestion) --> Added open and close actions to employees as the teacher encourage to
    implement it after Fita 1 session. In order to make a more realistic ACS.*/
    ArrayList<String> managersActions = new ArrayList<>(Arrays.asList(Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));
    ArrayList<String> adminActions = new ArrayList<>(Arrays.asList(Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));

    //UserGroup Schedule's creation

    //Create work dates, work days and work times
    //employees
    LocalDate dataInici_employees = LocalDate.of(2023, 9, 1);
    LocalDate dataFi_employees = LocalDate.of(2024, 3, 1);
    ArrayList<DayOfWeek> workDays_employees = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
    LocalTime horaInici_employees = LocalTime.of(9, 0);
    LocalTime horaFi_employees = LocalTime.of(17, 0);

    //managers
    LocalDate dataInici_manegers = LocalDate.of(2023, 9, 1);
    LocalDate dataFi_manegers = LocalDate.of(2024, 3, 1);
    ArrayList<DayOfWeek> workDays_manegers = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));
    LocalTime horaInici_manegers = LocalTime.of(8, 0);
    LocalTime horaFi_manegers = LocalTime.of(20, 0);

    //admin
    LocalDate dataInici_admin = LocalDate.of(2023, 1, 1);
    LocalDate dataFi_admin = LocalDate.of(2100, 12, 31);
    ArrayList<DayOfWeek> workDays_admin = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    LocalTime horaInici_admin = LocalTime.of(0, 0);
    LocalTime horaFi_admin = LocalTime.of(23, 59);

    //Schedules initialization (constructor's call)
    Schedule employeesSchedule = new Schedule(dataInici_employees, dataFi_employees, workDays_employees, horaInici_employees, horaFi_employees);
    Schedule manegersSchedule = new Schedule(dataInici_manegers, dataFi_manegers, workDays_manegers, horaInici_manegers, horaFi_manegers);
    Schedule adminSchedule = new Schedule(dataInici_admin, dataFi_admin, workDays_admin, horaInici_admin, horaFi_admin);
    //Create UserGroups
    UserGroup employees = new UserGroup("employees", employeesUsers, employeesAreas, employeesActions, employeesSchedule);
    UserGroup manegers = new UserGroup("manegers", managersUsers, managersAreas, managersActions, manegersSchedule);
    UserGroup admin = new UserGroup("admin", adminUsers, adminAreas, adminActions, adminSchedule);

    //UserGroups assignment to the private attribute
    userGroups.add(employees);
    userGroups.add(manegers);
    userGroups.add(admin);

  }

  public static User findUserByCredential(String credential) {
    /*process() method of RequestReader class calls this method expecting to match the credential passed by parameter
    with some user's credential. So the User returned exists in the database of the ACU*/
    for (UserGroup userGroup : userGroups) {
      /*Scroll through the UserGroups list to have access to Users that belong to a specific UserGroup (ug)*/
      ArrayList<User> users = userGroup.getUsers(); //Returns the Users list in a UserGroup
      for (User user : users){
        /*Scroll through the Users of a UserGroup the search if one User (u) of them has the credential passed
        by parameter.*/
        if (user.getCredential().equals(credential)) { /*If it is the case the method returns true. If not a null*/
          logger.debug("user with credential " + credential + " found");
          return user;
        }
      }
    }
    logger.warn("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }
}
