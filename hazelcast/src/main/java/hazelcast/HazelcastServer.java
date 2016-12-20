package hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;

public class HazelcastServer {

	public static void main(String[] args) {
		Config cfg = new Config();
		cfg.setInstanceName("hazelcast_service");
		Hazelcast.newHazelcastInstance(cfg);
	}

}
