package springboot;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hazelcast.HazelcastServerClient;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final static String topic = "simple_message";

	@RequestMapping("/greeting/{id}")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name,
			@PathVariable(name = "id", value = "id") String path) throws IOException {
		System.out.println("path===" + path);
		HazelcastServerClient client = new HazelcastServerClient();
		return client.getMessage(topic);
	}
}