package models.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formatter {
	protected final static String COLON = ":";
	protected final static String COMMA = ",";
	protected final static String SPACE = " ";
	protected final static String NEWLINE = "\n";
	protected final static String TAB = "\t";
	protected final static String OPEN_BRACKET = "{";
	protected final static String CLOSE_BRACKET = "}";
	protected final static String OPEN_SQUARE = "[";
	protected final static String CLOSE_SQUARE = "]";
	protected static final String OPEN_PAR = "(";
	protected static final String CLOSE_PAR = ")";
	protected final static String REPLACEMENT = "\t";
	protected String name;
	protected String prefix;
	protected Map<String, String> keyValueMap;
	protected List<String> objectsToAppend;
	protected Map<String, ArrayList<String>> arraysToAppend;
	
	public Formatter() {
		keyValueMap = new HashMap<String, String>(); 
		objectsToAppend = new ArrayList<String>();
		arraysToAppend = new HashMap<String, ArrayList<String>>();
	}
	
	public Formatter name(String name) {
		this.name = name;
		return this;
	}
	
	public Formatter addProperty(String key, String value) {
		keyValueMap.put(key, value);
		return this;
	}
	
	public Formatter object(String object) {
		objectsToAppend.add(object);
		return this;
	}
	
	public Formatter array(String name, Object[] elements) {
		arraysToAppend.put(name, new ArrayList<String>());
	
		for (Object element : elements)
			arraysToAppend.get(name).add(element.toString());
		return this;
	}
	
	public String format() {
		if(isEmptyText())
			return "";
		if (isEmpty())
			return emptyObject();
		return formattedObject();
	}

	public Formatter prefix(String prefix) {
		this.prefix = prefix;
		return this;
	}
	
	protected boolean isEmpty() {
		return (keyValueMap.isEmpty() && objectsToAppend.isEmpty() && arraysToAppend.isEmpty());
	}
	
	protected String emptyObject() {
		String objectName = prefix == null ? name : prefix + OPEN_PAR + name + CLOSE_PAR;
		return objectName + COLON + SPACE + OPEN_BRACKET + CLOSE_BRACKET;
	}
	
	protected String formattedObject() {
		String objectName = prefix == null ? name : prefix + OPEN_PAR + name + CLOSE_PAR;
		String objectString = objectName + COLON + SPACE + OPEN_BRACKET;
		
		int keysCount = keyValueMap.keySet().size();
		String[] keys = keyValueMap.keySet().toArray(new String[keysCount]);
		
		for (int index = 0; index < keysCount; index++) {
			objectString += keyValue(keys[index], keyValueMap.get(keys[index]));
		}
		
		for (String objectsToAppend: objectsToAppend)
			objectString = append(objectString, objectsToAppend) + COMMA;
		
		for (String arrayName: arraysToAppend.keySet())
			objectString = append(objectString, arrayHelper(arrayName, arraysToAppend.get(arrayName))) + COMMA;
		
		return objectString + NEWLINE + CLOSE_BRACKET;
	}
	
	public static String keyValue(String key, String value) {
		return NEWLINE + REPLACEMENT + key + COLON + SPACE + value + COMMA;
	}
	
	public static String append(String head, String append) {
		if (append == "")
			return head;
		append = NEWLINE + append;
		return head + append.replaceAll(NEWLINE, NEWLINE + REPLACEMENT);
	}
	
	public static String arrayHelper(String name, ArrayList<String> content) {
		if (content.size() == 0)
			return name + COLON + SPACE + OPEN_SQUARE + CLOSE_SQUARE;
		String arr = name + COLON + SPACE + OPEN_SQUARE;
		for (int index = 0; index < content.size() ; index++) {
			if (! content.get(index).toString().equals(""))
			arr = (index == content.size() - 1)
				  ? append(arr, content.get(index).toString())
				  : append(arr, content.get(index).toString() + COMMA);
		}
			
		return arr += NEWLINE + CLOSE_SQUARE;
	}

	protected boolean isEmptyText() {
		if (name.equals("TextNode")) {
			for (char letter: keyValueMap.get("textValue").toCharArray())
				if (letter != '\n' && letter != '\t' && letter != ' ' && letter != '\r') {
					return false;
				}
			return true;
		}
		return false;
	}
	
}
