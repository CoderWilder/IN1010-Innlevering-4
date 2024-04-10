// Definerer klassen Blå Resept
// Subklasse av Resept
// Deler av koden er hentet fra Innlevering 1

public class BlaaResept extends Resept {
    public static final String farge = "blå"; //

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String getFarge() { // Metode for å returnere reseptens farge
        return farge;
    }

    @Override
    public int prisAaBetale() {
        return (int) Math.round(legemiddel.hentPris()*0.25); // Returnerer prisen for legemiddelet med riktig prosent
    }

    @Override
    public String toString() {
        return super.toString() + "\nFarge : " + farge;
    }
}