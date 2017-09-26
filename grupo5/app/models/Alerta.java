package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

import java.util.Date;

@Entity(value = "Alerta")
public class Alerta{

    @Id
    private ObjectId _id;
    private Integer tipo;
//    private MicroControlador microControlador;



     public Alerta() {

    }

    public Alerta(ObjectId _id, Integer  tipo) {
        this._id = _id;
        this.tipo = tipo;
//        this.microControlador = microControlador;
    }


    public Alerta( Integer tipo) {
        this.tipo = tipo;
    }

//    public MicroControlador getMicroControlador() {
//        return microControlador;
//    }
//
//    public void setMicroControlador(MicroControlador microControlador) {
//        this.microControlador = microControlador;
//    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public static Alerta bind(JsonNode j) {
        Integer tipo = j.findPath("tipo").asInt();

        Alerta alerta = new Alerta(tipo);
        return alerta;
    }
    //TODO: hacer el repositorio luego el controller y luego modificar los routes y ya despues test
}