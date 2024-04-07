// Subklasse Narkotisk

public class Narkotisk extends Legemiddel {
    public final int styrke;

    public Narkotisk(int styrke, String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff); // Kaller konstruktøren i superklassen med gitte parametere
        this.styrke = styrke; // Initialiserer variabelen styrke i subklassen
    }
}
