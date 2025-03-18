package Abstract;

public class main {
    public static void main(String[] args) {
        Spiewak s1 = new Spiewak("Eminem") {
            @Override
            public String spiewaj() {
                return "The Real Slim Shady";
            }
        };

        Spiewak s2 = new Spiewak("Eagles") {
            @Override
            public String spiewaj() {
                return "HOTEL CALIFORNIA";
            }
        };

        Spiewak s3 = new Spiewak("Dzem") {
            @Override
            public String spiewaj() {
                return "Whisky moja żono";
            }
        };

        Spiewak sp[] = {s1, s2, s3};
        for (Spiewak s : sp) System.out.println(s);
        System.out.println("\n" + Spiewak.najglosniej(sp));
    }
}
