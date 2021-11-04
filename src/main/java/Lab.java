import java.util.ArrayList;
import java.util.List;

public class Lab {
    private List <Member> members;
    public Lab(List<Member> members){
        this.members = new ArrayList<Member>(members);
  ***REMOVED***



    public double calcGradePercentage(String grade){
        int allRead = 0;
        int allReceive = 0;

        for(Member member : members){
            if(grade.equals(member.getGrade())){
                allReceive += member.getCounter().getAllReceived();
                allRead += member.getCounter().getAllRead();
          ***REMOVED***
      ***REMOVED***

        int percentage =(int) ((double) allRead / allReceive*100);
        System.out.println(grade + "|"+allRead+"|"+allReceive);
        return (double) percentage / 100 ;
  ***REMOVED***


}
