// Superklasse Legemiddel

public class Legemiddel {
    public static int nyId = 0; // Oppretter en statisk variabel (delt mellom alle instanser) for kontroll på ID
    public final int Id; // Deklarerer variabelen ID
    public final double virkestoff;
    public int pris;
    public final String navn;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.Id = nyId; // Setter ID lik nyID for hver instans av klassen
        nyId++; // Øker nyID med en for hver instans av klassen
    }

    @Override
    public String toString() { // Overskriver her toString metoden
        return "Navn: " + navn + "\n" + "Pris: " + pris + "\n" + "Virkestoff: " + virkestoff + "\n" + "ID: " + ID;
    }

    public int hentPris() {
        return pris;
    }

    public void settNyPris(int nyPris) {
        this.pris = nyPris;
    }

    public String hentNavn() {
        return navn;
    }
}
