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

    @Override
    public ResultsArray clone(){
        ResultsArray r = null;
        try {
            r=(ResultsArray) super.clone(); //親クラスのcloneメソッドを呼び出す(親クラスの型で返ってくるので、自分自身の型でのキャストを忘れないようにする)
            r.date = new ArrayList<>(this.date); //親クラスのcloneメソッドで深いコピー(複製先のクラス型変数と複製元のクラス型変数で指しているインスタンスの中身が違うコピー)がなされていないクラス型変数をその変数のcloneメソッドで複製し、複製先のクラス型変数に代入
            r.value = new ArrayList<>(this.value);
            r.grade = this.grade;
        }catch (Exception e){
            e.printStackTrace();
        }
        return r;

    }
    public ResultsArray(){}
}
