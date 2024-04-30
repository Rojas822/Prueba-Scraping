public class Producto{

    private String url;
    private String nombre
    private String precio;
    private String codigo

    public Producto(){

    }

    public Producto(String url, String nombreString precio, String codigo) {
        this.url = url;
        this.nombre = nombre
        this.precio = precio;
        this.codigo = codigo
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre
    }

    public void setNombre(String nombre{
        this.nombre = nombre
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return code;
    }

    public void setCodigo(String code) {
        this.code = codigo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "url='" + url + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
