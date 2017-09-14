package DataArchitecture;

import java.io.File;

public class Store {

	private Store next;
	private Store prev;
	private File directorio;
	private String nombre;

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

}
