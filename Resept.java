// Abstrakt klasse Resept
// Deler av koden er hentet fra Innlevering 1

public abstract class Resept {
    public static int nyId = 0;
    public final int Id;
    public Legemiddel legemiddel;
    public Lege utskrivendeLege;
    public Pasient pasient;
    public int reit; // Antall ganger resepten kan brukes

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) { 
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.Id = nyId; // Setter ID lik nyID for hver instans av klassen
        nyId++; // Øker nyID med en for hver instans av klassen
    }

    public int hentId() {
        return Id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public Pasient hentPasientId() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit--; // Reduserer reit med en
            return true;
        }
        return false; // Returnerer false dersom reit ikke er større enn null (oppbrukt)
    }

    abstract public String getFarge(); // Returnerer reseptens farge, enten hvit eller blå, abstrakt metode

    abstract public int prisAaBetale(); // Returnerer prisen pasienten må betale, abstrakt metode

    @Override
    public String toString() { // Overskriver her toString metoden
        return "Resept Id: " + Id + "\n" + "Legemiddel: " + legemiddel.navn + "\n" + "Utskrivende lege " + utskrivendeLege + "\n" + "Pasient " + pasient + "Reit: " + reit;
    }
}


