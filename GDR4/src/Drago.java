import java.util.ArrayList;

public class Drago extends Personaggio {
    public Drago() {
        super("Drago", 200, 30, 25, new ArrayList<>()); // hp, attacco, forza, inventario vuoto
    }

    public void sputaFuoco(Personaggio nemico) {
        System.out.println(getNome() + " sputa fuoco! 🔥");
        nemico.subisciDanno(50);
    }

    public void zampata(Personaggio nemico) {
        System.out.println(getNome() + " colpisce con una zampata! 🐾");
        nemico.subisciDanno(20);
    }

    @Override
    public void attacca(Personaggio nemico) {
        if (Math.random() < 0.5) {
            sputaFuoco(nemico);
        } else {
            zampata(nemico);
        }
    }
}
