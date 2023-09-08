# event-driven-microservices
event-driven-microservices


-Descargar axon:
https://developer.axoniq.io/download
	
-Entrar por linea de comandos:
C:\Cursos\Programacion\CursoJavaSpringCQRSEventsDriven\AxonServer
	
-Inicializar axon:
PS C:\Cursos\Programacion\CursoJavaSpringCQRSEventsDriven\AxonServer> java -jar axonserver.jar
	
-Revisar:
http://localhost:8024/

-BeanUtils.copyProperties : copia valores de propiedades entre diferentes objetos.

-Acceder a la BD H2:
	-Ejecutar el proyecto:
	http://localhost:8081/h2-console/
	-En la pantalla colocar password: password
	-En JDBC URL colocar: jdbc:h2:file:~/data/productDB
	-Revisar:
	select * from product
	
-Ejecución:
	-Levantar api.
	-con postman llamar al crear.
	-ir a axon dashboard, en search colocar el GUID que arrojó postman y presionar "search":
		aggregateIdentifier = "e5ed6d60-8a8b-4b54-b1ae-67dbeb30fd6a" 
	-En payloadData está el mensaje enviado.
	