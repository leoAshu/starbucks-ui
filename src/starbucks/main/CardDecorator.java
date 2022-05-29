/**
 * Card Decorator Class for formatting attribute values
 */
public class CardDecorator implements ICard {
    Card card;

    public CardDecorator(Card card) {
        this.card = card;
    }

    public String cardNum() {
        String num = card.cardNum();
        int groupSize = (int)Math.sqrt(num.length());
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        for(; i<(num.length() - groupSize); i+=groupSize) {
            buffer.append(num.substring(i, i+groupSize));
            buffer.append(" ");
        }
        buffer.append(num.substring(i));
        return buffer.toString();
    }

    @Override
    public String cardCode() {
        return card.cardCode();
    }

    @Override
    public String balance() {
        return "$ " + card.balance();
    }

    @Override
    public void setCard(String cardNum, String cardCode) {
        card.setCard(cardNum, cardCode);
    }

}
