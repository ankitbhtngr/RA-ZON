//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.common;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ChannelCreator {

  public static Channel create() throws IOException {
    ConnectionFactory factory = new ConnectionFactory();
    Connection connection = factory.newConnection("localhost");
    Channel channel = connection.createChannel();
    return channel;
  }
}
