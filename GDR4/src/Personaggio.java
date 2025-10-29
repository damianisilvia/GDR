import java.util.*;

public abstract class Personaggio {
    private String nome;
    private int puntiVita;
    private int puntiVitaMax;
    private int forza;
    private List<Oggetto> inventario;
    protected int oro;

    public Personaggio(String nome, int puntiVita, int puntiVitaMax, int forza, List<Oggetto> inventario, int oro) {
        this.nome = nome;
        this.puntiVita = puntiVita;
        this.puntiVitaMax = puntiVitaMax;
        this.forza = forza;
        this.inventario = new ArrayList<>(inventario);
        this.oro = oro;
    }
    public Personaggio(String nome, int puntiVita, int puntiVitaMax, int forza, List<Oggetto> inventario) {
        this(nome, puntiVita, puntiVitaMax, forza, inventario, 0); // default: oro = 0
    }

    public String getNome() { return nome; }
    public int getPuntiVita() { return puntiVita; }
    public int getPuntiVitaMax() { return puntiVitaMax; }
    public int getForza() { return forza; }
    public int getOro() { return oro; }
    public void aggiungiOro(int valore) { oro += valore; }

    public List<Oggetto> getInventario() { return inventario; }

    public boolean isVivo() { return puntiVita > 0; }

    public void subisciDanno(int danno) {
        puntiVita -= danno;
        System.out.println(nome + " ha subito " + danno + " danni. HP rimanenti: " + Math.max(puntiVita, 0));
    }


    public void setOro(int oro) {
        this.oro = oro;
    }

    public void aggiungiOggetto(Oggetto o) { inventario.add(o); }

    public void curati() {
        for (Oggetto o : new ArrayList<>(inventario)) {
            if (o instanceof Pozione p) {
                puntiVita = Math.min(puntiVita + p.getHpRipristino(), puntiVitaMax);
                System.out.println(nome + " usa " + p.getNome() + " e recupera " + p.getHpRipristino() + " HP!");
                inventario.remove(o);
                return;
            }
        }
        System.out.println(nome + " non ha pozioni!");
    }

    public abstract void attacca(Personaggio nemico);
}
