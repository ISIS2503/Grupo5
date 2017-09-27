package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

/**
 * Created by dgguarin20 on 23/09/2017.
 */
@Entity(value = "AreaFisica") // Nombre de la coleccion de datos
public class AreaFisica {

    @Id
    private ObjectId _id;

    private String tipoArea;



    // Constructores

    public AreaFisica() {}

    public AreaFisica(ObjectId _id, String tipoArea) {
        this._id = _id;
        this.tipoArea = tipoArea;

    }

    public AreaFisica(String tipoArea) {
        this.tipoArea = tipoArea;

    }

    // Getters and Setters

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(String tipoArea) {
        this.tipoArea = tipoArea;
    }



    // Helpers

    public static AreaFisica bind(JsonNode j) {
        String tipoArea= j.findPath("tipoArea").asText();

        AreaFisica medicion = new AreaFisica(tipoArea);
        return medicion;
    }
}
