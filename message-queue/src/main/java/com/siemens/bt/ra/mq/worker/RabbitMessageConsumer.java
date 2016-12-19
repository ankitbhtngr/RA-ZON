package com.siemens.bt.ra.mq.worker;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.siemens.bt.ra.mq.api.MessageConsumer;
import com.siemens.bt.ra.mq.common.ChannelCreator;

public class RabbitMessageConsumer implements MessageConsumer {

  private static String TASK_QUEUE_NAME = "default_queue";

  @Override
  public void consume(String topic) throws IOException {
    TASK_QUEUE_NAME = topic;
    final Channel channel = createChannel();
    final Consumer consumer = configConsumer(channel);
    consume(channel, consumer);
  }

  private static Channel createChannel() throws IOException {
    final Channel channel = ChannelCreator.create();
    channel.queueDeclare(TASK_QUEUE_NAME, true);
    System.out.println(" [*] Waiting for messages.");
    channel.basicQos(1);
    return channel;
  }

  private static Consumer configConsumer(final Channel channel) {
    final Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String messageAsTask = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + messageAsTask + "'");
        try {
          execute(messageAsTask);
        } finally {
          System.out.println(" [x] Done");
          channel.basicAck(envelope.getDeliveryTag(), false);
        }
      }
    };
    return consumer;
  }

  private static void consume(final Channel channel, final Consumer consumer) throws IOException {
    boolean autoAck = false;
    channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
  }

  private static void execute(String task) {
    for (char ch : task.toCharArray()) {
      if (ch == '.') {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException _ignored) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }

}