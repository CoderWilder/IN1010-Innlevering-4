// Klassen Legesystem

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

public class Legesystem {

    public IndeksertListe<Pasient> pasientListe = new IndeksertListe<>(); 
    public IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>(); 
    public IndeksertListe<Lege> legeListe = new IndeksertListe<>(); 
    
    public void lesFraFil(String filnavn) {
        try {

            Scanner fil = new Scanner(new File(filnavn)); 
            String linje = null; 
            System.out.println("\nStarter nå lesing av filen: " + filnavn + "\n");

            while (fil.hasNextLine()) {
                if (linje == null) {
                    linje = fil.nextLine(); 
                }

                if (linje.startsWith("# Pasienter")) {
                    System.out.println("\n---Leser pasientdata---");
                    while(fil.hasNextLine()) {
                        linje = fil.nextLine();

                        if (linje.charAt(0) == '#') {
                            break; // Ønsker å starte på nytt, når linje er lik ny overskrift
                        }

                        String[] deler = linje.split(","); 
                        String navn = deler[0]; 
                        String fødselsnummer = deler[1]; 

                        pasientListe.leggTil(pasientListe.stoerrelse(), new Pasient(navn, fødselsnummer));
                        System.out.println("Lagt til Pasient: " + navn);
                    }

                } else if (linje.startsWith("# Legemidler")) {
                    System.out.println("\n---Leser Legemiddeldata---");
                    while(fil.hasNextLine()) {
                        linje = fil.nextLine();

                        if (linje.charAt(0) == '#') {
                            break; // Ønsker å starte på nytt, når linje er lik ny overskrift
                        }

                        String[] deler = linje.split(","); 
                        String navn = deler[0]; 
                        String type = deler[1]; 
                        int pris = Integer.parseInt(deler[2]); 
                        int virkestoff = Integer.parseInt(deler[3]); 

                        if (type.equals("narkotisk")) {
                            int styrke = Integer.parseInt(deler[4]); 
                            legemiddelListe.leggTil(legemiddelListe.stoerrelse(), new Narkotisk(styrke, navn, pris, virkestoff));

                        } else if (type.equals("vanedannende")) {
                            int styrke = Integer.parseInt(deler[4]); 
                            legemiddelListe.leggTil(legemiddelListe.stoerrelse(), new Vanedannende(styrke, navn, pris, virkestoff));

                        } else if (type.equals("vanlig")) {
                            legemiddelListe.leggTil(legemiddelListe.stoerrelse(), new Vanlig(navn, pris, virkestoff));
                            
                        }

                        System.out.println("Lagt til Legemiddel: " + type);
                    }

                } else if (linje.startsWith("# Leger")) {
                    System.out.println("\n---Leser Legedata---");
                    while (fil.hasNextLine()) {
                        linje = fil.nextLine();

                        if (linje.charAt(0) == '#') {
                            break; // Ønsker å starte på nytt, når linje er lik ny overskrift
                        }
    
                        String[] deler = linje.split(","); 
                        String navn = deler[0]; 
                        int kontrollId = Integer.parseInt(deler[1].trim()); 
    
                        if (kontrollId == 0) {
                            legeListe.leggTil(legeListe.stoerrelse(), new Lege(navn));
                            System.out.println("Lagt til Lege: " + navn);
    
                        } else {
                            legeListe.leggTil(legeListe.stoerrelse(), new Spesialist(navn, Integer.toString(kontrollId)));
                            System.out.println("Lagt til Legespesialist: " + navn);
                        }
                    }

                } else if (linje.startsWith("# Resepter")) {
                    System.out.println("\n---Leser Reseptdata---");
                    while (fil.hasNextLine()) {
                        linje = fil.nextLine();

                        if (linje.charAt(0) == '#') {
                            break; // Ønsker å starte på nytt, når linje er lik ny overskrift
                        }

                        String[] deler = linje.split(","); 
                        int legemiddelNummer = Integer.parseInt(deler[0]); 
                        String legeNavn = deler[1];
                        int pasientID = Integer.parseInt(deler[2]); 
                        String type = deler[3];

                        if (type.equals("hvit")) {
                            int reit = Integer.parseInt(deler[4]); 
                            finnLegeMedNavn(legeNavn).skrivHvitResept(legemiddelListe.hent(legemiddelNummer), finnPasientMedId(pasientID), reit);

                        } else if (type.equals("blaa")) {
                            int reit = Integer.parseInt(deler[4]); 
                            finnLegeMedNavn(legeNavn).skrivBlaaResept(legemiddelListe.hent(legemiddelNummer), finnPasientMedId(pasientID), reit); 

                        } else if (type.equals("militaer")) {
                            finnLegeMedNavn(legeNavn).skrivMilResept(legemiddelListe.hent(legemiddelNummer), finnPasientMedId(pasientID), 0);

                        } else if (type.equals("p")) {
                            int reit = Integer.parseInt(deler[4]); 
                            finnLegeMedNavn(legeNavn).skrivPResept(legemiddelListe.hent(legemiddelNummer), finnPasientMedId(pasientID), reit);
                        }

                        System.out.println("Legen " + legeNavn + " har skrevet ut Resepten: " + type);
                    }

                } else {

                    break; 
                }
            }

            fil.close();

        } catch (Exception e) {
            System.out.println("Error oppstod ved lesing av fil: " + e);
        }
    }

    public Pasient finnPasientMedId(int Id) { // Egen metode for å finne Pasient med Id
        for (Pasient pasient : pasientListe) {
            if (pasient.Id == Id) {
                return pasient; 
            }
        }

        return null; 
    }

    public Lege finnLegeMedNavn(String legeNavn) { // Egen metode for å finne Lege med legeNavn
        for (Lege lege : legeListe) {
            if (lege.hentNavn().equals(legeNavn)) {
                return lege; 
            }
        } 

        return null; 
    }

    public void skrivUt() {
        System.out.println("\n---Skriver ut registrerte Pasienter---");
        System.out.println(pasientListe);

        System.out.println("---Skriver ut registrerte Legemiddel---");
        System.out.println(legemiddelListe);

        System.out.println("---Skriver ut registrerte Leger i ordnet rekkefølge---");
        ArrayList<Lege> tempLegeListe = new ArrayList<>(); 

        for (Lege lege : legeListe) { // Lager en midlertidig ArrayList, for å kunne bruke sort for List interfacet
            tempLegeListe.add(lege);    
        }

        Collections.sort(tempLegeListe);

        for (Lege lege : tempLegeListe) {
            System.out.println(lege);
        }

        System.out.println("\n---Skriver ut registrerte Resepter for hver Lege---");
        for (Lege lege : legeListe) {
            System.out.println(lege.utskrevneResepter); 
        }
    }
}





