package ProjectJAVA;

import java.util.*;

public class Zespol implements Iterable<Pracownik> {

    private static int nextId = 1;
    private final int id;
    private final String nazwa;
    private Manager manager;
    private final Set<Pracownik> pracownicy = new LinkedHashSet<>();



    public Zespol(String nazwa) {
        this.id = nextId++;
        this.nazwa = nazwa;
    }


    public void dodajManagera(Manager manager) {
        if (manager != null) {
            this.manager = manager;
            manager.dodajZespoly(this);
        }
    }


    public void usunManagera() {
        if (this.manager != null) {
            this.manager.usunZespol(this);
        }
    }


    public void dodajPracownika(Pracownik pracownik) {
        if (pracownik != null) {
            if (pracownik instanceof Manager) {
                System.out.println("Nie można dodać managera jako szeregowego pracownika.");
            } else {
                this.pracownicy.add(pracownik);
            }
        }
    }


    public void dodajPracownika(Set<Pracownik> pracownicy) {
        for (Pracownik pracownik : pracownicy) {
            dodajPracownika(pracownik);
        }
    }

    public void usunPracownika(Pracownik pracownik) {
        if (pracownik != null) {
            pracownicy.remove(pracownik);
        }
    }


    public Manager getManager() {
        return manager;
    }

    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Iterator<Pracownik> iterator() {
        return pracownicy.iterator();
    }


    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zespol that = (Zespol) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Zespol{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", manager=" + manager.getImie() + " " + manager.getNazwisko() +
                ", iloscPracownikow=" + pracownicy.size() +
                '}';
    }
}
