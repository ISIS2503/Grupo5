package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

/**
 * Created by a.echeverrir on 23/09/2017.
 */
@Entity(value = "VariableAmbiental") // Nombre de la coleccion de datos
public class VariableAmbiental {


    // Atributos

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;

    private String tipoVariable;

    private double minimo;

    private double maximo;

    private double variacion;

    private String unidadMedida;

    private int precision;

    private int frecuencia;


    // Constructores

    public VariableAmbiental() {}

    public VariableAmbiental(ObjectId _id, String pTipoVariable, double pMinimo, double pMaximo, double pVariacion, String pUnidadMedida, int pPrecision, int pFrecuencia ) {

        this.set_id(_id);
        this.setTipoVariable(pTipoVariable);
        this.setMinimo(pMinimo);
        this.setMaximo(pMaximo);
        this.setVariacion(pVariacion);
        this.setUnidadMedida(pUnidadMedida);
        this.setPrecision(pPrecision);
        this.setFrecuencia(pFrecuencia);
    }

    public VariableAmbiental(String pTipoVariable, double pMinimo, double pMaximo, double pVariacion, String pUnidadMedida, int pPrecision, int pFrecuencia ) {

        this.setTipoVariable(pTipoVariable);
        this.setMinimo(pMinimo);
        this.setMaximo(pMaximo);
        this.setVariacion(pVariacion);
        this.setUnidadMedida(pUnidadMedida);
        this.setPrecision(pPrecision);
        this.setFrecuencia(pFrecuencia);
    }

    // Getters and Setters

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(String tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public double getVariacion() {
        return variacion;
    }

    public void setVariacion(double variacion) {
        this.variacion = variacion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    // Helpers

    public static VariableAmbiental bind(JsonNode jsonNode) {

        String tipo = jsonNode.findPath("tipo").asText();
        double min = jsonNode.findPath("minimo").asDouble();
        double max = jsonNode.findPath("maximo").asDouble();
        double var = jsonNode.findPath("variacion").asDouble();
        String unit = jsonNode.findPath("unidad").asText();
        int pres = jsonNode.findPath("precision").asInt();
        int frec = jsonNode.findPath("frecuencia").asInt();

        VariableAmbiental variableAmbiental = new VariableAmbiental(tipo,min,max,var,unit,pres,frec);

        return variableAmbiental;

    }


}
