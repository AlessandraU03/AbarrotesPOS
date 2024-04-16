import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionVentas {
    private ArrayList<Ventas> listaVentas;

    public GestionVentas() {
        this.listaVentas = new ArrayList<>();
    }

    public void menuVentas(SelectorProductos productos) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("\n---- Menu de Gestión de Ventas ----");
                System.out.println("1. Registrar venta");
                System.out.println("2. Mostrar ventas");
                System.out.println("3. Eliminar venta");
                System.out.println("4. Buscar venta");
                System.out.println("5. Salir");
                System.out.print("Ingrese su opción: ");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        Ventas nuevaVenta = new Ventas(productos);
                        nuevaVenta.seleccionarProductos();
                        agregarVenta(nuevaVenta);
                        break;
                    case 2:
                        mostrarVentas();
                        break;
                    case 3:
                        if (!listaVentas.isEmpty()) {
                            System.out.print("Ingrese el número de venta que desea eliminar: ");
                            int indiceEliminar = scanner.nextInt();
                            eliminarVenta(indiceEliminar - 1);
                        } else {
                            System.out.println("No hay ventas registradas para eliminar.");
                        }
                        break;
                    case 4:
                        System.out.print("Ingrese el número de venta que desea buscar: ");
                        int numeroVentaBuscar = scanner.nextInt();
                        buscarVenta(numeroVentaBuscar);
                        break;
                    case 5:
                        salir = true;
                        System.out.println("Saliendo del programa de gestión de ventas.");
                        break;
                    default:
                        System.out.println("Opción inválida. Inténtelo de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); 
            }
        }
    }

    public void agregarVenta(Ventas venta) {
        listaVentas.add(venta);
    }

    public void mostrarVentas() {
        if (listaVentas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            System.out.println("Ventas registradas:");
            for (int i = 0; i < listaVentas.size(); i++) {
                System.out.println("Venta #" + (i + 1));
                listaVentas.get(i).generarTicket();
            }
        }
    }

    public void eliminarVenta(int indice) {
        if (indice >= 0 && indice < listaVentas.size()) {
            listaVentas.remove(indice);
            System.out.println("Venta eliminada exitosamente.");
        } else {
            System.out.println("Índice de venta inválido.");
        }
    }


    public void buscarVenta(int numeroVenta) {
        boolean ventaEncontrada = false;

        for (int i = 0; i < listaVentas.size(); i++) {
            Ventas venta = listaVentas.get(i);
            if (venta != null && (i + 1) == numeroVenta) {
                System.out.println("Venta encontrada:");
                venta.generarTicket();
                ventaEncontrada = true;
                break;
            }
        }

        if (!ventaEncontrada) {
            System.out.println("No se encontró ninguna venta con el número especificado.");
        }
    }
}
