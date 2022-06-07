/** Focus Subject Interface */
public interface IFocusSubject
{
    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    void attach(IFocusObserver obj);

    /**
     * Remove Observer from Subscription
     * @param obj Observer Object
     */
    void removeObserver(IFocusObserver obj);

    /**
     * Trigger Events to Observers
     */
    void notifyObservers();
}
