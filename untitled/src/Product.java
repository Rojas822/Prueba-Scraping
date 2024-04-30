public class Product {

    private String url;
    private String name;
    private String precio;
    private String code;

    public Product(){

    }

    public Product(String url, String name, String precio, String code) {
        this.url = url;
        this.name = name;
        this.precio = precio;
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", precio=" + precio +
                ", code='" + code + '\'' +
                '}';
    }
}
