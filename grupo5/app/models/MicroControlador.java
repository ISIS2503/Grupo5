package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;


@Entity(value = "MicroControlador")
public class MicroControlador{

    @Id
    private ObjectId _id;


    @OneToMany(mappedBy = "microControlador")
    private List<Alerta> alertas;
    

    public MicroControlador() {
    }

    public MicroControlador(ObjectId _id, List<Alerta> alertas) {
        this._id = _id;
        this.alertas = alertas;
    }


    public MicroControlador(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    public List<Alertas> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public static MicroControlador bind(JsonNode j) {

        MicroControlador MicroControlador = new MicroControlador();
        return MicroControlador;
    }
}