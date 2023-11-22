package basenostates.fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {

  public static void main(String[] args) {
    /* Logger is incorporated
     * All instantiation of Areas and Users (UserGroups) is done there,
     * after initialization WebServer is called to build the simulator API.
     * as you can se every function responsibility is granted to the corresponding
     * class Main only calls them to execute and mount the final functional API.
     * Grasp Pattern: Expert Applied.
     * */

    Logger logger = LoggerFactory.getLogger("basenostates.fita1.Main");
    logger.trace("entering Main");
    DirectoryAreas.getInstance().makeAreas();
    DirectoryUserGroups.getInstance().makeUserGroups();
    new WebServer();
  }
}
