package DataArchitecture;

import java.io.File;

public class Document {

	private Document next = null;
	private Document prev = null;
	private File Directorio;
	private String carpeta;
	private String nombre;

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

}
