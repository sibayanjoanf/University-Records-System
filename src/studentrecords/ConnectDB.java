/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentrecords;
import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;
/**
 *
 * @author Joan
 */
public class ConnectDB {
    public static Connection Connect() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            prop.load(fis);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(
                prop.getProperty("db.url"),
                prop.getProperty("db.user"),
                prop.getProperty("db.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
