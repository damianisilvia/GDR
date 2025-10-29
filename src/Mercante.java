import java.util.*;

public class Mercante extends Personaggio {

    public Mercante() {
        super("Mercante Corrotto", 50, 50, 5, new ArrayList<>(), 0);
    }

    @Override
    public void attacca(Personaggio nemico) {
        System.out.println(getNome() + " lancia un coltello!");
        nemico.subisciDanno(getForza());
    }

    public void apriNegozio(Personaggio acquirente, Scanner scanner) {
        System.out.println("\n🧺 Benvenuto nel negozio del Mercante!");
        System.out.println("1. Pozione piccola (+20 HP) - 10 monete");
        System.out.println("2. Pozione grande (+50 HP) - 20 monete");
        System.out.println("3. Esci dal negozio");
        System.out.print("Cosa vuoi comprare? ");
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1 -> compra(acquirente, new Pozione("Pozione Piccola", 20), 10);
            case 2 -> compra(acquirente, new Pozione("Pozione Grande", 50), 20);
            case 3 -> System.out.println("Arrivederci!");
            default -> System.out.println("Scelta non valida!");
        }
    }

    private void compra(Personaggio p, Pozione pozione, int costo) {
        if (p.getOro() >= costo) {
            p.aggiungiOggetto(pozione);
            p.aggiungiOro(-costo);
            System.out.println(p.getNome() + " ha comprato " + pozione.getNome() + " per " + costo + " monete.");
        } else {
            System.out.println("Non hai abbastanza oro!");
        }
    }
}
