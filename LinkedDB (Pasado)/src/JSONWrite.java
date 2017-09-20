import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONWrite {

	JSONObject doc;
	FileWriter file;

	public void deleteJSON(String rute){
		File ruta = new File(rute);
		if (ruta.delete())
			System.out.println("Arhivo Eliminado");
		else
			System.out.println("Arhivo Eliminado");
	}

	public void createJSON(String fileName){
		doc = new JSONObject();
		try {
			file = new FileWriter("C:\\Users\\este0\\Desktop\\"+fileName+".json");
			file.flush();
		} catch (IOException e) {
		}
	}

	@SuppressWarnings("unchecked")
	public void addObject(Object[] a, String name){
		JSONObject obj = new JSONObject();
		int n = a.length;
		int j = 0;
		int k = 1;
		Object current1 = a[j];
		Object current2 = a[k];
		for (int i=0; i < n/2; i++){
			obj.put(current1, current2);
			if(i<(n/2)-1){
				j+=2;
				k+=2;
				current1=a[j];
				current2=a[k];
			}
		}
		doc.put(name, obj);
	}

	public void deleteObject(){
	}

	public void writeJSON(){
		try {
			file.write(doc.toJSONString());
			file.flush();
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		Object[] array= new Object[4];
		array[0]="Carnet";
		array[1]=2017097066;
		array[2]="Carrera";
		array[3]="Ing. en Computadores";
		Object[] array2= new Object[4];
		array2[0]="Carnet";
		array2[1]=2012780453;
		array2[2]="Carrera";
		array2[3]="Ing. en Computacion";
		JSONWrite doc = new JSONWrite();
		doc.createJSON("Persona");
		doc.addObject(array, "Esteban");
		doc.addObject(array2, "Daniel");
		doc.writeJSON();
		System.out.println(doc.doc);


		FileReader reader = new FileReader("C:\\Users\\este0\\Desktop\\Persona.json");
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

		JSONObject carnet = (JSONObject) jsonObject.get("Esteban");
		System.out.println("Into Esteban carnet, : " + carnet.get("Carnet"));
		System.out.println(jsonObject);
		jsonObject.remove("Daniel");
		System.out.println(jsonObject);

		 /*JSONParser parser = new JSONParser();
	        try {
	            Object obj = parser.parse(new FileReader("C:\\Users\\este0\\Desktop\\Persona.json"));
	            JSONObject jsonObject = (JSONObject)obj;

	            System.out.println("Object from file:" + jsonObject);

	            JSONObject id = (JSONObject)jsonObject.get("Esteban");
	            System.out.println("Esteban:"+id);

	            JSONArray list = (JSONArray)jsonObject.get("Daniel");
				System.out.println(list);
	            for (int i = 0; i < list.size(); i++) {
	                System.out.println(i+ ": " + list.get(i));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	}
}