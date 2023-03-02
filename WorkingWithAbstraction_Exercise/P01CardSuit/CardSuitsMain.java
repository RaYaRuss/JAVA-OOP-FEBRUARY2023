package WorkingWithAbstraction_Exercise.P01CardSuit;

public class CardSuitsMain {
    enum CardSuits {
        CLUBS, 
        DIAMONDS, 
        HEARTS, 
        SPADES
    }

    public static void main(String[] args) {
        System.out.println("Card Suits:");
        CardSuits[] values = CardSuits.values();
        for (CardSuits value : values) {
            System.out.println("Ordinal value: " + value.ordinal() + "; Name value: " + value);
        }
    }
}
