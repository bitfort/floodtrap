import floodtrap.*;

public class Main {
  public static void main(String[] args) throws Throwable{
    System.out.println("Hello, world!");

    TestTrapListener ttl = new TestTrapListener();
    SNMPTrapEvent e = new SNMPTrapEvent();

    SNMPTrapQ tq = new SNMPTrapQ(ttl);

    SNMP4JTrapper traps = new SNMP4JTrapper(tq);
    traps.start();
  }
};
