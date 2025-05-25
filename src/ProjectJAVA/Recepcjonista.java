package ProjectJAVA;

import java.time.LocalDate;


public class Recepcjonista extends Pracownik {

    private static int nextId = 1;
    private final int id;
    private final String login;
    private final String haslo;
    private String initial;


    public Recepcjonista(String imie, String nazwisko, LocalDate dataUrodzenia,
                         DzialPracownikow dzial, String login, String haslo) {

        super(imie, nazwisko, dataUrodzenia, dzial);
        this.id = nextId++;
        this.login = login;
        this.haslo = haslo;
        this.initial = createInitial(imie, nazwisko); // metoda inicjalowa wywolywana przy tworzeniu osoby automatycznie

    }



    private String createInitial(String imie, String nazwisko) {
        String initial = "";
        if (!imie.isEmpty()) {
            initial += imie.charAt(0);
        }
        if (!nazwisko.isEmpty()) {
            initial += nazwisko.charAt(0);
        }
        return initial;
    }


    @Override
    public boolean czyPrzychodziNaCzas() {
        return getDniPunktualne() > 15;
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
        return czyPrzychodziNaCzas() ? base + 1.5 : base;
    }


    public String getInitial() {
        return initial;
    }


    @Override
    public void setImie(String imie) {
        super.setImie(imie);
        this.initial = createInitial(imie, getNazwisko());
    }


    @Override
    public void setNazwisko(String nazwisko) {
        super.setNazwisko(nazwisko);
        this.initial = createInitial(getImie(), nazwisko);
    }



    @Override
    public String toString() {
        return "Recepcjonista{" +
                "id=" + id +
                ", imie='" + getImie() + '\'' +
                ", nazwisko='" + getNazwisko() + '\'' +
                ", initial='" + initial + '\'' +
                ", login='" + login + '\'' +
                ", zadania=" + getZadania().size() +
                '}';
    }
}