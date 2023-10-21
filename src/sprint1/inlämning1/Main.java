package sprint1.inlämning1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main(){
        Växter saguaro = new Kaktusar("saguaro", 15);
        Växter aloeVera = new Kaktusar("aloe Vera", 0.3);
        Växter agave = new Kaktusar("agave", 5);
        Växter venusFlugFälla = new KöttätandeVäxter("Venusflugfälla", 0.3);
        Växter drosera = new KöttätandeVäxter("Drosera", 0.2);
        Växter sarracenia = new KöttätandeVäxter("Sarracenia", 0.5);
        Växter dadelpalm = new Palmer("Dadelpalm", 20);
        Växter kokospalm = new Palmer("Kokospalm", 25);
        Växter vindpalm = new Palmer("Vindpalm", 15);
        Växter igge = new Kaktusar("Igge", 0.2);
        Växter meatloaf = new KöttätandeVäxter("Meatloaf", 0.7);
        Växter laura = new Palmer("Laura", 5);
        Växter olof = new Palmer("Olof", 1);
        // Alla växter ovan har vi använt polymorfism på.

        // Vi lägger till dessa i en Arraylist av klassen Växter
        List<Växter> hotelletsVäxter= new ArrayList<>();
        hotelletsVäxter.add(agave);
        hotelletsVäxter.add(aloeVera);
        hotelletsVäxter.add(venusFlugFälla);
        hotelletsVäxter.add(drosera);
        hotelletsVäxter.add(saguaro);
        hotelletsVäxter.add(sarracenia);
        hotelletsVäxter.add(dadelpalm);
        hotelletsVäxter.add(vindpalm);
        hotelletsVäxter.add(kokospalm);
        hotelletsVäxter.add(igge);
        hotelletsVäxter.add(meatloaf);
        hotelletsVäxter.add(olof);
        hotelletsVäxter.add(laura);

        Växter v = new Växter();

        boolean b;
        do {
            b = v.plantExist(hotelletsVäxter, v.whatPlantNeedsWater());
        }while (!b);


    }
    public static void main(String[]args){
        Main main = new Main();

    }

}
