import java.util.List;

/** Nav Bar Interface */
public interface INavBar {
    /** 
     * Configure the Nav Bar with Nav Bar options
     * @param commands List of INavBarCommand commands to be invoked on touch
     */
    public void setUp(List<INavBarCommand> commands);
}
