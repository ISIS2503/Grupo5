package models;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by wr.ravelo on 22/09/2017.
 */
@Entity(value = "DB.prueba")
public class Prueba {
    @Id
    private ObjectId _id;
    private String firstname;

    public Prueba() {}

    public Prueba(ObjectId _id, String firstname) {
        this._id = _id;
        this.firstname = firstname;
    }

    public Prueba(String firstname) {
        this.firstname = firstname;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public static Prueba bind(JsonNode j) {
        String firstName = j.findPath("firstname").asText();
        Prueba prueba = new Prueba(firstName);
        return prueba;
    }
}
