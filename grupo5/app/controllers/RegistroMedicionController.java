package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.RegistroMedicion;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.RegistroMedicionRepository;

/**
 * Created by wr.ravelo on 23/09/2017.
 */
public class RegistroMedicionController extends Controller {
    @Inject
    private RegistroMedicionRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            RegistroMedicion medicion = RegistroMedicion.bind(j);
            repository.save(medicion);
            return ok(play.libs.Json.toJson(medicion));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear"));
        }
    }

    public Result find() {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar las mediciones"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la medicion"));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la medicion"));
        }
    }
}
