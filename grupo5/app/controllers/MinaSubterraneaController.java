package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.MinaSubterranea;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.MinaSubterraneaRepository;

/**
 * Created by n.aguilar on 25/09/2017.
 */
public class MinaSubterraneaController extends Controller{
    @Inject
    private MinaSubterraneaRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            MinaSubterranea minaSubterranea = MinaSubterranea.bind(j);
            repository.save(minaSubterranea);
            return ok(play.libs.Json.toJson(minaSubterranea));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear"));
        }
    }

    public Result find() {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar las minas"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la mina"));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la mina"));
        }
    }

}
