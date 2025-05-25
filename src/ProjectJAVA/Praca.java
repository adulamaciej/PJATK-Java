package ProjectJAVA;

import java.util.*;

public class Praca implements Runnable {

    private static int nextId = 1;
    private final int id;
    private final Set<Zadanie> zadania;
    private final String opis;
    private final Zespol zespol;
    private final static Map<Integer, Praca> wszystkiePrace = new HashMap<>();



    public Praca(String opis, Zespol zespol, Set<Zadanie> zadania){
        this.id = nextId++;
        this.opis = opis;
        this.zespol = zespol;
        this.zadania = zadania;
        wszystkiePrace.put(id, this);
    }


    public static Praca getPracaByZadanieId(long zadanieId) {
        for (Praca praca : wszystkiePrace.values()) {
            for (Zadanie zadanie : praca.getZadania()) {
                if (zadanie.getId() == zadanieId) {
                    return praca;
                }
            }
        }
        return null;
    }


    public Set<Zadanie> getZadania() {
        return new LinkedHashSet<>(zadania);
    }


    public int getId() {
        return id;
    }


    @Override
    public void run() {
        if (zadania.isEmpty()) {
            System.out.println("Praca (ID=" + id + "): Brak zadań do wykonania");
            return;
        }
        System.out.println("Rozpoczęto pracę (ID=" + id + ") dla zespołu " + zespol.getNazwa());

        List<Thread> uruchomioneWatki = new ArrayList<>();

        for (Zadanie zadanie : zadania) {
            if (zadanie.isZatwierdzone()) {
                zadanie.przydzielZespol(zespol);
                Thread zadanieThread = new Thread(zadanie);
                zadanieThread.start();
                uruchomioneWatki.add(zadanieThread);
            } else {
                System.out.println("Zadanie " + zadanie.getNazwa() + " nie jest zatwierdzone. Pomijam.");
            }
        }

        for (Thread watek : uruchomioneWatki) {
            try {
                watek.join();
            } catch (InterruptedException e) {
                System.out.println("Przerwano wykonywanie zadania: " + e.getMessage());
            }
        }

        System.out.println("Zakończono pracę (ID=" + id + ") dla zespołu " + zespol.getNazwa());
    }


    @Override
    public String toString() {
        return "Praca{" +
                "id=" + id +
                ", opis='" + opis + '\'' +
                ", iloscZadan=" + zadania.size() +
                ", zespol=" + zespol.getNazwa() +
                '}';
    }


}