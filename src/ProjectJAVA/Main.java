package ProjectJAVA;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("\n=== TWORZENIE DZIAŁÓW ===");
            DzialPracownikow dzialIT = DzialPracownikow.createDzial("IT");
            DzialPracownikow dzialHR = DzialPracownikow.createDzial("HR");

            System.out.println("Utworzono dzial: " + dzialIT.getNazwa());
            System.out.println("Utworzono dzial: " + dzialHR.getNazwa());

            try {
                System.out.println("\n=== TESTOWANIE UNIKALNOŚCI NAZWY ===");
                DzialPracownikow.createDzial("IT");
            } catch (NotUniqueNameException e) {
                System.out.println("Złapano wyjątek: " + e.getMessage());
            }

            System.out.println("\n=== TWORZENIE PRACOWNIKÓW ===");
            Trener trener1 = new Trener("Jan", "Kowalski",
                    LocalDate.of(1990, 5, 15), dzialIT, "Java");
            Trener trener2 = new Trener("Jan", "Fijor",
                    LocalDate.of(1995, 5, 15), dzialHR, "HR");
            Recepcjonista recepcjonista1 = new Recepcjonista("Filip", "Wisniewski",
                    LocalDate.of(1990, 5, 15), dzialIT, "filip.w", "haslo123");
            Recepcjonista recepcjonista2 = new Recepcjonista("Maciej", "Wiz",
                    LocalDate.of(1999, 7, 20), dzialHR, "maciej.w", "haslo423");
            Recepcjonista recepcjonista3 = new Recepcjonista("Adam", "Wiz",
                    LocalDate.of(2000, 2, 20), dzialHR, "adam.w", "haslo425");

            System.out.println("Utworzono : " + trener1);
            System.out.println("Utworzono : " + trener2);
            System.out.println("Utworzono : " + recepcjonista1);
            System.out.println("Utworzono : " + recepcjonista2);
            System.out.println("Utworzono : " + recepcjonista3);

            System.out.println("\n=== TEST INITIAL ===");
            System.out.println("Przed zmianą - initial: " + recepcjonista1.getInitial() + " dla pracownika " + recepcjonista1.getImie() + " " + recepcjonista1.getNazwisko());
            recepcjonista1.setImie("Robert");
            recepcjonista1.setNazwisko("Jankowski");
            System.out.println("Po zmianie imienia i nazwiska - initial: " + recepcjonista1.getInitial() + " dla pracownika " + recepcjonista1.getImie() + " " + recepcjonista1.getNazwisko());

            System.out.println("\n=== WSZYSCY PRACOWNICY ===");
            List<Pracownik> wszyscyPracownicy = Pracownik.getWszyscyPracownicy();
            for (Pracownik p : wszyscyPracownicy) {
                System.out.println(p.getImie() + " " + p.getNazwisko() + " (" + p.getClass().getSimpleName() + ")");
            }

            System.out.println("\n=== SORTOWANIE PRACOWNIKÓW ===");
            List<Pracownik> pracownicyDoSortowania = new ArrayList<>(Pracownik.getWszyscyPracownicy());
            Collections.sort(pracownicyDoSortowania);
            System.out.println("Pracownicy posortowani (data urodzenia - imie - nazwisko):");
            for (Pracownik p : pracownicyDoSortowania) {
                System.out.println(p.getNazwisko() + " " + p.getImie() + " (" + p.getDataUrodzenia() + ")");
            }

            System.out.println("\n=== PRACOWNICY DZIAŁU IT ===");
            Set<Pracownik> pracownicyIT = dzialIT.getPracownicy();
            for (Pracownik p : pracownicyIT) {
                System.out.println("ID=" + p.getId() + ", " + p.getImie() + " " + p.getNazwisko() + " (" + p.getClass().getSimpleName() + ")");
            }

            System.out.println("\n=== PRACOWNICY DZIAŁU HR ===");
            Set<Pracownik> pracownicyHR = dzialHR.getPracownicy();
            for (Pracownik p : pracownicyHR) {
                System.out.println("ID=" + p.getId() + ", " + p.getImie() + " " + p.getNazwisko() + " (" + p.getClass().getSimpleName() + ")");
            }

            System.out.println("\n=== TWORZENIE ZADAŃ (PRZED USUNIĘCIEM) ===");
            Zadanie zadanieA = new Zadanie("A", "dlaProgramistow", true);
            Zadanie zadanieB = new Zadanie("B", "dlaProgramistow", true);
            Zadanie zadanieC = new Zadanie("C", "dlaHR", false);
            Zadanie zadanieD = new Zadanie("D", "dlaHR", true);

            System.out.println("Utworzono zadanie: ID=" + zadanieA.getId() + ", Nazwa=" + zadanieA.getNazwa() + " (zatwierdzone: " + zadanieA.isZatwierdzone() + ")");
            System.out.println("Utworzono zadanie: ID=" + zadanieB.getId() + ", Nazwa=" + zadanieB.getNazwa() + " (zatwierdzone: " + zadanieB.isZatwierdzone() + ")");
            System.out.println("Utworzono zadanie: ID=" + zadanieC.getId() + ", Nazwa=" + zadanieC.getNazwa() + " (zatwierdzone: " + zadanieC.isZatwierdzone() + ")");
            System.out.println("Utworzono zadanie: ID=" + zadanieD.getId() + ", Nazwa=" + zadanieD.getNazwa() + " (zatwierdzone: " + zadanieD.isZatwierdzone() + ")");

            System.out.println("\n=== PRZYPISYWANIE ZADAŃ (PRZED USUNIĘCIEM) ===");
            zadanieA.przydzielPracownika(trener1);
            zadanieB.przydzielPracownika(recepcjonista1);
            zadanieD.przydzielPracownika(trener2);
            zadanieD.przydzielPracownika(recepcjonista2);

            System.out.println("Przypisano zadanie A do trenera " + trener1.getImie() + " " + trener1.getNazwisko());
            System.out.println("Przypisano zadanie B do recepcjonisty " + recepcjonista1.getImie() + " " + recepcjonista1.getNazwisko());
            System.out.println("Przypisano zadanie D do trenera " + trener2.getImie() + " " + trener2.getNazwisko());
            System.out.println("Przypisano zadanie D do recepcjonisty " + recepcjonista2.getImie() + " " + recepcjonista2.getNazwisko());

            System.out.println("\n=== USUWANIE PRACOWNIKA Z DZIAŁU ===");
            dzialHR.usunPracownika(recepcjonista3);
            System.out.println("Usunięto recepcjonistę: ID=" + recepcjonista3.getId() +
                    ", " + recepcjonista3.getImie() + " " + recepcjonista3.getNazwisko() +
                    " z działu " + dzialHR.getNazwa());

            System.out.println("\nPracownicy działu HR po usunięciu:");
            Set<Pracownik> pracownicyHRPo = dzialHR.getPracownicy();
            System.out.println("Liczba pracowników w dziale HR po usunięciu: " + pracownicyHRPo.size());
            for (Pracownik p : pracownicyHRPo) {
                System.out.println("ID=" + p.getId() + ", " + p.getImie() + " " + p.getNazwisko() + " (" + p.getClass().getSimpleName() + ")");
            }

            System.out.println("\n=== TWORZENIE MANAGERÓW ===");
            Manager manager1 = new Manager("Karolina", "Kowalczyk",
                    LocalDate.of(1980, 9, 12), dzialIT, "karolina.k", "manager123");
            Manager manager2 = new Manager("Adam", "Kowalski",
                    LocalDate.of(1984, 9, 12), dzialHR, "adam.k", "manager321");

            System.out.println("Utworzono: " + manager1);
            System.out.println("Utworzono: " + manager2);

            System.out.println("\n=== TWORZENIE ZESPOŁÓW ===");
            Zespol zespolProgramistow = new Zespol("Programiści");
            Zespol zespolHR = new Zespol("HRowcy");

            System.out.println("Utworzono zespół: ID=" + zespolProgramistow.getId() + ", Nazwa=" + zespolProgramistow.getNazwa());
            System.out.println("Utworzono zespół: ID=" + zespolHR.getId() + ", Nazwa=" + zespolHR.getNazwa());

            System.out.println("\n=== DODAWANIE PRACOWNIKÓW DO ZESPOŁU (POJEDYNCZO) ===");
            zespolProgramistow.dodajManagera(manager1);
            zespolProgramistow.dodajPracownika(trener1);
            zespolProgramistow.dodajPracownika(recepcjonista1);

            System.out.println("Dodawanie managera (ID=" + manager1.getId() + ") do zespołu Programiści...");
            System.out.println("Dodawanie trenera (ID=" + trener1.getId() + ") do zespołu Programiści...");
            System.out.println("Dodawanie recepcjonisty (ID=" + recepcjonista1.getId() + ") do zespołu Programiści...");

            System.out.println("\n=== DODAWANIE PRACOWNIKÓW DO ZESPOŁU (LISTA) ===");
            Set<Pracownik> pracownicyDoZespoluHR = new LinkedHashSet<>(Arrays.asList(trener2, recepcjonista2));
            zespolHR.dodajManagera(manager2);
            zespolHR.dodajPracownika(pracownicyDoZespoluHR);

            System.out.println("Utworzono listę pracowników dla zespołu HR z trenera (ID=" + trener2.getId() +
                    ") i recepcjonisty (ID=" + recepcjonista2.getId() + ")");
            System.out.println("Liczba pracowników na liście: " + pracownicyDoZespoluHR.size());
            System.out.println("Dodawanie managera (ID=" + manager2.getId() + ") do zespołu HR...");
            System.out.println("Dodawanie listy pracowników do zespołu HR...");

            System.out.println("\n=== ITEROWANIE PO ZESPOŁACH ===");
            System.out.println("Pracownicy zespołu: " + zespolProgramistow.getNazwa());
            for (Pracownik p : zespolProgramistow) {
                System.out.println(p);
            }
            System.out.println("\nPracownicy zespołu: " + zespolHR.getNazwa());
            for (Pracownik p : zespolHR) {
                System.out.println(p);
            }

            System.out.println("\n=== PRACE DLA ZESPOŁÓW ===");
            Set<Zadanie> zadaniaProgramistow = new LinkedHashSet<>();
            zadaniaProgramistow.add(zadanieA);
            zadaniaProgramistow.add(zadanieB);

            Set<Zadanie> zadaniaHR = new LinkedHashSet<>();
            zadaniaHR.add(zadanieC);
            zadaniaHR.add(zadanieD);

            Praca pracaProgramistow = new Praca("Zadania dla zespołu programistów", zespolProgramistow, zadaniaProgramistow);
            Praca pracaHR = new Praca("Zadania dla zespołu HR", zespolHR, zadaniaHR);

            System.out.println("Utworzono pracę: ID=" + pracaProgramistow.getId());
            System.out.println("Utworzono pracę: ID=" + pracaHR.getId());
            System.out.println("Liczba zadań dla zespołu Programiści: " + zadaniaProgramistow.size());
            System.out.println("Liczba zadań dla zespołu HR: " + zadaniaHR.size());

            System.out.println("\n=== TESTOWANIE POZYSKANIA PRACY PRZEZ ID ZADANIA ===");
            Praca znalezionaPraca = Praca.getPracaByZadanieId(zadanieA.getId());
            if (znalezionaPraca != null) {
                System.out.println("Znaleziono pracę o ID=" + znalezionaPraca.getId() + " dla zadania o ID=" + zadanieA.getId());
            } else {
                System.out.println("Nie znaleziono pracy dla zadania o ID=" + zadanieA.getId());
            }

            System.out.println("\n=== TESTOWANIE URUCHAMIANIA - PRZYPADEK Z CHORYM PRACOWNIKIEM ===");
            trener1.setCzyZdrowy(false);
            Thread watekPracyProgramistow1 = new Thread(pracaProgramistow);
            watekPracyProgramistow1.start();

            try {
                watekPracyProgramistow1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\n=== TESTOWANIE URUCHAMIANIA - PRZYPADEK Z WSZYSTKIMI ZDROWYMI PRACOWNIKAMI ===");
            trener1.setCzyZdrowy(true);
            System.out.println("Uruchamianie pracy dla zespołu " + zespolProgramistow.getNazwa() + "...");
            Thread watekPracyProgramistow2 = new Thread(pracaProgramistow);
            watekPracyProgramistow2.start();

            try {
                watekPracyProgramistow2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\nUruchamianie pracy dla zespołu " + zespolHR.getNazwa() + "...");
            Thread watekPracyHR = new Thread(pracaHR);
            watekPracyHR.start();
            try {
                watekPracyHR.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\n=== ZESPOŁY ORAZ ZADANIA MANAGERÓW ===");

            System.out.println("Manager " + manager1.getImie() + " " + manager1.getNazwisko() + " (ID=" + manager1.getId() + "):");
            System.out.println("Zespoły managera:");
            for (Zespol zespol : manager1.getZespoly()) {
                System.out.println(" - Zespół: ID=" + zespol.getId() + ", Nazwa=" + zespol.getNazwa());
            }

            System.out.println("Zadania managera:");
            Set<Zadanie> zadaniaManagera1 = manager1.getZadaniaManagera();
            if (zadaniaManagera1.isEmpty()) {
                System.out.println(" - Brak zadań.");
            } else {
                for (Zadanie zadanie : zadaniaManagera1) {
                    System.out.println(" - Zadanie: ID=" + zadanie.getId() + ", Nazwa=" + zadanie.getNazwa() + ", Stan=" + zadanie.getStanZadania());
                }
            }

            System.out.println("\nManager " + manager2.getImie() + " " + manager2.getNazwisko() + " (ID=" + manager2.getId() + "):");
            System.out.println("Zespoły managera:");
            for (Zespol zespol : manager2.getZespoly()) {
                System.out.println(" - Zespół: ID=" + zespol.getId() + ", Nazwa=" + zespol.getNazwa());
            }

            System.out.println("Zadania managera:");
            Set<Zadanie> zadaniaManagera2 = manager2.getZadaniaManagera();
            if (zadaniaManagera2.isEmpty()) {
                System.out.println(" - Brak przypisanych zadań.");
            } else {
                for (Zadanie zadanie : zadaniaManagera2) {
                    System.out.println(" - Zadanie: ID=" + zadanie.getId() + ", Nazwa=" + zadanie.getNazwa() + ", Stan=" + zadanie.getStanZadania());
                }
            }

            System.out.println("\n=== ZADANIA WYKONANE PRZEZ PRACOWNIKÓW ===");
            Pracownik.wyswietlZadaniaPracownikow();

            System.out.println("\n=== OCENY PRACY PRACOWNIKÓW ===");
            for (Pracownik p : Pracownik.getWszyscyPracownicy()) {
                System.out.println("Ocena pracy " + p.getClass().getSimpleName().toLowerCase() + "a " +
                        p.getImie() + " " + p.getNazwisko() + " (ID=" + p.getId() + "): " +
                        p.ocenaPracy());
            }
        } catch (Exception e) {
            System.out.println("Wiadomość błędu: " + e.getMessage());
        }
    }
}