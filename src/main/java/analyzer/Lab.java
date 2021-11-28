package analyzer;

import java.util.*;

/**
 * 研究室全メンバー
 * 学年ごとの統計を出します
 */
public class Lab {

    private List<Member> members = new ArrayList<>();

    /**
     *
     * @param members 研究室全メンバーの配列
     */
    public Lab(List<Member> members){
        for(Member member: members){
            this.members.add(member);
        }
    }

    public Optional<Member> searchMember(String name){
        return this.members.stream()
                .filter(x -> name.contains(x.getName())).findFirst();
    }

    public void memberAdd(Member member){
        this.members.add(member);
    }

    public void memberAdd(String name,String number,String grade){
        this.members.add(new Member(name,number,grade));
    }



    public double calcGradePercentage(String grade){
        int allRead = 0;
        int allReceive = 0;

        System.out.println("--- 既読率 - "+grade+" ---");

        for (Member member : members) {
            if(grade.equals(member.getGrade())){
                allReceive += member.getCounter().getAllReceived();
                allRead += member.getCounter().getAllRead();
                System.out.println(member.getName()+"::"+member.getCounter().getAllRead()+"||"+member.getCounter().getAllReceived()+"--"+member.getCounter().getSend());
            }

        }

        int percentage =(int) ((double) allRead / allReceive*10000);
        System.out.println(grade + "|"+allRead+"|"+allReceive);
        return (double) percentage / 10000 *100;
    }


}
