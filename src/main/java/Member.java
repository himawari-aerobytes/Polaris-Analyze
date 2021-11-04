public class Member {
    private String name;
    private String grade;
    private String number;
    private Counter counter;


    public String getName() {
        return name;
  ***REMOVED***

    public String getGrade() {
        return grade;
  ***REMOVED***

    public String getNumber() {
        return number;
  ***REMOVED***

    public Counter getCounter() {
        return counter;
  ***REMOVED***

    /**
     * 研究室メンバーをセットします
     * @param name 名前
     * @param number t_device_mng_id
     * @param grade 学年(統一されていれば何でもoK)
     */
    public Member(String name, String number, String grade){
        this.name = name;
        this.grade = grade;
        this.number = number;
        this.counter = new Counter();
  ***REMOVED***

    @Override
    public String toString(){
        return grade+name;
  ***REMOVED***

    public void setName(String name){
        this.name = name;
  ***REMOVED***

    public void setGrade(String grade){
        this.grade = grade;
  ***REMOVED***





}
