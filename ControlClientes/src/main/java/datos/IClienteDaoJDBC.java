package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.*;


interface IClienteDaoJDBC {
    
    public List<Cliente> listar();
    public Cliente encontrar(Cliente cliente);
    public int insertar(Cliente cliente);
    public int eliminar(Cliente cliente);
    
    
    
    
    
}
