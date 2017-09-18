package DataArchitecture;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StoreList {

	private Store start;
	private Store end;
	private String carpeta;

	public StoreList(){
		this.start=null;
		this.end=null;
		carpeta="C:\\Users\\este0\\Desktop\\Pruebas";
	}

	public void add(String ruta){
		Store newStore = new Store();
		String carpeta = this.carpeta+"\\"+ruta;
		File directorio = new File(carpeta);
		newStore.setDirectorio(directorio);
		newStore.setCarpeta(carpeta);
		newStore.setNombre(ruta);
		if (start==null){
			start=newStore;
			end = start;
		}
		else{
			Store current = end;
			current.setNext(newStore);
			newStore.setPrev(current);
			end = newStore;
		}
	}

	public void delete(String nombre){
		Store current = find(nombre);
		if (current == start) {
			start=current.getNext();
			start.setPrev(null);
			if (current.getDirectorio().delete())
				 System.out.println("El fichero " + current.getDirectorio() + " ha sido borrado correctamente");
			else
				 System.out.println("El fichero " + current.getDirectorio() + " no se ha podido borrar");
		}
		else if (current == end) {
			end=current.getPrev();
			end.setNext(null);
			if (current.getDirectorio().delete())
				 System.out.println("El fichero " + current.getDirectorio() + " ha sido borrado correctamente");
			else
				 System.out.println("El fichero " + current.getDirectorio() + " no se ha podido borrar");
		}
		else {
			Store anterior = current.getPrev();
			Store siguiente = current.getNext();
			anterior.setNext(siguiente);
			siguiente.setPrev(anterior);
			if (current.getDirectorio().delete())
				 System.out.println("El fichero " + current.getDirectorio() + " ha sido borrado correctamente");
			else
				 System.out.println("El fichero " + current.getDirectorio() + " no se ha podido borrar");
		}
	}

	public void display(){
		if(this.start==null){
			return;
		}
		Store current = start;
		while (current != null) {
            System.out.println(current.getDirectorio());
            current=current.getNext();
		}
	}

	public static void read(StoreList stores){
		File d = new File(stores.getCarpeta());
		if (d.exists()){
			String[] listaArchivos=d.list();
			for(int i=0; i<listaArchivos.length; i++){
            	stores.add(listaArchivos[i]);
        	}
		}
	}

	public void write(){
		if(this.start==null){
			return;
		}
		Store current = start;
		while (current != null) {
            current.getDirectorio().mkdir();
            current=current.getNext();
		}
	}

	public Store find(String nombre) {
    	Store current = start;
    	while (current != null) {
        	if (current.getNombre().equals(nombre)) {
                return current;
            } else {
                current = current.getNext();
            }
        }
        return null;
	}

	public static void readJSON(TypeList types, Store store) throws IOException, ParseException{
		File d = new File(store.getCarpeta());
		if (d.exists()){
			String[] listaArchivos=d.list();
			for(int i=0; i<listaArchivos.length; i++){
				FileReader reader = new FileReader(store.getCarpeta()+"\\"+listaArchivos[i]);
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
				String[] atributos = types.find((String) jsonObject.get("Tipo")).getAtributos();
				int n = atributos.length;
				Object[] valores = new Object[n];
				int j = 0;
				while (j<n){
					if (jsonObject.get(atributos[j])!=null)
						valores[j] = jsonObject.get(atributos[j]);
					j++;
				}
				if (jsonObject.get("Requerido")==null)
					store.add(types,(String)jsonObject.get("Tipo"),listaArchivos[i].substring(0,listaArchivos[i].length()-5),valores);
        	}
		}
	}

	public String getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

	public static void main(String[] args) throws IOException, ParseException{
		String[] atributos = {"Nombre", "Carnet", "Número"};
		Object[] R = {"", 0};
		Object[] N = {0};
		String nombre = "Persona";
		Object[] valores = {"Esteban",2017097066};
		StoreList stores = new StoreList();
		TypeList types = new TypeList(stores);
		types.add(atributos,R,N,nombre);
		read(stores);
		stores.display();
		/*stores.find("Proyecto 1").add(types,nombre,"Esteban",valores);
		System.out.println(stores.find("Proyecto 1").start.getCarpeta());
		System.out.println(stores.find("Proyecto 1").start.getAtributos());
		stores.find("Proyecto 1").start.addObject(types,"Nombre","Daniel","Requerido");
		System.out.println(stores.find("Proyecto 1").start.getAtributos());
		stores.find("Proyecto 1").start.display();
		System.out.println(stores.find("Proyecto 1").start.exists("Numero"));
		System.out.println(stores.find("Proyecto 1").start.find("Nombre"));
		stores.find("Proyecto 1").start.write();*/
		readJSON(types,stores.find("Proyecto 1"));

		/*File d = new File(stores.find("Proyecto 1").getCarpeta());
		String[] listaArchivos=d.list();
		FileReader reader = new FileReader(stores.find("Proyecto 1").getCarpeta()+"\\"+listaArchivos[0]);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		String[] atributos2 = types.find((String) jsonObject.get("Tipo")).getAtributos();
		int n = atributos.length;
		Object[] valores2 = new Object[n];
		int j = 0;
		while (j<n){
			if (jsonObject.get(atributos[j])!=null)
				valores[j] = jsonObject.get(atributos2[j]);
			j++;
		}
		if (jsonObject.get("Requerido")==null)
			store.add(types,(String)jsonObject.get("Tipo"),listaArchivos[i],valores);
		System.out.println(listaArchivos[0].substring(0,listaArchivos[0].length()-5));*/

	}
}
