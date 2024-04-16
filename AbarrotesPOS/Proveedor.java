import java.util.ArrayList;
import java.util.List;

public class Proveedor {
    private String nombre;
    private String[] horarios;
    private String contacto;
    private ArrayList<Producto> listaProductos;
    private Producto productoSurte;

    public Proveedor() {
        listaProductos = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setHorarios(String[] horarios) {
        this.horarios = horarios;
    }

    public String[] getHorarios() {
        return horarios;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getContacto() {
        return contacto;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void setProductoSurte(Producto productoSurte) {
        this.productoSurte = productoSurte;
    }

    public Producto getProductoSurte() {
        return productoSurte;
    }

    
}
