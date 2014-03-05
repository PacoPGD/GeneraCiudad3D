GeneraCiudad3D
==============

Programa para crear un documento VRML/X3D generando una ciudad tridimensional

Documentación sobre generador de ciudades
a) Algoritmo de generación.
El algoritmo de construir la ciudad construye los distintos elementos de la ciudad en un orden determinado, esto se hace leyendo una matriz que tendrá distintos valores los cuales indican que elemento hay que insertar en cada posición.


b) Estructuras de datos.
Se usa una matriz de dimensiones dadas por el usuario con el cual se representará el terreno, está matriz puede contener distintos valores numericos, siendo los valores numericos los equivalentes a cada tipo de terreno:
0. Arena
1. Calle
2. Cruce
3. Arbol
4. Iglesia

Los edificios ocupan los valores del 5 en adelante siendo este valor la altura de los mismos.


c) Funcionamiento de la generación.
Se ejecutan una serie de funciones para conseguir rellenar la matriz:

ConstruirCiudad: Es la función que se encarga de inicializar la matriz a 0 "arena"  y posteriormente llama al resto de funciones de generación.

ConstruirCalles: Esta función inserta en la matriz las calles pedidas en el usuario tanto en vertical como en horizontal, primero comprueba que la posición donde va a insertar la carretera es arena "porque puede haber otra calle ya insertada en esa posición" y en el caso de que este libre inserta la calle.

ConstruirArboles: Esta función comprueba si entre calles hay espacios pequeños "calles separadas por solo una posición" e inserta arboles en estos pequeños huecos que no darán para la construcción de los edificios.

ConstruirIglesia: Se inserta una iglesia en una posición que sea posible "que tengamos una superficie de arena lo suficientemente grande como para insertarla" y se inserta, en caso de no haber espacio no se genera este edificio.

ConstruirCasas: Se insertan casas en posiciones que sean posibles, las casas como minimo tienen una medida de 2 metros al cuadrado y pueden llegar a una superficie de 10 metros al cuadrado.


d) Objetos y/o Plantillas WRL utilizados.
Una vez está completa la matriz hay que escribir el fichero de texto vrml, para eso hay una función en el programa llamada escribeFichero, está función lee los distintos valores de la matriz mientras la recorre y dependiendo del valor encontrado inserta un objeto xml que esta guardado en la carpeta adjunta de figuras:

Bloque: Es uno de los bloques básicos que despues formarán los edificios, se insertarán tantos bloques como sean necesarios para que un edificio cubra su superficie y su altura.
Calle: Es un trozo de carretera de la ciudad
Calle2: Es un cruce entre carreteras de la ciudad
ejes: Usados como guia al hacer la ciudad
iglesia: Iglesia diseñada en vrml para insertar en la ciudad
tierra: Es un trozo de tierra de la ciudad.
Tree: Es un arbol usado para decorar la ciudad

En la carpeta de figuras también hay una carpeta con las texturas usadas en las mismas.


e) Limitaciones.
Las calles no pueden tomar giros actualmente.
Los edificios aun no tienen insertadas ni puertas ni ventanas.
Se pueden poner mas tipos de edificios y decoraciones.


g) Anexo

Para probar el generador solo con las calles es tan facil como comentar las funciones de construirIglesia y construirCasas las cuales se usan en la función de construirCiudad.

