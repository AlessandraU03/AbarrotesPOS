import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SelectorProductos {
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    SelectorProductos() {
    }

    SelectorProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void addListaProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public ArrayList<Producto> getListaPedidos() {
        return listaProductos;
    }

    public void mostrarMenuProductos() {
        @SuppressWarnings("resource")
        Scanner leerTeclado = new Scanner(System.in);
        int respuesta = 0;
        boolean bucle = true;

        do {
            bucle = true;
            System.out.println("\n=== MENU DE PRODUCTOS ===");
            System.out.println("1.- Añadir Producto");
            System.out.println("2.- Eliminar Producto");
            System.out.println("3.- Modificar Producto");
            System.out.println("4.- Buscar Producto");
            System.out.println("5.- Imprimir Lista de Productos");
            System.out.println("6.- SALIR");

            System.out.print("Ingrese el número de la opción que desea realizar: ");
            try {
                respuesta = leerTeclado.nextInt();
                switch (respuesta) {
                    case 1:
                        agregarProducto();
                        break;
                    case 2:
                        eliminarProducto();
                        break;
                    case 3:
                        modificarProducto();
                        break;
                    case 4:
                        buscarProducto();
                        break;
                    case 5:
                        imprimirListaProductos();
                        break;
                    case 6:
                        bucle = false;
                        break;
                    default:
                        System.out.println(" ");
                        System.out.println("=== ERROR ===");
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                        System.out.println(" ");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(" ");
                System.out.println("=== ERROR ===");
                System.out.println("Por favor, ingrese un número entero válido.");
                System.out.println(" ");
                leerTeclado.nextLine(); // Limpiar el buffer del scanner
            }

        } while (bucle);

    }
    
    public void agregarProducto() {
        Scanner scanner = new Scanner(System.in);
        Producto nuevoProducto = null;
    
        System.out.println("\nSeleccione el tipo de producto:");
        System.out.println("1. Producto Comestible");
        System.out.println("2. Producto del Hogar");
        System.out.println("3. Producto de Limpieza");
        System.out.print("Ingrese el número correspondiente al tipo de producto: ");
        int tipoProducto = scanner.nextInt();
        scanner.nextLine();
    
        switch (tipoProducto) {
            case 1:
                ProductoComestible productoComestible = new ProductoComestible();
                nuevoProducto = productoComestible.agregarDatos();
                break;
            case 2:
                ProductoHogar productoHogar = new ProductoHogar();
                nuevoProducto = productoHogar.agregarDatos();
                break;
            case 3:
                ProductoLimpieza productoLimpieza = new ProductoLimpieza();
                nuevoProducto = productoLimpieza.agregarDatos();
                break;
            default:
                System.out.println("Tipo de producto no válido. Ingrese una opción válida");
                break;
        }
    
        if (nuevoProducto != null) {
            boolean productoRepetido = false;
            for (Producto producto : listaProductos) {
                if (producto.getNombre().equalsIgnoreCase(nuevoProducto.getNombre())) {
                    productoRepetido = true;
                    break;
                }
            }
    
            if (!productoRepetido) {
                listaProductos.add(nuevoProducto);
                System.out.println("Producto agregado con éxito.");
            } else {
                System.out.println("\n=== ERROR ===\nEl producto ya existe en la lista.\n");
            }
        }
    
        System.out.print("\n¿Desea agregar otro producto? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            agregarProducto();
        }
    }
    
    
    public void eliminarProducto() {
        Producto productoEliminar;
        Scanner leerTeclado = new Scanner(System.in);
        String respuesta;

        if (listaProductos == null || listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");

        }else{

        do {
            String nombreEliminar;

            System.out.println(" ");
            System.out.println("(___Escriba 'exit' para cancelar la operación___)");
            System.out.print("Ingrese el nombre del producto a eliminar: ");
            nombreEliminar = leerTeclado.nextLine();

            if (nombreEliminar.equals("exit")) {
                System.out.println(" ");
                System.out.println("=== CANCEL ===");
                System.out.println("Se ha cancelado la operación");
                System.out.println(" ");
                leerTeclado.close();
                return;
            }

            productoEliminar = null;
            for (Producto producto : listaProductos) {
                if (producto.getNombre().equals(nombreEliminar)) {
                    productoEliminar = producto;
                    break;
                }
            }

            if (productoEliminar != null) {
                listaProductos.remove(productoEliminar);
                System.out.println(" ");
                System.out.println("=== ÉXITO ===");
                System.out.println("Producto eliminado");
                System.out.println(" ");
            } else {
                System.out.println(" ");
                System.out.println("=== ERROR ===");
                System.out.println("Producto no encontrado");
                System.out.println(" ");
            }

            do {
                System.out.print("¿Desea eliminar otro producto? (s/n): ");
                respuesta = leerTeclado.nextLine();
            } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));
    
        } while (respuesta.equalsIgnoreCase("s"));
    }
    }

    public void modificarProducto() {
        Scanner leerTeclado = new Scanner(System.in);
        Producto productoModificar = null;
        String nombreModificar = "";
        String respuesta = "";

        if (listaProductos == null || listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        }else{

        do {
            System.out.println(" ");
            System.out.println("(___Escriba 'exit' para cancelar la operación___)");
            System.out.print("Ingrese el nombre del producto a modificar: ");
            nombreModificar = leerTeclado.nextLine();

            if (nombreModificar.equals("exit")) {
                System.out.println(" ");
                System.out.println("=== CANCEL ===");
                System.out.println("Se ha cancelado la operación");
                System.out.println(" ");
                leerTeclado.close();
            }

            for (Producto producto : listaProductos) {
                if (producto.getNombre().equalsIgnoreCase(nombreModificar)) {
                    productoModificar = producto;
                }
            }

    
            if (productoModificar != null) {
                System.out.println(" ");
                System.out.println("=== ÉXITO ===");
                System.out.println("Producto encontrado");
                System.out.println(" ");
    
                System.out.print("¿Desea modificar todos los atributos del producto? (s/n): ");
                respuesta = leerTeclado.nextLine();
    
                if (respuesta.equalsIgnoreCase("s")) {
                    // Modificar todos los atributos
                    System.out.print("Ingrese la nueva marca: ");
                    String nuevaMarca = leerTeclado.nextLine();
                    productoModificar.setMarca(nuevaMarca);
    
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = leerTeclado.nextLine();
                    productoModificar.setNombre(nuevoNombre);
    
                    System.out.print("Ingrese la nueva descripción: ");
                    String nuevaDescripcion = leerTeclado.nextLine();
                    productoModificar.setDescripcion(nuevaDescripcion);
    
                    System.out.print("Ingrese el nuevo precio: ");
                    double nuevoPrecio = leerTeclado.nextDouble();
                    if (nuevoPrecio < 0) {
                        System.out.println("El precio no puede ser negativa. Por favor, ingrese un valor válido.");
                        continue;
                    }
                    productoModificar.setPrecio(nuevoPrecio);
                    
                    System.out.print("Ingrese la nueva cantidad de productos: ");
                    int nuevaCantidad = leerTeclado.nextInt();
                    leerTeclado.nextLine();
                    if (nuevaCantidad < 0) {
                        System.out.println("La cantidad no puede ser negativa. Por favor, ingrese un valor válido.");
                        continue;
                    }
                    productoModificar.setCantidad(nuevaCantidad);
    
                    // Modificar atributos específicos según el tipo de producto
                    if (productoModificar instanceof ProductoComestible) {
                        System.out.print("Ingrese el nuevo tipo de producto comestible: ");
                        String nuevoTipo = leerTeclado.nextLine();
                        ((ProductoComestible) productoModificar).setTipo(nuevoTipo);
                    } else if (productoModificar instanceof ProductoHogar) {
                        System.out.print("Ingrese la nueva categoría del producto de hogar: ");
                        String nuevaCategoria = leerTeclado.nextLine();
                        ((ProductoHogar) productoModificar).setCategoria(nuevaCategoria);
                    } else if (productoModificar instanceof ProductoLimpieza) {
                        System.out.print("Ingrese el nuevo tipo del producto de limpieza: ");
                        String nuevoTipo = leerTeclado.nextLine();
                        ((ProductoLimpieza) productoModificar).setTipo(nuevoTipo);
                    }
                } else if (respuesta.equalsIgnoreCase("n")) {
                    // Bucle para modificar cada atributo individualmente
                    do {
                        System.out.println("Seleccione qué atributo desea modificar:");
                        System.out.println("1. Marca");
                        System.out.println("2. Nombre");
                        System.out.println("3. Descripción");
                        System.out.println("4. Precio");
                        System.out.println("5. Cantidad");
                        System.out.println("6. Tipo (solo para productos Comestibles)");
                        System.out.println("7. Volumen (solo para productos Comestibles)");
                        System.out.println("8. Categoría (solo para productos de Hogar)");
                        System.out.println("9. Tipo (solo para productos de Limpieza)");
    
                        System.out.print("Ingrese el número correspondiente al atributo que desea modificar: ");
                        int opcion = leerTeclado.nextInt();
                        leerTeclado.nextLine(); // Limpiar el buffer
    
                        switch (opcion) {
                            case 1:
                                System.out.print("Ingrese la nueva marca: ");
                                String nuevaMarca = leerTeclado.nextLine();
                                productoModificar.setMarca(nuevaMarca);
                                break;
                            case 2:
                                System.out.print("Ingrese el nuevo nombre: ");
                                String nuevoNombre = leerTeclado.nextLine();
                                productoModificar.setNombre(nuevoNombre);
                                break;
                            case 3:
                                System.out.print("Ingrese la nueva descripción: ");
                                String nuevaDescripcion = leerTeclado.nextLine();
                                productoModificar.setDescripcion(nuevaDescripcion);
                                break;
                            case 4:
                                System.out.print("Ingrese el nuevo precio: ");
                                double nuevoPrecio = leerTeclado.nextDouble();
                                if (nuevoPrecio < 0) {
                                    System.out.println("El precio no puede ser negativa. Por favor, ingrese un valor válido.");
                                    continue;
                                }
                                productoModificar.setPrecio(nuevoPrecio);
                                break;
                            case 5:
                                System.out.print("Ingrese la nueva cantidad de productos: ");
                                int nuevaCantidad = leerTeclado.nextInt();
                                leerTeclado.nextLine();
                                if (nuevaCantidad < 0) {
                                    System.out.println("La cantidad no puede ser negativa. Por favor, ingrese un valor válido.");
                                    continue;
                                }
                                productoModificar.setCantidad(nuevaCantidad);
                                break;
                            case 6:
                                if (productoModificar instanceof ProductoComestible) {
                                    System.out.print("Ingrese el nuevo tipo del producto comestible: ");
                                    String nuevoTipo = leerTeclado.nextLine();
                                    ((ProductoComestible) productoModificar).setTipo(nuevoTipo);
                                } else {
                                    System.out.println("Este producto no tiene un tipo de producto comestible.");
                                }
                                break;
                            case 7:
                                if (productoModificar instanceof ProductoComestible) {
                                    System.out.print("Ingrese el nuevo volumen del producto comestible: ");
                                    float nuevoVolumen = leerTeclado.nextFloat();
                                    ((ProductoComestible) productoModificar).setVolumen(nuevoVolumen);
                                } else {
                                    System.out.println("Este producto no tiene un volumen del producto comestible.");
                                }
                                break;
                            case 8:
                                if (productoModificar instanceof ProductoHogar) {
                                    System.out.print("Ingrese la nueva categoría del producto de hogar: ");
                                    String nuevaCategoria = leerTeclado.nextLine();
                                    ((ProductoHogar) productoModificar).setCategoria(nuevaCategoria);
                                } else {
                                    System.out.println("Este producto no tiene categoría.");
                                }
                                break;
                            case 9:
                                if (productoModificar instanceof ProductoLimpieza) {
                                    System.out.print("Ingrese el nuevo tipo del producto de limpieza: ");
                                    String nuevoTipo = leerTeclado.nextLine();
                                    ((ProductoLimpieza) productoModificar).setTipo(nuevoTipo);
                                } else {
                                    System.out.println("Este producto no tiene categoría.");
                                }
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
    
                        System.out.print("¿Desea modificar otro atributo? (s/n): ");
                        respuesta = leerTeclado.nextLine();
                    } while (respuesta.equalsIgnoreCase("s"));
                } else {
                    System.out.println(" ");
                    System.out.println("=== ERROR ===");
                    System.out.println("Respuesta no válida.");
                    System.out.println(" ");
                }
    
                do {
                    System.out.print("¿Desea modificar otro producto? (s/n): ");
                    respuesta = leerTeclado.nextLine();
                } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));
    
            } else {
                System.out.println(" ");
                System.out.println("=== ERROR ===");
                System.out.println("Producto no encontrado");
                System.out.println(" ");
    
            }
    
        } while (respuesta.equalsIgnoreCase("s"));
    }
    }
    
    public void buscarProducto() {
        Scanner leerTeclado = new Scanner(System.in);
        String respuesta = "";
    
        
        if (listaProductos == null || listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            
        } else{
    
        do {
            System.out.println(" ");
            System.out.print("Ingrese el nombre del producto a buscar: ");
            final String nombreBuscar = leerTeclado.nextLine(); // Variable final auxiliar
    
            Producto productoBuscar = listaProductos.stream()
                    .filter(producto -> producto.getNombre().equalsIgnoreCase(nombreBuscar))
                    .findAny()
                    .orElse(null);
    
            if (productoBuscar != null) {
                System.out.println(" ");
                System.out.println("==========");
                productoBuscar.imprimirDatos();
                System.out.println(" ");
                System.out.println("==========");
            } else {
                System.out.println(" ");
                System.out.println("=== ERROR ===");
                System.out.println("Producto no encontrado");
                System.out.println(" ");
            }
    
            do {
                System.out.print("¿Desea buscar otro producto? (s/n): ");
                respuesta = leerTeclado.nextLine();
            } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));
    
        } while (respuesta.equalsIgnoreCase("s"));
    }
    }
    
    

    public void imprimirListaProductos() {
        System.out.println(" ");
        System.out.println("=== LISTA DE PRODUCTOS ===");
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos en la lista.");
        } else {
            for (int i = 0; i < listaProductos.size(); i++) {
                Producto producto = listaProductos.get(i);
                System.out.println(" ");
                System.out.println("Producto " + (i + 1) + ":");
                producto.imprimirDatos();
            }
        }
    }
    
    
    // ===============================================================================================================================================
    // ===============================================================================================================================================

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    // ................................................................................................................................................
    // ................................................................................................................................................

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
}
