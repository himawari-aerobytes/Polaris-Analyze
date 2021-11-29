package analyzer;

import analyzer.io.CSVDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History {
    final private ObjectMapper mapper = new ObjectMapper();
    private List<Map<String, Object>> History;
    private FileInputStream MembersFile;
    private List<Map<String, String>> MembersJSON;
    private List<Member> members=new ArrayList<>();
    private Lab lab;

    public History(String filename) throws IOException {
        final String PolarisJSON = CSVDeserializer.ReadCSV(filename);
        this.History = mapper.readValue(PolarisJSON, new TypeReference<List<Map<String, Object>>>() {
        });

        this.MembersFile = new FileInputStream("Members.json");
        this.MembersJSON = mapper.readValue(MembersFile, new TypeReference<List<Map<String, String>>>() {
        });


        for(Map<String,String> member : MembersJSON ){
            final String number = member.get("number");
            final String grade = member.get("grade");
            final String name = member.get("name");
            members.add(new Member(name,number,grade));
        }

        lab = new Lab(members);

    }

    public List<Map<String, Object>> getHistory() {
        return History;
    }

    public List<Map<String, String>> getMembersJSON(){
        return MembersJSON;
    }

    public Lab getLab() {
        return lab;
    }
    public List<Member> getMembers(){
        return members;
    }
    public void allCounterReset(){
        for(Member member : members){
            member.getCounter().Reset();
        }

    }
}
