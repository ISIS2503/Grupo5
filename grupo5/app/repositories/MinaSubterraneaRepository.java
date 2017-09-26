package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.MinaSubterranea;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by n.aguilar on 25/09/2017.
 */
public class MinaSubterraneaRepository {
    @Inject
    private PlayMorphia morphia;

    public MinaSubterranea findById(String id) {
        MinaSubterranea minaSubterranea= morphia.
                datastore().
                createQuery(MinaSubterranea.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return minaSubterranea;
    }

    public List<MinaSubterranea> find() {
        List<MinaSubterranea> minaSubterraneas= morphia.
                datastore().
                createQuery(MinaSubterranea.class).asList();

        return minaSubterraneas;
    }

    public void save(MinaSubterranea minaSubterranea) {
        morphia.datastore().save(minaSubterranea);
    }

    public MinaSubterranea destroy(String id) {
        MinaSubterranea minaSubterranea= findById(id);
        morphia.datastore().delete(minaSubterranea);
        return minaSubterranea;
    }
}
