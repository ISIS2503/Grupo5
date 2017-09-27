package IntegrityTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.*;

public class AlertaIntegrationTest {

    @Test
    public void testAddAlerta() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            //hacer get vacio
            Http.RequestBuilder x = fakeRequest(GET, "/alertas");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Verificar respuesta del JSON
            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t));
            assertTrue(jsonAns.size() == 0);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/alertas");
            ObjectNode json = play.libs.Json.newObject();
            json.put("tipo", 6);
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");

            // Verificar respuesta del JSON
            JsonNode jsonAns1 = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns1.get("tipo").asInt() == 6);

            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            // Hago GET con la nueva entidad en la base de datos
            Http.RequestBuilder x2 = fakeRequest(GET, "/alertas");
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            // Verificar respuesta del JSON
            JsonNode jsonAns2 = play.libs.Json.parse(contentAsString(t2));
            assertTrue(jsonAns2.size() == 1);


            // Hago POST sin JSON Deberia Fallar con 415
            Http.RequestBuilder x3 = fakeRequest(POST, "/alertas");
            Result t3 = route(x3);
            System.out.println(t3.status());
            assertTrue(t3.status() == 415);
        });
    }

//    @Test
//    public void testAddAlertas() {
//        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
//            //hacer get de 1
//            Http.RequestBuilder x = fakeRequest(GET, "/alertas");
//            Result t = route(x);
//            assertTrue(t.status() == 200);
//
//            // Verificar respuesta del JSON
//            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t));
//            assertTrue(jsonAns.size() == 1);
//
//            // Creo una entidad
//            for (int i = 0; i < 6; i++) {
//                int numero = (int) (Math.random());
//                Http.RequestBuilder x1 = fakeRequest(POST, "/alertas");
//                ObjectNode json = play.libs.Json.newObject();
//                json.put("tipo", 6);
//                x1.bodyJson(json);
//                //Result t1 = route(x1).as("application/json");
//
//                // Verificar respuesta del JSON
//
//                //JsonNode jsonAns1 = play.libs.Json.parse(contentAsString(t1));
//                //assertTrue(jsonAns1.get("tipo").asInt() == numero);
//
//                //assertTrue(t1.status() == 200);
//                //assertTrue(t1.contentType().toString().equals("Optional[application/json]"));
//            }
//            // Hago GET con la nueva entidad en la base de datos
//            Http.RequestBuilder x2 = fakeRequest(GET, "/alertas");
//            Result t2 = route(x2).as("application/json");
//            assertTrue(t2.status() == 200);
//            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));
//
//            // Verificar respuesta del JSON
//            JsonNode jsonAns2 = play.libs.Json.parse(contentAsString(t2));
//            assertTrue(jsonAns2.size() == 6);
//
//        });
//    }

    @Test
    public void testRemoveAlerta() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET base de datos actual
            Http.RequestBuilder x = fakeRequest(GET, "/alertas");
            Result t = route(x);
            assertTrue(t.status() == 200);


            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/alertas");
            ObjectNode json = play.libs.Json.newObject();
            json.put("tipo", 6);
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");

            // Verificar respuesta del JSON
            JsonNode jsonAns1 = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns1.get("tipo").asInt() == 6);

            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            // Elimino una entidad
            Http.RequestBuilder x2 = fakeRequest(DELETE, "/alertas/" + jsonAns1.get("_id").asText());
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            // Busco la entidad borrada
            Http.RequestBuilder x3 = fakeRequest(GET, "/alertas/" + jsonAns1.get("_id").asText());
            Result t3 = route(x3).as("application/json");
            assertTrue(t3.status() == 404);
            assertTrue(t3.contentType().toString().equals("Optional[application/json]"));
        });
    }

    @Test
    public void testGetAlertaById() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET vacio de la base de datos
            Http.RequestBuilder x = fakeRequest(GET, "/alertas");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/alertas");
            ObjectNode json = play.libs.Json.newObject();
            json.put("tipo", 6);
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");
            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            JsonNode jsonAns = play.libs.Json.parse(contentAsString(t1));
            assertTrue(jsonAns.get("tipo").asInt() == 6);

            // Busco la entidad creada
            Http.RequestBuilder x2 = fakeRequest(GET, "/alertas/" + jsonAns.get("_id").asText());
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));

            JsonNode jsonAns1 = play.libs.Json.parse(contentAsString(t2));
            assertTrue(jsonAns1.get("tipo").asInt() == 6);
        });
    }
}
