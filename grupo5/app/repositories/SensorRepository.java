package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.Sensor;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by jd.ahumada10 on 27/09/2017.
 */
public class SensorRepository {

    @Inject
    private PlayMorphia morphia;

    public Sensor findById(String id) {
        Sensor sensor = morphia.
                datastore().
                createQuery(Sensor.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return sensor;
    }

    public List<Sensor> find() {
        List<Sensor> sensores = morphia.
                datastore().
                createQuery(Sensor.class).asList();

        return sensores;
    }

    public void save(Sensor sensor) {
        morphia.datastore().save(sensor);
    }

    public Sensor destroy(String id) {
        Sensor sensor = findById(id);
        morphia.datastore().delete(sensor);
        return sensor;
    }
}
