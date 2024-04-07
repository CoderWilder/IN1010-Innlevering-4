// Interfacet Liste<E> utvider Iterable<E>
// Alle klasser som implementerer Liste<E> må implementere iterator() metoden

public interface Liste<E> extends Iterable<E> {

    int stoerrelse(); // Returnerer størrelse på Listen (antallet elementer)
    void leggTil(E x); // Legger objektet x av typen E til i Liste
    E hent(); // Returnerer ett element fra listen av typen E
    E fjern(); // Fjerner ett element fra Liste, og returnerer det fjernede elementet av typen E
    
}