import java.util.Comparator;
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
 
		public ValueComparator(Map<K, V> map, boolean rev) {
			this.map = map;
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
	
	private static class ValueComparator2<K , V extends Comparable<V>> implements Comparator<K>
	{
		Map<K, V> map;
		boolean reverse; 
 
		public ValueComparator2(Map<K, V> map, boolean rev) {
			this.map = map;
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
	
	public static<K, V extends Comparable<V>> Map<K, V> sortByValueTitle(Map<K, V> unsortedMap, boolean reverse)
	{
		Map<K, V> sortedMap = new TreeMap<K, V>(new ValueComparator<K, V>(unsortedMap, reverse));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}
}