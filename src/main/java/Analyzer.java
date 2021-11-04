import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.exit;

public class Analyzer {
    private static Calendar parseCalendar(String createdDate) {
        Calendar cal = Calendar.getInstance();
        final SimpleDateFormat strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cal.setTime(strDate.parse(createdDate));
      ***REMOVED*** catch (ParseException e) {
            e.printStackTrace();
            exit(-1);
      ***REMOVED***

        return cal;

  ***REMOVED***

    public static void main(String args***REMOVED******REMOVED***) throws IOException {
        final Calendar START_DATE = parseCalendar("2021-10-1 00:00:00");
        final Calendar END_DATE = parseCalendar("2021-10-31 23:59:59");
        final String PolarisJSON = CSVProcessor.ReadCSV("source.csv");
        //final File PolarisJSON = new File("source.json");


        List<Member> TMPmembers = new ArrayList<>();

        TMPmembers.add(new Member("小堺", "135", "M2"));
        TMPmembers.add(new Member("中井", "134", "M2"));
        TMPmembers.add(new Member("NP中島", "136", "B4"));
        TMPmembers.add(new Member("RB藤村", "137", "B4"));
        TMPmembers.add(new Member("GP五十嵐", "138", "B4"));
        TMPmembers.add(new Member("RB菅沼", "139", "B4"));
        TMPmembers.add(new Member("BD渡邉", "140", "B4"));
        TMPmembers.add(new Member("RB梅澤", "141", "B4"));
        TMPmembers.add(new Member("GP木村", "142", "B4"));
        TMPmembers.add(new Member("ED林", "143", "B4"));
        TMPmembers.add(new Member("GP鈴木", "144", "B4"));
        TMPmembers.add(new Member("河北", "148", "B3"));
        TMPmembers.add(new Member("小原", "149", "B3"));
        TMPmembers.add(new Member("小熊", "147", "B3"));
        TMPmembers.add(new Member("迫田", "150", "B3"));
        TMPmembers.add(new Member("高瀬", "151", "B3"));
        TMPmembers.add(new Member("中島啓", "152", "B3"));
        TMPmembers.add(new Member("夏目", "153", "B3"));
        TMPmembers.add(new Member("長谷川", "154", "B3"));
        TMPmembers.add(new Member("三田", "155", "B3"));


        final Lab wadaLab = new Lab(TMPmembers.toArray(new Member***REMOVED***TMPmembers.size()***REMOVED***));
        final ObjectMapper mapper = new ObjectMapper();



        List<Map<String, Object>> polarisJSON = mapper.readValue(PolarisJSON, new TypeReference<List<Map<String, Object>>>() {
      ***REMOVED***);

        /**
         * 全てのJSONデータ処理
         */
        for (Map<String, Object> msg : polarisJSON) {
            final Calendar createdDate = parseCalendar((String) msg.get("登録日時"));
            final List<Map<String, String>> readers = mapper.readValue((String) msg.get("既読状態"), new TypeReference<List<Map<String, Object>>>(){});
            final String messageType = (String) msg.get("形態");


            /**
             * 送信日時の絞り込み
             * a.compareTo(b)
             * -1 : a < b
             * 0 : a == b
             * 1 : a > b
             */
            if (!((createdDate.compareTo(START_DATE) >= 0) && (createdDate.compareTo(END_DATE) <= 0))) {
                continue;
          ***REMOVED***

            /**
             * メッセージ既読のJSONデータ処理
             */
            for (Map<String, String> reader : readers) {
                final String sender = (String) msg.get("sender");
                final String readCondition = reader.get("status_name");

                //自分自身を既読を除外
                if (sender.contains(reader.get("user_notes"))) {
                    continue;
              ***REMOVED***

                if (readCondition.equals("既読")) {

                    final Calendar readDate = parseCalendar(reader.get("response_result_created"));

                    /**
                     * 既読日時の絞り込み
                     * a.compareTo(b)
                     * -1 : a < b
                     * 0 : a == b
                     * 1 : a > b
                     */
                    if (!((readDate.compareTo(START_DATE) >= 0) && (readDate.compareTo(END_DATE) <= 0))) {
                        continue;
                  ***REMOVED***

                    TMPmembers.stream()
                            .filter(x -> reader.get("t_device_mng_id").equals(x.getNumber()))
                            .forEach(x -> {
                                x.getCounter().addRead(messageType);
                                x.getCounter().addReceive(messageType);

                          ***REMOVED***);
              ***REMOVED***

                if (readCondition.equals("未読")) {

                    TMPmembers.stream()
                            .filter(x -> reader.get("t_device_mng_id").equals(x.getNumber()))
                            .forEach(x -> {
                                x.getCounter().addReceive(messageType);

                          ***REMOVED***);
              ***REMOVED***
          ***REMOVED***
      ***REMOVED***

        System.out.println(wadaLab.calcGradePercentage("B3"));
        System.out.println(wadaLab.calcGradePercentage("B4"));
        System.out.println(wadaLab.calcGradePercentage("M2"));


  ***REMOVED***

}
