// Sub-klassen Hvit Resept
// Deler av koden er hentet fra Innlevering 1

public class HvitResept extends Resept{ 
    public static final String farge = "hvit"; //

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String getFarge() { // Metode for å returnere reseptens farge
        return farge;
    }

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris(); // Returnerer prisen for legemiddelet med riktig prosent
    }

    @Override
    public String toString() {
        return super.toString() + "\nFarge: " + farge;
    }
}

