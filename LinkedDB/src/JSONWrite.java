import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWrite {

	public static void deleteJSON(String rute){
		File ruta = new File(rute);
		if (ruta.delete())
			System.out.println("Arhivo Eliminado");
		else
			System.out.println("Arhivo Eliminado");
	}

	@SuppressWarnings("unchecked")
	public static void createJSON(String rute, LinkedList list){
		JSONObject obj = new JSONObject();
		int n = list.getLargo();
		Node current1 = list.root;
		Node current2 = current1.getNext();
		for (int i=0; i < n/2; i++){
			obj.put(current1.getDato(), current2.getDato());
			if(i<(n/2)-1){
				current1=current2.getNext();
				current2=current1.getNext();
			}
		}
		try {
			FileWriter file = new FileWriter(rute);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {

		/*JSONArray list = new JSONArray();
		list.add("tag 1");
		list.add("tag 2");
		list.add("tag 3");

		obj.put("Tags", list);

		JSONObject innerObj = new JSONObject();
		innerObj.put("PostX","Escribir un JSON");
		innerObj.put("PostY", "Leer un JSON");
		innerObj.put("PostZ", "lalala");

		obj.put("Posts",innerObj);*/
		LinkedList list = new LinkedList();
		list.add("Nombre");
		list.add("Cadena");
		list.add("Carnet");
		list.add("Entero");
		createJSON("C:\\Users\\este0\\Desktop\\prueba.json", list);

	}

}