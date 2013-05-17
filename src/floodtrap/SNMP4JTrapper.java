package floodtrap;

import java.io.IOException;

import org.snmp4j.*;
import org.snmp4j.smi.*;
import org.snmp4j.util.*;
import org.snmp4j.transport.*;
import org.snmp4j.mp.*;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

public class SNMP4JTrapper implements CommandResponder {
  private MultiThreadedMessageDispatcher dispatcher;
  private ThreadPool threadPool;
  private Snmp snmp;
  private SNMPTrapListener listen;


  public SNMP4JTrapper(SNMPTrapListener l) {
    listen = l;
  }

  public void start() {
    Address listenAddress = GenericAddress.parse("udp:0.0.0.0/162");
    threadPool = ThreadPool.create("Trap", 2);
    dispatcher = new MultiThreadedMessageDispatcher(threadPool, new MessageDispatcherImpl());
    TransportMapping transport;
    try {
      transport = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
      snmp = new Snmp(transport);
      snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
      snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
      snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
      snmp.listen();
    } catch(IOException e) {
      System.out.println("error opening port to listen.");
    }
    snmp.addCommandResponder(this);
  }


  @Override
  public synchronized void processPdu(final CommandResponderEvent respEvnt) {
    System.out.println("We got one!!\n");
    /*
    if (respEvnt != null && respEvnt.getPDU() != null) {
      final Vector recVBs = respEvnt.getPDU().getVariableBindings();
      for (int i = 0; i < recVBs.size(); i++) {
        final VariableBinding recVB = recVBs.elementAt(i);
        System.out.println(recVB.getOid() + " : " + recVB.getVariable());
      }

    }
    */
  }
};
