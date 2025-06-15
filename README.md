Producer runs on http://localhost:8080
Consumer runs on http://localhost8081
Kafka UI runs on http://localhost8082

Cada rama tiene el ejemplo:
1. ***string value serializer/deserializer*** (StringSerializer and StringDeserializer)
2. ***json object serializer/deserializer*** (JsonSerializer and JsonDeserializer). Nota: recorda que hay que definirle el
trust packages es decir tanto el evento generado y que se serializa para ingresar al topic tiene que tener la misma 
estructura de paquete que al momento de deserializar. Es decir si tu clase MiEvento dentro del productor estaba
dentro de paquete com.example.productor.model y tu clase MiEvento dentro del consumidor estaba dentro de paquete
com.example.consumidor.model por mas de que tengan los mismos campos no coinciden en la ruta de paquetes y por seguridad
el consumidor rechazada al deserializarlo entonces la solucion es siempre que la clase a serializar y deserializar tienen
que tener la misma ruta de paquetes ejemplo aqui com.example.model tanto en el productor como el consumidor.
3. ***avro serializer/deserializer*** (AvroSerializer and AvroDeserializer)