/** Key Pad Subject Interface */
public interface IKeyPadSubject {
  /**
   * Add Observer to Subscribers List
   * @param obj Observer Object
   */
  void attach(IKeyPadObserver observer);

  /**
   * Remove Observer from Subscription
   * @param obj Observer Object
   */
  void removeObserver(IKeyPadObserver observer);

  /**
   * Trigger Events to Observers
   */
  void notifyObservers();
}
