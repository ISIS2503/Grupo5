package IntegrityTests;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import org.junit.*;
import play.mvc.*;
import static play.test.Helpers.*;
import static org.junit.Assert.*;
/**
 * Created by jd.ahumada10 on 27/09/2017.
 */
public class SensorIntegrationTest {

    @Test
    public void testAddSensor() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET vacio de la base de datos
            Http.RequestBuilder x = fakeRequest(GET, "/sensores");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Verificar respuesta del JSON
            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t));
            assertTrue(jsonAns.size() == 0);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/sensores");
            ObjectNode json = play.libs.Json.newObject();
            json.put("valorActual",465.3);
            json.put("tipoVariableAmbiental","Temperatura");
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");

            // Verificar respuesta del JSON
            JsonNode jsonAns1 = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns1.get("valorActual").asDouble() == 465.3);
            assertTrue(jsonAns1.get("tipoVariableAmbiental").asText().equals("Temperatura"));
            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            // Hago GET con la nueva entidad en la base de datos
            Http.RequestBuilder x2 = fakeRequest(GET, "/sensores");
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            // Verificar respuesta del JSON
            JsonNode jsonAns2 = play.libs.Json.parse(contentAsString(t2));
            assertTrue(jsonAns2.size() == 1);


            // Hago POST sin JSON Deberia Fallar con 415
            Http.RequestBuilder x3 = fakeRequest(POST, "/sensores");
            Result t3 = route(x3);
            System.out.println(t3.status());
            assertTrue(t3.status() == 415);
        });
    }

    @Test
    public void testRemoveSensor() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET base de datos actual
            Http.RequestBuilder x = fakeRequest(GET, "/sensores");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/sensores");
            ObjectNode json = play.libs.Json.newObject();
            json.put("valorActual",465.3);
            json.put("tipoVariableAmbiental","Temperatura");
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");
            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns.get("valorActual").asDouble() == 465.3);
            assertTrue(jsonAns.get("tipoVariableAmbiental").asText().equals("Temperatura"));

            // Elimino una entidad
            Http.RequestBuilder x2 = fakeRequest(DELETE, "/sensores/"+jsonAns.get("_id").asText());
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            // Busco la entidad borrada
            Http.RequestBuilder x3 = fakeRequest(GET, "/sensores/"+jsonAns.get("_id").asText());
            Result t3 = route(x3).as("application/json");
            assertTrue(t3.status() == 404);
            assertTrue(t3.contentType().toString().equals("Optional[application/json]"));
        });
    }

    @Test
    public void testGetByIdSensor() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET vacio de la base de datos
            Http.RequestBuilder x = fakeRequest(GET, "/sensores");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/sensores");
            ObjectNode json = play.libs.Json.newObject();
            json.put("valorActual",465.3);
            json.put("tipoVariableAmbiental","Temperatura");
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");
            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns.get("valorActual").asDouble() == 465.3);
            assertTrue(jsonAns.get("tipoVariableAmbiental").asText().equals("Temperatura"));

            // Busco la entidad creada
            Http.RequestBuilder x2 = fakeRequest(GET, "/sensores/"+jsonAns.get("_id").asText());
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            JsonNode jsonAns1 = play.libs.Json.parse(contentAsString(t2));
            assertTrue(jsonAns1.get("valorActual").asDouble() == 465.3);
            assertTrue(jsonAns1.get("tipoVariableAmbiental").asText().equals("Temperatura"));
        });
    }
}
