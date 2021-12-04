package analyzer.logic;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({
        "name",
        "number",
        "grade"
})
public class Member {
    @JsonProperty("name")
    private String name;
    @JsonProperty("grade")
    private String grade;
    @JsonProperty("number")
    private String number;
    @JsonIgnore
    private Counter counter;
    private Map<String ,Object> extensions = new HashMap<>();


    @JsonAnyGetter
    public Map<String,Object> getExtensions(){
        return this.extensions;
    }
    @JsonAnySetter
    public void setExtensions(String key,Object value){
        this.extensions.put(key,value);
    }
    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getNumber() {
        return number;
    }

    public Counter getCounter() {
        return counter;
    }

    /**
     * 研究室メンバーをセットします
     * @param name 名前
     * @param number t_device_mng_id
     * @param grade 学年(統一されていれば何でもoK)
     */
    @JsonCreator
    public Member(@JsonProperty("name") String name,
                  @JsonProperty("number")String number,
                  @JsonProperty("grade")String grade
    ){
        this.name = name;
        this.grade = grade;
        this.number = number;
        this.counter = new Counter();

    }

    public Member(){}

    @Override
    public String toString(){
        return grade+name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj){

        if(obj == this){
            System.out.println("同一オブジェクト");
            return true;
        }
        if(!(obj instanceof Member)){
            System.out.println("非同一オブジェクト");
            return false;
        }
        Member member = (Member) obj;

        System.out.println(member.getNumber() == this.getNumber());


        return member.getNumber() == this.getNumber();
    }

    @Override
    public int hashCode(){
        return Integer.parseInt(number);
    }







}
