

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
public class CSVProcessor {
    public static String ReadCSV(String _path) throws IOException{
        final ObjectMapper objectMapper = new ObjectMapper();
        final CsvMapper mapper = new CsvMapper();
        // ヘッダあり
        CsvSchema schema = mapper.schemaFor(Message.class).withHeader();
        File file = new File(_path);

        FileInputStream input = new FileInputStream(file);
        InputStreamReader stream = new InputStreamReader(input,"Shift-JIS");

        try (BufferedReader br =  new BufferedReader(stream);) {

            MappingIterator<Message> it = mapper.readerFor(Message.class).with(schema).readValues(br);

            // CSVファイルを全行まとめて読み込む場合
            List<Message> messageList = it.readAll();

            return objectMapper.writeValueAsString(messageList);
        }catch (IOException e){
            throw new ExportException("原因不明のエラーです");
        }


    }

}


