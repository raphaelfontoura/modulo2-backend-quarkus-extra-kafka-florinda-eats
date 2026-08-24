# florinda-eats

Florinda Eats é uma aplicação de entrega de comida para o restaurante da Dona Florinda.

É uma Arquitetura de Microservices composta pelos seguintes serviços:

- **Pedidos**, que tem informações do cardápio e dos pedidos realizados. Roda por default na porta `8080`
- **Pagamentos**, que permite a confirmação de um pagamento. Roda por default na porta `8081`
- **Nota Fiscal**, que gera XMLs de notas fiscais na saída padrão. Roda por default na porta `8082`

# O que você precisa fazer?

## Executar o serviço de Pagamento

Abra a código do serviço de `pagamentos` no IntelliJ.

Execute o serviço através do IntelliJ, utilizando _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Abra o Postman e importe a coleção `florinda-eats-pagamento.postman_collection`.

Explore o serviço e seus endpoints. Por exemplo, confirme um pagamento realizando um `PUT http://localhost:8081/pagamentos/1`.

## Executar o serviço de Pedidos

Abra a código do serviço de `pedidos` no IntelliJ.

Execute o serviço através do IntelliJ, utilizando _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Abra o Postman e importe a coleção `florinda-eats-pedidos.postman_collection`.

Verifique a listagem de pedidos na URL `http://localhost:8080/pedidos`.

Explore o serviço e seus endpoints. Por exemplo, detalhe um pedido (p. ex., o de id 1) com a URL `http://localhost:8080/pedidos/1`

## Executar o serviço de Notas Fiscal

Abra a código do serviço de `notas-fiscais` no IntelliJ.

Execute o serviço através do IntelliJ, utilizando _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Abra o Postman e importe a coleção `florinda-eats-notas-fiscais.postman_collection`.

Obtenha uma nota fiscal para um pedido (p. ex., o de id 1) com a URL `http://localhost:8082/nota-fiscal/pedido/1`

## Executar o Kafka

O arquivo `docker-compose.yml` contém uma configuração de Kafka cuja porta para conexões externas ao container é `9094`.

Para executar o Kafka, abra um Terminal e rode na raiz do projeto:

```sh
docker compose up
```

Espera um tiquinho que o Kafka deve subir.

Agora é fazer a integração dos serviços usando o Quarkus Messaging Kafka! ;)

## Teste via CLI no Kafka

Listando os tópicos:
```sh
docker exec -it modulo2-backend-quarkus-extra-kafka-florinda-eats-kafka-1 \
  /opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --list
```

Criando tópico:
```sh
docker exec -it modulo2-backend-quarkus-extra-kafka-florinda-eats-kafka-1 \
  /opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --create \
  --partitions 2 \
  --topic pagamentosConfirmados
```

E criando com docker compose:
```sh
docker compose exec kafka \
  /opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --create \
  --partitions 2 \
  --topic pagamentosConfirmados
```

Detalhando um tópico:
```sh
docker compose exec kafka \
  /opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --describe \
  --topic pagamentosConfirmados
```

Produzindo uma mensagem:
```sh
docker compose exec kafka \
  /opt/kafka/bin/kafka-console-producer.sh \
  --bootstrap-server localhost:9094 \
  --topic pagamentosConfirmados \
  --property "parse.key=true" \
  --property "key.separator=;" 
 
```
Entre com os as mensagens:

`1; {"pagamentoId": 1, "pedidoId": 1 }`

`2; {"pagamentoId": 2, "pedidoId": 2 }`

`3; {"pagamentoId": 3, "pedidoId": 3 }`

Consumindo mensagens:
```sh
docker compose exec kafka \
  /opt/kafka/bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic pagamentosConfirmados \
  --from-beginning \
  --group teste 
```

Verificando consumer groups:
```sh
docker compose exec kafka  \
/opt/kafka/bin/kafka-consumer-groups.sh \
--bootstrap-server localhost:9092 \
--all-groups --describe
```