	package serverStuff;
	
	import java.util.NoSuchElementException;
	import java.util.Arrays;
	import java.util.HashMap;
	
	
	public class SpecificMap<String, V > extends HashMap<String, V>
{
	
	public int length =0;
	//HashMap<String, ServerGroup> hash = new HashMap<String, ServerGroup>(128);
	
		
		
		
	public V remove(String key)
	{
		length--;
		return super.remove(key);
		
	
	}
	
	public void put(String key, V value)
	{
		length++;
		super.put(key, value);
		
	}
}
	
	
	
