package festival.srv.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class Util {

	public static String toJson(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); //.serializeNulls()
		return gson.toJson(object);
	}
}
