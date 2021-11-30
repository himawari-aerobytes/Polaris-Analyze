package analyzer.analyze;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private String date;
    private String grade;
    private Double value;

    public Result(String date,String grade,Double value){
        this.date = date;
        this.grade = grade;
        this.value = value;
    }
    public Result(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.grade = name;
    }
}
