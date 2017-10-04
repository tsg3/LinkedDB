import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Interfaz {

    public MenuItem crear_ventana;
    public MenuItem ver_tipos;
    public AnchorPane anchorPane;

    public Label info;
    public Button crear_JSONStore;
    public Button crear_JSONdoc;
    public Button read;
    public Button borrar_item;
    public Button escribir_en_disco;
    public TreeView<String> gran_arbol;
    private TreeItem<String> root;
    private TreeItem<String> itemChild;


    private void segundaVentana() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Type.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        crear_ventana.setVisible(true);
    }

    public void segunda_ventana(ActionEvent actionEvent) throws IOException {
        segundaVentana();
    }

    private void tipos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Types.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        ver_tipos.setVisible(true);
    }

    public void tipos(ActionEvent actionEvent) throws IOException {
        tipos();
    }


    public void crear_JSONStore(ActionEvent actionEvent) throws IOException {
    	if (gran_arbol.getRoot() == null){
    		root = new TreeItem<>("JSONStores");
        	root.setExpanded(true);
        	gran_arbol.setRoot(root);
    	}
        createTree();
    }

    private void createTree(String... rootItems) throws IOException {
            String respuesta = JOptionPane.showInputDialog("¿Nombre del JSONStore?");
            itemChild = new TreeItem<>(respuesta);
            itemChild.setExpanded(true);
            root.getChildren().add(itemChild);
            Main.stores.add(respuesta);
    }

    public void crear_JSONdoc(ActionEvent actionEvent) throws IOException, ParseException {

    	if (gran_arbol.getSelectionModel().getSelectedItem().getParent()==gran_arbol.getRoot()){

    	JTextField nombre = new JTextField(5);
        JTextField tipo = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Nombre:"));
        myPanel.add(nombre);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Tipo:"));
        myPanel.add(tipo);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Inserte el nombre y el tipo de documento:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
        	if (Main.types.exists((String) tipo.getText())==true){
        		int n = Main.types.find((String) tipo.getText()).getAtributosRString().length;
        		JTextField[] array = new JTextField[n];
        		int i = 0;
        		while (i<n){
        			array[i]=new JTextField(5);
        			i++;
        		}
        		JPanel atributos = new JPanel();
        		int j = 0;
        		while (j<n){
        			if (i==n-1){
        				atributos.add(new JLabel(Main.types.find((String) tipo.getText()).getAtributosRString()[j]+":"));
        				atributos.add(array[j]);
        				break;
        			}
        			atributos.add(new JLabel(Main.types.find((String) tipo.getText()).getAtributosRString()[j]+":"));
        			atributos.add(array[j]);
        			atributos.add(Box.createHorizontalStrut(15));
        			j++;
        		}
        		int result2 = JOptionPane.showConfirmDialog(null, atributos, "Inserte los atributos requeridos:", JOptionPane.OK_CANCEL_OPTION);
        		if (result2 == JOptionPane.OK_OPTION) {
        			Object[] valores=new Object[n];
        			int k = 0;
        			while (k<n){
        				valores[k]=(String) array[k].getText();
        				k++;
        			}
        			Main.stores.find(gran_arbol.getSelectionModel().getSelectedItem().getValue()).add(Main.types,(String) tipo.getText(),(String) nombre.getText(), valores);

        			TreeItem<String> itemChild_2 = new TreeItem<>((String) nombre.getText());
    				itemChild_2.setExpanded(true);
    				TreeItem<String> store = gran_arbol.getSelectionModel().getSelectedItem();
    				store.getChildren().add(itemChild_2);
        		}
        	}
        }
    	}

    }

    public void clicked(MouseEvent mouseEvent) {
        TreeItem<String> item = gran_arbol.getSelectionModel().getSelectedItem();
        crear_JSONdoc.setVisible(true);
        borrar_item.setVisible(true);
        if (item.getParent().getParent()==gran_arbol.getRoot()){
        	DocumentObject current = Main.stores.find(item.getParent().getValue()).find(item.getValue()).root;
        	StringBuilder msg = new StringBuilder();
        	while (current != null){
       		    msg.append(current.getAtributo() + " = " + current.getValor());
        	    msg.append("\n");
        	    current=current.getNext();
        	}
        	info.setText(msg.toString());
        }
        else if (item==gran_arbol.getRoot()){
        	return;
        }
        else {
        	StringBuilder msg = new StringBuilder();
        	info.setText(msg.toString());
        }
    }

    private void commit() throws IOException {
    	Main.stores.write();
        escribir_en_disco.setVisible(true);
    }

    public void commit(ActionEvent actionEvent) throws IOException {
    	commit();
    }

    private void leer() throws IOException, ParseException {
    	File d = new File(Main.stores.carpeta);
		String[] listaArchivos=d.list();
		for(int i=0; i<listaArchivos.length; i++){
			if (gran_arbol.getRoot() == null){
	    		root = new TreeItem<>("JSONStores");
	        	root.setExpanded(true);
	        	gran_arbol.setRoot(root);
	    	}
			itemChild = new TreeItem<>(listaArchivos[i]);
			itemChild.setExpanded(true);
			root.getChildren().add(itemChild);
			Main.stores.add(listaArchivos[i]);
		Store current = Main.stores.find(listaArchivos[i]);
    	while (current != null) {
    		File directorio = new File(current.getCarpeta());
    		if (directorio.exists()){
    			String[] listaJSONS=directorio.list();
    			for(int j=0; j<listaJSONS.length; j++){
    				FileReader reader = new FileReader(current.getCarpeta()+"\\"+listaJSONS[j]);
    				JSONParser jsonParser = new JSONParser();
    				JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
    				String[] atributos = Main.types.find((String) jsonObject.get("Tipo")).getAtributos();
    				String[] requeridos = Main.types.find((String) jsonObject.get("Tipo")).getAtributosRString();
    				int n = atributos.length;
    				int n2 = requeridos.length;
    				Object[] valores = new Object[n2];
    				Object[] valoresT = new Object[n-n2];
    				int k = 0;
    				while (k<n){
    					if (k<n2)
    						valores[k] = jsonObject.get(requeridos[k]);
    					else if (jsonObject.get(atributos[k])!=null)
    						valoresT[k] = jsonObject.get(atributos[k]);
    					k++;
    				}
    				if (jsonObject.get("Requerido")==null)
    					current.add(Main.types,(String)jsonObject.get("Tipo"),listaJSONS[j].substring(0,listaJSONS[j].length()-5),valores);
    				if (valoresT[0]!=null){
    					for (int l=n2; l<n; l++)
    						current.start.addObject(Main.types,atributos[l],valoresT[l-2],"No Requerido");}
    				TreeItem<String> itemChild_2 = new TreeItem<>(listaJSONS[j].substring(0,listaJSONS[j].length()-5));
    				itemChild_2.setExpanded(true);
    				itemChild.getChildren().add(itemChild_2);
            	}
    		}
            current = current.getNext();
        }}
        read.setVisible(true);
    }

    public void leer(ActionEvent actionEvent) throws IOException, ParseException {
    	leer();
    }

    private void borrar() throws IOException {
    	if (gran_arbol.getSelectionModel().getSelectedItem().getParent()==gran_arbol.getRoot()){
    		TreeItem<String> store = gran_arbol.getSelectionModel().getSelectedItem();
    		gran_arbol.getRoot().getChildren().remove(store);
    	}
    	if (gran_arbol.getSelectionModel().getSelectedItem().getParent().getParent()==gran_arbol.getRoot()){
    		TreeItem<String> json = gran_arbol.getSelectionModel().getSelectedItem();
    		json.getParent().getChildren().remove(json);
    	}
    }

    public void borrar(ActionEvent actionEvent) throws IOException {
    	borrar();
    }

}

