import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductoComestible extends Producto {
    private String tipo;
    private float volumen;
    private String unidadMedida;

    ProductoComestible() {
    }

    public ProductoComestible(String marca, String nombre, String descripcion, float volumen, double precio, String tipo, String unidadMedida) {
        this.marca = marca;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.volumen = volumen;
        this.precio = precio;
        this.tipo = tipo;
        this.unidadMedida = unidadMedida;
    }

    public void imprimirDatos() {
        System.out.println(" ");
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Marca: " + this.getMarca());
        System.out.println("Descripción: " + this.getDescripcion());
        System.out.println("Precio: " + this.getPrecio());
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Volumen: " + this.volumen + " " + obtenerUnidadDeMedida());
        System.out.println("Cantidad: " + this.getCantidad());
    }

    private String obtenerUnidadDeMedida() {
        switch (this.unidadMedida) {
            case "ml":
                return "mililitros (ml)";
            case "l":
                return "litros (l)";
            case "g":
                return "gramos (g)";
            case "kg":
                return "kilogramos (kg)";
            default:
                return "unidad no especificada";
        }
    }

    public ProductoComestible agregarDatos() {
        @SuppressWarnings("resource")
        Scanner leerTeclado = new Scanner(System.in);
        ProductoComestible productoComestible = new ProductoComestible();
        boolean bucleFor = true;
        int cantidad = 0;
        List<String> nombresProductos = new ArrayList<>();

        for (int i = 0; i < 8 || bucleFor; i++) {
            String peticion = "";
            String dato = "";

            switch (i) {
                case 0:
                    peticion = "Ingrese el Nombre del producto: ";
                    break;
                case 1:
                    dato = leerTeclado.nextLine();
                    productoComestible.setNombre(dato);
                    if (nombresProductos.contains(dato)) {
                        System.out.println("¡El nombre del producto ya ha sido ingresado! Ingrese un nombre diferente.");
                        continue;
                    }
                    peticion = "Ingrese la Marca del producto: ";
                    break;
                case 2:
                    dato = leerTeclado.nextLine();
                    productoComestible.setMarca(dato);
                    peticion = "Ingrese la descripción del producto: ";
                    break;
                case 3:
                    dato = leerTeclado.nextLine();
                    productoComestible.setDescripcion(dato);
                    peticion = "Ingrese el Precio del producto: ";
                    break;
                case 4:
                    double precio;
                    do {
                        System.out.print(peticion);
                        precio = leerTeclado.nextDouble();
                        leerTeclado.nextLine();

                        if (precio <= 0) {
                            System.out.println("El precio debe ser mayor que 0. Por favor, ingrese un precio válido.");
                            System.out.print("Ingrese el Precio del producto nuevamente: ");
                        }
                    } while (precio <= 0);
                    productoComestible.setPrecio(precio);
                    peticion = "Ingrese el tipo de producto comestible (Lácteos, Botanas, Bebidas...): ";
                    break;
                case 5:
                    dato = leerTeclado.nextLine();
                    productoComestible.setTipo(dato);
                    System.out.println("Elija la unidad de medida para el volumen del producto:");
                    System.out.println("1. Mililitros (ml)");
                    System.out.println("2. Litros (l)");
                    System.out.println("3. Gramos (g)");
                    System.out.println("4. Kilogramos (kg)");
                    System.out.print("Ingrese su opción: ");
                    int opcionUnidad = leerTeclado.nextInt();
                    leerTeclado.nextLine(); 
                    String unidad = "";

                    switch (opcionUnidad) {
                        case 1:
                            unidad = "ml";
                            break;
                        case 2:
                            unidad = "l";
                            break;
                        case 3:
                            unidad = "g";
                            break;
                        case 4:
                            unidad = "kg";
                            break;
                        default:
                            System.out.println("Opción no válida. Utiliza alguna de las unidades establecidas");
                            break;
                    }

                    productoComestible.setUnidadMedida(unidad);
                    peticion = "Ingrese el volumen del producto (Por ejemplo: 600 " + unidad + "): ";
                    break;
                case 6:
                    float volumen;
                    do {
                        System.out.print(peticion);
                        volumen = leerTeclado.nextFloat();
                        leerTeclado.nextLine();

                        if (volumen <= 0) {
                            System.out.println("El volumen debe ser mayor que 0. Por favor, ingrese un volumen válido.");
                            System.out.print("Ingrese el Volumen del producto nuevamente: ");
                        }
                    } while (volumen <= 0);
                    productoComestible.setVolumen(volumen);
                    peticion = "Ingrese la cantidad de productos en inventario: ";
                    break;
                case 7:
                    do {
                        System.out.print(peticion);
                        cantidad = leerTeclado.nextInt();
                        leerTeclado.nextLine();

                        if (cantidad <= 0) {
                            System.out.println("La cantidad debe ser mayor que 0. Por favor, ingrese una cantidad válida.");
                            System.out.print("Ingrese la cantidad del producto nuevamente: ");
                        }
                    } while (cantidad <= 0);
                    bucleFor = false;
                    productoComestible.setCantidad(cantidad);
                    break;
            }

            System.out.println(" ");
            System.out.println("(___Escriba 'exit' para cancelar la operación___)");
            System.out.print(peticion);

            if (dato.equalsIgnoreCase("exit")) {
                System.out.println(" ");
                System.out.println("=== CANCEL ===");
                System.out.println("Se ha cancelado la operación");
                System.out.println(" ");

                return null;
            }
        }

        return productoComestible;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getVolumen() {
        return volumen;
    }

    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
