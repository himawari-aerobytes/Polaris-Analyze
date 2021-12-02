package analyzer.analyze;

import java.util.ArrayList;
import java.util.List;

public class ResultsArray {
    private List<String> date = new ArrayList<>();
    private List<Double> value = new ArrayList<>();
    private String grade;

    public ResultsArray(List<String> date, List<Double> value, String grade){
        this.date = new ArrayList<>(date);
        this.value = new ArrayList<>(value);
        this.grade = grade;

    }

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void addDate(String date){
        this.date.add(date);
    }

    public void addValue(Double value){
        this.value.add(value);
    }


    public ResultsArray(){}
}
