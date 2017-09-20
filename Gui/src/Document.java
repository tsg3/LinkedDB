

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Document {

	private DocumentObject root;
	private Document next = null;
	private Document prev = null;
	private File Directorio;
	private String carpeta;
	private String nombre;
	private String tipo;
	private JSONObject atributos = new JSONObject();

	@SuppressWarnings("unchecked")
	public void type(String name){
		Object lector = atributos.get("Tipo");
		if (lector==null)
			atributos.put("Tipo", name);
	}

	@SuppressWarnings("unchecked")
	public void addObject(TypeList types, String atributo, Object valor, String requerido) throws IOException, ParseException{
		JSONObject reader = types.find(tipo).getAtributosR();
		if (requerido.equals("No Requerido")){
			reader = types.find(tipo).getAtributosNR();
		}
		Object atributoTipo = reader.get(atributo);
		if (!(atributoTipo==null)){
			Object lector = atributos.get(atributo);
			if (lector == null){
				DocumentObject obj = new DocumentObject();
				obj.setAtributo(atributo);
				obj.setValor(valor);
				atributos.put(atributo, valor);
				if (root==null){
					root = obj;
				}
				else{
					DocumentObject current = root;
					while(current.getNext()!=null){
						current = current.getNext();
					}
					current.setNext(obj);
				}
			}
		}
	}

	public void deleteObject(String name){
		if (root.getAtributo().equals(name)){
			root = root.getNext();
			atributos.remove(name);
		}
		else{
			DocumentObject current = root;
			DocumentObject current2 = current.getNext();
			while (current2 != null){
				if (current2.getAtributo().equals(name)){
					current.setNext(current2.getNext());
					atributos.remove(name);
					return;
				}
				current = current2;
				current2 = current2.getNext();
			}
			return;
		}
	}

	public void display() {
		if(this.root == null){
			return;
		}
        DocumentObject current = root;
        while (current != null) {
            System.out.println(current.getAtributo() + " = " +  current.getValor());
            current = current.getNext();
        }
	}

	public boolean exists(String name){
		DocumentObject current = root;
        while (current != null) {
       		if (current.getAtributo().equals(name)){
        		return true;
        	}
        	current=current.getNext();
        }
        return false;

	}

	public Object find(String name){
		DocumentObject current = root;
        while (current != null) {
       		if (current.getAtributo().equals(name)){
        		return current.getValor();
        	}
        	current=current.getNext();
        }
        return null;

	}

	@SuppressWarnings("unchecked")
	public void write(){
		if(this.root == null){
			return;
		}
		DocumentObject current = root;
		JSONObject doc = new JSONObject();
		while (current != null) {
            doc.put(current.getAtributo(),current.getValor());
            current = current.getNext();
		}
		doc.put("Tipo",tipo);
        try {
           	String archivo = carpeta+".json";
    		@SuppressWarnings("resource")
			FileWriter file = new FileWriter(archivo);
    		file.write(doc.toJSONString());
    		file.flush();
    	} catch (IOException e) {
        }
	}

	public Document getPrev() {
		return prev;
	}
	public void setPrev(Document prev) {
		this.prev = prev;
	}
	public Document getNext() {
		return next;
	}
	public void setNext(Document next) {
		this.next = next;
	}
	public File getDirectorio() {
		return Directorio;
	}
	public void setDirectorio(File directorio) {
		Directorio = directorio;
	}
	public String getCarpeta() {
		return carpeta;
	}
	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public JSONObject getAtributos() {
		return atributos;
	}
	public void setAtributos(JSONObject atributos) {
		this.atributos = atributos;
	}

}
