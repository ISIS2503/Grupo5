package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Alerta;
import models.MicroControlador;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.AlertaRepository;
import repositories.MicroControladorRepository;

/**
 * Created by d.jaimes on 25/09/2017.
 */
public class MicroControladorController extends Controller {
    @Inject
    private MicroControladorRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            MicroControlador micro = MicroControlador.bind(j);
            repository.save(micro);
            return ok(play.libs.Json.toJson(micro));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear el micro controlador"));
        }
    }

    public Result find() {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudieron  encontrar los micro controladores"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar el micro controlador con el id: "+id));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo eliminar el microcontrolador"));
        }
    }
}
