package Abstract;

public abstract class Spiewak {
    private String nazwisko;
    private final int numerStartowy;
    private static int licznikNumeRow = 0;

    public Spiewak(String nazwisko) {
        this.nazwisko = nazwisko;
        this.numerStartowy = licznikNumeRow++;
    }

    public abstract String spiewaj();

    @Override
    public String toString() {
        return "Spiewak{id=" + numerStartowy + ", name='" + nazwisko + "'}";
    }

    public static Spiewak najglosniej(Spiewak[] spiewacy) {
        if (spiewacy == null || spiewacy.length == 0) {
            return null;
        }

        Spiewak najglosniejszy = spiewacy[0];
        int maxLiczbaWielkichLiter = liczbaWielkichLiter(spiewacy[0].spiewaj());

        for (int i = 1; i < spiewacy.length; i++) {
            int liczbaWielkichLiter = liczbaWielkichLiter(spiewacy[i].spiewaj());
            if (liczbaWielkichLiter > maxLiczbaWielkichLiter) {
                maxLiczbaWielkichLiter = liczbaWielkichLiter;
                najglosniejszy = spiewacy[i];
            }
        }

        return najglosniejszy;
    }

    private static int liczbaWielkichLiter(String tekst) {
        int liczba = 0;
        for (int i = 0; i < tekst.length(); i++) {
            if (Character.isUpperCase(tekst.charAt(i))) {
                liczba++;
            }
        }
        return liczba;
    }
}
