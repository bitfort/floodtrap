package floodtrap;

import java.util.LinkedList;

public class SNMPTrapBroadcast implements SNMPTrapListener {
  private LinkedList<SNMPTrapListener> listens;

  public SNMPTrapBroadcast() {
    listens = new LinkedList<SNMPTrapListener>();
  }

  public void AddListener(SNMPTrapListener l) {
    listens.add(l);
  }

  public void Notify(SNMPTrapEvent trap) {
    for (SNMPTrapListener l : listens) {
      l.Notify(trap);
    }
  }
}
