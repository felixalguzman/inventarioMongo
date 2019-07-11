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
                <a class="nav-link" href="/movimiento">Movimiento</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="/movimientos">Movimientos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/generarOrden">Generar Orden</a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="/ordenes">Ordenes</a>
            </li>
        </ul>
    </div>
</nav>

<main role="main" class="container">

    <div class="starter-template">
        <h1>Ordenes</h1>

        <form>


            <table id="table" class="table table-hover">
                <thead>
                <tr>
                    <#--                <th scope="col">#</th>-->
                    <th scope="col">Total</th>
                    <th scope="col">Fecha entrega</th>
                </tr>
                </thead>
                <tbody>

                <#list ordenes as orden>
                    <td>${orden.total}</td>
                    <td>${orden.fechaEntrega}</td>
                </#list>

                </tbody>
            </table>
    </div>


</main><!-- /.container -->
<script>

    var articulos = [];

    $(document).ready(function () {

        $('#agregar').on('click', function () {
            agregar();
        });

        $('#vender').on('click', function () {

            var nombre = $('#cliente').val();

            if (nombre === '' || nombre.length === 0) {
                alert('El nombre no puede estar vacio');
                return false;
            }


            console.log('a vender', articulos);
            $.ajax({
                type: 'POST',
                contentType: "application/json",
                url: '/vender/' + nombre,
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

        var articulo = $('#articulos option:selected').val();
        var cantidad = $('#cantidad').val();

        console.log("articulo: ", articulo, "cantidad", cantidad);

        if (cantidad.length === 0 || cantidad === '') {
            alert('La cantidad no puede estar vacia');
            return false;
        } else {


        }
        var articuloNombre, articuloDescripcion, articuloPrecio;

        $.ajax({
            type: 'POST',
            url: '/articulo/' + articulo,
            async: false,
            success: function (response) {
                // console.log('arti', response);
                articuloNombre = response.nombre;
                articuloDescripcion = response.descripcion;
                articuloPrecio = response.precio;


            }
        });

        articulos.push({articulo: articulo, cantidad: cantidad});

        $('#table').find('tbody').append('<tr><td>' + articuloNombre + ' </td> <td>' + articuloDescripcion + '</td>  <td>' + articuloPrecio + '</td> <td>' + cantidad + '</td></tr>');


    }

    function actualizarCantidadDisponible() {

        var data = $('#articulos option:selected').val();

        console.log('id seleccionado', data);

        $.ajax({
            type: 'POST',
            url: '/articulo/' + data,
            async: false,
            success: function (response) {
                console.log('arti', response);

                $("#cantidad").attr({
                    "max": response.stock,        // substitute your own
                    "min": 1  // values (or variables) here
                });

                $("#cantidad").val(1);
            }
        });


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