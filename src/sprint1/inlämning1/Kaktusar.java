package sprint1.inlämning1;

public class Kaktusar extends Växter implements GreenestHotel {
    public Kaktusar(String name, double length) {
        super(name, length);
        this.liquid = VäxtVätska.MINERALWATER.liquidType;
        this.amountLiquid = 0.02;
    }

}
