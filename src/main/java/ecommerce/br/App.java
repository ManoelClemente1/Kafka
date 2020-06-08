package ecommerce.br;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		var producer = new KafkaProducer<String, String>(properties());
		var key = UUID.randomUUID().toString();
		var value = "133333,1231,88888888128";
		var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", key, value);		
		Callback callback = (data,ex) -> {
			if(ex != null) {
				ex.printStackTrace();
			}
			System.out.println("sucesso enviando " + data.topic() + ":::partition" + data.partition() + "/offset" + data.offset() + "/timestamp" + data.timestamp());
		};
		var email = "Bem vindo ao processo do seu pedido";
		var emailRecord = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", key,email);
		producer.send(record,callback).get();
		producer.send(emailRecord, callback).get();
	}
	
	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}
}
