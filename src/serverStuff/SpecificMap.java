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
		//Add an object: increase length
		length++;
		return super.put(key, value);
		
	}
	
	public void putAll(SpecificMap m){
		//The new length equals original length plus the length of the added map
		length+=m.length;

		while (m.keySet().iterator().hasNext());
		{
			//Reduces length is there is a duplicate
		if (this.containsKey(m.keySet().iterator().next()))
			{
			length--;
			}
		}
	}
	public java.lang.String toString(){
		return null;
	}
}
	
	
	
