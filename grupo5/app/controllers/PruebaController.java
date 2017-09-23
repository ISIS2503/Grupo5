package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Prueba;
import play.api.libs.json.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.PruebaRepository;
import scala.util.parsing.json.JSON;


/**
 * Created by wr.ravelo on 22/09/2017.
 */
public class PruebaController extends Controller {
    @Inject
    private PruebaRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();

        System.out.println("hola");
        System.out.println(j.toString());
        System.out.println(j);
        Prueba u = Prueba.bind(j);
        repository.save(u);
        return ok(play.libs.Json.toJson(u));
    }

    public Result find() {
        return ok(play.libs.Json.toJson(repository.find()));
    }
}
