package web;

import datos.PasajeroDaoJDBC;
import dominio.Pasajero;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.accionDefault(request, response);
    }

    protected void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pasajero> pasajeros = new PasajeroDaoJDBC().listar();
        HttpSession sesion = request.getSession();
        System.out.println("Pasajeros = " + pasajeros);
        sesion.setAttribute("pasajeros", pasajeros);
        request.getRequestDispatcher("pasajeros.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarPasajero(request, response);
                    break;
                case "modificar":
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarPasajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //recuperamos los valores del formulario agregarCliente
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cadena = "dni";
        int dni = (Integer.parseInt(request.getParameter("dni")));   

        //Creamos el objeto de empleado (modelo)
        Pasajero pasajero = new Pasajero(nombre, apellido, dni);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new PasajeroDaoJDBC().insertar(pasajero);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
