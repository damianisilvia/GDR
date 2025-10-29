import java.util.ArrayList;
import java.util.List;

public class Guerriero extends Personaggio {

    public Guerriero(String nome, int hp, int hpMax, int forza, List<Oggetto> inventario, int oro) {
        super(nome, hp, hpMax, forza, inventario, oro); // ✅ passa anche l’oro al costruttore padre
    }

    @Override
    public void attacca(Personaggio nemico) {
        int bonus = 0;

        // 🔍 Cerca se nello zaino ci sono armi
        for (Oggetto o : getInventario()) {
            if (o.getNome().toLowerCase().contains("spada")) {
                bonus += 5; // la spada aumenta il danno
            }
            if (o.getNome().toLowerCase().contains("ascia")) {
                bonus += 8; // l’ascia è ancora più potente
            }
        }

        int danno = getForza() + bonus + (int)(Math.random() * 5);
        System.out.println(getNome() + " colpisce con la sua arma! ⚔️ Danno: " + danno);
        nemico.subisciDanno(danno);
    }

}
