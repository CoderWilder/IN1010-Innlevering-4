// Subklasse Vanedannende

public class Vanedannende extends Legemiddel {
    public final int styrke;

    public Vanedannende(int styrke, String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff); // Kaller konstruktøren i superklassen med gitte parametere
        this.styrke = styrke; // Initialiserer variabelen styrke i subklassen
    }
    
}
