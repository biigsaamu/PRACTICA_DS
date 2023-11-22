package basenostates.fita1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Schedule {
  /* this class can be a record, that by definition is a class only used for representing data
   * in this case Schedule is used by representing the corresponding rol schedule, which is
   * required by UserGroup.
   * */
  Logger logger = LoggerFactory.getLogger("basenostates.fita1.Schedule");
  private UserGroup userGroup;
  private final LocalDate initialDate;
  private final LocalDate endDate;
  private final ArrayList<DayOfWeek> workDays;
  private final LocalTime initialHour;
  private final LocalTime endHour;

  public Schedule(LocalDate initialDate, LocalDate endDate, ArrayList<DayOfWeek> workDays,
                  LocalTime initialHour, LocalTime endHour) {
    this.initialDate = initialDate;
    this.endDate = endDate;
    this.workDays = workDays;
    this.initialHour = initialHour;
    this.endHour = endHour;
    userGroup = null;
    logger.warn("Schedule has null userGroup for now");
  }

  public UserGroup getUserGroup() {
    return userGroup;
  }

  public void setUserGroup(UserGroup userGroup) {
    this.userGroup = userGroup;
    logger.info("Schedule is assigned to " + userGroup.getName() + " userGroup");
  }

  public LocalDate getInitialDate() {
    return initialDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public ArrayList<DayOfWeek> getWorkDays() {
    return workDays;
  }

  public LocalTime getInitialHour() {
    return initialHour;
  }

  public LocalTime getEndHour() {
    return endHour;
  }

}

