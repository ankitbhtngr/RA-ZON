//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package hazelcast;

import java.io.Serializable;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class HazelcastServerClient implements Serializable {
	/** The serialVersionUID. */
	private static final long serialVersionUID = -823821989121344486L;
	private final HazelcastInstance client;

	public HazelcastServerClient() {
		ClientConfig clientConfig = new ClientConfig();
		ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
		networkConfig.addAddress("192.168.238.1:5701");
		client = HazelcastClient.newHazelcastClient(clientConfig);
	}

	public void persistMessage(String topic, String message) {
		IMap map = getMap();
		map.put(topic, message);
	}

	public String getMessage(String topic) {
		IMap map = getMap();
		return (String) map.get(topic);
	}

	private IMap getMap() {
		IMap map = client.getMap("ra_microservices");
		return map;
	}

}
