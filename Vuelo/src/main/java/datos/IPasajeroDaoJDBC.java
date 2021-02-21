package datos;

import dominio.Pasajero;
import java.sql.*;
import java.util.*;

public interface IPasajeroDaoJDBC {
    
    public List<Pasajero> listar();

    public Pasajero encontrar(Pasajero pasajero);

    public int insertar(Pasajero pasajero);

    public int actualizar(Pasajero pasajero);

    public int eliminar(Pasajero pasajero);

    
    
}

    
    
    

