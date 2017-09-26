package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

@Entity(value = "Alerta")
public class Alerta{

    @Id
    private ObjectId _id;
    private Integer tipo;
    private Date fecha ;



    @ManyToOne(mappedBy = "Alerta")
    private MicroControlador microControlador;

    public Alerta() {

    }

    public Alerta(ObjectId _id, Integer  tipo, Date fecha, MicroControlador microControlador) {
        this._id = _id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.microControlador = microControlador;
    }

    public Alerta(Date fecha, Integer tipo, MicroControlador microControlador) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.MicroControlador = microControlador;
    }

    public Alerta(Date fecha, Integer tipo) {
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public MicroControlador getMicroControlador() {
        return microControlador;
    }

    public void setMicroControlador(MicroControlador microControlador) {
        this.microControlador = microControlador;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Date getDate() {
        return fecha;
    }

    public void setDate(Date  fecha) {
        this.fecha = fecha;
    }


    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public static Alerta bind(JsonNode j) {
        Date fecha = j.findPath("fecha").asText();
        Integer tipo = j.findPath("tipo").asText();

        Alerta alerta = new Alerta(fecha, tipo);
        return alerta;
    }
}