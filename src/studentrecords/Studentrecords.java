/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentrecords;

/**
 *
 * @author Joan
 */
public class Studentrecords {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConnectDB con = new ConnectDB();
        con.Connect();
        MainScreen ms = new MainScreen();
        ms.setVisible(true);
    }
    
}
