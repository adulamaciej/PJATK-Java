package ProjectJAVA;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Manager extends Recepcjonista {

    private static int nextId = 1;
    private final int id;
    private final Set<Zespol> zespolyManagera = new LinkedHashSet<>();
    private final Set<Zadanie> zadaniaManagera = new LinkedHashSet<>(); // manager powinien widziec
    // zadania w kolejonsci ich przypisania

    public Manager(String imie, String nazwisko, LocalDate dataUrodzenia,
                   DzialPracownikow dzial, String login, String haslo) {
        super(imie, nazwisko, dataUrodzenia, dzial, login, haslo);
        this.id = nextId++;
    }

    public void dodajZespoly(Zespol zespol) {
        if (zespol != null) {
            zespolyManagera.add(zespol);
        }
    }

    @Override
    public void dodajZadanie(Zadanie zadanie) {
        if (zadanie != null) {
            zadaniaManagera.add(zadanie);
        }
    }

    public void usunZespol(Zespol zespol) {
        if (zespol != null) {
            zespolyManagera.remove(zespol);
        }
    }

    public void usunZadanie(Zadanie zadanie) {
        if (zadanie != null) {
            zadaniaManagera.remove(zadanie);
        }
    }

    public Set<Zespol> getZespoly() {
        return new LinkedHashSet<>(zespolyManagera);
    }

    public Set<Zadanie> getZadaniaManagera() {
        return new LinkedHashSet<>(zadaniaManagera);
    }

    @Override
    public boolean czyPrzychodziNaCzas() {
        return getDniPunktualne() > 19;
    }

    @Override
    public long iloscWykonanychZadan() {
        return getZadania().size() + (zadaniaManagera.size() * 2L);
    }

    @Override
    public double ocenaPracy() {
        double base = iloscWykonanychZadan() * 0.5;
        return czyPrzychodziNaCzas() ? base + 1.5 : base;
    }



    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", imie='" + getImie() + '\'' +
                ", nazwisko='" + getNazwisko() + '\'' +
                ", initial='" + getInitial() + '\'' +
                ", zespoly=" + zespolyManagera.size() +
                ", zadania=" + getZadania().size() +
                ", zadaniaManagerskie=" + zadaniaManagera.size() +
                '}';
    }
}
