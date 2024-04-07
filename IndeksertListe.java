// Subklassen IndeksertListe<E>, arver fra Lenkeliste<E>
// Deler av koden er hentet fra Innlevering 1

public class IndeksertListe<E> extends Lenkeliste<E> {

    public void leggTil(int pos, E x) { // Setter inn element E i posisjon pos
        if (pos < 0 || pos > stoerrelse()) {
            throw new UgyldigListeindeks(pos); // Sjekk at pos er en gyldig indeks. Vil "throwe" en RunTimeException
        }

        Node ny = new Node(x); // Oppretter en ny node som skal legges til
        Node temp = start; // Setter Node temp lik start node

        if (pos == 0) { // Egen metode dersom pos == 0
            ny.neste = start; // Ny sin neste referanse peker på nåværende start node
            start = ny; // Oppdaterer deretter start til å peke på neste node, som nå vil være start-noden i listen
            return;
        }

        if (pos == stoerrelse()) {
            for (int i = 0; i < stoerrelse()-1; i++) {
                temp = temp.neste;
            }
            
            temp.neste = ny;
            return;
        }

        for (int i = 0; i < pos-1; i++) { 
            temp = temp.neste;
        }

        Node nesteOriginal = temp.neste; // Lagrer verdien av opprinnelig temp.neste
        temp.neste = ny; 
        ny.neste = nesteOriginal; // Sikrer at ny peker til den opprinnelige neste noden i listen

    }

    public void sett(int pos, E x) { // Erstatter elment E med elementet i posisjon pos
        if (pos < 0 || pos > stoerrelse()-1) {
            throw new UgyldigListeindeks(pos); // Sjekk at pos er en gyldig indeks. Vil "throwe" en RunTimeException
        }

        Node temp = start; // Setter Node temp lik start node

        if (pos == 0) {
            start.data = x;
        }

        for (int i = 0; i < pos; i++) { 
            temp = temp.neste;
        }

        temp.data = x; 
    }

    public E hent(int pos) { // Returnerer element i posisjon pos
        if (pos < 0 || pos > stoerrelse()-1) {
            throw new UgyldigListeindeks(pos); // Sjekk at pos er en gyldig indeks. Vil "throwe" en RunTimeException
        }

        Node peker = start; // Setter peker lik første Node i listen, teller frem til riktig objekt
        for (int i = 0; i<pos; i++) {
            peker = peker.neste;
        }

        return peker.data;
    }

    public E fjern(int pos) { // Fjerner element i posisjon pos
        if (pos < 0 || pos > stoerrelse()-1) {
            throw new UgyldigListeindeks(pos); // Sjekk at pos er en gyldig indeks. Vil "throwe" en RunTimeException
        }

        if (pos == 0) {
            Node midlertidig = start; 
            start = start.neste;
            return midlertidig.data;
        }

        Node peker = start; // Setter peker lik første Node i listen, teller frem til riktig objekt
        for (int i = 0; i<pos-1; i++) {
            peker = peker.neste;
        }

        Node temp = peker.neste;
        peker.neste = peker.neste.neste;
        return temp.data;
    }
}
