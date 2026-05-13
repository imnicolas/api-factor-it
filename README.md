# Desafío
Se deberán crear tres tipos de carritos, el COMUN, el promocionable por FECHA ESPECIAL y el promocionable por
USUARIO VIP.

# Contexto
Cuando el usuario solicite crear un nuevo carrito la aplicación deberá distinguir o indicar a que tipo de carrito
corresponde. 

# No alcanzado por el sistema
- No se podrán combinar las promociones. 
(Por ej. Un carrito NO puede ser Usuario VIP y Promocionable por Fecha ESPECIAL a la vez)

- No se debe implementar formas de pagos, solo
que se puedan visualizar los ítems del mismo.

- No será necesario desarrollar ningún tipo de ABM, (ni de los productos, ni de fechas promocionables, ni de usuarios) Con solo contar con ellos en la base de datos estará bien.

# Funcionalidades
Las funcionalidades que se deberán mostrar son:
- crear un carrito nuevo
- eliminar un carrito
- agregar y eliminar productos del carrito
- consultar el estado del carrito (que nos dará el total a pagar).

El servicio deberá contar con un parámetro que permita simular la fecha.
Si el cliente completo la compra, deberá quedar registrado. Sino se concretó la compra, el carrito automáticamente se
destruye.
El cliente podrá realizar varias compras en el mismo día.

El frontend deberá simular el envío de parámetros con la correcta visualización del funcionamiento de los request. Por
ejemplo, agregar y eliminar cantidades de productos, agregar y eliminar un producto, eliminar carrito, finalizar compra del
carrito, consultar clientes VIP, clientes que pasaron a ser VIP en un determinado mes, clientes que dejaron de ser VIP en
un determinado mes.

# Reglas de negocio.
Para calcular el valor del carrito se debe tener en cuenta:

- <b>Si se compran</b> exactamente 4 productos: Se hace un descuento general del 25%.
- <b>Si se compran</b> más de 10 productos: Se le realizará además un descuento de 100.
- Si el carrito <b>es promocionable por fecha especial</b>: Se hace un descuento general de 300.
- Si el carrito <b>es VIP</b>: Se bonifica el producto más barato y se hace un descuento general de 500.

# Cliente reglas.
<b>Si el cliente</b> en un determinado mes, <b>realizó</b> compras por más de 10.000, pasa a ser considerado VIP en su próxima
compra. (considerar el valor de lo que realmente paga el cliente por los carritos luego de aplicarle los descuentos)

<b>Si el cliente</b> en un determinado mes, <b>no realizó</b> compras, deja de ser VIP si lo era.

# Stack
La aplicación deberá estar utilizar las siguientes pautas y tecnologías (utilice todo o lo que más conoce:

Angular 7 o +
Java 6+
Css
Spring 5+ / Springboot
JPA / Hibernate
Exponer servicios REST (agregar proyecto postman. Usar Swagger /open API)
Exponer servicios SOAP (agregar proyecto SOAP UI)
Test Unitarios Junit /Mockito
Scripts SQL

Se valorará la implementación de algún caso y/o método de testing unitario