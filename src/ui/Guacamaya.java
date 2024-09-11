package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    /**
     * Descripción: Este método está encargado de, por medio de un ciclo, preguntarle al usuario por consola el precio y unidades vendidas de cada artículo vendido 
     * durante el día. A continuación, almacena los datos en un dos arreglos, precios y unidades, respectivamente.
     * pre: Clase Scanner debe ser declarada e inicializada.
     * pre: Los arreglos "precios" y "unidades" deben haber sido inicializados previamente, y deben estar vacíos.
     * pos: Los arreglos "precios" y "unidades" salen llenos, cada uno con ejemplares del tipo de dato correspondiente (double e int, respectivamente.)
    */

    public static void solicitarDatos(){
        for(int i=0; i<unidades.length; i++){
            System.out.println("Digite el precio del artculo: "+(i+1));
            precios[i] = reader.nextDouble();

            System.out.println("Digite la cantidad de unidades vendidas del articulo: " +(i+1));
            unidades[i] = reader.nextInt();
        }
       

     
    }

    /**
     *Descripción: El método, dado un arreglo LLENO de unidades vendidas, itera sobre el array y suma las cantidades de 
     cada posición. Esta sumatoria se aloja en una variable int unidadesTotales.
     pre: El arreglo de unidades debe ser previamente inicializado, y debe contener datos en todas sus posiciones.
     @return int unidadesTotales: Variable donde se aloja la sumatoria de artículos del arreglo.
    */
    public static int calcularTotalUnidadesVendidas(){
        int unidadesTotales = 0;
        for(int i=0; i<unidades.length; i++){
            unidadesTotales += unidades[i];
        }
        return unidadesTotales;

    }

    /**
     * Descripción: Este método, dado un arreglo LLENO de precios de artículos, itera sobre el array y suma las cantidades de 
     * cada posición, cantidad que guarda en la variable double precioPromedio. A continuación, divide precioPromedio (la sumatoria)
     * entre el tamaño del arreglo.
     * pre: Arreglo precios debe haber sido inicializado previamente, y debe estar lleno de datos double en todas sus posiciones. 
     * @return double precioPromedio: Precio promedio de los artículos insertados.
     */
    public static double calcularPrecioPromedio(){
        double precioPromedio = 0;
        for(int i=0; i<precios.length; i++){
            precioPromedio += precios[i];
        }
        precioPromedio /= precios.length;
        return precioPromedio;

    }

    /**
     * Descripción: El método calcula el dinero recuadado durante el día. Dados los arreglos LLENOS de unidades y precios, 
     * itera sobre ellos, multiplicando las unidades vendidas por el precio del artículo en cada posición; la variable double 
     * dineroRecaudado suma el resultado de cada iteración hasta el final del tamaño del arreglo.
     * pre: Los arreglos de "unidades" y "precios" deben haber sido inicializados, y estar llenos con datos del tipo correspondiente.
     * @return double dineroRecaudado: Sumatoria total de las ventas del día.
     */
    public static double calcularVentasTotales(){
        double dineroRecaudado = 0;
        for(int i=0; i<unidades.length; i++){
            dineroRecaudado += (unidades[i] * precios[i]);
        }
        return dineroRecaudado;

    }

    /**
     * Descripción: Este método cuenta el número de referencias que superan un límite de dinero recaudado insertado por el usuario. Itera sobre los arreglos
     * precio y unidades, multiplicando el contenido de cada posición en los arreglos; si la venta del artículo es mayor al límite, se suma 1 al número de referencias.
     * pre: Los arreglos de "unidades" y "precios" deben haber sido inicializados, y estar llenos con datos del tipo correspondiente.
     * @param double limite: Limite mínimo de dinero recaudado, usado para hacer la comparación, y definir si el valor pasa del límite o no.
     * @return int referenciasLimite: Número de referencias que superan el límite de dinero recaudado.
     */
    public static int consultarReferenciasSobreLimite(double limite){
        int referenciasLimite = 0;
        double auxiliar;
        for(int i=0; i<precios.length; i++){
            auxiliar = (unidades[i] * precios[i]);
            if(auxiliar > limite){
                referenciasLimite += 1;
            }
        }
        
        return referenciasLimite;

    }

}
