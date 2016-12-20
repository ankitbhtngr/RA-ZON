//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.worker;

import java.io.IOException;

import com.siemens.bt.ra.mq.api.MessageConsumer;

public class SimpleMessageConsumerService {

	private final static String topic = "simple_message";

	public static void main(String[] args) throws IOException {
		MessageConsumer consumer = new RabbitMessageConsumer();
		consumer.consume(topic);
	}
}
