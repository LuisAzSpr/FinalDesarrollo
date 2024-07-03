# Sistema de verificacion del clima (Sprint1)

Comencemos con el codigo:

### 1. Diseño y implementación inicial:

Podemos empezar de la siguiente forma, aqui un ejemplo para temperatura pero se cumple para
las otras clases de manera equivalente.

```java
public class Temperatura{
    private int valor;
    private String entrada;
    private String estado;

    public Temperatura(String entrada){
        this.entrada = entrada;
        this.valor = Calculator.calcularValor(entrada,"C");
        this.estado = calcularEstado();
    }

    public String getEntrada() {
        return entrada;
    }

    public int getValor() {
        return valor;
    }

    public String calcularEstado(){
        return "alta";
    }

    public String getEstado() {
        return estado;
    }
}

```

Calculator se encarga de verificar que las entradas sean validas, si no son validas entonces se lanza una exception, si
es que son validas entonces retorna el valor. (Esta implementacion se hara con TDD)

```java
public class Calculator {
    public static int calcularValor(String entrada,String unidades) {
        return 0;
    }
}
```

Como podemos ver las clases anteriores cumplen con una unica responsabilidad,

- Las clases que representan las condiciones climaticas solo tienen la responsabilidad de conocer su estado en base a la entrada del cliente.
- Las clase Calculator solo tiene la responsabilidad de tomar la entrada del usuario y convertirla en un valor o lanzar una exception en caso sea una entrada incorrecta.

### 2. Desarrollo con TDD:

Utilicemos el patron AAA para nuestras pruebas unitarias :D .

```java
class CalculatorTest {
    
    @Test
    void calcularLluviaConEntradasCorrectas(){
        
        // Arrange
        String entrada = "30 mm";
        String unidades = "mm";
        
        //Act
        int valor = Calculator.calcularValor(entrada,unidades);

        //Assert
        assertThat(valor).isEqualTo(30);
    }

    @Test
    void calcularHumedadConEntradaSinEspacioNoRetornaUltimoDigito(){
        // Arrange
        String entrada = "90%";
        String unidades = "%";
        
        //Act
        int valor = Calculator.calcularValor(entrada,unidades);

        //Assert
        assertThat(valor).isEqualTo(9);
    }
    
}
```

Veamos ahora que las pruebas fallan:

![img.png](Imagenes%2Fimg.png)

Ahora escribamos el codigo para hacer que las pruebas pasen:

Se que en teoria deberiamos escribir solo para que las pruebas pasen, es decir, no deberiamos escribir
exceptions pero por cuestiones de tiempo, estamos implementando la logica completa...

```java
//Importante:
// Las entradas deben contener un espacio entre el valor numerico y las unidades!!!
public class Calculator {
    public static int calcularValor(String entrada,String unidades) {
        for (int i = 0; i < entrada.length(); i++) {
            if(entrada.substring(i).equals(unidades)){
                try{
                    int valor = Integer.parseInt(entrada.substring(0,i-1));
                    return valor;
                }catch(Exception e){
                    throw new IllegalArgumentException("Las unidades son incorrectas");
                }
            }
        }
        throw new IllegalArgumentException("Las unidades son incorrectas");
    }
}
```

Podemos ver que ahora las pruebas si pasan!!

![img_1.png](Imagenes%2Fimg_1.png)

Ahora podemos refactorizar un poco, podemos ver que existe una linea largaa que se repite 2 veces, esta es 
la de lanzar la exception IlegalArgumentException, por lo que la idea esta en ponerla en una funcion
privada.


```java
public static int calcularValor(String entrada,String unidades) {
    for (int i = 0; i < entrada.length(); i++) {
        if(entrada.substring(i).equals(unidades)){
            try{
                int valor = Integer.parseInt(entrada.substring(0,i-1));
                return valor;
            }catch(Exception e){
            lanzarException();
            }
        }
    }
    lanzarException();
    return -1;
}

private static void lanzarException(){
    throw new IllegalArgumentException("Las unidades son incorrectas");
}
```

Luego veamos nuevamente que las pruebas siguen en verde. 

![img_2.png](Imagenes%2Fimg_2.png)

Ahora veamos para el caso de Temperatura, para esta clase (o en general para las clases condiciones climaticas) debemos probar el
estado en el que se encuentran.


Vemos que falla.

```java
@Test
void calcularEstadoSegunLaTemperatura(){
    //Arrange
    Temperatura temperatura = new Temperatura("15 C");

    //Act
    String estado = temperatura.getEstado();

    //Assert
    assertThat(estado).isEqualTo("promedio");
}

```


![img_3.png](Imagenes%2Fimg_3.png)

al igual que en el anterior implementaremos la logica defrente por cuestiones de tiempo,en lugar de codear solo lo necesario

```java
public class Temperatura{
    private int valor;
    private String entrada;
    private String estado;

    public Temperatura(String entrada){
        this.entrada = entrada;
        this.valor = Calculator.calcularValor(entrada,"C");
        this.estado = calcularEstado();
    }

    public String getEntrada() {
        return entrada;
    }

    public int getValor() {
        return valor;
    }

    public String calcularEstado(){
        if(valor>25){
            return "alta";
        }
        else if(valor>14){
            return "promedio";
        }
        else{
            return "baja";
        }
    }

    public String getEstado() {
        return estado;
    }
}
```

Vemos que ahora esta en verde!!

