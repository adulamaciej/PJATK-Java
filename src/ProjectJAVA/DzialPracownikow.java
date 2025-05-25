package ProjectJAVA;

import java.util.*;

public class DzialPracownikow {

    private static int nextId = 1;
    private final int id;
    private final String nazwa;
    private final static Set<String> istniejaceNazwy = new HashSet<>();
    private final Set<Pracownik> pracownicy = new HashSet<>();


    private DzialPracownikow(String nazwa) {
        this.id = nextId++;
        this.nazwa = nazwa;
    }

    public static DzialPracownikow createDzial(String nazwa) {
        if (istniejaceNazwy.contains(nazwa)) {
            throw new NotUniqueNameException("Nazwa dzialu '" + nazwa + "' juz istnieje!");
        }
        istniejaceNazwy.add(nazwa);
        return new DzialPracownikow(nazwa);
    }

    public void dodajPracownika(Pracownik pracownik) {
        if (pracownik != null) {
            pracownicy.add(pracownik);
        }
    }

    public void usunPracownika(Pracownik pracownik) {
        if (pracownik != null) {
            pracownicy.remove(pracownik);
        }
    }

    public Set<Pracownik> getPracownicy() {
        return new HashSet<>(pracownicy);
    }


    public String getNazwa() {
        return nazwa;
    }

    @Override
    public String toString() {
        return "DzialPracownikow{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", iloscPracownikow=" + pracownicy.size() +
                '}';
    }
}