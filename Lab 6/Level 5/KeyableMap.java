import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class KeyableMap<T, K, V extends Keyable<K>> {
    protected T mapName;
    protected Map<K, V> map = new HashMap<K, V>();
    
    public KeyableMap(T t) {
        this.mapName = t;
    }
	
	// edited
    public Optional<V> get(K k) {
        return Optional.ofNullable(map.get(k));
    }
	
    public KeyableMap<T, K, V> put(V keyable) {
        map.put(keyable.getKey(), keyable);
        return this;
    }

    @Override
    public String toString() {
        String result = mapName + ": " + map.values();
        result = result.replace('[', '{').replace(']', '}');
        return result;
    }
}
