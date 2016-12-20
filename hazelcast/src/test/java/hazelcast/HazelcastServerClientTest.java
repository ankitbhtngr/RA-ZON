//------------------------------------------------------------------------------
// Copyright Siemens Switzerland Ltd., 2016
//------------------------------------------------------------------------------

package hazelcast;

public class HazelcastServerClientTest {

	public static void main(String args[]) {
		// HazelcastClient.shutdownAll();
		// Hazelcast.shutdownAll();
		HazelcastServerClient client = new HazelcastServerClient();
		client.persistMessage("test_topic", "message");
		String message = client.getMessage("test_topic");
		System.out.println(message);
	}

}
