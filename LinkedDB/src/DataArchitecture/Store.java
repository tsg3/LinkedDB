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
				start.setNext(end);
				start.setPrev(end);
				end.setNext(start);
				end.setPrev(start);
			}
			else{
				Document current = end;
				current.setNext(newDocument);
				newDocument.setPrev(current);
				end = newDocument;
				end.setNext(start);
				start.setPrev(end);
			}
		}
		else {
			System.out.println("No existe" + type);
		}
	}

	public void delete(String name){
		if (start.getNombre().equals(name)){
			Document exStart = start;
			start = start.getNext();
			exStart.setNext(null);
			exStart.setPrev(null);
			start.setPrev(end);
			end.setNext(start);
		}
		else if (end.getNombre().equals(name)){
			Document newEnd = end.getPrev();
			end.setPrev(null);
			end.setNext(null);
			end=newEnd;
			end.setNext(start);
			start.setPrev(end);
		}
		else{
			Document current = start;
			while (current.getNext() != null){
				if (current.getNext().getNombre().equals(name)){
					Document deleting = current.getNext();
					current.setNext(current.getNext().getNext());
					deleting.setNext(null);
					deleting.setPrev(null);
					return;
				}
				current = current.getNext();
			}
			return;
		}
	}

	public void display() {
		if((this.start == null)&&(this.end == null)){
			return;
		}
        Document current = start;
        do {
        	System.out.println(current.getNombre());
            current.display();
            System.out.println("---------------------------------------");
            current = current.getNext();
        } while (current != start);

	}

	public Document find(String name) {
    	Document current = start;
        while (current != null) {
            if (current.getNombre().equals(name)) {
                return current;
            } else {
                current = current.getNext();
            }
        }
        return null;
	}
	public boolean exists(String name) {
    	Document current = start;
        while (current != null) {
            if (current.getNombre().equals(name)) {
                return true;
            } else {
                current = current.getNext();
            }
        }
        return false;
	}
	public void write() {
    	Document current = start;
        do {
            current.write();
            current=current.getNext();
        } while (current != start);
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
