// Subklassen P-resept
// Subklasse av HvitResept
// Deler av koden er hentet fra Innlevering 1

public class PResept extends HvitResept {

    public PResept(Legemiddel legemiddel, Lege utskrivendelege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendelege, pasient, reit);
        this.reit = 3; // Overskriver verdien til reit, og setter den lik 3
    }

    @Override // Legger til abstract metode prisAaBetale 
    public int prisAaBetale() {
        return (int) legemiddel.hentPris() - 108; // Returnerer prisen for legemiddelet med 108 kr i avdrag
    }
    
}
