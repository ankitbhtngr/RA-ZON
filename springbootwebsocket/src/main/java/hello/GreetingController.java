package hello;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.siemens.bt.ra.mq.api.MessageProducer;
import com.siemens.bt.ra.mq.worker.RabbitMessageProducer;

@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		Greeting greeting = new Greeting("Hello, " + message.getName() + "!");
		MessageProducer messageProducer = new RabbitMessageProducer();
		messageProducer.produce("simple_message", greeting.getContent());
		return greeting;
	}

	@Controller
	public class ServletConfig {
		@Bean
		public EmbeddedServletContainerCustomizer containerCustomizer() {
			return (container -> {
				container.setPort(8012);
			});
		}
	}
}