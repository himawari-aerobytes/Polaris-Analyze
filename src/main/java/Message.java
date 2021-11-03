import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

public class Message {
    private String Header;
    private Member sender;
    private Date createdDate;
    private ReadCondition[] readers;
    private String messageType;

    public void Message(String Header, Member sender, Date createdDate, String readers, String messageType) throws IOException {

        this.Header = Header;
        this.sender = sender;
        this.createdDate = createdDate;
        this.messageType = messageType;

        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(readers,ReadCondition.class);




    }
}
