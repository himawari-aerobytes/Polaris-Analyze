package analyzer;

import analyzer.io.CSVDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

public class History {
    final private ObjectMapper mapper = new ObjectMapper();
    private List<Message> History;
    private FileInputStream MembersFile;
    private List<Map<String, String>> MembersJSON;
    private List<Member> members= new ArrayList<>();
    private Lab lab;

    public History(){}

    public History(String filename) {

        try{
            this.History = CSVDeserializer.ReadCSV(filename);
            this.MembersFile = new FileInputStream("Members.json");
            this.members = mapper.readValue(MembersFile, new TypeReference<List<Member>>() {
            });
        }catch (IOException e){
           e.printStackTrace();
           exit(-1);
        }

        lab = new Lab(this.members);
        System.out.println(lab.getMembers().get(0));

    }

    public List<Message> getHistory() {
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
