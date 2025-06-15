Producer runs on http://localhost:8080
Consumer runs on http://localhost8081
Kafka UI runs on http://localhost8082

Cada rama tiene el ejemplo:
1. string value serializer/deserializer (StringSerializer and StringDeserializer)
2. string object serializer/deserializer (JacksonSerializer and JacksonDeserializer)
3. avro serializer/deserializer (AvroSerializer and AvroDeserializer)