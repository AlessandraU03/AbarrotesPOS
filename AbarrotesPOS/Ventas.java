import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ventas {
    private SelectorProductos productos;
    private int cantidadProducto;
    private float total;
    private float pago;
    private float cambio;
    private ArrayList<Producto> productosComprados;
    private ArrayList<Integer> cantidadesCompradas;

    public Ventas(SelectorProductos productos) {
        this.productos = productos;
        this.productosComprados = new ArrayList<>();
        this.cantidadesCompradas = new ArrayList<>();
    }

    public ArrayList<Integer> getCantidadesCompradas() {
        return cantidadesCompradas;
    }

    public SelectorProductos getProductos() {
        return productos;
    }

    public void setProductos(SelectorProductos productos) {
        this.productos = productos;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
    }

    public float getCambio() {
        return cambio;
    }

    public void setCambio(float cambio) {
        this.cambio = cambio;
    }

    public ArrayList<Producto> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(ArrayList<Producto> productosComprados) {
        this.productosComprados = productosComprados;
    }


    public void seleccionarProductos() throws InputMismatchException {
        boolean continuar = true;

        do {
            productos.imprimirListaProductos();;
            int num = obtenerNumero();
            Producto productoSeleccionado = productos.getListaProductos().get(num - 1);
            int cantidadComprada = obtenerCantidadProducto(productoSeleccionado);
            productosComprados.add(productoSeleccionado);
            cantidadesCompradas.add(cantidadComprada);
            continuar = agregarOtroProducto();
        } while (continuar);

        hacerCuentas();
    }

    private int obtenerNumero() {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        boolean numValido = false;

        while (!numValido) {
            try {
                System.out.println("Ingrese el número de producto para registrar su venta");
                num = scanner.nextInt();
                numValido = (num >= 1 && num <= productos.getListaProductos().size());
                if (!numValido) {
                    System.out.println("Número de producto inválido. Inténtelo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                scanner.nextLine();
            }
        }
        return num;
    }

    private int obtenerCantidadProducto(Producto producto) {
        int cantidadComprada = 0;
        boolean cantidadValida = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Unidades restantes: " + producto.getCantidad());
            System.out.println("Ingrese la cantidad de producto");
            try {
                cantidadComprada = scanner.nextInt();
                if (cantidadComprada >= 1 && cantidadComprada <= producto.getCantidad()) {
                    cantidadValida = true;
                } else {
                    System.out.println("Cantidad inválida, por favor ingrese una cantidad válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                scanner.nextLine();
            }
        } while (!cantidadValida);

        return cantidadComprada;
    }

    private boolean agregarOtroProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n¿Desea registrar la venta de otro producto? (s/n): ");
        String opc = scanner.nextLine();
        return opc.equalsIgnoreCase("s");
    }
    
    private void hacerCuentas() {
        total = calcularTotal();
        mostrarTotal();
        ingresarPago();
        calcularCambio();
        generarTicket();
        quitarProductos();
    }

    private float calcularTotal() {
        float total = 0;
        for (int i = 0; i < productosComprados.size(); i++) {
            Producto producto = productosComprados.get(i);
            int cantidad = cantidadesCompradas.get(i);
            total += producto.getPrecio() * cantidad;
        }
        return total;
    }

    private void mostrarTotal() {
        System.out.println("Total: " + getTotal());
    }

    private void ingresarPago() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("Ingrese la cantidad con que pagará el cliente");
                setPago(scanner.nextFloat());
                if (pago < getTotal()) {
                    System.out.println("Ingrese una cantidad igual o mayor al total");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número por favor");
                scanner.nextLine();
            }
        } while (pago < getTotal());
    }

    private void calcularCambio() {
        setCambio(pago - total);
        System.out.println("Cambio: " + getCambio());
    }

    public void generarTicket() {
        System.out.println("********** TICKET DE VENTA **********");
        System.out.println("Productos vendidos:");
        for (int i = 0; i < productosComprados.size(); i++) {
            Producto producto = productosComprados.get(i);
            Integer cantidad = cantidadesCompradas.get(i);
            System.out.println("Nombre: " + producto.getNombre() + ", Cantidad: " + cantidad + ", Precio unitario: $" + producto.getPrecio());
        }
        System.out.println("Total: $" + getTotal());
        System.out.println("Pago recibido: $" + getPago());
        System.out.println("Cambio: $" + getCambio());
    }

    public void quitarProductos(){
        for (int i = 0; i < productosComprados.size(); i++) {
            Producto producto = productosComprados.get(i);
            producto.setCantidad(producto.getCantidad() - cantidadesCompradas.get(i));
        }
    }

    
}
