package ProjectJAVA;

import java.time.LocalDate;

public class Trener extends Pracownik {

    private final String specjalizacja;
    private static int nextId = 1;
    private final int id;

    public Trener(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial, String specjalizacja) {
        super(imie, nazwisko, dataUrodzenia, dzial);
        this.specjalizacja = specjalizacja;
        this.id = nextId++;
    }


    @Override
    public boolean czyPrzychodziNaCzas() {
        return getDniPunktualne() > 18;
    }

    @Override
    public long iloscWykonanychZadan() {
        return getZadania().stream()
                .filter(zadanie -> zadanie.getStanZadania().equals("Zakonczone"))
                .count();
    }

    @Override
    public double ocenaPracy() {
        double base = iloscWykonanychZadan() * 0.25;
        double bonus = czyPrzychodziNaCzas() ? 2.0 : 0;
        if (specjalizacja.equals("Java")) {
            bonus += 0.5;
        }
        return base + bonus;
    }


    @Override
    public int getId() { return id; }



    @Override
    public String toString() {
        return "Trener{" +
                "id=" + id +
                ", imie='" + getImie() + '\'' +
                ", nazwisko='" + getNazwisko() + '\'' +
                ", specjalizacja='" + specjalizacja + '\'' +
                ", zadania=" + getZadania().size() +
                '}';
    }

}

