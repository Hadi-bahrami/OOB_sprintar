package sprint1.inlämning1;

import javax.swing.*;
import java.util.List;

public class Växter implements GreenestHotel{ // Implementerar GreenestHotel

    protected String name; // Variabel inkapsling
    protected double length; // Variabel inkapsling
    protected double amountLiquid; // Variabel inkapsling
    protected String liquid; // Variabel inkapsling



    public Växter(){}
    public Växter(String name, double length) { // Konstruktorn
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public double getAmountLiquid() {
        return amountLiquid;
    }

    public void setAmountLiquid(double amountLiquid) {
        this.amountLiquid = amountLiquid;
    }

    // Stod override, vet att vi kan deleta den men vad är poängen här det funkar utan override.

    public boolean plantExist(List<Växter> plants, String searchPlant){
        boolean found = false;
        for (Växter v : plants)  {
            if (v.getName().equalsIgnoreCase(searchPlant)){
                v.printDesc();
                return true;
            }
        }
        if (!found){
            noPlantExist();
        }
        return false;
    }
    public void printDesc() {
        JOptionPane.showMessageDialog(null, "Växten: " + getName() + "\n" +
                "Vätska: " + liquid + "\n" + "Mängden vätska: " + amountLiquid + " Liter per dag");


    }

    public String whatPlantNeedsWater() {
        String witchPlant = JOptionPane.showInputDialog("Vilken växt ska få vätska?");
        try {
            if (witchPlant != null || !witchPlant.isEmpty()){
                return witchPlant;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return witchPlant;
    }

    public void noPlantExist() {
        JOptionPane.showMessageDialog(null, "Växten finns inte på hotellet. \n försök igen");
    }
}
