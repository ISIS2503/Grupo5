package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Usuario;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.UsuarioRepository;

/**
 * Created by n.aguilar on 25/09/2017.
 */
public class UsuarioController extends Controller{

    @Inject
    private UsuarioRepository repository;

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            Usuario usuario = Usuario.bind(j);
            repository.save(usuario);
            return ok(play.libs.Json.toJson(usuario));
        } catch(Exception e) {
            return internalServerError( play.libs.Json.newObject().put("error", "No se pudo crear"));
        }
    }

    public Result find() {
        try {
            return ok(play.libs.Json.toJson(repository.find()));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar los usuarios"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar el usuario"));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la usuario"));
        }
    }
}
