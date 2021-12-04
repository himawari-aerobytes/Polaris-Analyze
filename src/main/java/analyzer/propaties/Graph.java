package analyzer.propaties;

import java.util.ArrayList;
import java.util.List;


public class Graph<K,V> {
    private List<K> key = new ArrayList<>();
    private List<V> value = new ArrayList<>();

    public Graph(){ }
    public Graph(List<K> K,List<V> V){
        this.key.addAll(K);
        this.value.addAll(V);
    }

    public void addValue(V v){
        this.value.add(v);
    }
    public void addKey(K k){
        this.key.add(k);
    }

    public void addValue(List<V> v){
        this.value.addAll(v);
    }
    public void addKey(List<K> k){
        this.key.addAll(k);
    }

    public List<K> getKey() {
        return key;
    }

    public void setKey(List<K> key) {
        this.key = key;
    }

    public List<V> getValue() {
        return value;
    }

    public void setValue(List<V> value) {
        this.value = value;
    }

}
