package basenostates;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule {
  private UserGroup userGroup;
  private LocalDate dataInici;
  private LocalDate dataFi;
  private ArrayList<DayOfWeek> workDays;

  private LocalTime horaInici;
  private LocalTime horaFi;

  public Schedule(LocalDate dataInici, LocalDate dataFi, ArrayList<DayOfWeek> workDays, LocalTime horaInici, LocalTime horaFi){
    this.dataInici = dataInici;
    this.dataFi = dataFi;
    this.workDays = workDays;
    this.horaInici = horaInici;
    this.horaFi = horaFi;
    userGroup = null;
  }

  public UserGroup getUserGroup() {
    return userGroup;
  }

  public void setUserGroup(UserGroup userGroup) {
    this.userGroup = userGroup;
  }

  public LocalDate getDataInici() {
    return dataInici;
  }
  public LocalDate getDataFi() {
    return dataFi;
  }
  public ArrayList<DayOfWeek> getWorkDays() {
    return workDays;
  }
  public LocalTime getHoraInici() {
    return horaInici;
  }
  public LocalTime getHoraFi() {
    return horaFi;
  }

}
