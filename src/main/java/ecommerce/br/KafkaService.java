package ecommerce.br;

import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaService  implements Closeable{

	private final KafkaConsumer<String, String> consumer;
	private final ConsumerFunction parse;

	KafkaService(String topic, ConsumerFunction parse) {
		this.parse = parse;
		this.consumer = new KafkaConsumer<String, String>(properties());
		consumer.subscribe(Collections.singletonList(topic));

	}

	public KafkaService(String groupId, Pattern topic, ConsumerFunction parse) {
		this.parse = parse;
		this.consumer = new KafkaConsumer<String, String>(properties());
		consumer.subscribe(topic);
	}

	void run() {
		while (true) {
			var records = consumer.poll(Duration.ofMillis(100));

			if (!records.isEmpty()) {
				System.out.println("encontrei" + records.count() + " registros");

				for (var record : records) {
					parse.consume(record);
				}
			}
		}

	}

	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, EmailService.class.getSimpleName());
		return properties;
	}

	@Override
	public void close(){
		consumer.close();
		
	}

}
