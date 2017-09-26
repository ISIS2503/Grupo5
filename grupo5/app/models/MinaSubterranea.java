package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

//@Entity(value = "MinaSubterranea")
public class MinaSubterranea{
//
//    @Id
//    private ObjectId _id;
//
//    private String nombre;
//
//    @OneToMany(mappedBy = "minaSubterranea")
//    private List<Usuario> usuarios;
//
//    @OneToMany(mappedBy = "minaSubterranea")
//    private List<Nivel> niveles;
//
//    public MinaSubterranea() {
//    }
//
//    public MinaSubterranea(ObjectId _id, String nombre, List<Usuario> usuarios, List<Nivel> niveles) {
//        this._id = _id;
//        this.nombre = nombre;
//        this.usuarios = usuarios;
//        this.niveles = niveles;
//    }
//
//    public MinaSubterranea(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public MinaSubterranea(String nombre, List<Usuario> usuarios, List<Nivel> niveles) {
//        this.nombre = nombre;
//        this.usuarios = usuarios;
//        this.niveles = niveles;
//    }
//
//    public List<Nivel> getNiveles() {
//        return niveles;
//    }
//
//    public void setNiveles(List<Nivel> niveles) {
//        this.niveles = niveles;
//    }
//
//    public List<Usuario> getUsuarios() {
//        return usuarios;
//    }
//
//    public void setUsuarios(List<Usuario> usuarios) {
//        this.usuarios = usuarios;
//    }
//
//    public ObjectId get_id() {
//        return _id;
//    }
//
//    public void set_id(ObjectId _id) {
//        this._id = _id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public static MinaSubterranea bind(JsonNode j) {
//        String nombre = j.findPath("nombre").asText();
//
//        MinaSubterranea minaSubterranea = new MinaSubterranea(nombre);
//        return minaSubterranea;
//    }
}