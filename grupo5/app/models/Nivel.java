package models;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

@Entity (value = "Nivel")
public class Nivel {

    @Id
    private ObjectId _id;

    private int numeroNivel;

    @ManyToOne (mappedBy = "Nivel")
    private MinaSubterranea minaSubterranea;

    public Nivel() {
    }

    public Nivel(ObjectId _id, int numeroNivel, MinaSubterranea minaSubterranea) {
        this._id = _id;
        this.numeroNivel = numeroNivel;
        this.minaSubterranea = minaSubterranea;
    }

    public Nivel(int numeroNivel, MinaSubterranea minaSubterranea) {
        this.numeroNivel = numeroNivel;
        this.minaSubterranea = minaSubterranea;
    }

    public Nivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getNumeroNivel() {
        return numeroNivel;
    }

    public void setNumeroNivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
    }

    public MinaSubterranea getMinaSubterranea() {
        return minaSubterranea;
    }

    public void setMinaSubterranea(MinaSubterranea minaSubterranea) {
        this.minaSubterranea = minaSubterranea;
    }

    public static Nivel bind(JsonNode j) {
        int numeroNivel = j.findPath("numeroNivel").asDouble();
        Nivel nivel = new Nivel(numeroNivel);
        return nivel;
    }
}