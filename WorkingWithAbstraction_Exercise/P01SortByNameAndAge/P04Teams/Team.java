package WorkingWithAbstraction_Exercise.P01SortByNameAndAge.P04Teams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Team {
     private String name;
     private List<Person> firstTeam;
     private List<Person> reserveTeam;

     Team(String name) {
         this.firstTeam = new ArrayList<>();
         this.reserveTeam = new ArrayList<>();

         this.name = name;
     }
     private void setName(String name) {
         if (name.length() < 5) {
           throw new IllegalArgumentException("Team name should be at least 5 symbols");
         }
         this.name = name;
     }
     void addPlayer(Person player) {
         if (player.getAge() < 40) {
             this.firstTeam.add(player);
         } else {
             this.reserveTeam.add(player);
         }
     }
     public List<Person> getReserveTeam() {
         return Collections.unmodifiableList(reserveTeam);
     }

     List<Person> getFirstTeam() {
         return firstTeam;
     }
}
