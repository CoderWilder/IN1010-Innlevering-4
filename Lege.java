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

    public IndeksertListe<Resept> hentResepterListe() {
        return utskrevneResepter;
    }

    

    @Override
    public String toString() { // Overskriver her toString metoden
        return "Navn: " + navn + "\n";
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

    public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift { // Metode for opprettelse av HvitResept
        if (legemiddel instanceof Narkotisk) { // Sjekker om legemiddelet er av Typen Narkotisk
            throw new UlovligUtskrift(this, legemiddel); 
        }

        HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil((utskrevneResepter.stoerrelse), nyResept); 
        pasient.hentReseptListe().add(nyResept); 
        System.out.println("Resept lagt til for pasient " + pasient.hentNavn());
        return nyResept;
    }

    public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift { // Metode for opprettelse av MilResept
        if (legemiddel instanceof Narkotisk) { // Sjekker om legemiddelet er av Typen Narkotisk
            throw new UlovligUtskrift(this, legemiddel); 
        }

        MilResept nyResept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil((utskrevneResepter.stoerrelse), nyResept); 
        pasient.hentReseptListe().add(nyResept); 
        System.out.println("Resept lagt til for pasient " + pasient.hentNavn());
        return nyResept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift { // Metode for opprettelse av PResept
        if (legemiddel instanceof Narkotisk) { // Sjekker om legemiddelet er av Typen Narkotisk
            throw new UlovligUtskrift(this, legemiddel); 
        }

        PResept nyResept = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil((utskrevneResepter.stoerrelse), nyResept); 
        pasient.hentReseptListe().add(nyResept); 
        System.out.println("Resept lagt til for pasient " + pasient.hentNavn());
        return nyResept;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift { // Metode for opprettelse av PResept
        if (legemiddel instanceof Narkotisk) { // Sjekker om legemiddelet er av Typen Narkotisk
            if (this.getClass() == Lege.class) { // Sjekker her om Lege er instans av Lege eller subklassen Spesialist 
                throw new UlovligUtskrift(this, legemiddel); 
            }
        }

        BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit); // Metode for opprettelse av Blaa Resept
        utskrevneResepter.leggTil((utskrevneResepter.stoerrelse), nyResept); 
        pasient.hentReseptListe().add(nyResept); 
        System.out.println("Resept lagt til for pasient " + pasient.hentNavn());
        return nyResept;
    }
}
