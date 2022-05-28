/**
 * Card Class for Managing Card Balance & Transactions
 */
public class Card implements ICard {
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

    @Override
    public String cardNum() {
        return cardNum;
    }

    @Override
    public String cardCode() {
        return cardCode;
    }

    @Override
    public String balance() {
        return "" + balance;
    }

    @Override
    public void setCard(String cardNum, String cardCode) {
        this.cardNum = cardNum;
        this.cardCode = cardCode;
        this.balance = 20.00f;
    }

}
