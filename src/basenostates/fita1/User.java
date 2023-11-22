package basenostates.fita1;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
  // Users are individual persons recognized by the system which is identified by
  // a valid credential (unique). The systems follow a standard approach known as
  // Role-based access control (RBAC), so every User should know their own rol and
  // be able to determine if they have permission to access some Area through Door
  // that give access to them.
  
  Logger logger = LoggerFactory.getLogger("basenostates.fita1.User");
  private final String name;
  private final String credential;
  private UserGroup group;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
    this.group = null;
    logger.warn("User" + this.name + "  with credential " + this.credential
                + " has null group assignation for now");
  }

  public UserGroup getUserGroup() {
    return group;
  }

  public void setUserGroup(UserGroup group) {
    this.group = group;
    logger.info("User" + this.name + "  with credential " + this.credential
                + " is assigned to" + group.getName() + "userGroup");
  }

  public String getCredential() {
    return credential;
  }

  public boolean isAllowedToBeInSpace(Space space) {
    // Where: The method verifies if the User's UserGroup has permission to
    // enter to the Space passed by parameter
    
    boolean authorize = group.isAllowedToEnter(space);
    if (authorize) {
      // If the condition returns an Area (something different to null).
      // Means the User has access to the Space as it was found among
      // the userAreas the User has access permission
      
      //System.out.println("User " + this.name + " has permission to enter to Space " + space.id);
      logger.info("User " + this.name + " has permission to enter to Space " + space.id);
    } else {
      // If the Space is not among the User's UserGroup areas returns false.
      // The User can not enter to this Space
      
      //System.out.println("User " + this.name + " is forbidden to enter to Space " + space.id);
      logger.info("User " + this.name + " is forbidden to enter to Space " + space.id);
    }
    return authorize;
  }

  public boolean isInWorkTime(LocalDateTime now) {
    // When: The method verifies if the User can send a Request depending on
    // his/her UserGroup work schedule
    
    boolean authorize = group.isInWorkSchedule(now);
    if (authorize) {
      //System.out.println("User " + this.name + " is in Schedule time");
      logger.info("User " + this.name + " is in Schedule time");
    } else {
      //System.out.println("User " + this.name + " is not in Schedule time");
      logger.info("User " + this.name + " is not in Schedule time");
    }
    return authorize;
  }

  public boolean isAllowedToDoAction(String action) {
    // What: The method verifies if this User's UserGroup is allowed to do
    // the action passed by parameter.
    // Returns true if it is the case. If not false.
    
    boolean authorize = group.isActionAuthorized(action);
    if (authorize) {
      //System.out.println("User " + this.name + " can " + action + " the door");
      logger.info("User " + this.name + " can " + action + " the door");
    } else {
      //System.out.println("User " + this.name + " can not " + action + " the door");
      logger.info("User " + this.name + " can not " + action + " the door");
    }
    return authorize;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
