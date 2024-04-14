// Klassen Legesystem

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Legesystem {

    public IndeksertListe<Pasient> pasientListe = new IndeksertListe<>(); 
    public IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>(); 
    public IndeksertListe<Lege> legeListe = new IndeksertListe<>(); 

    private Scanner scanner = new Scanner(System.in); 
    
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

    public void meny() {
        System.out.println("\n**********LEGESYSTEM**********");
        System.out.println("1: Skrive ut fullstendig oversikt over pasienter, leger, legemidler og resepter");
        System.out.println("2: Opprette og legge til nye elementer i legesystemet");
        System.out.println("3: Bruke en gitt resept fra listen til en pasient");
        System.out.println("4: Skrive ut forskjellige former for statistikk");
        System.out.println("5: Skrive alle data til fil");
        System.out.println("0: Avslutt");
    }

    public void kommandoLokke() {
        int inputBruker = -1; 

        while (inputBruker != 0) {
            if (inputBruker == 1) {
                skrivUt();

            } else if (inputBruker == 2) {
                System.out.println("Hvilket element vil du legge til (Skriv inn leger, pasient osv?");
                System.out.println("1: Leger");
                System.out.println("2: Pasient");
                System.out.println("3: Legemidler");
                System.out.println("4: Resept");

                String typeL = scanner.nextLine().toLowerCase(); 
                List<String> gyldigElement= List.of("leger", "pasient", "legemidler", "resept"); 

                if (gyldigElement.contains(typeL)) {
                    switch (typeL) {
                        case "leger":
                            System.out.println("Hva heter legen?");
                            String legeNavn = scanner.nextLine(); 

                            System.out.println("Hva er kontrollId?");
                            int kontrollId = Integer.parseInt(scanner.nextLine()); 

                            leggTilLege(legeNavn, kontrollId);
                            break;

                        case "pasient":
                            System.out.println("Hva heter pasienten?");
                            String pasientNavn = scanner.nextLine(); 

                            System.out.println("Hva er pasienten sitt fødselsnummer?");
                            String fødselsnummer = scanner.nextLine(); 

                            leggTilPasient(pasientNavn, fødselsnummer);
                            break;

                        case "legemilder":
                            System.out.println("Hvilken type legemiddel?");
                            String type = scanner.nextLine(); 

                            System.out.println("Hva heter legemiddelet?");
                            String navn = scanner.nextLine(); 

                            System.out.println("Hva koster det?");
                            int pris = Integer.parseInt(scanner.nextLine()); 

                            System.out.println("Virkestoff?");
                            int virkestoff = Integer.parseInt(scanner.nextLine()); 

                            System.out.println("Styrke?");
                            int styrke = Integer.parseInt(scanner.nextLine()); 

                            leggTilLegemiddel(type, navn, pris, virkestoff, styrke);
                            break;

                        case "resept": 
                            System.out.println("Hvilken type resept?");
                            String typeResept = scanner.nextLine(); 

                            System.out.println("Utskrivende lege?");
                            String utskrivendelegeNavn = scanner.nextLine(); 

                            System.out.println("Navn legemiddel?");
                            String legeMiddelNavn = scanner.nextLine(); 

                            System.out.println("Pasient ID?");
                            int pasientID = Integer.parseInt(scanner.nextLine()); 

                            System.out.println("Reit?");
                            int reit = Integer.parseInt(scanner.nextLine()); 
                            break;
                    }
                }

            } else if (inputBruker == 3) {

                System.out.println("Hvilken pasient vil du se resept for (velg ut fra indeks)?: ");
                System.out.println(this.pasientListe);

                int indeks = Integer.parseInt(scanner.nextLine()); 
                System.out.println("Valg paseint: " + pasientListe.hent(indeks));

                brukResept(indeks);

                Pasient pasient = pasientListe.hent(indeks); 
                
            } else if (inputBruker == 4) {

                System.out.println("Skriver ut statistikk for antall Vanedannende legemidler: "); 
                System.out.println("Totalt antall: " + skrivAntallVanedannende()); 

            } else if (inputBruker == 5) {

                System.out.println("Skriver ut pasienter til ønsket fil: "); 
                System.out.println("Filnavn? ");
                String filnavn = scanner.nextLine(); 
                skrivTilFil(filnavn); 
            }

            meny();
            inputBruker = Integer.parseInt(scanner.nextLine());
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

    public Legemiddel finnLegemiddel(String legemiddelNavn) {
        for (Legemiddel legemiddel : legemiddelListe) {
            if(legemiddel.hentNavn().equals(legemiddelNavn)) {
                return legemiddel;
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

    public void leggTilLege(String legeNavn, int kontrollId) {
        
        if (kontrollId == 0) {
            legeListe.leggTil(legeListe.stoerrelse(), new Lege(legeNavn));
        } else {
            legeListe.leggTil(legeListe.stoerrelse(), new Spesialist(legeNavn, Integer.toString(kontrollId)));
        }

        System.out.print("Lagt til Legen " + legeNavn + " i legeliste"); 
    }

    public void leggTilPasient(String pasientNavn, String fødselsnummer) {
        pasientListe.leggTil(pasientListe.stoerrelse(), new Pasient(pasientNavn, fødselsnummer));
    }

    public void leggTilLegemiddel(String type, String navn, int pris, int virkestoff, int styrke) {
        String typeL = type.toLowerCase();
        List<String> gyldigLegemiddelType = List.of("narkotisk", "vanlig", "vanedannende"); 

        if (gyldigLegemiddelType.contains(typeL)) {
            switch (typeL) {
                case "narkotisk":
                    legemiddelListe.leggTil(legemiddelListe.stoerrelse(), new Narkotisk(styrke, navn, pris, virkestoff));
                    break;
                case "vanilg": 
                    legemiddelListe.leggTil(legemiddelListe.stoerrelse(), new Vanlig(navn, pris, virkestoff));
                    break;
                case "vanedannende": 
                    legemiddelListe.leggTil(legemiddelListe.stoerrelse(), new Vanedannende(styrke, navn, pris, virkestoff));
                    break;
            }
        }
    }

    public void leggTilResept(String type, String legeNavn, String legemiddelNavn, int pasientID, int reit) throws UlovligUtskrift {
        String typeL = type.toLowerCase();
        List<String> gyldigReseptType = List.of("hvit", "blaa", "militaer", "p"); 

        Lege lege = finnLegeMedNavn(legeNavn); 
        Legemiddel legemiddel = finnLegemiddel(legemiddelNavn);
        Pasient pasient = finnPasientMedId(pasientID); 

        if (lege != null && gyldigReseptType.contains(typeL) && legemiddel != null && pasient != null) {
            switch (typeL) {
                case "hvit":
                    lege.skrivHvitResept(legemiddel, pasient, reit); 
                    break;
                case "blaa": 
                    lege.skrivBlaaResept(legemiddel, pasient, reit); 
                    break; 
                case "militaer":
                    lege.skrivMilResept(legemiddel, pasient, reit); 
                    break; 
                case "p":
                    lege.skrivPResept(legemiddel, pasient, reit); 
                    break;
                default: 
                    throw new IllegalArgumentException("Ugyldig resepttype");
            }


        } else {
               throw new UlovligUtskrift(lege, legemiddel); 
        }
    }

    public void brukResept(int indeks) {
        Pasient pasient = pasientListe.hent(indeks); 

        System.out.println("Hvilken resept?");
        if (pasient.hentReseptListe().isEmpty()) {
            System.out.println("Ingen resepter tilgjengelig for denne pasienten.");
            
        } else {
            int teller = 0;
            for (Resept resept : pasient.hentReseptListe()) {
                System.out.println(teller + ": " + resept.hentLegemiddel().hentNavn() + " (" + resept.hentReit() + " reit)");
                teller++;
            }
        }

        int reseptIndeks = Integer.parseInt(scanner.nextLine());
        Resept resept = pasient.hentReseptListe().get(reseptIndeks); 

        resept.bruk(); 
    }

    public int skrivAntallVanedannende() {
        int teller = 0;

        for (Lege lege : legeListe) {
            for (Resept resept : lege.hentResepterListe()) {
                if (resept.hentLegemiddel() instanceof Vanedannende) {
                    teller++;
                }
            }
        }

        return teller; 
    }

    public void skrivTilFil(String filnavn) {
        try {

            PrintWriter utskrift = new PrintWriter(filnavn); 

            // Skriver pasienter
            utskrift.println("# Pasienter (navn, fnr)");
            for (Pasient pasient : pasientListe) {
                utskrift.println(pasient.hentNavn() + "," + pasient.hentFødselsnummer());
            }

            utskrift.close();

        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke skrive til fil: " + e);
        }
    }
}





