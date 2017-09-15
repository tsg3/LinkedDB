package DataArchitecture;

import java.io.File;

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
		File d = new File(stores.carpeta);
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
        	if (current.getNombre()==nombre) {
                return current;
            } else {
                current = current.getNext();
            }
        }
        return null;
	}

	public static void main(String[] args){
		String[] atributos = {"Nombre", "Carnet", "Número"};
		Object[] R = {"", 0};
		Object[] N = {0};
		String nombre = "Persona";
		TypeList types = new TypeList();
		types.add(atributos,R,N,nombre);
		Object[] valores = {"Esteban",2017097066};
		StoreList stores = new StoreList();
		read(stores);
		stores.display();
		stores.find("Proyecto 1").add(types,"Persona", "Oscar",valores);
	}
}
