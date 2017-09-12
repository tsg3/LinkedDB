package DataArchitecture;

import java.io.IOException;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class JSONList {

	public JSON root;
	private int largo;
	private String[] atributos;
	public FileWriter file;

	public JSONList(String[] atributos){
		this.root=null;
		this.largo = 0;
		this.atributos = atributos;
	}

	@SuppressWarnings("unchecked")
	public void add(Object[] valores, String nombre){
		JSONObject obj = new JSONObject();
		JSON newJSON = new JSON();
		newJSON.setName(nombre);
		int n = atributos.length;
		int j = 0;
		Object atributo = atributos[j];
		Object valor = valores[j];
		for (int i=0; i < n; i++){
			obj.put(atributo, valor);
			if(i<n-1){
				j++;
				atributo = atributos[j];
				valor = valores[j];
			}
		}
		newJSON.setAtributos(obj);
		if (root==null){
			root = newJSON;
		}
		else{
			JSON current = root;
			while(current.getNext()!=null){
				current = current.getNext();
			}
			current.setNext(newJSON);
		}
		largo++;
	}

	@SuppressWarnings("unchecked")
	public void insert(Object[] valores, String nombre, int pos){
		JSONObject obj = new JSONObject();
		JSON newJSON = new JSON();
		newJSON.setName(nombre);
		int n = atributos.length;
		int j = 0;
		Object atributo = atributos[j];
		Object valor = valores[j];
		for (int i=0; i < n; i++){
			obj.put(atributo, valor);
			if(i<n-1){
				j++;
				atributo = atributos[j];
				valor = valores[j];
			}
		}
		newJSON.setAtributos(obj);
		if (pos>(largo)){
			System.out.println("Error: Excedió el largo de la lista");
		}
		else if (pos==0){
			newJSON.setNext(this.root);
	        this.root = newJSON;
		}
		else{
			JSON current = root;
			for (int i = 0; i < pos - 1; i++){
				current = current.getNext();
			}
			newJSON.setNext(current.getNext());
			current.setNext(newJSON);
		}
		largo++;
	}

	public int getLargo() {
		return largo;
	}

	public void delete(int pos){
		if (pos>=(largo)){
			System.out.println("Error: Excedió el largo de la lista");
		}
		else if (pos == 0) {
			root = root.getNext();
		}
		else{
			JSON current = root;
			for (int i = 0; i < pos - 1; i++){
				current = current.getNext();
			}
			JSON next = current.getNext();
			current.setNext(next.getNext());
			next.setNext(null);
		}
		largo--;
	}

	public void display() {
		if(this.root == null){
			return;
		}
        JSON current = root;
        while (current != null) {
            System.out.println(current.getAtributos());
            current = current.getNext();
        }

	}

	@SuppressWarnings("unchecked")
	public void write(String rute){
		if(this.root == null){
			return;
		}
		JSON current = root;
		JSONObject doc = new JSONObject();
		while (current != null) {
            doc.put(current.getName(),current.getAtributos());
            current = current.getNext();
        }
		try {
			file = new FileWriter("C:\\Users\\este0\\Desktop\\"+rute+".json");
			file.write(doc.toJSONString());
			file.flush();
		} catch (IOException e) {
		}
	}

    public String find(Object dato){
    	JSON current = root;
        while (current != null) {
        	for (int i = 0; i < atributos.length; i++){
        		if (current.getAtributos().get(atributos[i])==dato){
        			System.out.println(current.getName());
        			return current.getName();
        		}
            }
        	current=current.getNext();
        }
        return null;

	}

	public static void main(String[] args){
		String[] a = new String[2];
		a[0]="Carnet";
		a[1]="Edad";
		Object[] b = new Object[2];
		b[0]="2017097066";
		b[1]=17;
		Object[] c = new Object[2];
		c[0]="2013093564";
		c[1]=20;
		Object[] d = new Object[2];
		d[0]="2008567120";
		d[1]=26;
		JSONList list = new JSONList(a);
		list.add(b,"Esteban");
		list.add(c,"Daniel");
		list.display();
		list.write("Persona");
		list.find("2017097066");
		list.insert(d,"Marco",2);
		list.display();
		list.write("Persona");
	}

}
