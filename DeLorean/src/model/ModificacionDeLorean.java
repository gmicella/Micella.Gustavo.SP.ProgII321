package model;

public class ModificacionDeLorean implements Comparable<ModificacionDeLorean>, CSVSerializable {
    
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private String responsable;
    private TipoModificacion tipo;

    public ModificacionDeLorean(int id, String nombre, String responsable, TipoModificacion tipo) {
        this.id = id;
        this.nombre = nombre;
        this.responsable = responsable;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getResponsable() {
        return responsable;
    }

    public TipoModificacion getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "ModificacionDeLorean{" + "id=" + id + ", nombre=" + nombre + ", responsable=" + responsable + ", tipo=" + tipo + '}';
    }

    @Override
    public int compareTo(ModificacionDeLorean otra) {
        return Integer.compare(id, otra.id);
    }

    @Override
    public String toCSV() {
        return id + "," + nombre + "," + responsable + "," + tipo + "\n";
    }
    
    public static ModificacionDeLorean fromCSV(String modificacionCSV){
        modificacionCSV = modificacionCSV.substring(0, modificacionCSV.length());
        String[] datos = modificacionCSV.split(",");
        return new ModificacionDeLorean(Integer.parseInt(datos[0]), datos[1], datos[2], TipoModificacion.valueOf(datos[3]));
    }
    
    
}
