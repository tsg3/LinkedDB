import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class InterfazTipos {

    public Button ver_tipos;
    public Label tipos;
    public AnchorPane anchorPane;

    private void verTipos() throws IOException {
    	TypeList lTipo = Main.types;
    	Type tipo = lTipo.root;
    	StringBuilder msg = new StringBuilder();
    	while (tipo!=null){
   		    msg.append(tipo.name);
    	    msg.append("\n");
    	    tipo=tipo.getNext();
    	}
    	tipos.setText(msg.toString());
    }

    public void ver_tipos(ActionEvent actionEvent) throws IOException {
        verTipos();
    }

}