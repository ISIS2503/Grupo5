package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.Alerta;
import models.RegistroMedicion;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by d.jaimes on 25/09/2017.
 */
public class AlertaRepository {

    @Inject
    private PlayMorphia morphia;

    public Alerta findById(String id) {
        Alerta alerta = morphia.
                datastore().
                createQuery(Alerta.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return alerta;
    }

    public List<Alerta> find() {
        List<Alerta> alertas = morphia.
                datastore().
                createQuery(Alerta.class).asList();

        return alertas;
    }

    public void save(Alerta alerta) {
        morphia.datastore().save(alerta);
    }

    public Alerta destroy(String id) {
        Alerta alerta = findById(id);
        morphia.datastore().delete(alerta);
        return alerta;
    }
}
