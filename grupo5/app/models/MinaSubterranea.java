package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

//@Entity(value = "MinaSubterranea")
public class MinaSubterranea{

    @Id
    private ObjectId _id;

    private String nombre;

    public MinaSubterranea() {
    }


    public MinaSubterranea(String nombre) {
        this.nombre = nombre;
    }

    public MinaSubterranea(ObjectId _id, String nombre) {

        this._id = _id;
        this.nombre = nombre;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static MinaSubterranea bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();

        MinaSubterranea minaSubterranea = new MinaSubterranea(nombre);
        return minaSubterranea;
    }
}