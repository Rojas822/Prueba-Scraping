import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            driver.get("https://www.ktronix.com/computadores-tablet/computadoresportatiles/c/BI_104_KTRON");

            List<WebElement> productElements = driver.findElements(By.xpath("//div[@class='product__listing product__list']"));
            System.out.println("Cantidad de productos: " + productElements.size());

            // Establecer conexión a la base de datos MySQL
            String url = "jdbc:mysql://localhost:3306/prueba";
            String usuario = "root";
            String contraseña = "8228";
            conn = DriverManager.getConnection(url, usuario, contraseña);

            // Sentencia SQL para insertar datos en la tabla productos
            String sql = "INSERT INTO productos (nombre, precio, codigo) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            for (WebElement productElement : productElements) {
                String productName = productElement.findElement(By.xpath(".//h3[contains(@class, 'product__item__top__title')]")).getText();
                String productPrice = productElement.findElement(By.xpath(".//span[@class='price']")).getText();
                String productCode = productElement.getAttribute("data-id");

                // Establecer los valores de los parámetros de la declaración preparada
                stmt.setString(1, productName);
                stmt.setString(2, productPrice);
                stmt.setString(3, productCode);

                // Ejecutar la inserción
                stmt.executeUpdate();
            }

            System.out.println("Datos insertados correctamente en la tabla productos.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            driver.quit();
        }
    }
}
