import java.util.*;

public class GiocoGDR {

    public static void main(String[] args) throws InterruptedException {
        // Creazione personaggi principali
        Guerriero guerriero = new Guerriero("Marius", 100, 100, 20, new ArrayList<>(), 50);
        Arciere arciere = new Arciere("Fiero", 80, 80, 15, new ArrayList<>(), 50);
        Mago mago = new Mago("Annica", 70, 70, 10, new ArrayList<>(), 50);
        Drago drago = new Drago();
        Mercante mercante = new Mercante();

        // 🧳 ZAINO INIZIALE (aggiungiamo oggetti)
        guerriero.aggiungiOggetto(new Pozione("Pozione curativa", 25));
        guerriero.aggiungiOggetto(new Oggetto("Spada lunga"));

        arciere.aggiungiOggetto(new Pozione("Pozione minore", 15));
        arciere.aggiungiOggetto(new Oggetto("Arco corto"));

        mago.aggiungiOggetto(new Pozione("Pozione di mana", 20));
        mago.aggiungiOggetto(new Oggetto("Bastone magico"));

        // 👋 Benvenuto
        System.out.println("✨ Benvenuto nel mondo di GDR Deluxe! ✨");
        Thread.sleep(1000);

        // Mostra zaini prima del combattimento
        mostraZaino(guerriero);
        mostraZaino(arciere);
        mostraZaino(mago);

        // Inizio del gioco
        combattimentoConScanner(guerriero, arciere, mago, drago, mercante);
    }


    public static void combattimentoConScanner(Guerriero guerriero, Arciere arciere, Mago mago, Drago drago, Mercante mercante) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int turno = 1;

        while (true) {
            System.out.println("\n========== 🕹️ TURNO " + turno + " ==========");

            // Se tutti i nemici sono sconfitti → vittoria
            if (!drago.isVivo() && !mercante.isVivo()) {
                System.out.println("\n🎉 COMPLIMENTI! Gli eroi hanno sconfitto tutti i nemici!");
                break;
            }

            // Se tutti i giocatori sono morti → sconfitta
            if (!guerriero.isVivo() && !arciere.isVivo() && !mago.isVivo()) {
                System.out.println("\n💀 GAME OVER! Tutti gli eroi sono caduti in battaglia!");
                break;
            }

            // Scelta del personaggio
            System.out.println("\n1. Guerriero\n2. Arciere\n3. Mago");
            System.out.print("Scegli il tuo personaggio (1, 2, 3): ");
            int scelta = scanner.nextInt();

            Personaggio scelto = switch (scelta) {
                case 1 -> guerriero;
                case 2 -> arciere;
                case 3 -> mago;
                default -> null;
            };

            if (scelto == null || !scelto.isVivo()) {
                System.out.println("❌ Scelta non valida o personaggio già sconfitto!");
            } else {
                azioneGiocatore(scelto, mercante, drago, scanner);
            }

            // Attacchi dei nemici solo se vivi
            if (drago.isVivo()) {
                drago.attacca(guerriero);
            }
            if (mercante.isVivo()) {
                mercante.attacca(arciere);
            }

            turno++;
            Thread.sleep(1000); // piccola pausa per rendere il gioco leggibile
        }

        System.out.println("\n=== ⚔️ FINE PARTITA ⚔️ ===");
        System.out.println("Grazie per aver giocato a GDR Deluxe!");
    }


    public static void azioneGiocatore(Personaggio p, Mercante mercante, Drago drago, Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("🎯 Turno di " + p.getNome());
        System.out.println("❤️ HP: " + p.getPuntiVita() + "/" + p.getPuntiVitaMax());
        System.out.println("💰 Oro: " + p.getOro());
        System.out.print("🎒 Zaino: ");
        if (p.getInventario().isEmpty()) {
            System.out.println("vuoto");
        } else {
            for (Oggetto o : p.getInventario()) {
                System.out.print(o.getNome() + "  ");
            }
            System.out.println();
        }
        System.out.println("==============================");

        // nuovo menù tattico ✨
        System.out.println("1. Attacca ⚔️");
        System.out.println("2. Cura (usa pozione) 💊");
        System.out.println("3. Apri zaino 🎒");
        System.out.println("4. Usa abilità speciale 💥");
        System.out.println("5. Visita il mercante 🛍️");
        System.out.print("Scelta: ");
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1 -> {
                Personaggio nemico = scegliNemico(drago, mercante);
                p.attacca(nemico);
            }
            case 2 -> p.curati();
            case 3 -> mostraZaino(p);
            case 4 -> usaAbilitaSpeciale(p, drago, mercante);
            case 5 -> mercante.apriNegozio(p, scanner);
            default -> System.out.println("Scelta non valida!");
        }
    }
    public static void usaAbilitaSpeciale(Personaggio p, Drago drago, Mercante mercante) {
        Personaggio nemico = scegliNemico(drago, mercante);

        if (p instanceof Guerriero g) {
            int danno = g.getForza() * 2 + (int)(Math.random() * 10);
            System.out.println("🛡️ " + g.getNome() + " usa COLPO POSSENTE! 💥");
            nemico.subisciDanno(danno);

        } else if (p instanceof Arciere a) {
            System.out.println("🏹 " + a.getNome() + " usa DOPPIO TIRO!");
            int danno1 = a.getForza() + (int)(Math.random() * 5);
            int danno2 = a.getForza() + (int)(Math.random() * 5);
            nemico.subisciDanno(danno1 + danno2);
            System.out.println("Totale danno: " + (danno1 + danno2));

        } else if (p instanceof Mago m) {
            System.out.println("🔥 " + m.getNome() + " lancia una PALLA DI FUOCO!");
            int danno = m.getForza() * 3;
            nemico.subisciDanno(danno);
            System.out.println("Il nemico è avvolto dalle fiamme! 🔥");

        } else {
            System.out.println(p.getNome() + " non ha abilità speciali!");
        }
    }


    public static Personaggio scegliNemico(Drago drago, Mercante mercante) {
        if (drago.isVivo()) return drago;
        else return mercante;
    }

    public static void mostraZaino(Personaggio p) {
        System.out.println("\n🎒 Zaino di " + p.getNome() + ":");
        if (p.getInventario().isEmpty()) {
            System.out.println("Vuoto!");
        } else {
            for (Oggetto o : p.getInventario()) {
                System.out.println("- " + o.getNome());
            }
        }
        System.out.println("💰 Oro: " + p.getOro());
    }
}
