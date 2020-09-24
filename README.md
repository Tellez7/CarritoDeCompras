# CarritoDeCompras

1. Implementación de logueo con JWT
El archivo el cual contiene los usuarios se encuentra en src/main/resources "usuarios.txt", el archivo se encuentra estructurado de la siguiente manera:
usuario1,contrasena1|usuario2,contrasena2|.......

Como se observa el nombre de usuario y contraseña se separa por "," y cada usuario se separa por "|".

Al ser el login exitoso este generara el respectivo token de dicho usuario, el cual tendra que ser enviado como un header en cualquier otra peticion que vayamos a realizar. Seguido de esto en la carpeta logs quedara el log de loggin "LogLoggin.log" el cual tendra el registro de todas las peticiones de /login, esta compuesto de la siguiente manera:

FechaHora TipoMensaje mensaje Usuario de loggeo

2. Cada entidad tiene sus respectivas implementaciones.

MANEJADOR DE ERRORES:
El error personalizado se creo segun la estructura dada:

HttpStatus
Message
Code
BackendMessage

El error estandarizado se encuentra en la carpeta src/main/java/com/mainsoft/main/exception del proyecto con el nombre "ManejadorErrores", dicho error se implemento en el servicio del producto. La evidencia se encuentra en la carpeta "evidencias/JsonEstandarizado".

LOG4J2 ERRORES:
Cada servicio cuenta con su propio log, estos logs se encuentran en la carpeta logs, alli estan los logs con los siguientes nombres "LogClienteServicio", "LogProductoServicio", "LogVentaServicio". Cada log cuenta con la estructura dada.

RxJava:
El servicio implementado se encuentra en la clase "VentaServicio", se realizo la busqueda por el idVenta.

PRUEBAS UNITARIAS:
Se utilizo JUnit4 y las pruebas se encuentran en la carpeta src/main/java/com/mainsoft/main/tests, en ella se implementaron los 3 casos.

Las evidencias se pueden ver en la carpeta "evidencias/PruebasUnitarias" y en la carpeta correspondiente a cada entidad esta la imagen.

3. DOCKER:

El puerto expuesto es el 8081.

La aplicacion se levanta con los siguientes codigos:

docker build -t carritocompras-docker .

docker pull mysql:5.7

docker run --name mysql-bd -e MYSQL_ROOT_PASSWORD=Ing.-2018 -e MYSQL_DATABASE=CarritoCompras -e MYSQL_USER=esteban -e MYSQL_PASSWORD=Ing.-2018 -d mysql:5.7

docker container logs mysql-bd

docker run -d -p 8081:8081 --name carritocompras-container --link mysql-bd:mysql carritocompras-docker

docker container logs carritocompras-container

4. SEGURIDAD:
En cuanto a la seguridad propondria no manejar las credenciales de los usuarios por archivos planos, o que al menos esten encriptados o protegidos dichos archivos. Propondria manejarlo mejor por base de datos y hacer algun tipo de encriptacion en especial para la contraseña, esto para que no quede tan visible para cualquier usuario interno o externo a la aplicacion.

Otro punto a tener en cuenta es la obligatoriedad de algunos campos como no nulos, ya sean ID, nombre, etc. Algo ligado a esto tambien seria el tamaño de las variables, hay que tener en cuenta que no todas pueden tener el mismo tamaño, es decidir cual tiene mas funcionalidad sobre otra y decidir un tamaño optimo para cada una.

Algo que tambien tendria en cuenta seria el manejo de las pruebas, se podria decir que la prueba presentaba pocos casos de pruebas. Un punto a mejorar seria la realizacion de mas pruebas para tener en cuenta todos los escenarios posibles en una eventual salida a produccion.

Algo a tener en cuenta, es el cuidado de la informacion ya que se estan manejando logs. El punto a tener en cuenta seria la verificacion y las posteriores pruebas de los datos a mostrar ya que no seria correcto, ni seguro mostrar informacion privada de una entidad o de la aplicacion a personas ajenas al proyecto.