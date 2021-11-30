package analyzer;

import java.util.*;

/**
 * 研究室全メンバー
 * 学年ごとの統計を出します
 */
public class Lab {
    private List<Member> members = new ArrayList<>();
    private Map<String,Integer> allRead = new HashMap<>();
    private Map<String,Integer> allReceive = new HashMap<>();

    /**
     *
     * @param members 研究室全メンバーの配列
     */
    public Lab(List<Member> members){
        for(Member member: members){
            this.members.add(member);
        }
    }
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Map<String, Integer> getAllRead() {
        return allRead;
    }

    public void setAllRead(Map<String, Integer> allRead) {
        this.allRead = allRead;
    }

    public Map<String, Integer> getAllReceive() {
        return allReceive;
    }

    public void setAllReceive(Map<String, Integer> allReceive) {
        this.allReceive = allReceive;
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



    public Double calcGradePercentage(String grade){
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

        this.allRead.put(grade,allRead);
        this.allReceive.put(grade,allReceive);

        if(0==allReceive){
            return null;

        }

        final double value = (double)percentage / 10000 *100;
        System.out.println(value);

        return Double.valueOf(value);

    }


}
