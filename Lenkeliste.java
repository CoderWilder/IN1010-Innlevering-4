// Abstrakt Klasse Lenkeliste
// Implementerer interface Liste<E>
// Subklasse Node og LenkelisteIterator som implementerer Iterator<E>
// Deler av koden er hentet fra Innlevering 2

import java.util.Iterator;
import java.util.NoSuchElementException;

abstract public class Lenkeliste<E> implements Liste<E> {

    protected Node start = null;
    int stoerrelse; 

    protected class Node { // Definerer indre klassen Node (Protected, kan brukes av subklasser og klassen)

        Node neste = null;
        E data;

        Node (E x) {
            this.data = x;
        }
    }

    @Override 
    public Iterator<E> iterator() { // Implementerer iterator metoden, siden Liste<E> utvider Iterable<E>. 
        return new LenkelisteIterator(); // Returnerer instans av indre klassen, som implementerer Iterator<E>
    }


    protected class LenkelisteIterator implements Iterator<E>{ // Definerer den indre klassen LenkelisteIterator, implementerer Iterator grensesnittet 

        private Node temp = start; // Vil oppdateres for hvert kall av next() metoden, for iteratoren

        @Override
        public boolean hasNext() { // Returnerer true dersom iterasjonen har flere element

            return temp != null;
        }
    
        @Override
        public E next() { // Returnerer neste element i iterasjonen 

            if (!hasNext()) {
                throw new NoSuchElementException(); 
            } 

            E data = temp.data; 
            temp = temp.neste; 
            return data; 
        }
        
    }

    @Override
    public int stoerrelse() { // Returnerer størrelsen, antall elementer i List 
        int teller = 0;
        Node peker = start;

        while (peker != null) { // Teller hver node i Lenketliste
            teller++;
            peker = peker.neste;
        }

        return teller;
    }

    @Override 
    public void leggTil(E x) { // Legger til et element sist i listen
        Node ny = new Node(x);

        if (start == null) { // Sjekker først om første node i List er definert
            start = ny; // Oppretter en ny start Node

        } else {
            Node temp = start;
            while (temp.neste != null) { 
                temp = temp.neste; // Sjekker her om node er siste element
            }

            temp.neste = ny; // Legger til Node ny på siste ledige plass
        }

        stoerrelse++; 
    }

    @Override
    public E hent() { // Returnerer første element i listen 

        return start.data;
    }

    @Override 
    public E fjern() { // Fjerner første elementet i List, og returnerer elementet 
        if (stoerrelse() == 0) {
            throw new UgyldigListeindeks(0);
        }

        Node fjernet = start;
        start = start.neste;

        stoerrelse--; 

        return fjernet.data;
    }

    @Override // Overksriver Object toString metode
    public String toString() { // Skriver ut data og indeks om hver node i listen 
        String svar = "****Lenkelisten har " + stoerrelse() + " elementer med følgende Indeks og tilhørende Objektdata****\n";

        Node temp = start;

        for (int i = 0; i < stoerrelse(); i++) {
            E info = temp.data;
            svar += "\nIndeks: " + i + "\nObjektdata: \n\n" + info + "\n";

            temp = temp.neste;
        }

        return svar;
    }
}

