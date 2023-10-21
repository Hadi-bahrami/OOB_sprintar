package sprint2.inlämning2;


public class Huvudkod {
    public Huvudkod(){
        BestGymEver bestGymEver = new BestGymEver();

        bestGymEver.readFromFile("src/sprint2/inlämning2/kundData.txt");
        //bestGymEver.searchInList();
        bestGymEver.isAtGym();
    }
    public static void main(String[] args) {
        Huvudkod huvudkod = new Huvudkod();

    }
}
