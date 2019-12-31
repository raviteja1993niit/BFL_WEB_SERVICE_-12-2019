package com.posidex;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.posidex.config.OracleAQConfig;

//@Component
public class OracleAQReceiver {
// @JmsListener(destination = "PSX_NSP_PM_RESP_QUEUE", containerFactory ="myJMSListenerFactory")
  public void handleMessage(String message) {//implicit message type conversion
      System.out.println("received: "+message);
  }
}

