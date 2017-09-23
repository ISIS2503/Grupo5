package repositories;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.Prueba;
import org.omg.CORBA_2_3.portable.OutputStream;

import java.util.List;

/**
 * Created by wr.ravelo on 22/09/2017.
 */
public class PruebaRepository {
    @Inject
    private PlayMorphia morphia;

    public Prueba findById(String id) {
        Prueba prueba = morphia.
                datastore().
                createQuery(Prueba.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return prueba;
    }

    public List<Prueba> find() {
        List<Prueba> pruebas = morphia.
                datastore().
                createQuery(Prueba.class).asList();
        return pruebas;
    }

    public void save(Prueba u) {
        morphia.datastore().save(u);
    }
}
