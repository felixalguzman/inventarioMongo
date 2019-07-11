<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
            integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o"
            crossorigin="anonymous"></script>
    <script src="https://unpkg.com/feather-icons"></script>

    <style>
        body {
            padding-top: 5rem;
        }

        .starter-template {
            padding: 3rem 1.5rem;
            text-align: center;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">

    <div class="collapse navbar-collapse" _id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/movimiento">Movimiento</a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="/movimientos">Movimientos</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/generarOrden">Generar Orden</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/ordenes">Ordenes</a>
            </li>


        </ul>

    </div>
</nav>

<main role="main" class="container">

    <div class="starter-template">


        <h3>Movimientos</h3>

        <table class="table table-hover">
            <thead>
            <tr>
                <#--                <th scope="col">#</th>-->
                <th scope="col">Tipo</th>
                <th scope="col">Fecha</th>
<#--                <th scope="col">Cantidad movimiento</th>-->
            </tr>
            </thead>
            <tbody>
            <#list movimientos as moviento>
                <tr>
                    <td>${moviento.tipoMovimiento}</td>
                    <td>${moviento.fecha}</td>
<#--                    <td>${moviento.detalleMovimiento.cantidadMovimiento}</td>-->
<#--                    <td>${articulo.}</td>-->

                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Articulo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <form action="">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" id="nombre" placeholder="Nombre" name="nombre">

                        </div>
                        <div class="form-group">
                            <label for="desc">Descripción</label>
                            <textarea class="form-control" id="desc"
                                      placeholder="Descripción"> </textarea>
                        </div>
                        <div class="form-group">
                            <label class="" for="stock">Cantidad</label>
                            <input type="number" class="form-control" id="stock" name="stock">
                        </div>

                        <div class="modal-footer">

                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            <button type="button" class="btn btn-primary">Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main><!-- /.container -->
<!-- Modal -->


<script>
    feather.replace();

    $(document).ready(function () {

        console.log('ok');


        $('#agregar').on('click', function () {
            $('#modal').modal('show');
        });

    });




</script>
</body>
</html>