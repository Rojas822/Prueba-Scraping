import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
    private static Connection conn = null;

    public Conexion () {
        establecerConexion();
    }

    private void establecerConexion() {
        String url = "jdbc:mysql://localhost:3306/prueba";
        String usuario = "root";
        String contraseña = "8228";

        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, usuario, contraseña);
                System.out.println("Conexión exitosa");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void insertarProducto(String nombre, String precio, String codigo) {
        try {
            String sql = "INSERT INTO productos (nombre, precio, codigo) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, precio);
            stmt.setString(3, codigo);

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Producto insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el producto.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }


}
