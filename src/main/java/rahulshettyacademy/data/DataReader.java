package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public static List<Map<String,String>>  getDataReaderToMap(String filePath) throws IOException{
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+filePath),StandardCharsets.UTF_8);
		
		//String to HashMap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<Map<String, String>>>() {
		});
		return data;
		
	}

}
