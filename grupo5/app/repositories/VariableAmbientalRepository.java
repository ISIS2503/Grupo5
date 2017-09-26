package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.VariableAmbiental;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by a.echeverrir on 24/09/2017.
 */
public class VariableAmbientalRepository
{

    @Inject
    private PlayMorphia morphia;

    public VariableAmbiental findById(String id) {
        VariableAmbiental variable = morphia.
                datastore().
                createQuery(VariableAmbiental.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return variable;
    }

    public List<VariableAmbiental> find()
    {
        List<VariableAmbiental> variables = morphia.
                datastore().
                createQuery(VariableAmbiental.class).asList();

        return variables;
    }

    public void save(VariableAmbiental variable)
    {
        morphia.datastore().save(variable);
    }

    public VariableAmbiental destroy(String id)
    {
        VariableAmbiental variable = findById(id);
        morphia.datastore().delete(variable);
        return variable;
    }

}
