# Proyecto Mercadolibre - Juan Matias Lopez

El proyecto se encuentra programado en Java 8. La API Rest se encuentra programada en Java 8, utilizando Maven y GCloud, y se encuentra deployeada en Google App Engine.
Se provee el codigo fuente y un archivo ejecutable listo para ejecutar.

Carpeta main : 
* src : contiene el codigo fuente del ejercicio
* release : contiene el ejecutable previamente compilado

Carpeta restAPI:
* src : contiene el codigo fuente del ejercicio

Uso del archivo ProyectoML.jar en la carpeta release:

1) Ejecución normal o mediante consola : java -jar ProyectoML.jar<br>
	Ejecuta la interfaz grafica provista para el ejercicio. Este modo permite observar la evolución de las actividades, avanzar en un numero variable de dias (1, 5 o 10 dias; 1, 5 o 10 años), y muestra tanto la información general del estado de la simulación, asi como una representacion grafica del estado.
2) Ejecucion mediante consola : java -jar ProyectoML.jar [dias]<br>
	Muestra el resultado de un universo en el que han transcurrido [dias] dias. Muestra los datos de los eventos climaticos relevantes.
3) Acceso por API : https://planet-weather.appspot.com/clima?dia=[dias]<br>
	Retorna un json con el formato:
	{
		"dia" : [dias],
		"clima" : [clima]
	}
	Donde [clima] es el clima del universo en el dia ingresado.

Clarificaciones y asunciones:

* Para poder hacer uso de una abstracción matematica para resolver el problema, se asume que:
	* Los planetas comienzan en el dia 0, en el punto (X, 0), donde X es la distancia del planeta al sol.
	* Se considera al sol referido en el ejercicio, como el punto (0,0).
	* A fin de simplificar el modelaje, se considera un año como 360 dias. La cantidad de dias que representan los años en la aplicación, son : 360 dias (1 año), 1800 dias (5 años) y 3600 dias (10 años).
	* Con la misma intencion de que el modelaje sea mas claro, se aplico una flexibilización de la condicion de alineación de los elementos necesarios para determinar un evento climatologico. En la practica, esto significa que se considera un rango de error para sobrellevar los efectos de el exceso, o falta de, precisión en el calculo. Esto se hizo tambien para que el ejercicio logre resultados mas interesantes.
	* Se considera la distancia de los planetas en forma simplificada. Esto es, la distancias estan divididas por 100, lo que no afecta los calculos. Las distancias programadas son 5,10 y 20 kilometros.