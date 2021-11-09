import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.*;
import java.rmi.server.ExportException;
import java.util.List;

/**
 * CSVをJSONに変換します
 */
public class CSVDeserializer {
    public static String ReadCSV(String path) throws IOException{
        final ObjectMapper objectMapper = new ObjectMapper();
        final CsvMapper mapper = new CsvMapper();

        CsvSchema schema = mapper.schemaFor(Message.class).withHeader();

        FileInputStream input = new FileInputStream(path);
        //shift-jis to utf-8
        InputStreamReader stream = new InputStreamReader(input,"Shift-JIS");


        try (BufferedReader br =  new BufferedReader(stream)) {

            MappingIterator<Message> it = mapper.readerFor(Message.class).with(schema).readValues(br);
            List<Message> messageList = it.readAll();

            return objectMapper.writeValueAsString(messageList);

        }catch (IOException e){
            throw new ExportException("読み込み時にエラーが発生しました\n"+e);
        }


    }

}


