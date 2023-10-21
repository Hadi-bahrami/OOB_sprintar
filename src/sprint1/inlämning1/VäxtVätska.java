package sprint1.inlämning1;

public enum VäxtVätska {
    MINERALWATER("Minteralvatten"), TAPWATER("Kranvatten"), PROTEINWATER("Proteinvatten");
    public final String liquidType;

    VäxtVätska(String vätska) {
        this.liquidType = vätska;
    }
}
