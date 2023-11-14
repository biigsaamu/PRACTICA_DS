package basenostates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {

  public static void main(String[] args) {

    /* DirectoryDoors is merged into DirectoryAreas
     * DirectoryUsers is merged into DirectoryUserGroup */
    Logger logger = LoggerFactory.getLogger("basenostates.Main");
    logger.trace("entering Main");
    //DirectoryDoors.makeDoors();
    DirectoryAreas.makeAreas();
    //DirectoryUsers.makeUsers();
    DirectoryUserGroups.makeUserGroups();
    new WebServer();
  }
}
