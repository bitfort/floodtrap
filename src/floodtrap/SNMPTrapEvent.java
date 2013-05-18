package floodtrap;

import java.util.HashMap;

public class SNMPTrapEvent {
  private HashMap<String, String> vars;
  private String addr;
  public SNMPTrapEvent(String a) {
    addr = a;
    vars = new HashMap<String, String>();
  }

  public void AddVar(String k, String v) {
    vars.put(k, v);
  }

  public String toString() {
    String s =  "";
    for (String k : vars.keySet()) {
      s += k + " : " + vars.get(k) + ", ";
    }
    return s;
  }

  public String Address() {
    return addr;
  }
  
  public HashMap<String, String> Variables() { 
    return vars;
  }
};
