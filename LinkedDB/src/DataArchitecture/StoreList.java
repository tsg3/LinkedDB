package DataArchitecture;

import java.io.File;

public class StoreList {

	private Store start;
	private Store end;

	public StoreList(){
		this.start=null;
		this.end=null;
	}

	public void add(String ruta){
		Store newStore = new Store();
		File directorio = new File("C:\\Users\\este0\\Desktop\\"+ruta);
		newStore.setDirectorio(directorio);
		newStore.setNombre(ruta);
		if (start==null){
			start=newStore;
			end = start;
			start.setNext(end);
			start.setPrev(end);
			end.setNext(start);
			end.setPrev(start);
		}
		else{
			Store current = end;
			current.setNext(newStore);
			newStore.setPrev(current);
			end = newStore;
			end.setNext(start);
			start.setPrev(end);
		}
	}

	public void delete(String nombre){
		Store current = find(nombre);
		if (current == start) {
			Store exStart = start;
			start = start.getNext();
			exStart.setNext(null);
			exStart.setPrev(null);
			start.setPrev(end);
			end.setNext(start);
		}
		else if (current == end) {
			Store newEnd = end.getPrev();
			newEnd.setNext(start);
			end.setPrev(null);
			end.setNext(null);
			end=newEnd;
			start.setPrev(end);
		}
		else {
			Store anterior = current.getPrev();
			Store siguiente = current.getNext();
			current.setNext(null);
			current.setNext(null);
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
		do {
            System.out.println(current.getDirectorio());
            current=current.getNext();
		} while (current != start);
	}

	public void write(){
		if(this.start==null){
			return;
		}
		Store current = start;
		do {
            current.getDirectorio().mkdir();
            current=current.getNext();
		} while (current != start);
	}

	public Store find(String nombre) {
    	Store current = start;
        do {
        	if (current.getNombre()==nombre) {
                return current;
            } else {
                current = current.getNext();
            }
        } while (current != start);
        return null;
	}

	public static void main(String[] args){
		StoreList l = new StoreList();
		l.add("Proyecto 1");
		l.add("Proyecto 2");
		l.add("Proyecto 3");
		l.write();
		l.delete("Proyecto 2");
		l.display();
	}
}
