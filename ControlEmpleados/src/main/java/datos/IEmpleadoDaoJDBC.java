
package datos;

import dominio.Empleado;
import java.sql.*;
import java.util.*;

interface IEmpleadoDaoJDBC {
    
    public List<Empleado> listar();

    public Empleado encontrar(Empleado empleado);

    public int insertar(Empleado empleado);

    public int actualizar(Empleado empleado);

    public int eliminar(Empleado empleado);

    
    
}
