package controllers;

import models.Sensor;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import repositories.SensorRepository;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by jd.ahumada10 on 27/09/2017.
 */
public class SensorController extends Controller {
    @Inject
    private SensorRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            Sensor sensor = Sensor.bind(j);
            repository.save(sensor);
            return ok(play.libs.Json.toJson(sensor));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear"));
        }
    }

    public Result find() {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar los sensores"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar el sensor"));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar el sensor"));
        }
    }
}
