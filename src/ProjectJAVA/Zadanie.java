package ProjectJAVA;

import java.time.LocalDateTime;
import java.util.*;

public class Zadanie extends Thread {

    private static int nextId = 1;
    private final int id;
    private final String nazwa;
    private final String opis;
    private Stan stan;
    private final LocalDateTime dataUtworzenia;
    private LocalDateTime dataZakonczenia;
    private final int czasWykonania;
    private final boolean zatwierdzone;
    private Zespol zespol;
    private final Set<Pracownik> przypisaniPracownicy = new LinkedHashSet<>();



    public Zadanie(String nazwa, String opis, boolean zatwierdzone) {
        this.id = nextId++;
        this.nazwa = nazwa;
        this.opis = opis;
        this.zatwierdzone = zatwierdzone;
        this.stan = Stan.Utworzone;
        this.dataUtworzenia = LocalDateTime.now();
        this.czasWykonania = new Random().nextInt(6) + 3; // inicuje pole czas wykonania od 3 do 8
    }



    //ma parametr opis domyślny i false dla zatwierdzenia
    // celem tego konstruktora jest: tworzenie zadań w trybie projektowania czyli
    // umozliwia tworzenie zadań które są początkowo w stanie wersji roboczej i nie
    // sa gotowe do wykonania

    public Zadanie(String nazwa) {
        this(nazwa, "Brak opisu", false);
    }



    public long getId() {
        return id;
    }


    public void przydzielZespol(Zespol zespol) {
        this.zespol = zespol;
    }


    public void przydzielPracownika(Pracownik pracownik) {
        if (pracownik != null) {
            przypisaniPracownicy.add(pracownik);
        }
    }

    public void usunPracownika(Pracownik pracownik) {
        if (pracownik != null) {
            przypisaniPracownicy.remove(pracownik);
        }
    }

    public void usunZespol() {
        if (przypisaniPracownicy.isEmpty()) {
            this.zespol = null;
        } else {
            throw new IllegalStateException("Zadanie ma przypisanych pracowników - nie można usunać");
        }
    }



    public String getStanZadania() {
        return stan.toString();
    }

    public String getNazwa() {
        return nazwa;
    }


    public boolean isZatwierdzone() {
        return zatwierdzone;
    }


    public enum Stan {
        Utworzone,
        Rozpoczete,
        Zakonczone
    }

    @Override
    public void run() {
        if (zespol == null) {
            System.out.println("Zadanie: " + nazwa + " nie moze byc wykonane - brak przypisanego zespolu.");
            return;
        }

        if (przypisaniPracownicy.isEmpty()) {
            System.out.println("Zadanie: " + nazwa + " nie moze byc wykonane - brak przypisanych pracowników.");
            return;
        }

        for (Pracownik p : przypisaniPracownicy) {
            if (!p.getCzyZdrowy()) {
                System.out.println("Zadanie: " + nazwa + " nie moze byc wykonane - przypisany pracownik "
                        + p.getImie() + " " + p.getNazwisko() + " jest chory");
                return;
            }

        }

        if (!zatwierdzone) {
            System.out.println("Zadanie: " + nazwa + " nie zostalo zatwierdzone.");
            return;
        }

        stan = Stan.Rozpoczete;

        try {
            for (int i = 1; i <= czasWykonania; i++) {
                System.out.println("Zadanie " + nazwa + ": " + i + "/" + czasWykonania + " sekund");
                Thread.sleep(1000);
            }

            stan = Stan.Zakonczone;
            dataZakonczenia = LocalDateTime.now();
            System.out.println("Zadanie " + nazwa + " zakończone o " + dataZakonczenia);

            for (Pracownik pracownik : przypisaniPracownicy) {
                pracownik.dodajZadanie(this);
            }

            if (zespol.getManager() != null) {
                zespol.getManager().dodajZadanie(this);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zadanie zadanie = (Zadanie) o;
        return id == zadanie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Zadanie{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", opis='" + opis + '\'' +
                ", stan=" + stan +
                ", dataUtworzenia=" + dataUtworzenia +
                ", dataZakonczenia=" + dataZakonczenia +
                ", czasWykonania=" + czasWykonania +
                ", zatwierdzone=" + zatwierdzone +
                '}';
    }
}
