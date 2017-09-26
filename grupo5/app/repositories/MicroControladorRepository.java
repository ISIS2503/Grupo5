package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.Alerta;
import models.MicroControlador;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by d.jaimes on 25/09/2017.
 */
public class MicroControladorRepository {

    @Inject
    private PlayMorphia morphia;

    public MicroControlador findById(String id) {
        MicroControlador microControler = morphia.
                datastore().
                createQuery(MicroControlador.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return microControler;
    }


    public void save(MicroControlador microControler) {
        morphia.datastore().save(microControler);
    }

    public MicroControlador destroy(String id) {
        MicroControlador microControler = findById(id);
        morphia.datastore().delete(microControler);
        return microControler;
    }
}
