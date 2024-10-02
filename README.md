# Horus microservice template Java

Repositorio base para crear un microservicio en Java para el producto Horus Cloud.

-	Estructura de proyecto (Maven)
-	Configuración del CI/CD V3
-	Configuración de logs (log4j)
-	Configuración de properties
-	Configuración de healthcheck
-	Ejemplo de estructura de aplicación
-	Ejemplo de endpoint
    -   lógica de negocio
    -   administración
-	Ejemplo de servicios
-	Ejemplo de BBDD
-	Ejemplo de Redis
-	Ejemplo de JMS
    -   productor y consumidor
    -   colas y tópicos
-	Ejemplos de tests
    -   unitario
    -   integración
    -   aceptación
-	README y CHANGELOG

### Requerimientos previos
* Java 17
* Maven 3>
* Git
* IDE (IntelliJ, Eclipse)
* Docker (para la ejecución de los tests de integración)

### Instalación, configuración y ejecución en local 
1. Descarga o clona el proyecto en tu equipo local: [template-java bitbucket repo](https://bitbucket.mova.indra.es/projects/CCTRITSH77/repos/horus.microservices.app.template-java/browse)
2. Busca el texto "template-java" y reemplazarlo con el nombre del microservicio (ejemplo: "panels")
3. En la carpeta helm_values renombra el archivo con el nombre del microservicio (ejemplo: "horus.microservices.app.panels-cloud-dev.yaml")
4. Ejecuta el comando "mvn clean install" para compilar
5. Localiza el archivo "Application" dentro del package "es.indra.horus.cloud" y antes de lanzar el "Run", **debemos añadir
el perfil "local"** de Spring Boot para desarrollo local a través de las VM options ("-Dspring.profiles.active=local") 
6. Arranca la aplicación y comprueba que funciona
7. Antes de subir los cambios al repositorio **comprueba la configuración del CI/CD** en el archivo JenkinsFile y las carpetas jenkins y helm

Si el proceso ha salido bien, los siguientes endpoints estarán habilitados:

* [http://localhost:8080/template-java/actuator/health] (para verificar que el microservicio está levantado UP)
* [http://localhost:8080/template-java/swagger-ui/index.html] (swagger ui)

*NOTA: también podemos activar otros perfiles como "dev" para que nuestra aplicación cargue las properties de la maqueta
AWS ("/resources/application-dev.properties") con este parámetro: "-Dspring.profiles.active=dev"*

### Docker para las dependencias externas en local

Los microservicios utilizan componentes externos para funciones de persistencia, caché, mensajería, etc. Para poder
desarrollar y probar nuestras funcionalidades en local, se recomienda utilizar Docker ya que permite cargar y arrancar 
con un solo comando, contenedores con las aplicaciones y los puertos más comunes habilitados.

A continuación se detallan los contenedores básicos y ya probados que puedes utilizar para tus desarrollos:

1. **Redis + RedisInsight** -> este paquete ("redis-stack-server") levanta un redis en tu máquina + el gestor "insight" que 
te permite navegar por el contenido guardado. Comando: `docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server`
2. **ActiveMq** -> este contenedor levanta ActiveMq con su panel de administración. Comando: `docker run -d --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq`
3. **PostgreSQL** -> este contenedor levanta una instancia de PotgreSQl DB. Comando: `docker run -d --name postgresdb -p 5432:5432 -e POSTGRES_PASSWORD=postgres postgres`

### Test plan

Los tests son una parte esencial para garantizar una óptima calidad del código y verificar que la funcionalidad 
se ajusta a los requisitos funcionales. Para ello, se han organizado las pruebas en dos grandes grupos, los "tests unitarios"
y los "tests de aceptación/integración" que serán creados en la siguiente estructura de carpetas:

```
  /src
    /main
    /test
        /java
            /es/indra/horus/cloud/XXX -> test unitarios (organizados con la misma estructura de carpetas que "src" - efecto espejo)
            /acceptance -> se cubrirán flujos completos funcionales, similar a los tests end2end
            /integration -> se cubrirán especialmente servicios externos como redis, jms, etc
```
 
1. **Tests unitarios**

Se ejecutan al compilar el proyecto y cubren cada una de las piezas o clases de forma unitaria, siempre mockeando las 
dependencias involucradas. El nivel de cobertura en Sonar se genera a partir de los resultados obtenidos en este paso.

Se excluyen de la cobertura las siguientes carpetas, por lo que no es necesario cubrirlas:
```
  /repository
  /model
  /config
  /entity
  /mapper
  /exception
  Application
```
**Mockear dependencias:** utilizamos la herramienta mockito y por norma general mockeamos todas las dependencias con la
anotación @Mock, y las que son autogeneradas tipo mappers (mapstruct, jackson, ...) con la anotación @Spy. Además, 
apara instanciar la clase a testear, lo hacemos también mediante @InjectMocks.

Ejemplo:
```
@ExtendWith(MockitoExtension.class)
class ConfigControllerTest {

    private Panel panel1;
    
    @Spy
    private ConfigMapperImpl configMapperMock;
    @Mock
    private ConfigService configServiceMock;
    @InjectMocks
    private ConfigController configController;

    @BeforeEach
    void setUp() {
    ...
```

2. **Tests de aceptación/integración**

Estas pruebas se ejecutan en la etapa de integración, posterior a los unit tests y están especialmente indicados para
probar flujos funcionales (tests de aceptación) así como componentes que llaman a servicios externos (tests de integración)
tales como redis, jms, DB, ... 

Estos tests son esenciales para cubrir los requerimientos funcionales pero no se incluyen en los reportes de cobertura
de Sonar a diferencia de los tests unitarios.

Para desarrollarlos utilizaremos la anotación @SpringBootTest que permite levantar el contexto Springboot de forma 
muy similar a cuando levantamos el microservicio, lo que permite ser la herramienta ideal para probar la funcionalidad
del mismo.

Ejemplo:
```
@AutoConfigureMockMvc
@SpringBootTest(classes = {EmbedRedisTestConfig.class, Application.class})
class ActiveSignalingFlowIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Value("classpath:activeSignalingList.json")
    Resource activeSignalingList;

    @BeforeEach
    void setUp() throws Exception {
        mvc.perform(get("/admin/cache/activeSignaling/reset/")
        ...
```

### Documentación y referencias
* [especificaciones técnicas "template-java"](https://mind.indra.es/display/HORUS/SAAS-0-01+-+Plantilla+de+microservicio+en+Java)
