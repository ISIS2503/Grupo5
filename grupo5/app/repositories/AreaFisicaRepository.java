package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.RegistroMedicion;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by wr.ravelo on 23/09/2017.
 */
public class AreaFisicaRepository {

    @Inject
    private PlayMorphia morphia;

    public AreaFisica findById(String id) {
        AreaFisica medicion = morphia.
                datastore().
                createQuery(.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return medicion;
    }

    public List<AreaFisica> find() {
        List<AreaFisica> mediciones = morphia.
                datastore().
                createQuery(RegistroMedicion.class).asList();

        return mediciones;
    }

    public void save(AreaFisica medicion) {
        morphia.datastore().save(medicion);
    }

    public AreaFisica destroy(String id) {
        AreaFisica medicion = findById(id);
        morphia.datastore().delete(medicion);
        return medicion;
    }
}
