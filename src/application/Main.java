package application;
	
import java.io.IOException;

import accesoDatos.AccesoHibernate;
import accesoDatos.AccesoJSONRemoto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import vista.InstalacionController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    private Stage stagePrincipal;
    private AnchorPane rootPane;
    

    @Override
    public void start(Stage stagePrincipal) throws Exception {
        this.stagePrincipal = stagePrincipal;
        mostrarVentanaPrincipal();

    }
    public static void main(String[] args) {
        launch(args);
    }
    /*
     * cargamos la ventana principal
     */
    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            	loader.setLocation(Main.class.getResource("../vista/Instalacion.fxml"));
            rootPane=(AnchorPane) loader.load();
            Scene scene = new Scene(rootPane);
            stagePrincipal.setScene(scene);
            stagePrincipal.setResizable(false);
            InstalacionController controller = loader.getController();
            controller.setProgramaPrincipal(this);
            stagePrincipal.show();

        } catch (IOException e) {
            //tratar la excepci�n.
        	System.out.println (e.toString());
        }
   }
	

}
