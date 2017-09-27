package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

/**
 * Created by dgguarin20 on 23/09/2017.
 */
@Entity(value = "Actuador") // Nombre de la coleccion de datos
public class Actuador {

    @Id
    private ObjectId _id;

    private Boolean activo;

    private Integer ciclos;

    // Constructores

    public Actuador() {}

    public Actuador(ObjectId _id, Boolean activo, Integer ciclos) {
        this._id = _id;
        this.activo = activo;
        this.ciclos = ciclos;
    }

    public Actuador(Boolean activo,Integer ciclos) {
        this.activo = activo;
        this.ciclos  = ciclos;
    }

    // Getters and Setters

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getCiclo() {
        return ciclos;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclos = ciclo;
    }

    // Helpers

    public static Actuador bind(JsonNode j) {
        Boolean activo = j.findPath("activo").asBoolean();
        Integer ciclos = j.findPath("ciclos").asInt();
         Actuador medicion = new Actuador(activo, ciclos);
        return medicion;
    }
}
