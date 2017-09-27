package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by jd.ahumada10 on 27/09/2017.
 */
@Entity(value = "Sensor") // Nombre de la coleccion de datos
public class Sensor {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;
    private double valorActual;
    private String tipoVariableAmbiental;

    // Constructores

    public Sensor() {}

    public Sensor(ObjectId _id, double valorActual,String tipoVariableAmbiental) {
        this._id = _id;
        this.valorActual = valorActual;
        this.tipoVariableAmbiental = tipoVariableAmbiental;

    }

    public Sensor(double valorActual, String tipoVariableAmbiental) {
        this.valorActual = valorActual;
        this.tipoVariableAmbiental = tipoVariableAmbiental;
    }

    // Getters and Setters

    // Getters and Setters

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public double getValorActual() {
        return valorActual;
    }

    public void setValorActual(double valorActual) {
        this.valorActual = valorActual;
    }

    public String getTipoVariableAmbiental() {
        return tipoVariableAmbiental;
    }

    public void setTipoVariableAmbiental(String tipoVariableAmbiental) {
        this.tipoVariableAmbiental = tipoVariableAmbiental;
    }

    // Helpers

    public static Sensor bind(JsonNode j) {
        double valorActual = j.findPath("valorActual").asDouble();
        String tipoVariableAmbiental = j.findPath("tipoVariableAmbiental").asText();
        Sensor sen = new Sensor(valorActual, tipoVariableAmbiental);
        return sen;
    }
}
