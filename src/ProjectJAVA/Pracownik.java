package ProjectJAVA;

import java.time.LocalDate;
import java.util.*;


public abstract class Pracownik implements Comparable<Pracownik>, IDobryPracownik  {

    private static int nextId = 1;
    private final int id;
    private final static List<Pracownik> wszyscyPracownicy = new ArrayList<>();
    private String imie;
    private String nazwisko;
    private final LocalDate dataUrodzenia;
    private final DzialPracownikow dzial;
    private boolean czyZdrowy = true;
    private final Set<Zadanie> zadania = new LinkedHashSet<>();
    private final static Random random = new Random();
    private final int dniPunktualne;



    public Pracownik(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial) {
        this.id = nextId++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.dzial = dzial;
        this.dniPunktualne = random.nextInt(21);
        wszyscyPracownicy.add(this); // dodaje tworzony obiekt do listy wszyscypracownicy
        dzial.dodajPracownika(this); // przypisanie pracownika do konkretnego dzialu - dzieki temu dzial wie ktorzy
        //pracownicy do niego naleza
    }


    public void dodajZadanie(Zadanie zadanie) {
        if (zadanie != null) {
            zadania.add(zadanie);
        }
    }


    @Override
    public int compareTo(Pracownik other) {
        int wynik = this.getDataUrodzenia().compareTo(other.getDataUrodzenia());

        if (wynik == 0) {
            wynik = this.getImie().compareTo(other.getImie());
        }
        if (wynik == 0) {
            wynik = this.getNazwisko().compareTo(other.getNazwisko());
        }
        return wynik;
    }


    public static void wyswietlZadaniaPracownikow() {
        for (Pracownik p : getWszyscyPracownicy()) {
            if (!(p instanceof Manager)) {
                wyswietlZadaniaPracownika(p);
            } // manager ma byc osobno - dla niego jest wlasna metoda wyswietlajaca
        }
    }

    private static void wyswietlZadaniaPracownika(Pracownik p) {
        System.out.println("Zadania pracownika " + p.getImie() + " " + p.getNazwisko() +
                " (ID=" + p.getId() + ", " + p.getClass().getSimpleName() + "):");

        Set<Zadanie> zadaniaPracownika = p.getZadania();
        if (zadaniaPracownika.isEmpty()) {
            System.out.println(" - Brak przypisanych zadań.");
        } else {
            for (Zadanie zadanie : zadaniaPracownika) {
                System.out.println(" - Zadanie: ID=" + zadanie.getId() + ", Nazwa=" +
                        zadanie.getNazwa() + ", Stan=" + zadanie.getStanZadania());
            }
        }
        System.out.println();
    }



    @Override
    public abstract boolean czyPrzychodziNaCzas();

    @Override
    public abstract long iloscWykonanychZadan();

    @Override
    public abstract double ocenaPracy();


    public int getDniPunktualne() {
        return dniPunktualne;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public boolean getCzyZdrowy() {
        return czyZdrowy;
    }

    public void setCzyZdrowy(boolean czyZdrowy) {
        this.czyZdrowy = czyZdrowy;
    }

    public Set<Zadanie> getZadania() {
        return new LinkedHashSet<>(zadania);
    }


    public static List<Pracownik> getWszyscyPracownicy() {
        return new ArrayList<>(wszyscyPracownicy);
    }


    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getId() {
        return id;
    }



    @Override
    public String toString() {
        return "Pracownik{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", dzial=" + dzial.getNazwa() +
                ", czyZdrowy=" + czyZdrowy +
                ", zadania=" + zadania.size() +
                ", dniPunktualne=" + dniPunktualne +
                '}';
    }
}
