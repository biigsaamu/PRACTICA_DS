package basenostates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class UserGroup {
/*Class that represents a UserGroup with:
* - Users in it
* - Allowed areas to enter
* - Allowed actions to do
* - Work Schedule
*
* UserGroups are declared at DirectoryUserGroups and each one of them have their own characteristics.
* */
  private final String name;
  private final ArrayList<User> users;
  //A userGroup can be composed by more than one user

  private final ArrayList<Area> userGroupAreas;
  /*A userGroup can have access to a specific number of areas represented by this attribute*/

  private ArrayList<String> actions = new ArrayList<>();
  //String list with the actions that the UserGroup is allowed to do

  private final Schedule schedule;

  public UserGroup(String name, ArrayList<User> users, ArrayList<Area> userGroupAreas, ArrayList<String> actions, Schedule schedule){
    this.name = name;
    this.users = users;
    if (!this.users.isEmpty()){
      /*If the Users list passed by parameter is not empty, then set this UserGroup to every User in the list.
      So Users finally are associated to one UserGroup.
      Because when they (Users) are initialized in makeUserGroups() the attribute UserGroup is null*/
      for (User user : this.users){
        user.setUserGroup(this);
      }
    }
    this.userGroupAreas = userGroupAreas;
    this.actions = actions;
    this.schedule = schedule;
    //Assigns this UserGroup to the Schedule
    this.schedule.setUserGroup(this);

  }

  public ArrayList<User> getUsers() {
    return users;
  }

/*
  public String getName(){return name;}
  public void setUserGroupArea(Area area) {
    //Adds a new area to the userGroupAreas list
    userGroupAreas.add(area);
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public ArrayList<Area> getUserGroupAreas() {
    return userGroupAreas;
  }

  public void setUser(User user) {
    //Adds a new user to the users list
    users.add(user);
  }

  public ArrayList<String> getActions(){return actions;}
  */


  public ArrayList<Space> getSpaces(){
    /*Calls getSpaces() of the Areas associated to this UserGroup. Applying recursion and returning the list of
      Spaces where all the Users of this UserGroup has access*/
    ArrayList<Space> userGroupSpaces = new ArrayList<>();
    for (Area area : userGroupAreas){
      userGroupSpaces.addAll(area.getSpaces());
    }
    return userGroupSpaces;
  }

  public boolean isAllowedToEnter(Space space){
    /*The method verifies if the UserGroup has permission to enter to the Space passed by parameter*/
    ArrayList<Space> userGroupSpaces = getSpaces(); //return the Spaces that the UserGroup has access

    /*System.out.println("Allowed Spaces to enter:"); C
    for (Space s: userGroupSpaces){
      System.out.println(s.id);
    }*/

    return userGroupSpaces.contains(space);
      //The Space is among the areas rang the UserGroup has permission to enter
  }
  public boolean isInWorkSchedule(LocalDateTime now){
    /*This method verifies that the dateTime (now) passed by parameter is within the working time of a UserGroup*/

    //Get information of User UserGroup Schedule
    LocalDate dataInici = schedule.getDataInici();
    LocalDate dataFi = schedule.getDataFi();
    ArrayList<DayOfWeek> workDays = schedule.getWorkDays();
    LocalTime horaInici = schedule.getHoraInici();
    LocalTime horaFi = schedule.getHoraFi();

    //Transform now attributes (date of the html) into LocalDate, DayOfWeek and LocalTime
    LocalDate nowDate = now.toLocalDate();
    LocalTime nowTime = now.toLocalTime();
    DayOfWeek nowDayOfWeek = now.getDayOfWeek();

    /*Set of booleans that check if the date, day and hour of the request is between the date, day and hour
    allowed to the UserGroup (this) UserGroup belongs*/
    boolean dateAuthorization = ((nowDate.isEqual(dataInici) || nowDate.isAfter(dataInici))
        && (nowDate.isEqual(dataFi) || nowDate.isBefore(dataFi)));

    /*Check that the date captured in the Request is between the interval of dates the UserGroup has authorization
    to do specific actions*/
    boolean dayOfWeekAuthorization = workDays.contains(nowDayOfWeek);
    /*Check that the dayOfWeek captured in the Request is among the daysOfWeek the UserGroup has authorization
    to do specific actions*/

    boolean timeAuthorization = ((nowTime.equals(horaInici) || nowTime.isAfter(horaInici))
        && (nowTime.equals(horaFi) || nowTime.isBefore(horaFi)));
    /*Check that the time captured in the Request is between the interval day of time the User has authorization
    to do specific actions*/

    return (dateAuthorization && dayOfWeekAuthorization && timeAuthorization);
      //If all the conditions are met the Request can be done in terms of time (when)

  }

  public boolean isActionAuthorized(String action){
    /*What: The method verifies if this UserGroup is allowed to do the action passed by parameter.
    Returns true if it is the case. If not false.*/
    return actions.contains(action);
  }
}
