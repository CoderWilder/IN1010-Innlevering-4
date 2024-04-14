// Klassen Pasient

import java.util.ArrayList;

public class Pasient {
    public String navn;
    public String fødselsnummer; 
    public static int nyId = 0;
    public final int Id; 
    ArrayList<Resept> resepter = new ArrayList<>();

    public Pasient(String navn, String fødselsnummer) {
        this.navn = navn; 
        this.fødselsnummer = fødselsnummer;
        this.Id = nyId; // Setter Id lik nyId for hver instans av klassen, sikrer unik Id
        nyId++; // Øker nyID med en for hver instans av klassen
    }

    @Override
    public String toString() { // Overskriver her toString metoden
        return "Navn: " + navn + "\nFødselsnummer: " + fødselsnummer + "\nPasient Id: " + Id + "\n"; 
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFødselsnummer() {
        return fødselsnummer;
    }

    public ArrayList<Resept> hentReseptListe() {
        return resepter;
    }
}
