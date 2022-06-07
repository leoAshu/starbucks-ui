/** Focus Subject Interface */
public interface IFocusSubject
{
    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    void attach(IFocusObserver observer);

    /**
     * Remove Observer from Subscription
     * @param obj Observer Object
     */
    void removeObserver(IFocusObserver observer);

    /**
     * Trigger Events to Observers
     */
    void notifyObservers();
}
