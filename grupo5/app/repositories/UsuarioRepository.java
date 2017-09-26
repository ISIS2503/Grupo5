package repositories;

import com.google.inject.Inject;
import it.unifi.cerm.playmorphia.PlayMorphia;
import models.Usuario;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by n.aguilar on 25/09/2017.
 */
public class UsuarioRepository {
    @Inject
    private PlayMorphia morphia;

    public Usuario findById(String id) {
        Usuario usuario= morphia.
                datastore().
                createQuery(Usuario.class).
                field("_id").
                equal(new ObjectId(id)).get();
        return usuario;
    }

    public List<Usuario> find() {
        List<Usuario> mediciones = morphia.
                datastore().
                createQuery(Usuario.class).asList();

        return mediciones;
    }

    public void save(Usuario usuario) {
        morphia.datastore().save(usuario);
    }

    public Usuario destroy(String id) {
        Usuario usuario = findById(id);
        morphia.datastore().delete(usuario);
        return usuario;
    }

}
