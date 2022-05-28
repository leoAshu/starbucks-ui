public class Card {
    private static Card theCard;

    private double balance;
    private String cardNum;
    private String cardCode;

    private Card() {
        balance = 0.00f;
        cardNum = "000000000";
        cardCode = "000";
    }

    public synchronized static Card getCard() {
        if(theCard == null)
            return getNewCard();
        return theCard;
    }

    public synchronized static Card getNewCard() {
        theCard = new Card();
        return theCard;
    }

    public double balance() {
        return balance;
    }

    public String cardNum() {
        return cardNum;
    }

    public String cardCode() {
        return cardCode;
    }

    public void setCard(String cardNum, String cardCode) {
        this.cardNum = cardNum;
        this.cardCode = cardCode;
        this.balance = 20.00f;
    }

}
