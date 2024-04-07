// Klassen Lege
// Implementerer Interfacet Comparable<Lege>

public class Lege implements Comparable<Lege> {
    public String navn; 
    public IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>(); 

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    @Override
    public String toString() { // Overskriver her toString metoden
        return "Lege har følgende infomasjon:\n" + "Navn: " + navn;
    }

    @Override
    public int compareTo(Lege annenLege) {

        return navn.compareTo(annenLege.navn); // Benytter Java sin innebygde compareTo() metode for Strings
    }

    public void hentResepter() { // Metode for å hente ut liste av resepter 
        System.out.println("Legen " + navn + " har utskrevet følgende resepter: \n");
        int teller = 1;
        for (Resept resept : utskrevneResepter) {
            System.out.println("Resept nummer " + teller + ": " + resept);
            teller++; 
        }
    }

    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel.getClass() == Narkotisk.class ) { // Sjekker om legemiddelet er av Typen Narkotisk
            if (this.getClass() == Lege.class) { // Sjekker her om Lege er instans av Lege eller subklassen Spesialist 
                throw new UlovligUtskrift(this, legemiddel); 
            }
        }

        HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil((utskrevneResepter.stoerrelse), nyResept); 
        return nyResept;
    }
}
