// Egendefinert unntaksklasse

public class UgyldigListeindeks extends RuntimeException {
    UgyldigListeindeks (int indeks) { // Konstrutkør, initialiserer et objekt av "UgyldigListeIndeks", og gir en spesifikk feilmelding
        super("Ugyldig indeks: " + indeks); // Kaller superklassen, sender feilmelding dersom indeks i konstruktør er ugyldig
    }
}

