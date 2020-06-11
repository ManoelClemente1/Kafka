# Curso Kafka

## Comandos básicos

#### Subir zookeper
* zookeeper-server-start.bat ../../config/zookeeper.properties

#### Subir Kafka
* kafka-server-start.bat ../../config/server.properties

#### Criar um topic
* kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic LOJA_NOVOPEDIDO

#### Listar topicos
* kafka-topics.bat --list --bootstrap-server localhost:9092

#### Detalhes topicos
* kafka-topics.bat --bootstrap-server localhost:9092 --describe

#### Ativar listener
* kafka-console-producer.bat --broker-list localhost:9092 --topic LOJA_NOVOPEDIDO

#### Ativar producer
* kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic ECOMMERCE_NEW_ORDER --from-beginning

#### Alterar topico para 3 particoes
* kafka-topics.bat --alter --zookeeper localhost:2181 --topic ECOMMERCE_NEW_ORDER --partitions 3