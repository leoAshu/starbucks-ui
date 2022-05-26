/** Pin Authentication Subject */
public interface IPinAuthSubject {
    /**
     * Add a Pin Auth Observer
     * @param observer Observer Object
     */
    void attach(IPinAuthObserver observer);

    /**
     * Remove Observer
     * @param observer Pin AUth Observer to Remove
     */
    void removeObserver(IPinAuthObserver observer);

    /**
     * Broadcast Event to Observers
     */
    void notifyObservers();
    
}
