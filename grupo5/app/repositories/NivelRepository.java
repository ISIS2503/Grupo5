package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.Nivel;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by n.aguilar on 25/09/2017.
 */
public class NivelRepository {
    @Inject
    private PlayMorphia morphia;

    public Nivel findById(String id) {
        Nivel nivel = morphia.
                datastore().
                createQuery(Nivel.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return nivel;
    }

    public List<Nivel> find() {
        List<Nivel> niveles = morphia.
                datastore().
                createQuery(Nivel.class).asList();

        return niveles;
    }

    public void save(Nivel nivel) {
        morphia.datastore().save(nivel);
    }

    public Nivel destroy(String id) {
        Nivel nivel = findById(id);
        morphia.datastore().delete(nivel);
        return nivel;
    }
}
