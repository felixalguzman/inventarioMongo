# inventarioMongo
Tarea de Sistema análisis tiempo  de entrega suplidor y manejo inventario con mongo

La empresa XYZ Computers es una empresa que se dedica a ensamblar computadoras. Hace un tiempo ha tenido inconveniente con el manejo de inventario, debido a que a veces no tiene disponible algunos de los componentes que necesita para armar las PCs. Es por eso que la empresa lo ha contratado para solicitarle una aplicación que genere ordenes de compras automáticas de los componentes necesarios para el ensamblaje de computadoras. Los requerimientos son:
* Opción para registrar los movimientos de inventario, es decir, las salidas y entradas de los componentes necesarios para el ensamble de las PCs.
* Deben tener defino por cada Componente de una Computadora los suplidores que la suplen y el tiempo de entrega de cada uno.
* Desarrollar un proceso: Generar Orden Compra Automática, el cual debe cumplir los siguientes requerimientos:

  * Una opción para especificar las partes de una computadora y cantidad que deseo tener en inventario en una fecha determinada.
  * Con estos datos generar la ordenes de compras tomando en cuenta que cantidad debo tener en inventario y el tiempo que tarda cada suplidor en entregar la misma.
  * El proceso debe seleccionar el suplidor con menor fecha de entrega para cada componente.
  * Se deben generar ordenes por cada Suplidor, es decir, si se especifican 2 partes de computadora y cada una es suplida por diferentes proveedores, entonces, se debe generar una orden para cada uno.
  * Al final mostrar la(s) orden(es) generada(s), el Suplidor y Fecha en la que debo ordenar cada parte de la PC.

### Observaciones:
* Cuando se refiere a tener una cantidad en una fecha especificada, se debe tener presente que existen movimientos que pueden ser realizados en los días que falten para la fecha especificada.
* MongoDB como base de datos.
* La interfaz de usuario puede ser como deseen, siempre y cuando cumpla con los requisitos.

A continuación, una idea de como debe ser el modelo de dato:


`MovimientoInventario:
{ codigoMovimiento,
codigoAlmacen,
tipoMovimiento,
codigoArticulo,
cantidad,
unidad }`

`TipoMovimiento, valores: “ENTRADA”, “SALIDA”`

`Articulo:
{ codigoArticulo,
descripcion,
[{codigoAlmacen,balanceActual}],
unidadCompra}`

`ArticuloSuplidor:
{codigoArticulo,codigoSuplidor,tiempoEntrega,precioCompra}`

`OrdenCompra:
{codigoOrdenCompra,
codigoSuplidor,
fechaOrden,
montoTotal,
articulos[{codigoArticulo,
cantidadOrdenada,
unidadCompra,
precioCompra}]
}`

#### Nota: esto solo una idea, pueden crear el modelo como les sea más cómodo
