public class Member {
    private String name;
    private String grade;
    private String number;
    private Counter counter;


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

    public Member(String name, String number, String grade){
        this.name = name;
        this.grade = grade;
        this.number = number;
        this.counter = new Counter();
    }

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





}
