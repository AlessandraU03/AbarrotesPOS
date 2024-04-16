import java.util.Scanner;

public class ProductoLimpieza extends Producto {
    private String tipo;

    ProductoLimpieza() {}

    public ProductoLimpieza(String marca, String nombre, String descripcion, double precio, String tipo) {
        this.marca = marca;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void imprimirDatos() {
        System.out.println(" ");
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Marca: " + this.getMarca());
        System.out.println("Descripcion: " + this.getDescripcion());
        System.out.println("Precio: " + this.getPrecio());
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Cantidad: " + this.cantidad);
    }

    public ProductoLimpieza agregarDatos() {
        @SuppressWarnings("resource")
        Scanner leerTeclado = new Scanner(System.in);
        ProductoLimpieza productoLimpieza = new ProductoLimpieza();
        boolean bucleFor = true;
    
        for (int i = 0; i < 6 || bucleFor; i++) {
            String peticion = "";
            String dato = "";
    
            switch (i) {
                case 0:
                    peticion = "Ingrese el Nombre del producto: ";
                    break;
                case 1:
                    dato = leerTeclado.nextLine();
                    productoLimpieza.setNombre(dato);
                    peticion = "Ingrese la Marca del producto: ";
                    break;
                case 2:
                    dato = leerTeclado.nextLine();
                    productoLimpieza.setMarca(dato);
                    peticion = "Ingrese la descripción del producto: ";
                    break;
                case 3:
                    dato = leerTeclado.nextLine();
                    productoLimpieza.setDescripcion(dato);
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
                    productoLimpieza.setPrecio(precio);
                    peticion = "Ingrese el tipo de producto de Limpieza (Desinfectantes, Accesorios, herramientas de limpieza...):";
                    break;
                case 5:
                    dato = leerTeclado.nextLine();
                    productoLimpieza.setTipo(dato);
                    break;
                case 6: 
                do {
                    System.out.print("Ingrese la cantidad de productos en inventario :");
                    cantidad = leerTeclado.nextInt();
                    leerTeclado.nextLine();
                    
                    if (cantidad <= 0) {
                        System.out.println("La cantidad debe ser mayor que 0. Por favor, ingrese un precio válido.");
                        System.out.print("Ingrese la cantidad del producto nuevamente: ");
                    }
                } while (cantidad <= 0);
                bucleFor = false;
                productoLimpieza.setCantidad(cantidad);
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
    
        return productoLimpieza;
    }
    
}
