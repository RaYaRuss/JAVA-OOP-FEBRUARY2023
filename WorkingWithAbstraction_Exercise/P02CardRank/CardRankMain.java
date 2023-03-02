package WorkingWithAbstraction_Exercise.P02CardRank;

public class CardRankMain {
    enum CardRank {
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING
    }
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        for (CardRank cardRank : CardRank.values()) {
            System.out.println("Ordinal value: " + cardRank.ordinal() +
                    "; Name value: " + cardRank);
        }
    }
}
