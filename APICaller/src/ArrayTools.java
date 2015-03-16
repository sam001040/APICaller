import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
 

/* Modified version of https://gist.github.com/gbersac/8666ad9a7682d6d865ce
 * 
 * 
 * */
public class ArrayTools
{
	private static class ValueComparator<K , V extends Comparable<V>> implements Comparator<K>
	{
		Map<K, V> map;
		boolean reverse; 
 
		public ValueComparator(Map<K, V> unsortedMap, boolean rev) {
			this.map = (Map<K, V>) unsortedMap;
			this.reverse = rev; 
		}
 
		@Override
		public int compare(K keyA, K keyB) {
			Comparable<V> valueA = map.get(keyA);
			V valueB = map.get(keyB);
			
			System.out.println("A Value: " +map.get(keyA) + " B Value: " + map.get(keyB) + " Result: " + valueA.compareTo(valueB));
			
			int result = valueA.compareTo(valueB);
			
			if (reverse && result == 1){
				return -1; 
			}
			else if (reverse && result == -1){
				return 1; 
			}
			else {
				return result;
			}

		}
 
	}
 
	public static<K, V extends Comparable<V>> Map<K, V> sortByValue(Map<K, V> unsortedMap, boolean reverse)
	{
		Map<K, V> sortedMap = new TreeMap<K, V>(new ValueComparator<K, V>(unsortedMap, reverse));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}
	
	private static class ValueComparatorForCode<K , V extends Comparable<V>> implements Comparator<K>
	{
		Map<K, V> map;
		boolean reverse; 
 
		public ValueComparatorForCode(HashMap<String, LinkedHashMap> items, boolean rev) {
			this.map = (Map<K, V>) items;
			this.reverse = rev; 
		}
 
		@Override
		public int compare(K keyA, K keyB) {
			Comparable<Long> valueA = Long.parseLong((String) ((LinkedHashMap) map.get(keyA)).get("code"));
			Long valueB =  Long.parseLong((String) ((LinkedHashMap) map.get(keyB)).get("code"));
			int result = valueA.compareTo(valueB);

			if (reverse && result == 1){
				return -1; 
			}
			else if (reverse && result == -1){
				return 1; 
			}
			else {
				return result;
			}
		}
	}
	
	public static<K, V extends Comparable<V>> Map<K, V> sortByCode(HashMap<String, LinkedHashMap> items, boolean reverse)
	{
		Map<K, V> sortedMap = new TreeMap<K, V>(new ValueComparatorForCode<K, V>(items, reverse));
		sortedMap.putAll((Map<? extends K, ? extends V>) items);
		return sortedMap;
	}



}