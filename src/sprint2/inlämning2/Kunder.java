package sprint2.inlämning2;

import java.time.LocalDate;

public class Kunder {
    protected String name;
    protected String personnr;
    protected LocalDate datum;

    public Kunder(String name, String personnr, LocalDate datum) {
        this.name = name;
        this.personnr = personnr;
        this.datum = datum;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonnr() {
        return personnr;
    }

    public void setPersonnr(String personnr) {
        this.personnr = personnr;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
