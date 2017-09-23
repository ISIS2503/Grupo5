package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.RegistroMedicion;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by wr.ravelo on 23/09/2017.
 */
public class RegistroMedicionRepository {

    @Inject
    private PlayMorphia morphia;

    public RegistroMedicion findById(String id) {
        RegistroMedicion medicion = morphia.
                datastore().
                createQuery(RegistroMedicion.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return medicion;
    }

    public List<RegistroMedicion> find() {
        List<RegistroMedicion> mediciones = morphia.
                datastore().
                createQuery(RegistroMedicion.class).asList();

        return mediciones;
    }

    public void save(RegistroMedicion medicion) {
        morphia.datastore().save(medicion);
    }

    public RegistroMedicion destroy(String id) {
        RegistroMedicion medicion = findById(id);
        morphia.datastore().delete(medicion);
        return medicion;
    }
}
