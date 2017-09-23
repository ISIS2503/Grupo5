package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Prueba;
import models.RegistroMedicion;
import org.bson.types.ObjectId;
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
        JsonNode j = Controller.request().body().asJson();
        RegistroMedicion medicion = RegistroMedicion.bind(j);
        repository.save(medicion);
        return ok(play.libs.Json.toJson(medicion));
    }

    public Result find() {
        return ok(play.libs.Json.toJson(repository.find()));
    }

    public Result findById(String id) {
        return ok(play.libs.Json.toJson(repository.findById(id)));
    }

    public Result destroy(String id) {
        return ok(play.libs.Json.toJson(repository.destroy(id)));
    }
}
