<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>

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

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/articulos">Articulos</a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="/compra">Compra</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/venta">Venta</a>
            </li>
        </ul>
    </div>
</nav>

<main role="main" class="container">

    <div class="starter-template">
        <h1>Nueva compra</h1>

        <form>

            <div class="form-group">


                <div class="row">

                    <div class="col">
                        <label for="suplidor">Suplidor</label>
                        <select id="suplidor" class="form-control" onchange="actualizarArticulos()" required
                                name="suplidor">
                            <option>Seleccione...</option>
                            <#list suplidores as suplidor>
                                <option value="${suplidor.id}">${suplidor.nombre}</option>
                            </#list>
                        </select>
                    </div>

                    <div class="col">

                        <label for="articulos">Articulos</label>
                        <select id="articulos" class="form-control" name="articulo"
                                onchange="actualizarCantidadDisponible()">
                            <option>Seleccione...</option>
                        </select>
                    </div>

                    <div class="col">
                        <label for="cantidad">Cantidad</label>
                        <input type="number" class="form-control" min="1" id="cantidad" name="cantidad">
                    </div>

                    <div class="col-auto align-self-end">
                        <button type="button" id="agregar" class="btn btn-success">Agregar</button>
                        <button type="reset" class="btn btn-secondary">Limpiar</button>
                    </div>
                </div>

                <br>
                <br>
                <br>

                <table id="table" class="table table-hover">
                    <thead>
                    <tr>
                        <#--                <th scope="col">#</th>-->
                        <th scope="col">Suplidor</th>
                        <th scope="col">Articulo</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Cantidad</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>

            <button type="button" id="comprar" class="btn btn-info">Comprar</button>
        </form>
    </div>

</main><!-- /.container -->
<script>

    var articulos = [];

    $(document).ready(function () {

        $('#agregar').on('click', function () {
            agregar();
        });

        $('#comprar').on('click', function () {

            console.log('a comprar', articulos);
            $.ajax({
                type: 'POST',
                contentType: "application/json",
                url: '/comprar',
                data: JSON.stringify(articulos),

                success: function (response) {


                    console.log('ok');

                }
            });
        });

    });

    function isInteger(x) {
        return (typeof x === 'number') && (x % 1 === 0);
    }


    function agregar() {

        var suplidor = $('#suplidor').val();
        var articulo = $('#articulos option:selected').val();
        var cantidad = $('#cantidad').val();

        console.log('supidor', suplidor, "articulo: ", articulo, "cantidad", cantidad);

        if (cantidad.length === 0 || cantidad === '') {
            alert('La cantidad no puede estar vacia');
            return false;
        } else {


        }
        var articuloNombre, articuloDescripcion, suplidorNombre;
        $.ajax({
            type: 'POST',
            url: '/suplidor/' + suplidor,
            async: false,
            success: function (response) {

                // console.log('suplidor', response);
                suplidorNombre = response.nombre;

            }
        });

        $.ajax({
            type: 'POST',
            url: '/articulo/' + articulo,
            async: false,
            success: function (response) {
                // console.log('arti', response);
                articuloNombre = response.nombre;
                articuloDescripcion = response.descripcion;


            }
        });

        articulos.push({suplidor: suplidor, articulo: articulo, cantidad: cantidad});

        $('#table').find('tbody').append('<tr><td>' + suplidorNombre + '</td> <td>' + articuloNombre + ' </td> <td>' + articuloDescripcion + '</td> <td>' + cantidad + '</td></tr>');


    }

    function actualizarArticulos() {

        var data = $('#suplidor').val();
        // console.log('supidor', data);

        $.ajax({
            type: 'POST',
            url: '/articulosSuplidor/' + data,
            success: function (response) {
                console.log("llego", response);
                $("#articulos").empty();
                response.forEach(function (articulo) {

                    $("#articulos").append(new Option(articulo.nombre, articulo._id, false, false));

                })

                // $('#modal-default').modal('toggle');
            }
        });


    }

    function actualizarCantidadDisponible() {

        var data = $('#articulos option:selected').val();

        console.log('id seleccionado', data);

    }

    function loadTable(tableId, fields, data) {
        //$('#' + tableId).empty(); //not really necessary
        var rows = '';
        $.each(data, function (index, item) {
            var row = '<tr>';
            $.each(fields, function (index, field) {
                row += '<td>' + item[field + ''] + '</td>';
            });
            rows += row + '<tr>';
        });
        $('#' + tableId + ' tbody').html(rows);
    }

</script>
</body>
</html>