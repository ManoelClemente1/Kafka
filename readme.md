# Curso Kafka

## Comandos básicos

zookeeper-server-start.bat ../../config/zookeeper.properties
kafka-server-start.bat ../../config/server.properties

kafka-server-start.bat ../../config/server.properties

kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic LOJA_NOVOPEDIDO

kafka-topics.bat --list --bootstrap-server localhost:9092

kafka-console-producer.bat --broker-list localhost:9092 --topic LOJA_NOVOPEDIDO

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic ECOMMERCE_NEW_ORDER --from-beginning

kafka-topics.bat --bootstrap-server localhost:9092 --describe

kafka-topics.bat --alter --zookeeper localhost:2181 --topic ECOMMERCE_NEW_ORDER --partitions 3