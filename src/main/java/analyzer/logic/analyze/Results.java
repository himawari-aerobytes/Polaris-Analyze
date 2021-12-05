package analyzer.logic.analyze;

import analyzer.propaties.Graph;

import java.util.List;

public class Results extends Graph<String,Double> {
    private String grade;
    public Results(List<String> date, List<Double> value, String grade){
        super.setKey(date);
        super.setValue(value);
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Results(){}
}
