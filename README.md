# Proyecto Rest API para realizar cambio de tipo de cotizacion de Monedas
 
 Objetivo: El objetivo es demostrar los conocimientos que poseo para el ingreso en Financiera OH!
 
 Instalacion: 
 
              1-Clonar el proyecto 
              2-Ejecutar el comando: mvn clean package deploy
              4-Ejecutar el comando: docker image ls
              5-Verificar que la imagen haya sido creado
              6-Ejecutar el siguiente comando para docker: docker run -d -p 8080:8080 (colocar el IMAGE ID que nos muestra la pantalla de docker)
              7-Testear sobre SWAGGER http://localhost:8080/swagger-ui.html#/ el estado de las API
              8-Puede consultar la Base de Datos H2
               -ingresar en http://localhost:8080/h2-console
               -JDBC URL: jdbc:h2:mem:monedaCambioDB
               -User Name: root
               -Password: root
  
  Api: 
  
              1- /cambio ejecutarCambio: 
                  a) Ingresar en nombreMoneda1 el abreviado de los nombres de las monedas ejemplo: "AED", "ARS", "EUR", "USD","JPY","RUB", Esta corresponde a la                          moneda base.
                  b) Ingresar en montoMoneda1 el monto base a cambiar por la moneda deseada
                  c) Ingresar en nombreMoneda2 en modo abreviado como en el primer caso, la moneda a la que se desea conocer valor
                  d) En el response observamos en montoMoneda2 su cambio de cotizacion
              
              2- /cambio/borrar/{id}
                  a) Ingrese el numero Id para realizar el borrado de la base de datos
                  b) En caso de ingresar un id inexistente retorna 404
              
              3- /cambio/buscarCambio/{id}
                  a) Ingrese el numero Id para realizar su busqueda en la base de datos
                  b) En caso de ingresar un id inexistente retorna 404
                  
              4- /cambio/historial
                  a) Podemos visualizar todos los registros almacenados en la base de datos
