package Comperable;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Square implements  Comparable<Square> {

    private int length;
    private int number;
    private static int counter = 1;

    public Square(int length) {
        this.length = length;
        this.number = counter++;
    }

    public int getArea() {
        return length * length;
    }

    @Override
    public String toString() {
        return number + " " + getArea();
    }

    @Override
    public int compareTo(Square other) {
        return Integer.compare(this.getArea(), other.getArea());
    }

    public static void main(String[] args) {
        List<Square> squares = Stream.of(5, 3, 7, 2, 4)
                .map(Square::new)
                .collect(Collectors.toList());

        System.out.println("Before: ");
        for (Square s : squares) {
            System.out.println(s);
        }

        Collections.sort(squares);

        System.out.println("\nAfter: ");
        for (Square s : squares) {
            System.out.println(s);
        }
    }

}
