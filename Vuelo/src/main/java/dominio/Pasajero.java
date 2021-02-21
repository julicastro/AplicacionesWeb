package dominio;

public class Pasajero {

    private int idPasajero;
    private int dni;
    private String nombre;
    private String apellido;

    public Pasajero() {
    }

    public Pasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public Pasajero(String nombre, String apellido, int dni) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Pasajero(int idPasajero, String nombre, String apellido, int dni) {
        this.idPasajero = idPasajero;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Pasajero{" + "idPasajero=" + idPasajero + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }
}
