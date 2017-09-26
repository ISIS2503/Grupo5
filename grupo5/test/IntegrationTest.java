import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.mongodb.util.JSON;
import controllers.RegistroMedicionController;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.RegistroMedicion;
import org.junit.*;

import play.api.ApplicationLoader;
import play.api.Environment;
import play.api.Play;
import play.api.inject.guice.GuiceApplicationBuilder;
import play.api.inject.guice.GuiceApplicationLoader;
import play.api.libs.json.Json;
import play.api.libs.json.Json$;
import play.api.test.*;
import play.db.Database;
import play.db.Databases;
import play.mvc.*;
import play.test.*;
import repositories.RegistroMedicionRepository;

import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

public class IntegrationTest {



    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void testEmptyList() {

    }

    @Test
    public void testListRegistroMedicion() {

    }

    @Test
    public void testAddRegistroMedicion() {
        running(testServer(3333, fakeApplication(inMemoryDatabase("test", ImmutableMap.of("MODE", "MYSQL")))), HTMLUNIT, browser -> {
            // Hago GET vacio de la base de datos
            Http.RequestBuilder x = fakeRequest(GET, "/registros");
            Result t = route(x);
            assertTrue(t.status() == 200);

            // Creo una entidad
            Http.RequestBuilder x1 = fakeRequest(POST, "/registros");
            ObjectNode json = play.libs.Json.newObject();
            json.put("valor",465.3);
            json.put("tipo","Temp");
            json.put("unidad","C");
            x1.bodyJson(json);
            Result t1 = route(x1).as("application/json");
            // TODO Verificar json

            assertTrue(t1.status() == 200);
            assertTrue(t1.contentType().toString().equals("Optional[application/json]"));

            // Hago GET con la nueva entidad en la base de datos
            Http.RequestBuilder x2 = fakeRequest(GET, "/registros");
            Result t2 = route(x2).as("application/json");
            assertTrue(t2.status() == 200);
            assertTrue(t2.contentType().toString().equals("Optional[application/json]"));


            // Hago POST sin JSON Deberia Fallar con 415
            Http.RequestBuilder x3 = fakeRequest(POST, "/registros");
            Result t3 = route(x3);
            System.out.println(t3.status());
            assertTrue(t3.status() == 415);
        });
    }

    @Test
    public void testRemoveRegistroMedicion() {

    }

    @Test
    public void testGetByIdRegistroMedicion() {

    }
}
