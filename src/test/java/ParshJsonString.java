import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

/**
 * @author vikasthange
 *
 * Mar 16, 2018 - 11:26:30 AM
 */
public class ParshJsonString {

	@Test
	public void exampleTestToReadValueFromJSONFile() throws FileNotFoundException{
		final Scanner s = new Scanner(new File("src/test/resources/sample_json_data.json"));
		final String jsonData = s.useDelimiter("\\Z").next();
		System.out.println("Input JSON: "+ jsonData);
		final JSONArray obj =  JsonPath.read(jsonData, "$.store.book.*");
		System.out.println("Total books: "+ obj.size());
		s.close();
		Assert.assertEquals(obj.size(), 4,"Books count from API failed");
	}
	@Test
	public void exampleTestToReadValueFromJSONUrl() throws IOException{
		final URL url = new URL("http://wxdata.weather.com/wxdata/ta/Mum.js?max=10&locType=1,9,4&locale=en_US&key=e88d42ec-a740-102c-bafd-001321203584");
		final List<String> locations = JsonPath.read(url.openStream(), "$.results[*].name");
		System.out.println("Locations found "+ locations);
		System.out.println("Total locations: "+ locations.size());
		Assert.assertEquals(locations.size(), 10,"10 Locations not found in webservice");
	}
}
