package basenostates.fita1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {

  public static void main(String[] args) {

    Logger logger = LoggerFactory.getLogger("basenostates.fita1.Main");
    logger.trace("entering Main");
    DirectoryAreas.getInstance().makeAreas();
    DirectoryUserGroups.getInstance().makeUserGroups();
    new WebServer();
  }
}
