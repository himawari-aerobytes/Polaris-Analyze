package analyzer.analyze;

public class Result {
    private String startDate;
    private String endDate;
    private int Read = 0;
    private int Receive = 0;
    private Double percentage = 0.0;
    private String grade;

    public Result(String startDate, String endDate, String grade, int read, int receive, Double percentage){
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
        this.Read = read;
        this.Receive = receive;
        this.percentage = percentage;

    }

    public int getReceive() {
        return Receive;
    }

    public void setReceive(int receive) {
        Receive = receive;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public int getRead() {
        return Read;
    }

    public void setRead(int read) {
        Read = read;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
