package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.VariableAmbiental;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.VariableAmbientalRepository;

/**
 * Created by a.echeverrir on 24/09/2017.
 */
public class VariableAmbientalController extends Controller {
    @Inject
    private VariableAmbientalRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create()
    {
        try {
            JsonNode j = Controller.request().body().asJson();
            VariableAmbiental variable = VariableAmbiental.bind(j);
            repository.save(variable);
            return ok(play.libs.Json.toJson(variable));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear"));
        }
    }

    public Result find()
    {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar las variables ambientales"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la variable ambiental"));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la variable ambiental"));
        }
    }
}
