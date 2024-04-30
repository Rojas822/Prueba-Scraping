import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Scraping {
    public static void main(String[] args) {
        Conection gestorProductos = new Conection(); // Crear instancia de GestorProductos
        WebDriver driver = null;

        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new EdgeDriver();
            driver.get("https://www.ktronix.com/computadores-tablet/computadoresportatiles/c/BI_104_KTRON");
            Thread.sleep(150000);
            List<WebElement> productElements = driver.findElements(By.cssSelector("h3.product_itemtop_title"));
            String baseUrl = getBaseUrl(driver.getCurrentUrl());
            int cont = 0;

            for (WebElement productElement : productElements) {
                driver = new EdgeDriver();
                driver.get(baseUrl + productElement.getAttribute("data-url"));
                Thread.sleep(150000);

                Product product = new Product();
                product.setUrl(baseUrl + productElement.getAttribute("data-url"));
                product.setName(driver.findElement(By.cssSelector("div.new-container_header-responsive>div.new-containerheader_title>h1")).getText());
                product.setPrecio(driver.findElement(By.cssSelector("div.price-block.d-inline.no-padding>span.js-original_price")).getText());
                product.setCode(driver.findElement(By.cssSelector("div.new-container__header__code>span.js-ean-pdp")).getText());


                gestorProductos.insertarProducto(product.getName(), product.getPrecio(), product.getCode());

                cont++;
                System.out.println(product);

                if (cont == 5) {
                    break;
                }
                driver.quit();
            }

            System.out.println("Número de productos: " + productElements.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
            gestorProductos.cerrarConexion();
        }
    }

    private static String getBaseUrl(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return "https://" + domain;
    }
}
