package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Alerta;
import models.RegistroMedicion;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.AlertaRepository;
import repositories.RegistroMedicionRepository;

/**
 * Created by d.jaimes on 25/09/2017.
 */
public class AlertaController extends Controller {
    @Inject
    private AlertaRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            Alerta alerta = Alerta.bind(j);
            repository.save(alerta);
            return ok(play.libs.Json.toJson(alerta));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear la alerta"));
        }
    }

    public Result find() {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudieron  encontrar las alertas"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la alerta con el id: "+id));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo eliminar la alerta"));
        }
    }
}
