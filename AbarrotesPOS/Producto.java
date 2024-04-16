import java.util.ArrayList;

public abstract class Producto {
    protected String marca;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected int cantidad;
    protected ArrayList<Producto> listaProductos = new ArrayList<>();


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public abstract void imprimirDatos();

    public boolean agregarProducto(ArrayList<Producto> listaProductos) {
        return listaProductos.add(this);
    }

    public void addListaProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public ArrayList<Producto> getListaPedidos() {
        return listaProductos;
    }

}
