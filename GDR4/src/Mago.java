import java.util.ArrayList;
import java.util.List;

public class Mago extends Personaggio {

    private static final int FIREBALL_POWER = 20;
    private int mana;

    // 🔹 Costruttore completo
    public Mago(String nome, int puntiVita, int puntiVitaMax, int forza, List<Oggetto> inventario, int mana) {
        super(nome, puntiVita, puntiVitaMax, forza, inventario);
        this.mana = mana;
    }

    // 🔹 Costruttore semplificato (usa ArrayList vuoto e mana di default)
    public Mago(String nome, int puntiVita, int forza, int puntiVitaMax) {
        this(nome, puntiVita, puntiVitaMax, forza, new ArrayList<>(), 50); // mana default = 50
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void attacca(Personaggio nemico) {
        if (mana >= 10) {
            System.out.println(getNome() + " lancia una palla di fuoco! 🔥");
            nemico.subisciDanno(getForza() + FIREBALL_POWER);
            mana -= 12;
        } else {
            System.out.println(getNome() + " non ha abbastanza mana, attacca con il bastone.");
            nemico.subisciDanno(getForza());
        }
    }
}
