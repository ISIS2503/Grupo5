package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

//@Entity(value = "Usuario")
public class Usuario{

//<<<<<<< HEAD
//    @Id
//    private ObjectId _id;
//    private String nombre;
//    private String contrasena;
//    private String tipo;
//
//    @ManyToOne(mappedBy = "usuario")
//    private MinaSubterranea minaSubterranea;
//
//    public Usuario() {
//
//    }
//
//    public Usuario(ObjectId _id, String nombre, String contrasena, String tipo, MinaSubterranea minaSubterranea) {
//        this._id = _id;
//        this.nombre = nombre;
//        this.contrasena = contrasena;
//        this.tipo = tipo;
//        this.minaSubterranea = minaSubterranea;
//    }
//
//    public Usuario(String nombre, String contrasena, String tipo, MinaSubterranea minaSubterranea) {
//        this.nombre = nombre;
//        this.contrasena = contrasena;
//        this.tipo = tipo;
//        this.minaSubterranea = minaSubterranea;
//    }
//
//    public Usuario(String nombre, String contrasena, String tipo) {
//        this.nombre = nombre;
//        this.contrasena = contrasena;
//        this.tipo = tipo;
//    }
//
//    public MinaSubterranea getMinaSubterranea() {
//        return minaSubterranea;
//    }
//
//    public void setMinaSubterranea(MinaSubterranea minaSubterranea) {
//        this.minaSubterranea = minaSubterranea;
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
//    public String getContrasena() {
//        return contrasena;
//    }
//
//    public void setContrasena(String contrasena) {
//        this.contrasena = contrasena;
//    }
//
//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
//
//    public static Usuario bind(JsonNode j) {
//        String nombre = j.findPath("nombre").asText();
//        String contrasena= j.findPath("contrasena").asText();
//        String tipo = j.findPath("nombre").asText();
//
//        Usuario usuario = new Usuario(nombre, contrasena, tipo);
//        return usuario;
//    }
//=======
    @Id
    private ObjectId _id;
    private String nombre;
    private String contrasena;
    private String tipo;

    public Usuario() {

    }

    public Usuario(ObjectId _id, String nombre, String contrasena, String tipo) {
        this._id = _id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public Usuario(String nombre, String contrasena, String tipo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipo = tipo;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static Usuario bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        String contrasena= j.findPath("contrasena").asText();
        String tipo = j.findPath("nombre").asText();

        Usuario usuario = new Usuario(nombre, contrasena, tipo);
        return usuario;
    }
//>>>>>>> 1617ec429aaa6e1caa45acfdd9429c6216d446d8
}