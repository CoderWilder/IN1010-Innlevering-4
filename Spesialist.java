// Definerer subklassen Spesialist med implementering av Interface Godkjenningsfritak
// Spesialister kan alltid skrive ut Narkotiske legemidler, men bare på blå resept

public class Spesialist extends Lege implements Godkjenningsfritak{
    public String kontrollkode; 

    public Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    @Override
    public String hentKontrollkode() {
        return kontrollkode;
    }

    @Override
    public String toString() {
        return super.toString() + "\nKontrollkode: " + kontrollkode + "\n";
    }
}

