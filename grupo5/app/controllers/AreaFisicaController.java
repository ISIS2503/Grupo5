package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.VariableAmbiental;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;


/**
 * This controller contains an action that demonstrates how to write
 * simple asynchronous code in a controller. It uses a timer to
 * asynchronously delay sending a response for 1 second.
 *
 * @param actorSystem We need the {@link ActorSystem}'s
 * {@link Scheduler} to run code after a delay.
 * @param exec We need a Java {@link Executor} to apply the result
 * of the {@link CompletableFuture} and a Scala
 * {@link ExecutionContext} so we can use the Akka {@link Scheduler}.
 * An {@link ExecutionContextExecutor} implements both interfaces.
 */

public class AreaFisicaController extends Controller {


    @Inject
    private AreaFisicaRepository repository;

    @BodyParser.Of(BodyParser.Json.class)

    public Result create() {
        try {
            JsonNode j = Controller.request().body().asJson();
            AreaFisica act = AreaFisica.bind(j);
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
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la area"));
        }
    }

    public Result findById(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.findById(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la area"));
        }
    }

    public Result destroy(String id) {
        try {
            return ok(play.libs.Json.toJson(repository.destroy(id)));
        } catch(Exception e) {
            return notFound( play.libs.Json.newObject().put("error", "No se pudo encontrar la area"));
        }
    }

}
