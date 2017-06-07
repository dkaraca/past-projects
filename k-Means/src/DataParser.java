 import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataParser {
	public static void readinFile(Path file,State state){
		try (BufferedReader filescan = Files.newBufferedReader(file,StandardCharsets.UTF_8);){
			String line;
			
			while((line = filescan.readLine()) != null){
				//Attribute att = new Attribute();
				
				String[] arr = line.split(",");
				Point p = new Point(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]),Double.parseDouble(arr[2]),Double.parseDouble(arr[3]));
				p.label = arr[4];
				
				//state.attributes.add(att);
				state.points.add(p);
				
			}
			}catch(IOException e){
				e.printStackTrace();
			}
	}
}
