package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.RegistroMedicion;
import org.bson.types.ObjectId;
import models.Actuador;

import java.util.List;

/**
 * Created by dgguarin20 on 23/09/2017.
 */
public class ActuadorRepository {

    @Inject
    private PlayMorphia morphia;

    public Actuador findById(String id) {
        Actuador medicion = morphia.
                datastore().
                createQuery(Actuador.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return medicion;
    }

    public List<Actuador> find() {
        List<Actuador> mediciones = morphia.
                datastore().
                createQuery(Actuador.class).asList();

        return mediciones;
    }

    public void save(Actuador medicion) {
        morphia.datastore().save(medicion);
    }

    public Actuador destroy(String id) {
        Actuador medicion = findById(id);
        morphia.datastore().delete(medicion);
        return medicion;
    }
}
