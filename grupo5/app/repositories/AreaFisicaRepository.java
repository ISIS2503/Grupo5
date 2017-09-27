package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.AreaFisica;

import org.bson.types.ObjectId;


import java.util.List;

/**
 * Created by dgguarin20 23/09/2017.
 */
public class AreaFisicaRepository {

    @Inject
    private PlayMorphia morphia;

    public AreaFisica findById(String id) {
        AreaFisica medicion = morphia.
                datastore().
                createQuery(AreaFisica.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return medicion;
    }

    public List<AreaFisica> find() {
        List<AreaFisica> mediciones = morphia.
                datastore().
                createQuery(AreaFisica.class).asList();

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
