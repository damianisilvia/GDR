public class Pozione extends Oggetto {
    private int hpRipristino;

    public Pozione(String nome, int hpRipristino) {
        super(nome);
        this.hpRipristino = hpRipristino;
    }

    public int getHpRipristino() {
        return hpRipristino;
    }

    public void setHpRipristino(int hpRipristino) {
        this.hpRipristino = hpRipristino;
    }
}
