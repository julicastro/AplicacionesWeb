package web;

import datos.EmpleadoDaoJDBC;
import dominio.Empleado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarEmpleado(request, response);
                    break;
                case "eliminar":
                    this.eliminarEmpleado(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empleado> empleados = new EmpleadoDaoJDBC().listar();
        System.out.println("empleados = " + empleados);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("empleados", empleados);
        sesion.setAttribute("totalEmpleados", empleados.size());
        sesion.setAttribute("salarioTotal", this.calcularSalarioTotal(empleados));
        sesion.setAttribute("salarioPromedio", this.calcularSalarioPromedio(empleados));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("empleados.jsp");
    }

    private double calcularSalarioPromedio(List<Empleado> empleados){
        double salarioTotal = 0;
        for(Empleado empleado : empleados) {
            salarioTotal += empleado.getSalario();
        }
        return salarioTotal / empleados.size();
    } 
    
    private double calcularSalarioTotal(List<Empleado> empleados) {
        double salarioTotal = 0;
        for(Empleado empleado : empleados) {
            salarioTotal += empleado.getSalario();
        }
        return salarioTotal;
    }

    private void editarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el empleado
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Empleado empleado = new EmpleadoDaoJDBC().encontrar(new Empleado(idEmpleado));
        request.setAttribute("empleado", empleado);
        String jspEditar = "/WEB-INF/paginas/empleado/editarEmpleado.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarEmpleado(request, response);
                    break;
                case "modificar":
                    this.modificarEmpleado(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario agregarCliente
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        double salario = 0;
        String salarioString = request.getParameter("salario");
        if (salarioString != null && !"".equals(salarioString)) {
            salario = Double.parseDouble(salarioString);
        }

        //Creamos el objeto de empleado (modelo)
        Empleado empleado = new Empleado(nombre, apellido, dni, telefono, salario);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new EmpleadoDaoJDBC().insertar(empleado);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        double salario = 0;
        String salarioString = request.getParameter("salario");
        if (salarioString != null && !"".equals(salarioString)) {
            salario = Double.parseDouble(salarioString);
        }

        //Creamos el objeto de empleado (modelo)
        Empleado empleado = new Empleado(idEmpleado, nombre, apellido, dni, telefono, salario);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new EmpleadoDaoJDBC().actualizar(empleado);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
     

        //Creamos el objeto de cliente (modelo)
        Empleado empleado = new Empleado(idEmpleado);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new EmpleadoDaoJDBC().eliminar(empleado);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