![img_4.png](Imagenes%2Fimg_4.png)

### 4. Refactorización y código limpio:

Hasta aqui hemos usado a la clase Temperatura como algo comun a todas las clases y es que realmente
todas definen el mismo comportamiento, todas tienen una entrada del usuario que seran convertidas a 
un numero, todas tienen unidades, todas tienen un estado, por lo que para respetar los principios SOLID,
podemos crear una clase padre de tal forma que implemente todas estos compartimientos y no estarlos repitiendo
en cada clase.

Veamos:

Esta clase es padre de todas las clases que son condiciones climaticas:

```java
public abstract class CondicionClimatica {
    
    protected String entrada;
    protected int valor;
    protected String estado;
    
    protected CondicionClimatica(String entrada,String unidades){
        this.entrada = entrada;
        this.valor = Calculator.calcularValor(entrada,unidades);
        this.estado = calcularEstado();
    }
    
    public abstract String calcularEstado();

    public String getEntrada(){
        return entrada;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

}

```

Como podemos ver si ahora queremos agregar una nueva condicion climatica solo tenemos que heredar
de la clase Condicion climatica y definir la funcion calcularEstado().

### 3. Validación de pruebas con stubs y fakes:

Hemos utilizado stubs para poder simular el comportamiento de las clases condiciones climaticas
y asi poder evuluar el metodo obtenerMensajes de la clase Sistema. Como podemos ver acontinuacion 
en el codigo:

```java
class SistemaTest {

    private Sistema sistema;

    private Humedad humedadMock;
    private Lluvia lluviaMock;
    private PresionAtmosferica presionMock;
    private Temperatura temperaturaMock;
    private Viento vientoMock;

    @BeforeEach
    void setUp(){
        ArrayList<CondicionClimatica> condicionesMock = new ArrayList<>();
        
        // Definimos los tubs de cada Condicion climatica
        humedadMock = mock(Humedad.class);
        condicionesMock.add(humedadMock);

        lluviaMock = mock(Lluvia.class);
        condicionesMock.add(lluviaMock);

        presionMock = mock(PresionAtmosferica.class);
        condicionesMock.add(presionMock);

        temperaturaMock = mock(Temperatura.class);
        condicionesMock.add(temperaturaMock);

        vientoMock = mock(Viento.class);
        condicionesMock.add(vientoMock);
        
        // inyeccion de dependencias (inyectamos una dependencia por el constructor)
        sistema = new Sistema(condicionesMock);
    }

    @Test
    void mensajesTest(){
        // Apartir de llamar al estado de las condiciones climaticas, estas tienen que devolver un valor predeterminado
        when(humedadMock.getEstado()).thenReturn("humedad alta");
        when(lluviaMock.getEstado()).thenReturn("lluvia intensa");
        when(presionMock.getEstado()).thenReturn("presion alta");
        when(temperaturaMock.getEstado()).thenReturn("temperatura alta");
        when(vientoMock.getEstado()).thenReturn("viento fuerte");

        // obtenemos los mensajes del sistema que usa a los mocks
        List<String> mensajes = sistema.obtenerMensajes();

        // La idea esta en poder probar lo retorna un arreglo con los estados de las condiciones climaticas
        List<String> expected = Arrays.asList("humedad alta", "lluvia intensa", "presion alta", "temperatura alta", "viento fuerte");
        assertThat(mensajes).isEqualTo(expected);
    }
}

```

Podemos ver que esta en verde...

![img_6.png](Imagenes%2Fimg_6.png)


### 5. Métricas de calidad:

Utilicemos jacoco para ver la cobertura de nuestro codigo;

![img_10.png](Imagenes%2Fimg_10.png)

![img_7.png](Imagenes%2Fimg_7.png)

Como podemos ver, lo que falta testear en la mayoria de las clases son para ciertos valores altos,moderados o bajos
de la unidad que representan.

![img_8.png](Imagenes%2Fimg_8.png)

![img_9.png](Imagenes%2Fimg_9.png)

Por lo que podemos hacer algunas pruebas estructurales para poder aumentar el porcentaje de cobertura de nuestro codigo.


```java
@Test
void lluviaMayor30DebeDevolverLluviaIntensa(){
// Arrange
Lluvia lluvia = new Lluvia("40 mm");

    //Act
    String estado = lluvia.getEstado();

    //Assert
    assertThat(estado).isEqualTo("lluvia intensa");
}
```

```java
@Test
void vientoMayor50DebeDevolverVientoFuerte(){
    // Arrange
    Viento viento = new Viento("55 km/h");

    //Act
    String estado = viento.getEstado();

    //Assert
    assertThat(estado).isEqualTo("viento fuerte");
}
```


```java
@Test
void presionMayor12DebeDevolverPresionAlta(){
    // Arrange
    Viento viento = new Viento("15 hPa");

    //Act
    String estado = viento.getEstado();

    //Assert
    assertThat(estado).isEqualTo("presion alta");
}

```

```java
@Test
void humedadMayor40Menor70DebeDevolverHumedadModerada(){
// Arrange
Viento viento = new Viento("45 %");

    //Act
    String estado = viento.getEstado();

    //Assert
    assertThat(estado).isEqualTo("humedad moderada");
}
```

Podemos verificar que hemos aumentado el porcentaje de cobertura en nuestro codigo.

![img_11.png](Imagenes%2Fimg_11.png)

**Complejidad Ciclotomica**



