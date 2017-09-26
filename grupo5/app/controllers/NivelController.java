package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Nivel;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.NivelRepository;

/**
 * Created by n.aguilar on 25/09/2017.
 */
public class NivelController extends Controller {
    @Inject
    private NivelRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            Nivel nivel = Nivel.bind(j);
            repository.save(nivel);
            return ok(play.libs.Json.toJson(nivel));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear"));
        }
    }

    public Result find() {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar los niveles"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar el nivel"));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar el nivel"));
        }
    }

}
