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

##### Requerimientos Funcionales:
- Conexión entre Peers:
    - El sistema permite la conexión de dos peers en la red P2P.
    - Los peers pueden interactuar entre sí como cliente y servidor a la vez.
    - El sistema permite la desconexión de peers en la red P2P.

- Búsqueda de Archivos:
    - Desde cualquier peer, es posible realizar búsquedas de archivos en la red utilizando la DHT.
    - Implementación de la búsqueda basada en el protocolo Chord, lo que permite encontrar el nodo responsable de un archivo según su hash.

- Notificación de Nuevos Archivos:
    - Cuando un archivo es subido por un peer, se notifica a otros peers en la red, actualizando las tablas de hash distribuidas (DHT).
    - El sistema permite que un peer informe a otros sobre su estado y actualice la información sobre la disponibilidad de archivos.

- Interfaz Gráfica (GUI):
    - Se ha desarrollado una interfaz gráfica básica para interactuar con el sistema. Permite al usuario subir y buscar archivos desde cualquier peer.

- Replicación y Actualización de Peers Nuevos:
    - Si un archivo es subido y luego un peer se une a la red, el nuevo peer puede actualizarse y obtener la información de los archivos existentes antes de su conexión.

##### Requerimientos No Funcionales:
- Descentralización:
    - El sistema sigue el modelo de Chord, lo que asegura que no hay un punto central de control en la red.
    - La DHT distribuye la responsabilidad de los archivos entre los peers, garantizando la descentralización.

- Escalabilidad:
    - Aunque actualmente se permite la conexión entre dos peers, la arquitectura está diseñada para que la red pueda escalar, permitiendo la incorporación de más peers sin pérdida de rendimiento en la búsqueda de archivos.

- Concurrencia:
    - El sistema soporta múltiples peticiones simultáneas por parte de los peers, lo que permite una interacción fluida entre varios nodos a la vez.

- Comunicación gRPC:
    - La comunicación entre peers se realiza utilizando gRPC, lo que garantiza una comunicación eficiente y con soporte para múltiples lenguajes y plataformas.


#### 1.2. Requerimientos Funcionales y No Funcionales No Alcanzados (Propuesta del Profesor)

##### Requerimientos Funcionales:
- Gestión Completa de Join/Leave:
    - Aunque los peers pueden conectarse y desconectarse, no se ha implementado completamente el proceso de actualización automática de las tablas de predecesor y sucesor en la red cuando un peer se une o abandona.
    - Faltan detalles en la actualización automática de la DHT cuando un peer deja la red.

- Partición y Replicación:
    - Aunque se ha abordado la replicación de archivos para nuevos peers, aún no se implementa una replicación robusta que asegure la disponibilidad de archivos en caso de fallas o desconexiones de múltiples peers.

- Transferencia Real de Archivos:
    - Actualmente, no se ha implementado la transferencia real de archivos. Se utiliza un servicio dummy para simular la transferencia.
    - La transmisión real de archivos entre peers aún debe desarrollarse para completar la funcionalidad de almacenamiento y recuperación.

- Manejo Completo de Desconexiones de Peers:
    - Aunque se notifica la desconexión de peers, falta una gestión robusta que asegure la redistribución adecuada de los archivos y la actualización de la DHT en caso de una salida inesperada de un peer.

##### Requerimientos No Funcionales:
- Alta Disponibilidad:
    - Debido a la falta de replicación, el sistema no garantiza aún la alta disponibilidad de los archivos si un peer deja la red.

- Tolerancia a Fallos:
    - El sistema no cuenta con mecanismos de recuperación automáticos si un peer falla o deja de estar disponible, lo cual es crucial en redes P2P descentralizadas.


### 2. Información General de Diseño de Alto Nivel | Arquitectura | Patrones 

#### 2.1. Diseño de la Tabla DHT
**Decisión:** Usar una Tabla Hash Distribuida (DHT) y no una tabla hash centralizada:

**Criterio:** La descentralización es clave en sistemas P2P porque queremos evitar puntos únicos de fallo y cuellos de botella en el rendimiento. Al usar una DHT, logramos que la carga de almacenamiento y búsqueda de archivos esté distribuida entre todos los peers. Esto hace que la red sea más escalable y robusta, ya que los peers actúan de forma autónoma para almacenar una parte de los datos.

**Justificación:** Chord sigue una arquitectura distribuida donde no hay un solo servidor que administre todos los datos. Cada peer se hace responsable de una porción de la tabla hash. Esto también es importante porque facilita la autoescalabilidad: cuando nuevos peers se unen o abandonan la red, solo se redistribuyen las claves que corresponden a esos peers, sin afectar la red completa.

