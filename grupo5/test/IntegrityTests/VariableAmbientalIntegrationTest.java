package IntegrityTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import org.junit.*;
import play.mvc.*;
import static org.junit.Assert.*;
import static play.test.Helpers.*;

public class VariableAmbientalIntegrationTest {

    @Test
    public void testAddVariableAmbiental() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET vacio de la base de datos
            Http.RequestBuilder x = fakeRequest(GET, "/variablesAmbientales");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Verificar respuesta del JSON
            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t));
            assertTrue(jsonAns.size() == 0);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/variablesAmbientales");
            ObjectNode json = play.libs.Json.newObject();
            json.put("tipo","tipo1");
            json.put("minimo",10.0);
            json.put("maximo",100.0);
            json.put("variacion",20.0);
            json.put("unidad","dB");
            json.put("precision",1);
            json.put("frecuencia",1);
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");

            // Verificar respuesta del JSON
            JsonNode jsonAns1 = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns1.get("tipo").asText().equals("tipo1"));
            assertTrue(jsonAns1.get("minimo").asDouble() == 10.0);
            assertTrue(jsonAns1.get("maximo").asDouble() == 100.0);
            assertTrue(jsonAns1.get("variacion").asDouble() == 20.0);
            assertTrue(jsonAns1.get("unidad").asText().equals("dB"));
            assertTrue(jsonAns1.get("precision").asInt() == 1);
            assertTrue(jsonAns1.get("frecuencia").asInt() == 1);

            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            // Hago GET con la nueva entidad en la base de datos
            Http.RequestBuilder x2 = fakeRequest(GET, "/variablesAmbientales");
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            // Verificar respuesta del JSON
            JsonNode jsonAns2 = play.libs.Json.parse(contentAsString(t2));
            assertTrue(jsonAns2.size() == 1);


            // Hago POST sin JSON Deberia Fallar con 415
            Http.RequestBuilder x3 = fakeRequest(POST, "/variablesAmbientales");
            Result t3 = route(x3);
            System.out.println(t3.status());
            assertTrue(t3.status() == 415);
        });
    }

    @Test
    public void testRemoveVariableAmbiental() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET base de datos actual
            Http.RequestBuilder x = fakeRequest(GET, "/variablesAmbientales");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/variablesAmbientales");
            ObjectNode json = play.libs.Json.newObject();
            json.put("tipo","tipo1");
            json.put("minimo",10.0);
            json.put("maximo",100.0);
            json.put("variacion",20.0);
            json.put("unidad","dB");
            json.put("precision",1);
            json.put("frecuencia",1);
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");
            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns.get("minimo").asDouble() == 10.0);
            assertTrue(jsonAns.get("tipo").asText().equals("tipo1"));
            assertTrue(jsonAns.get("maximo").asDouble() == 100.0);
            assertTrue(jsonAns.get("variacion").asDouble() == 20.0);
            assertTrue(jsonAns.get("unidad").asText().equals("dB"));
            assertTrue(jsonAns.get("precision").asInt() == 1);
            assertTrue(jsonAns.get("frecuencia").asInt() == 1);


            // Elimino una entidad
              Http.RequestBuilder x2 = fakeRequest(DELETE, "/variablesAmbientales/"+jsonAns.get("_id").asText());
              Result t2 = route(x2).as("application/json");
              assertTrue(t2.status() == 200);
              assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            // Busco la entidad borrada
            Http.RequestBuilder x3 = fakeRequest(GET, "/variablesAmbientales/"+jsonAns.get("_id").asText());
            Result t3 = route(x3).as("application/json");
            assertTrue(t3.status() == 404);
            assertTrue(t3.contentType().toString().equals("Optional[application/json]"));
        });
    }

    @Test
    public void testGetByIdVariablesAmbientales() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET vacio de la base de datos
            Http.RequestBuilder x = fakeRequest(GET, "/variablesAmbientales");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/variablesAmbientales");
            ObjectNode json = play.libs.Json.newObject();
            json.put("tipo","tipo1");
            json.put("minimo",10.0);
            json.put("maximo",100.0);
            json.put("variacion",20.0);
            json.put("unidad","dB");
            json.put("precision",1);
            json.put("frecuencia",1);
            Result t1 = route(x1).as("application/json");
            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns.get("tipo").asText().equals("tipo1"));
            assertTrue(jsonAns.get("minimo").asDouble() == 10.0);
            assertTrue(jsonAns.get("maximo").asDouble() == 100.0);
            assertTrue(jsonAns.get("variacion").asDouble() == 20.0);
            assertTrue(jsonAns.get("unidad").asText().equals("dB"));
            assertTrue(jsonAns.get("precision").asInt() == 1);
            assertTrue(jsonAns.get("frecuencia").asInt() == 1);

            // Busco la entidad creada
            Http.RequestBuilder x2 = fakeRequest(GET, "/variablesAmbientales/"+jsonAns.get("_id").asText());
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            JsonNode jsonAns1 = play.libs.Json.parse(contentAsString(t2));
            assertTrue(jsonAns.get("tipo").asText().equals("tipo1"));
            assertTrue(jsonAns.get("minimo").asDouble() == 10.0);
            assertTrue(jsonAns.get("maximo").asDouble() == 100.0);
            assertTrue(jsonAns.get("variacion").asDouble() == 20.0);
            assertTrue(jsonAns.get("unidad").asText().equals("dB"));
            assertTrue(jsonAns.get("precision").asInt() == 1);
            assertTrue(jsonAns.get("frecuencia").asInt() == 1);
        });
    }
}
