package basenostates;

import java.time.LocalDateTime;

public class User {
  private final String name;
  private final String credential;
  private UserGroup group;
  //private ArrayList<Area> userAreas;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
    this.group = null;
  }

  public UserGroup getUserGroup(){return group;}

  public void setUserGroup(UserGroup group) {
    this.group = group;
  }

  public String getCredential() {
    return credential;
  }

  public boolean isAllowedToBeInSpace(Space space) {
    /*Where: The method verifies if the User's UserGroup has permission to enter to the Space passed by parameter*/
    System.out.println("Does User " + this.name + " has access to Space " +  space.id + "?");
    boolean authorize = group.isAllowedToEnter(space);
    if (authorize){
      /*If the condition returns an Area (something different to null).
      Means the User has access to the Space as it was found among the userAreas the User has access permission*/
      System.out.println("User " + this.name + " has permission to enter to Space " + space.id);
    }else {
      //If the Space is not among the User's UserGroup areas returns false. The User can not enter to this Space
      System.out.println("User " + this.name + " is forbidden to enter to Space " + space.id);
    }
    return authorize;
  }

  public boolean isInWorkTime(LocalDateTime now){
    /*When: The method verifies if the User can send a Request depending on his/her UserGroup work schedule*/
    boolean authorize = group.isInWorkSchedule(now);
    if (authorize){
      System.out.println("User " + this.name + " is in Schedule time");
    }else {
      System.out.println("User " + this.name + " is not in Schedule time");
    }
    return authorize;
  }

  public boolean isAllowedToDoAction(String action){
    /*What: The method verifies if this User's UserGroup is allowed to do the action passed by parameter.
    Returns true if it is the case. If not false.*/
    boolean authorize = group.isActionAuthorized(action);
    if (authorize){
      System.out.println("User " + this.name + " can " + action + " the door");
    }else {
      System.out.println("User " + this.name + " can not " + action + " the door");
    }
    return authorize;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
