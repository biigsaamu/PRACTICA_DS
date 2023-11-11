package basenostates;

import org.json.JSONObject;

import java.util.ArrayList;

public class Partition extends Area{
/*Class that belongs tho COMPOSITE pattern. It is the complex object that heritages and implements Area's abstract methods.
Must be compound (have children/s) by other Partition/s and/or Space/s.
*/
  private final ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, String description, Partition father){
    super(id, description, father);
    //father = pare; //Associate the father of the Partition. Can be another partition or null if it is the tree father
  }

  public void addArea(Area area){
    //Error: "Consulting data..."
    areas.add(area);
  }

  //public ArrayList<Area> getAreas(){return areas;}

  @Override
  public String getId(){return id;}
  public ArrayList<Door> getDoorsGivingAccess(){
    /*Returns the Door's list giving access to this Partition*/
    ArrayList<Door> doors = new ArrayList<>();
    for (Area area : areas){
      /*Runs the ArrayList of areas that correspond to this Partition
      and calls all the children getDoorsGivingAccess*/
      doors.addAll(area.getDoorsGivingAccess());
    }
    if (doors.isEmpty()){ //If doors list is empty the function returns null
      System.out.println("No Doors giving access to " + id);
      return null;
    }else { //If doors list is not empty the function returns the doors that gives access to this Partition
      System.out.println("Doors " + doors + " giving access to Partition " + id);
      return doors;
    }

  }

  public Area findAreaById(String id) { //Searches an area by the id passed as a parameter
    if (this.id.equals(id)) {
      //System.out.println("[findAreaById Partition class]. Is area " + id + " " + this.id + "?"); C
      /*Is the Area we are searching by id the current Area?*/
      System.out.println("[findAreaById Partition class]. Yes it is!");
      return this;
    } else {
      System.out.println("[findAreaById Partition class]. Is area " + id + " among " + this.id + " areas?");
      /*Check if the Partition children's (subAreas) one of them is the Area searched by the id*/
      for (Area area : this.areas) {
        Area subArea = area.findAreaById(id);
        if (subArea != null) {
          //Search succeed. Children's id is the same as the searched area
          System.out.println("[findAreaById Partition class]. Area " + id + " is " + subArea.id);
          return subArea;
        } else {
          //Search did not succeed
          System.out.println("[findAreaById Partition class]. Area " + id + " is not " + area.id);
        }
      }
      System.out.println("[findAreaById Partition class]. Area " + id + " is not in " + this.id);
      return null;
    }
  }
  public ArrayList<Space> getSpaces(){
    ArrayList<Space> partitionSpaces = new ArrayList<>();
    for (Area area : areas){
        partitionSpaces.addAll(area.getSpaces());
        /*Calls the method getSpaces() of the Areas belonging to this Partition. So it would call to the getSpaces()
        of Partition or Space respectively (applies recursion)*/
      }
    if (partitionSpaces.isEmpty()){ //If partitionSpaces is empty the method returns null as it means the partition has no children
      //System.out.println("No Spaces to return"); C
      return null;
    }else{ //If the partitionSpaces list is not empty the method returns the list of Spaces that the Partition has
      //System.out.println("Partition " + id + " has " + partitionSpaces + " Spaces"); C
      return partitionSpaces;
    }
  }

  public String toString() { //Values that the program logs with toJson and makeJsonRequest
    return "Partition{"
        + ", id='" + id + '\''
        + ", Areas contained=" + areas
        //+ ", belongs to Partition=" + father
        + "}";
  }


  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("Areas contained", areas);
    //json.put("belongs to Partition", father);
    return json;
  }
}
