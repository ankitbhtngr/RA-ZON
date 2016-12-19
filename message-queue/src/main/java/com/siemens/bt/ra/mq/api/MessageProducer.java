//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package com.siemens.bt.ra.mq.api;

import java.io.IOException;

public interface MessageProducer {

  public void produce(String topic, String message) throws IOException;
}
