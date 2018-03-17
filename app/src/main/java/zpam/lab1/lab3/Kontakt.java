package zpam.lab1.lab3;

import com.orm.SugarRecord;

public class Kontakt extends SugarRecord {
    private String nazwa;
    private String numer;

    public Kontakt() {
    }

    public Kontakt(String nazwa, String numer) {
        this.nazwa = nazwa;
        this.numer = numer;
    }

    public String getFullContact(){
        return nazwa + " " + numer;
    }
}
