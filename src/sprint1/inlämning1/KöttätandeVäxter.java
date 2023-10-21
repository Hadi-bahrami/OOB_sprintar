package sprint1.inlämning1;

public class KöttätandeVäxter extends Växter implements GreenestHotel {
    public KöttätandeVäxter(String name, double length) {
        super(name, length);
        this.liquid = VäxtVätska.PROTEINWATER.liquidType;
        this.amountLiquid = 0.1 + (0.2 * length);
    }

}
