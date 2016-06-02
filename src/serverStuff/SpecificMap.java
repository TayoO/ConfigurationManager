	package serverStuff;
	
	import java.util.NoSuchElementException;
	import java.util.Arrays;
	import java.util.LinkedHashMap;
	
	
	public class SpecificMap<String, V > extends LinkedHashMap<String, V>
{
	
	public int length =0;
	//HashMap<String, ServerGroup> hash = new HashMap<String, ServerGroup>(128);
	
		
		
		
	public void remove(String key, V value)
	{
		length--;
		super.remove(key);
		
	
	}
	
	public V put(String key, V value)
	{
		length++;
		return super.put(key, value);
		
	}
	public java.lang.String toString(){
		return null;
	}
}
	
	
	
