package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Actuador;
import models.VariableAmbiental;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.ActuadorRepository;




public class ActuadorController extends Controller {


    @Inject
    private ActuadorRepository repository;

    @BodyParser.Of(BodyParser.Json.class)

    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            Actuador act = Actuador.bind(j);
            repository.save(act);
            return ok(play.libs.Json.toJson(act));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear"));
        }
    }

    public Result find() {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar el actuador"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar los actuadores"));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar el actuador"));
        }
    }

}
