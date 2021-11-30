package analyzer.analyze;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<String> date = new ArrayList<>();
    private List<Double> value = new ArrayList<>();
    private String name;


    public Result(){}

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Double> getValue() {
        return value;
    }

    public void setValue(List<Double> value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
