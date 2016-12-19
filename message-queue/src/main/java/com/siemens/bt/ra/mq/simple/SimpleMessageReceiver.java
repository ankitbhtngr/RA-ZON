//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.simple;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.siemens.bt.ra.mq.common.ChannelCreator;

public class SimpleMessageReceiver {
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException {

    Channel channel = ChannelCreator.create();
    channel.queueDeclare(QUEUE_NAME, false);
    System.out.println(" [*] Waiting for messages.");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);

  }

}
