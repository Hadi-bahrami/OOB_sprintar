package sprint1.inlämning1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VäxterTest {
    Växter igge = new Kaktusar("Igge", 0.2);
    Växter meatloaf = new KöttätandeVäxter("Meatloaf", 0.7);
    Växter laura = new Palmer("Laura", 5);
    Växter olof = new Palmer("Olof", 1);


    @Test
    void getName() {
        assert(igge.getName().toString() == "Igge");
        assertEquals(igge.getLength(), 0.2);
        igge.setName("sigge");
        assert(igge.getName() != "igge");

    }

    @Test
    void setName() {
        assert(meatloaf.getName().toString()== "Meatloaf");
        meatloaf.setName("Adam");
        assert(meatloaf.getName().toString() !=  "Meatloaf");
        assert(meatloaf.toString().equals("Meatloaf"));
        assert(!meatloaf.toString().equals("Adam"));

    }


    @Test
    public void getAmountLiquid() {
        double expectedVal = 0.24;
        double actualVal = meatloaf.getAmountLiquid();
        assertEquals(expectedVal, actualVal);


        assertEquals(0.02, igge.getAmountLiquid());

        laura.setAmountLiquid(3);
        assertEquals(2.5, laura.getAmountLiquid());

        assertEquals(0.5, olof.getAmountLiquid());

    }

    @Test
    public void setAmountLiquid() {
        double expectedVal = 0.24;
        double actualVal = meatloaf.getAmountLiquid();
        assertEquals(expectedVal, actualVal);
        assertEquals(0.24, actualVal);

        assertEquals(0.02, igge.getAmountLiquid());

        assertEquals(2.5, laura.getAmountLiquid());

        assertEquals(0.5, olof.getAmountLiquid());



    }


    @Test
    void whatPlantNeedsWater() {
        //Osäker på vad för tester man gör här
    }

    @Test
    void noPlantExist() {
        //Osäker på vad för tester man gör här
    }
}