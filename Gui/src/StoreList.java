

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StoreList {

	public Store start;
	public Store end;
	public String carpeta;

	public StoreList(){
		this.start=null;
		this.end=null;
		File miDir = new File (".");
		try {
			carpeta=miDir.getCanonicalPath();
		}
		    catch(Exception e) {
		    e.printStackTrace();
		}
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

	public static void read(TypeList types, StoreList stores) throws IOException, ParseException{
		File d = new File(stores.getCarpeta());
		if (d.exists()){
			String[] listaArchivos=d.list();
			for(int i=0; i<listaArchivos.length; i++){
				if (!(listaArchivos[i].substring(listaArchivos[i].length()-5).equals(".json"))){
					stores.add(listaArchivos[i]);}
        	}
			stores.readJSONS(types);
		}
	}

	public void write(){
		if(this.start==null){
			return;
		}
		File d = new File(carpeta);
		String[] listaArchivos=d.list();
		for(int i=0; i<listaArchivos.length; i++){
			File a = new File(carpeta+"\\"+listaArchivos[i]);
			if (a.delete())
				 System.out.println("El fichero " + listaArchivos[i] + " ha sido borrado correctamente");
				else
				 System.out.println("El fichero " + listaArchivos[i] + " no se ha podido borrar");

		}
		Store current = start;
		while (current != null) {
            current.getDirectorio().mkdirs();
            current.write();
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

	public void readJSONS(TypeList types) throws IOException, ParseException{
		File d = new File(carpeta);
		String[] listaArchivos=d.list();
		for(int i=0; i<listaArchivos.length; i++){
			add(listaArchivos[i]);
		}
		Store current = start;
    	while (current != null) {
        	readJSON(types, current);
            current = current.getNext();
        }
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
				String[] requeridos = types.find((String) jsonObject.get("Tipo")).getAtributosRString();
				int n = atributos.length;
				int n2 = requeridos.length;
				Object[] valores = new Object[n2];
				Object[] valoresT = new Object[n-n2];
				int j = 0;
				while (j<n){
					if (j<n2)
						valores[j] = jsonObject.get(requeridos[j]);
					else if (jsonObject.get(atributos[j])!=null)
						valoresT[j] = jsonObject.get(atributos[j]);
					j++;
				}
				if (jsonObject.get("Requerido")==null)
					store.add(types,(String)jsonObject.get("Tipo"),listaArchivos[i].substring(0,listaArchivos[i].length()-5),valores);
				if (valoresT[0]!=null){
					for (int k=n2; k<n; k++)
						store.start.addObject(types,atributos[k],valoresT[k-2],"No Requerido");}
        	}
		}
	}

	public String getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}
}
