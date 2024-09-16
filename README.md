# ST0263 | Tópicos Especiales en Telemática

## Estudiantes: 
- José Manuel Camargo Hoyos | jmcamargoh@eafit.edu.co
- Jose David Valencia Calle | jdvalenci2@eafit.edu.co

## Profesor:
- Juan Carlos Montoya Mendoza | jcmontoy@eafit.edu.co

## Reto #1: Aplicaciones P2P

### 1. Descripción de la Actividad
En esta actividad se realiza el diseño e implementación de un sistema P2P, en el que cada nodo contiene uno o más microservicios para implementar las funcionalidades que soportan un sistema 
de compartición de archivos totalmente distribuido y descentralizado.
En esta versión del reto se implementa una red P2P basada y soportada en una tabla hash distribuida (DHT), utilizando como lenguaje a Java.

#### 1.1. Requerimientos Funcionales y No Funcionales Alcanzados (Propuesta del Profesor)

# Requerimientos Funcionales:

Conexión entre Peers:
-El sistema permite la conexión de dos peers en la red P2P.
-Los peers pueden interactuar entre sí como cliente y servidor a la vez.

Búsqueda de Archivos:
-Desde cualquier peer, es posible realizar búsquedas de archivos en la red utilizando la DHT.
-Implementación de la búsqueda basada en el protocolo Chord, lo que permite encontrar el nodo responsable de un archivo según su hash.

Notificación de Nuevos Archivos:
-Cuando un archivo es subido por un peer, se notifica a otros peers en la red, actualizando las tablas de hash distribuidas (DHT).
-El sistema permite que un peer informe a otros sobre su estado y actualice la información sobre la disponibilidad de archivos.

Interfaz Gráfica (GUI):
-Se ha desarrollado una interfaz gráfica básica para interactuar con el sistema. Permite al usuario subir y buscar archivos desde cualquier peer.


# Requerimientos No Funcionales:

Descentralización:
-El sistema sigue el modelo de Chord, lo que asegura que no hay un punto central de control en la red.
-La DHT distribuye la responsabilidad de los archivos entre los peers, garantizando la descentralización.

Escalabilidad:
-Aunque actualmente se permite la conexión entre dos peers, la arquitectura está diseñada para que la red pueda escalar, permitiendo la incorporación de más peers sin pérdida de rendimiento en la búsqueda de archivos.

Concurrencia:
-El sistema soporta múltiples peticiones simultáneas por parte de los peers, lo que permite una interacción fluida entre varios nodos a la vez.

Comunicación gRPC:
-La comunicación entre peers se realiza utilizando gRPC, lo que garantiza una comunicación eficiente y con soporte para múltiples lenguajes y plataformas.


#### 1.2. Requerimientos Funcionales y No Funcionales No Alcanzados (Propuesta del Profesor)

# Requerimientos Funcionales:

Gestión Completa de Join/Leave:
-Aunque los peers pueden conectarse, no se ha implementado completamente el proceso de actualización automática de las tablas de predecesor y sucesor en la red cuando un peer se une o abandona.
-Faltan detalles en la actualización automática de la DHT cuando un peer deja la red.

Partición y Replicación:
-No se ha implementado la partición ni la replicación de archivos en la red, algo necesario para garantizar la disponibilidad de archivos en caso de que un peer deje de estar disponible.
-Este proceso es esencial para mantener la resiliencia en redes distribuidas.

Transferencia Real de Archivos:
-Actualmente, no se ha implementado la transferencia real de archivos. Se utiliza un servicio dummy para simular la transferencia.
-La transmisión real de archivos entre peers aún debe desarrollarse para completar la funcionalidad de almacenamiento y recuperación.

# Requerimientos No Funcionales:

Alta Disponibilidad:
-Debido a la falta de replicación, el sistema no garantiza aún la alta disponibilidad de los archivos si un peer deja la red.

Tolerancia a Fallos:
-El sistema no cuenta con mecanismos de recuperación automáticos si un peer falla o deja de estar disponible, lo cual es crucial en redes P2P descentralizadas.


### 2. Información General de Diseño de Alto Nivel | Arquitectura | Patrones | Mejores Prácticas Utilizadas

### 3. Descripción del Ambiente de Desarrollo y Técnico
- Lenguaje de programación, librerias, paquetes, etc, con sus numeros de versiones.
- *JDK 22.0.2:* https://www.oracle.com/java/technologies/downloads/?er=221886
- *Maven 3.9.9:* https://maven.apache.org/download.cgi
- *Protocol Buffers 3.20.3:* https://github.com/protocolbuffers/protobuf/releases
- Como se compila y ejecuta.
- Detalles del desarrollo.
- Detalles técnicos
- Descripción y como se configura los parámetros del proyecto (ej: ip, puertos, conexión a bases de datos, variables de ambiente, parámetros, etc)
- Opcional - detalles de la organización del código por carpetas o descripción de algún archivo. (ESTRUCTURA DE DIRECTORIOS Y ARCHIVOS IMPORTANTE DEL PROYECTO, comando 'tree' de linux)
- Opcional - si quiere mostrar resultados o pantallazos 

### 4. Descripción del Ambiente de Ejecución (en producción) lenguaje de programación, librerias, paquetes, etc, con sus numeros de versiones.
- IP o nombres de dominio en nube o en la máquina servidor.
- Descripción y como se configura los parámetros del proyecto (ej: ip, puertos, conexión a bases de datos, variables de ambiente, parámetros, etc)
- Como se lanza el servidor.
- Una mini guia de como un usuario utilizaría el software o la aplicación
- Opcionalmente - si quiere mostrar resultados o pantallazos 

### 5. Información Adicional (si se requiere)

### Referencias:
<debemos siempre reconocer los créditos de partes del código que reutilizaremos, así como referencias a youtube, o referencias bibliográficas utilizadas para desarrollar el proyecto o la actividad>
- https://www.youtube.com/watch?v=FLczfVQ7n7o&ab_channel=SaravananSeenivasan
- https://mvnrepository.com/search?q=dht
- https://www.youtube.com/watch?v=TR-otP45eYU&ab_channel=Techtter
- https://os72.github.io/protoc-jar-maven-plugin/index.html
- https://github.com/os72/protoc-jar-maven-plugin
- https://www.xolstice.org/protobuf-maven-plugin/
- https://grpc.io/docs/languages/java/basics/
- https://github.com/RitanMihai/Book-Finder/tree/37bef33df2b1a5b4a2e377ddb024d0dc6ed0fd77
- https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html
- https://pdos.csail.mit.edu/papers/chord:sigcomm01/chord_sigcomm.pdf
- https://www.freecodecamp.org/espanol/news/cadena-de-java-a-int-como-convertir-una-cadena-en-un-numero-entero/
