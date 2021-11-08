

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.mozilla.universalchardet.UniversalDetector;
import java.io.*;
import java.rmi.server.ExportException;
import java.util.List;

/**
 * CSVをJSONに変換します
 */
public class CSVProcessor {
    public static String ReadCSV(String _path) throws IOException{
        final ObjectMapper objectMapper = new ObjectMapper();
        final CsvMapper mapper = new CsvMapper();

        // ヘッダあり
        CsvSchema schema = mapper.schemaFor(Message.class).withHeader();
        File file = new File(_path);

        FileInputStream input = new FileInputStream(file);

        //shift-jis to utf-8
        InputStreamReader stream = new InputStreamReader(input,"SJIS");

        try (BufferedReader br =  new BufferedReader(stream);) {

            MappingIterator<Message> it = mapper.readerFor(Message.class).with(schema).readValues(br);

            // CSVファイルを全行まとめて読み込む場合
            List<Message> messageList = it.readAll();

            for(Message message : messageList){
                System.out.println(message.getSender());

          ***REMOVED***

            return objectMapper.writeValueAsString(messageList);

      ***REMOVED***catch (IOException e){
            throw new ExportException("読み込み時にエラーが発生しました\n"+e);
      ***REMOVED***


  ***REMOVED***

}


