package models;

import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by wr.ravelo on 23/09/2017.
 */
@Entity(value = "RegistroMedicion") // Nombre de la coleccion de datos
public class RegistroMedicion {

    @Id
    private ObjectId _id;

    private double valor;

    private String tipo;

    private String unidad;

    // Constructores

    public RegistroMedicion() {}

    public RegistroMedicion(ObjectId _id, double valor, String tipo, String unidad) {
        this._id = _id;
        this.valor = valor;
        this.tipo = tipo;
        this.unidad = unidad;
    }

    public RegistroMedicion(double valor, String tipo, String unidad) {
        this.valor = valor;
        this.tipo = tipo;
        this.unidad = unidad;
    }

    // Getters and Setters

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    // Helpers

    public static RegistroMedicion bind(JsonNode j) {
        double valor = j.findPath("valor").asDouble();
        String tipo = j.findPath("tipo").asText();
        String unidad = j.findPath("unidad").asText();
        RegistroMedicion medicion = new RegistroMedicion(valor, tipo, unidad);
        return medicion;
    }
}
