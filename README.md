Producer runs on http://localhost:9090
Consumer runs on http://localhost9091
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
3. ***avro serializer/deserializer*** (AvroSerializer and AvroDeserializer). Nota: soluciona la cambios en el modelo de
los eventos (evolucion de esquemas). Estos schemas son almacenados dentro de un componente llamado "Schema Registry",
al momento de serializar con AvroSerializer almacena el schema en el registry y cuando se deserializa el AvroDeserializer
obtiene el schema de dicho regitry previamente validando los campos y luego descerializa.
``` 
Productor -> AvroSerializer  ───────────────           Kafka   ────────────  AvroDeserializer <- Consumidor
                      └─send avro schema ──      Schema Registry   ── read avro schema ┘ 
                                       (employee.avcs, employee-v2.avcs...) 
``` 
Avro Schema = es un contrato entre el productos y consumidor (ejemplo "employee.avcs") existe una herramienta Avro
o plugin maven para generar las clases a partir de definir dicho esquema .avcs. 