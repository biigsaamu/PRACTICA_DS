package basenostates.fita1;

import basenostates.fita2.AreaSpacesFinder;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserGroup {
  //Class that represents a UserGroup. An UserGroup is a group of users with specific
  // characteristics:
  // Allowed areas to enter
  // Allowed actions to do
  // Work Schedule

  Logger logger = LoggerFactory.getLogger("basenostates.fita1.UserGroup");
  private final String name;
  private final ArrayList<User> users;
  private final ArrayList<Area> userGroupAreas;
  private ArrayList<String> actions = new ArrayList<>();
  private final Schedule schedule;

  public UserGroup(String name, ArrayList<User> users, ArrayList<Area> userGroupAreas,
                   ArrayList<String> actions, Schedule schedule) {
    this.name = name;
    this.users = users;
    if (!this.users.isEmpty()) {
      for (User user : this.users) {
        user.setUserGroup(this);
        logger.debug("User " + user + " added to " + this.name + " UserGroup");
      }
    }
    this.userGroupAreas = userGroupAreas;
    this.actions = actions;
    this.schedule = schedule;
    this.schedule.setUserGroup(this);
    logger.debug("Schedule fixed to " + this.name + " UserGroup");
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Space> getSpaces() {
    ArrayList<Space> userGroupSpaces = new ArrayList<>();
    AreaSpacesFinder userAreaSpacesFinder = new AreaSpacesFinder();

    for (Area area : userGroupAreas) {
      area.acceptVisitor(userAreaSpacesFinder);
      assert userAreaSpacesFinder.getAreaSpaces() != null;
      userGroupSpaces.addAll(userAreaSpacesFinder.getAreaSpaces());
    }
    logger.info("UserGroup Spaces: " + userGroupSpaces);
    return userGroupSpaces;
  }

  public boolean isAllowedToEnter(Space space) {
    //WHERE: The method verifies if the UserGroup has permission to enter to a specific Space

    ArrayList<Space> userGroupSpaces = getSpaces();
    return userGroupSpaces.contains(space);

  }

  public boolean isInWorkSchedule(LocalDateTime now) {
    //WHEN: This method verifies that the dateTime (now) passed by parameter is within the
    // working time of a UserGroup (date, day and time authorizations)

    LocalDate userGroupInitialDate = schedule.getInitialDate();
    LocalDate userGroupEndDate = schedule.getEndDate();
    ArrayList<DayOfWeek> userGroupWorkDays = schedule.getWorkDays();
    LocalTime userGroupInitialHour = schedule.getInitialHour();
    LocalTime userGroupEndHour = schedule.getEndHour();

    //Transform now attributes (selected LocalDateTime in the simulator)
    //into LocalDate, DayOfWeek and LocalTime
    LocalDate nowDate = now.toLocalDate();
    LocalTime nowTime = now.toLocalTime();
    DayOfWeek nowDayOfWeek = now.getDayOfWeek();


    boolean dateAuthorization = (
        (nowDate.isEqual(userGroupInitialDate) || nowDate.isAfter(userGroupInitialDate))
        && (nowDate.isEqual(userGroupEndDate) || nowDate.isBefore(userGroupEndDate)));

    boolean dayOfWeekAuthorization = userGroupWorkDays.contains(nowDayOfWeek);

    boolean timeAuthorization = (
        (nowTime.equals(userGroupInitialHour) || nowTime.isAfter(userGroupInitialHour))
        && (nowTime.equals(userGroupEndHour) || nowTime.isBefore(userGroupEndHour)));


    return (dateAuthorization && dayOfWeekAuthorization && timeAuthorization);

  }

  public boolean isActionAuthorized(String action) {
    //WHAT: The method verifies if this UserGroup is allowed to do the action passed by parameter.
    return actions.contains(action);
  }
}
