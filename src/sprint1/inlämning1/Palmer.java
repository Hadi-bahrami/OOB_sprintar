package sprint1.inlämning1;

public class Palmer extends Växter implements GreenestHotel {

    public Palmer(String name, double length) {
        super(name, length);
        this.liquid = VäxtVätska.TAPWATER.liquidType;
        this.amountLiquid = 0.5 * length;
    }



}
