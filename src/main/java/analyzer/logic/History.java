package analyzer.logic;

import analyzer.io.CSVDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class History {
    final private ObjectMapper mapper = new ObjectMapper();
    private List<Message> History;
    private List<Member> members= new ArrayList<>();

    public History(){}

    public History(String filename) {
        FileInputStream MembersFile = null;

        try{
            this.History = CSVDeserializer.ReadCSV(filename);
            MembersFile = new FileInputStream("Members.json");
            this.members = mapper.readValue(MembersFile, new TypeReference<List<Member>>() {
            });
        }catch (IOException e){
           e.printStackTrace();
           exit(-1);
        }


    }

    public List<Message> getHistory() {
        return History;
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
