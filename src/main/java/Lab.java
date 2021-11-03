import java.util.ArrayList;
import java.util.List;

public class Lab {
    private List <Member> members;
    public Lab(List<Member> members){
        this.members = new ArrayList<Member>(members);
    }



    public double calcGradePercentage(String grade){
        int allRead = 0;
        int allReceive = 0;
        int i;

        for(Member member: members){
            for(i=0;i<member.getCounter().getMaxIndex();i++){
                if(grade.equals(member.getGrade())){
                    allReceive += member.getCounter().getAllReceived();
                    allRead += member.getCounter().getAllRead();
                }
                member.getCounter().setIndex(i+1);
            }
            member.getCounter().setIndex(0);
        }

        System.out.println(grade +"|" +allReceive+"|" + allRead);

        return (double) allRead / allReceive;

    }


}
