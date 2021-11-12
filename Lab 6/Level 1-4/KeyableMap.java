import java.util.Map;
import java.util.HashMap;

public class KeyableMap<T, K, V extends Keyable<K>> {
    protected T mapName;
    protected Map<K, V> map = new HashMap<K, V>();
    
    public <K, V> KeyableMap(T t) {
        this.mapName = t;
    }

    public V get(K k) {
        return map.get(k);
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
