package models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

import java.util.List;


@Entity(value = "MicroControlador")
public class MicroControlador{

    @Id
    @JsonSerialize(using = ToStringSerializer.class)

    private ObjectId _id;
    public MicroControlador() {
    }

    public MicroControlador(ObjectId _id) {
        this._id = _id;
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