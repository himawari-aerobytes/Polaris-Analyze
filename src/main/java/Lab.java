import java.util.HashMap;
import java.util.Map;

public class Lab {
    private Map<String,Member> members = new HashMap<String,Member>();
    public Lab(Member[] members){
        for(Member member: members){
            this.members.put(member.getNumber(),member);
        }
    }

    public double calcGradePercentage(String grade){
        int allRead = 0;
        int allReceive = 0;

        for (Map.Entry<String, Member> member : members.entrySet()) {

            if(grade.equals(member.getValue().getGrade())){

                allReceive += member.getValue().getCounter().getAllReceived();
                allRead += member.getValue().getCounter().getAllRead();

            }
        }
        int percentage =(int) ((double) allRead / allReceive*100);
        System.out.println(grade + "|"+allRead+"|"+allReceive);
        return (double) percentage / 100 ;
    }


}
