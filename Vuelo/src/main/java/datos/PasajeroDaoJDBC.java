package datos;

import dominio.Pasajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDaoJDBC implements IPasajeroDaoJDBC {

    private static final String SQL_SELECT = "SELECT id_pasajero, nombre, apellido, dni "
            + " FROM pasajero";

    private static final String SQL_SELECT_BY_ID = "SELECT id_pasajero, nombre, apellido, dni"
            + " FROM pasajero WHERE id_pasajero = ?";

    private static final String SQL_INSERT = "INSERT INTO pasajero(nombre, apellido, dni,) "
            + " VALUES(?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE pasajero "
            + " SET nombre=?, apellido=?, dni=? WHERE id_pasajero=?";

    private static final String SQL_DELETE = "DELETE FROM pasajero WHERE id_pasajero = ?";

    @Override
    public List<Pasajero> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pasajero pasajero = null;
        List<Pasajero> pasajeros = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPasajero = rs.getInt("id_pasajero");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");

                pasajero = new Pasajero(idPasajero, nombre, apellido, dni);
                pasajeros.add(pasajero);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return pasajeros;
    }

    @Override
    public Pasajero encontrar(Pasajero pasajero) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, pasajero.getIdPasajero());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int dni = rs.getInt("dni");

            pasajero.setNombre(nombre);
            pasajero.setApellido(apellido);
            pasajero.setDni(dni);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return pasajero;

    }

    @Override
    public int insertar(Pasajero pasajero) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, pasajero.getNombre());
            stmt.setString(2, pasajero.getApellido());
            stmt.setInt(3, pasajero.getDni());
   
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    @Override
    public int actualizar(Pasajero pasajero) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, pasajero.getNombre());
            stmt.setString(2, pasajero.getApellido());
            stmt.setInt(3, pasajero.getDni());
            stmt.setInt(4, pasajero.getIdPasajero());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    @Override
    public int eliminar(Pasajero pasajero) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, pasajero.getIdPasajero());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

}