**Descripción del flujo:**
- Cada archivo se asocia con una clave hash generada por una función hash (SHA-1).
- Los peers también tienen una ID hash generada al unirse a la red.
- La tabla DHT se distribuye basándose en las claves hash de los archivos y los peers.
- El peer cuya ID es la más cercana (pero mayor o igual) al clave hash del archivo se convierte en el responsable de almacenar el archivo.

**Criterio para elegir SHA-1 como función hash:**
- Uniformidad: SHA-1 distribuye las claves uniformemente, lo cual minimiza la posibilidad de que algunos peers almacenen demasiados archivos y otros ninguno.
- Colisiones mínimas: Si bien SHA-1 no es la función hash más segura para criptografía, sigue siendo apropiada para distribuir claves en sistemas distribuidos, debido a su baja tasa de colisiones en el rango de bits que usamos.
- Eficiencia: Es una función hash eficiente en términos de tiempo de cómputo, crucial cuando estamos manejando múltiples solicitudes.

**Bosquejo del funcionamiento:**

![alt text](image.png)

**Tabla Hash**

![alt text](image-1.png)

1. Peer ID: Identificador único del peer en el anillo de la DHT, generado mediante SHA-1. Este valor indica la posición del peer en el anillo hash.

2. Sucesor: El peer que sigue inmediatamente al actual en el anillo. Este peer será responsable de las claves hash que no puedan ser resueltas por el peer actual. Cada peer debe conocer a su sucesor para mantener la consistencia en la red y asegurar que los archivos siempre estén accesibles.

3. Predecesor: El peer que está inmediatamente antes del peer actual en el anillo. Esto es relevante para asegurarse de que la red pueda “auto-repararse” si un peer abandona la red. 

4. Finger Table (k Entradas): Esta tabla contiene referencias a otros peers que están más adelante en el anillo, distribuidos exponencialmente a partir del nodo actual. Esto permite que la búsqueda sea eficiente, ya que se pueden saltar grandes secciones del anillo.
•Cada entrada en la finger table sigue la fórmula:
NodeID + 2^i mod (tamaño del anillo), donde i varía de 0 a (m-1) para un anillo de tamaño 2^m.

**Aplicación del "mod" en Chord:**
En el caso de la fórmula NodeID + 2^i mod (tamaño del anillo) en Chord:
1. NodeID es la posición actual del peer en el anillo, basada en su ID hash.
2. 2^i representa un salto exponencial (donde i varía de 0 a m-1, siendo m el tamaño del anillo).
3. El tamaño del anillo es el número total de posibles posiciones en el anillo, generalmente 2^m, donde m es el número de bits usados por la función hash (en el caso de SHA-1, serían 2160).

**¿Por qué usamos "mod" en la fórmula?** El uso de mod en Chord asegura que los valores resultantes de los saltos (NodeID + 2^i) se mantengan dentro del tamaño del anillo. Como el anillo es cíclico, una vez que llegas al valor máximo de posiciones (tamaño del anillo), vuelves a empezar desde cero.

**Ejemplo en un Anillo Pequeño:**

![alt text](image-2.png)

Data Items (Key Hash): Esta columna almacena los archivos que el peer es responsable de mantener. Los archivos están identificados por el valor hash de su clave (por ejemplo, el nombre del archivo), y se almacenan en el peer cuyo ID es mayor o igual al hash del archivo.


#### 2.2. Diagrama de Flujo de Conexiones entre Peers
![alt text](image-3.png)

Este flujo explica cómo se unen o salen los peers de la red y cómo se actualizan los sucesores y predecesores de los nodos en la red.

**Flujo de Conexión:**
1. Unión de un peer:
    - Un nuevo peer solicita a cualquier peer existente conectarse a la red.
    - El peer existente ayuda al nuevo peer a encontrar su posición en el anillo utilizando la función hash (SHA-1).
    - Los peers adyacentes al nuevo peer (su sucesor y predecesor) actualizan sus referencias.
    - Se redistribuyen las claves que pertenecen al rango del nuevo peer desde su sucesor.
    - El nuevo peer almacena las claves que le corresponden y se asegura de conocer a su sucesor y predecesor.

2. Actualización de Nodos Vecinos:
    - El peer de contacto notifica a los peers vecinos del nuevo peer para actualizar su sucesor y predecesor.

3. Redistribución de Responsabilidades:
    1. El nuevo peer recibe una porción de la DHT de su sucesor.
    2. Los archivos clave que ahora caen bajo la responsabilidad del nuevo peer se migran desde el sucesor.

