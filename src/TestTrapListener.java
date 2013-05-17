
import floodtrap.SNMPTrapListener;
import floodtrap.SNMPTrapEvent;

public class TestTrapListener implements SNMPTrapListener {
  public void Notify(SNMPTrapEvent trap) {
    System.out.println("Got Event: " + trap.toString());
  }
};
