// Subklassen Militærresepter
// Subklasse av HvitResept
// Deler av koden er hentet fra Innlevering 1

public class MilResept extends HvitResept {
    
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3); // Overskriver verdien til reit, og setter den lik 3
    }

    @Override // Legger til abstract metode prisAaBetale 
    public int prisAaBetale() {
        return 0; // Returnerer prisen for legemiddelet med riktig prosent
    }
}