4. Peer Deja la Red:
    - El peer notifica su salida a sus vecinos (sucesor y predecesor).
    - El sucesor del peer que se va asume las claves del peer que abandona la red.
    - Los peers adyacentes actualizan sus referencias.

**Criterio para evitar puntos únicos de fallo:** En lugar de que un nodo central coordine las conexiones y salidas, la responsabilidad de gestionar la conexión está distribuida. Esto asegura que la red siga funcionando incluso si varios peers se desconectan inesperadamente.

#### 2.3. Distribución de la DHT
Este flujo describe cómo los peers distribuyen y mantienen la DHT en la red. Siguiendo la estructura de Chord, los archivos se asignan de acuerdo con el hash de su clave.

**Decisión:** Utilizar tablas finger (finger tables) para mejorar la eficiencia de búsqueda.

**Criterio:** En redes P2P grandes, la búsqueda secuencial de un archivo puede ser muy lenta. Las finger tables permiten a los peers saltar a lo largo del anillo, logrando que las búsquedas sean más rápidas, reduciendo la latencia de las búsquedas a log(N) en una red de N peers.

**Justificación:** Aunque podríamos optar por una búsqueda lineal o centralizada, las finger tables son la opción óptima en términos de rendimiento y escalabilidad. Permiten que cada peer tenga una referencia a otros peers distantes en el anillo, lo que acelera las búsquedas y la resolución de solicitudes.

**Descripción del flujo:**
- Almacenamiento de un archivo:
    1. Un peer recibe una solicitud de almacenar un archivo.
    2. El peer calcula el hash de la clave del archivo.
    3. Si el peer no es responsable del archivo (según la DHT), consulta su finger table para redirigir la solicitud al peer responsable.
    4. El peer que recibe la solicitud de almacenamiento, y almacena el archivo en su porción de la DHT.
- Búsqueda de un archivo:
    1. Un peer recibe una solicitud para obtener un archivo.
    2. El peer calcula el hash de la clave del archivo.
    3. Si el peer no es responsable de la clave, utiliza su finger table para reenviar la solicitud al peer adecuado.

**Criterio para elegir finger tables en vez de una tabla completa de referencias:**
- Escalabilidad: Mantener referencias a todos los peers de la red es ineficiente a gran escala. En cambio, las finger tables permiten un número reducido de referencias (log(N)), mejorando la eficiencia de la búsqueda sin sobrecargar a los peers con información innecesaria.


#### 2.4. Diagrama de Flujo para Transmisiones de Archivos
![alt text](image-4.png)

**Decisión:** Uso de transmisión simulada utilizando servicios gRPC en lugar de transmisión real de archivos.

**Criterio:** En esta etapa académica, buscamos implementar la arquitectura y flujo de procesos sin necesidad de transferir archivos reales. Simular la transmisión utilizando un mecanismo de solicitud y respuesta es suficiente para cumplir con los objetivos del proyecto.

**Justificación:** Implementar una arquitectura distribuida para la transferencia de archivos reales sería más compleja y consumiría recursos innecesarios en esta fase del proyecto. La simulación permite validar el diseño arquitectónico sin los problemas de red y ancho de banda que traería la transmisión de archivos reales.

**Flujo de Transferencia de Archivos:**
1. Solicitud de Transferencia: Un peer solicita un archivo específico, enviando el hash del archivo a otro peer.
2. Búsqueda del Peer Responsable: Si el peer consultado no es responsable del archivo, utiliza su tabla finger para redirigir la solicitud al peer adecuado.
3. Transferencia Simulada: El peer responsable del archivo responde a la solicitud y realiza la transferencia (o simula el proceso de envío).
4. Confirmación: Una vez transferido el archivo, el peer receptor confirma la recepción del archivo.

**Criterio para simular la transmisión:**
    - Simplificación del proyecto: Simular la transmisión reduce la complejidad del proyecto y permite centrarse en los aspectos más importantes, como la distribución de claves, las conexiones entre peers y la replicación.

#### 2.5. Flujo para Partición y Replicación
Para mejorar la resiliencia y disponibilidad, implementamos partición y replicación. Cada archivo puede particionarse en múltiples fragmentos, y cada fragmento se almacena en varios peers (replicación).

**Decisión:** Particionar archivos y replicar los fragmentos en múltiples peers (mínimo 2 replicas).

**Criterio:** La partición de archivos es crucial para manejar archivos grandes y aumentar la eficiencia en la red. La replicación mejora la tolerancia a fallos y la disponibilidad de los archivos en caso de que algunos peers se desconecten inesperadamente.

