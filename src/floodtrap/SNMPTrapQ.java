package floodtrap;

import java.lang.Thread;
import java.util.concurrent.ArrayBlockingQueue;

public class SNMPTrapQ extends Thread implements SNMPTrapListener {

  private ArrayBlockingQueue<SNMPTrapEvent> q;
  private SNMPTrapListener delegate;

  public SNMPTrapQ(SNMPTrapListener delegate) {
    q = new ArrayBlockingQueue<SNMPTrapEvent>(100);
    this.delegate = delegate;
    this.start();
  }

  public void Notify(SNMPTrapEvent trap) {
    try {
      q.put(trap);
    } catch (InterruptedException e) {
      Notify(trap);
    }
  };

  public void run() {
    while (!this.interrupted()) {
    try {
      this.delegate.Notify(q.take());
    } catch (InterruptedException e) {
    }
    }
  };

};
