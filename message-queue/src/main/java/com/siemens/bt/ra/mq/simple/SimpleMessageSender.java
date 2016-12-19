//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.simple;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.siemens.bt.ra.mq.common.ChannelCreator;

public class SimpleMessageSender {

  private final static String QUEUE_NAME = "hello";

  public void send(String message) throws IOException {
    Channel channel = ChannelCreator.create();
    channel.queueDeclare(QUEUE_NAME, false);
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    channel.close();
    channel.getConnection().close();
  }
}