**Justificación:** La replicación asegura que, incluso si un peer abandona la red o falla, los archivos aún estarán disponibles desde otros peers. Al particionar los archivos, cada peer almacena solo una pequeña parte del archivo, lo que balancea la carga de almacenamiento y mejora la velocidad de búsqueda y recuperación de datos.

**Flujo de Partición y Replicación:**
1. Partición: Al almacenar un archivo, se divide en varios fragmentos (usando una técnica como Reed-Solomon). Cada fragmento se hashdea y se distribuye a diferentes peers, según la DHT.
2. Replicación: Cada fragmento del archivo es replicado en al menos dos peers vecinos en el anillo de Chord para aumentar la tolerancia a fallos.
3. Búsqueda de Fragmentos: Al solicitar un archivo, el peer consulta a la DHT por los fragmentos y los peers responsables.
4. Recuperación: Los peers responsables de cada fragmento son contactados, y los fragmentos son recuperados y reensamblados en el peer solicitante.

**Criterio para replicar en dos peers adyacentes:** 
- Disponibilidad: Replicar en los peers adyacentes en el anillo de Chord es eficiente, ya que reduce la latencia al no requerir grandes saltos a través del anillo para buscar réplicas.
- Tolerancia a fallos: Al tener al menos una réplica, el sistema puede soportar que uno de los peers responsables falle sin perder datos.

**¿Cómo funciona Reed-Solomon?**

Reed-Solomon es un tipo de "algoritmo y/o teorema" de corrección de errores que puede reconstruir archivos o datos bien sean corruptos/perdidos si se conoce un conjunto mínimo de fragmentos. Enfocandolo el contexto de partición y replicación de archivos de nuestro proyecto se estructura así:
1. División del archivo: El archivo original se divide en varios fragmentos de datos.
2. Generación de fragmentos de paridad: Se generan fragmentos adicionales de paridad (o redundancia) a partir de los datos originales mediante operaciones algebraicas. Estos fragmentos pueden utilizarse para reconstruir el archivo incluso si algunos de los fragmentos originales se pierden o corrompen.
3. Reparación de datos: Reed-Solomon permite que si algunos de los fragmentos de datos (ya sean originales o de paridad) se pierden, el archivo se pueda reconstruir utilizando los fragmentos restantes. El número de fragmentos que pueden perderse depende de la configuración del esquema, es decir, cuántos fragmentos de paridad se generaron.

**Ejemplo de uso**
Si se tiene un archivo dividido en 5 fragmentos y se crean 3 fragmentos de paridad, se pueden perder hasta 3 fragmentos y aún seria posible reconstruir el archivo completo utilizando los fragmentos restantes. Esto es especialmente útil en sistemas distribuidos como redes P2P o sistemas de almacenamiento en la nube, donde los nodos pueden fallar o desconectarse.

### 3. Descripción del Ambiente de Desarrollo y Técnico
- *JDK 22.0.2:* https://www.oracle.com/java/technologies/downloads/?er=221886
- *Maven 3.9.9:* https://maven.apache.org/download.cgi
- **OPCIONAL:** *Protocol Buffers 3.20.3:* https://github.com/protocolbuffers/protobuf/releases
- **Como se compila y se ejecuta:** Por consola, hay que ubicarse en la carpeta *p2p-network*. Una vez aquí, se ejecutará el comando *mvn clean* (para limpiar las clases generadas) y posteriormente *mvn compile* (para compilar el código y crear los ejecutables).
- **Detalles del desarrollo:**
    - El peer en el puerto 50051 debe existir primero que todos para que funcione, dado que este es la referencia de actualización para los peers nuevos en la red.
    - Debido a la complejidad y tiempo de desarrollo, se tuvo que hacer flooding al enviar peticiones a todos los peers, en lugar de hacerlo dinámicamente.
    - La implementación de vecinos no se pudo completar, por lo que se optó por que cada peer tuviese su propia hashtable con la información de todos los nodos.
    - La fragmentación no pudo llevarse a cabo por efectos de tiempo, lo que si pudo lograrse fue la replicación pero con un bajo nivel en la consistencia de los datos (explicado en el video)
    - El archivo *peer.proto* es el que define los mensajes, los requests y responses utilizados para la comunicación vía gRPC.
    - Para configurar el proyecto lo único que hay que hacer es ejecutar el método main en *Peer.java*, posterior a la ejecución de los comandos de compilación.

### 4. Video Explicativo
https://www.youtube.com/watch?v=LQH8vxTLRO4&ab_channel=JoseDavidValenciaCalle

### Referencias:
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
