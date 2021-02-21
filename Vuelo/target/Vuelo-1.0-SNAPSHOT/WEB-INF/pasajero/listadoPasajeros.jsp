<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="pasajeros">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4>Passengers list</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>ID Card</th>
                                <th></th>
                                <th>
                                    <a href="#" class="btn btn-danger btn-block"
                                       data-toggle="modal" data-target="#agregarEmpleadoModal">
                                        <i class="fas fa-user-plus"></i>
                                    </a>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pasajero" items="${pasajeros}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${pasajero.nombre} ${pasajero.apellido}</td>
                                    <td>${pasajero.dni}</td>
                                    <td>
                                        <a href="#" class="btn btn-warning">
                                            <i class="fas fa-user-edit"></i> Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="#" class="btn btn-primary">
                                            <i class="fas fa-plane"></i> Fly
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4>HT534 Buenos Aires - Madrid</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Passengers</th>
                                <th>Seat</th>
                            </tr>
                        </thead>   
                    </table>
                </div>
            </div>
        </div>                             
    </div>
</section>
<!-- Agregar pasajero MODAL -->
<jsp:include page="/WEB-INF/pasajero/agregarPasajero.jsp"/>