import java.util.ArrayList;
import java.util.List;

public class Arciere extends Personaggio {

    private static final int ARROW_BONUS = 5;
    private int frecce;

    // 🔹 Costruttore completo
    public Arciere(String nome, int puntiVita, int puntiVitaMax, int forza, List<Oggetto> inventario, int frecce) {
        super(nome, puntiVita, puntiVitaMax, forza, inventario);
        this.frecce = frecce;
    }

    // 🔹 Costruttore semplificato
    public Arciere(String nome, int puntiVita, int puntiVitaMax, int forza) {
        this(nome, puntiVita, puntiVitaMax, forza, new ArrayList<>(), 20); // 20 frecce default
    }

    public int getFrecce() {
        return frecce;
    }

    public void setFrecce(int frecce) {
        this.frecce = frecce;
    }

    @Override
    public void attacca(Personaggio nemico) {
        if (frecce > 0) {
            System.out.println(getNome() + " scocca una freccia! 🏹");
            nemico.subisciDanno(getForza() + ARROW_BONUS);
            frecce--;
        } else {
            System.out.println(getNome() + " non ha più frecce! Attacca con il pugnale!");
            nemico.subisciDanno(getForza() / 2);
        }
    }
}
