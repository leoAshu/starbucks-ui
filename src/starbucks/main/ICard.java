/**
 * Card Interface for Managing Card Balance & Transactions
 */
public interface ICard {
    String cardNum();
    String cardCode();
    String balance();
    void setCard(String cardNum, String cardCode);
}
