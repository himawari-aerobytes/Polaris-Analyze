import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 研究室全メンバー
 * 学年ごとの統計を出します
 */
public class Lab {
    private Map<String,Member> members = new HashMap<String,Member>();

    /**
     *
     * @param members 研究室全メンバーの配列
     */
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
        int percentage =(int) ((double) allRead / allReceive*10000);
        System.out.println(grade + "|"+allRead+"|"+allReceive);
        return (double) percentage / 10000 *100;
    }


}
