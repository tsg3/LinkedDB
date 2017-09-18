package DataArchitecture;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Store {

	public Document start;
	public Document end;
	private Store next;
	private Store prev;
	private File directorio;
	private String nombre;
	private String carpeta;

	public Store() {
		this.start=null;
		this.end=null;
		carpeta="";
	}

	public void add(TypeList types, String type, String ruta, Object[] atributosR) throws IOException, ParseException{
		if (types.exists(type)) {
			Document newDocument = new Document();
			String carpeta = this.carpeta+"\\"+ruta;
			File directorio = new File(carpeta);
			newDocument.setDirectorio(directorio);
			newDocument.setCarpeta(carpeta);
			newDocument.setNombre(ruta);
			newDocument.setTipo(type);
			newDocument.type(type);
			int n = atributosR.length;
			int i = 0;
			while (i<n){
				newDocument.addObject(types,types.find(type).getAtributosRString()[i],atributosR[i],"Requerido");
				i++;
			}
			if ((start==null) && (end==null)){
				start=newDocument;
				end = start;
			}
			else{
				Document current = end;
				current.setNext(newDocument);
				newDocument.setPrev(current);
				end = newDocument;
			}
		}
		else {
			System.out.println("No existe" + type);
		}
	}

	public Store getNext() {
		return next;
	}
	public void setNext(Store next) {
		this.next = next;
	}
	public Store getPrev() {
		return prev;
	}
	public void setPrev(Store prev) {
		this.prev = prev;
	}
	public File getDirectorio() {
		return directorio;
	}
	public void setDirectorio(File directorio) {
		this.directorio = directorio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

}
