//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.simple;

import java.io.IOException;

import org.junit.Test;

import com.siemens.bt.ra.mq.simple.SimpleMessageSender;

public class SimpleMessageSenderTest {

  @Test
  public void sendSimpleMessage() throws IOException {
    SimpleMessageSender messageSender = new SimpleMessageSender();
    messageSender.send("Hello Again Message Queue");
  }

}
