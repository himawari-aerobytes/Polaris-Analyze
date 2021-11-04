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
        } catch (ParseException e) {
            e.printStackTrace();
            exit(-1);
        }

        return cal;

    }

    public static void main(String args[]) throws IOException {
        final Calendar START_DATE = parseCalendar("2021-10-15 00:00:00");
        final Calendar END_DATE = parseCalendar("2021-10-31 00:00:00");

        File file = new File("source.json");


        List<Member> members = new ArrayList<>();
        members.add(new Member("小堺", "135", "M2"));
        members.add(new Member("中井", "134", "M2"));
        members.add(new Member("NP中島", "136", "B4"));
        members.add(new Member("RB藤村", "137", "B4"));
        members.add(new Member("GP五十嵐", "138", "B4"));
        members.add(new Member("RB菅沼", "139", "B4"));
        members.add(new Member("BD渡邉", "140", "B4"));
        members.add(new Member("RB梅澤", "141", "B4"));
        members.add(new Member("GP木村" , "142", "B4"));
        members.add(new Member("ED林", "143", "B4"));
        members.add(new Member("GP鈴木", "144", "B4"));
        members.add(new Member("河北", "148", "B3"));
        members.add(new Member("小熊", "147", "B3"));
        members.add(new Member("迫田", "150", "B3"));
        members.add(new Member("高瀬", "151", "B3"));
        members.add(new Member("中島啓", "152", "B3"));
        members.add(new Member("夏目", "153", "B3"));
        members.add(new Member("長谷川", "154", "B3"));
        members.add(new Member("三田", "155", "B3"));

        Lab wadaLab = new Lab(members);

        ObjectMapper mapper = new ObjectMapper();

        List<Map<String, Object>> polarisJSON = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {
        });

        /**
         * 全てのJSONデータ処理
         */
        for (Map<String, Object> msg : polarisJSON) {
            final Calendar createdDate = parseCalendar((String) msg.get("登録日時"));
            final List<Map<String, String>> readers = mapper.readValue((String) msg.get("既読状態"), new TypeReference<List<Map<String, Object>>>() {
            });
            final String messageType = (String) msg.get("形態");


            /**
             * 送信日時の絞り込み
             * a.compareTo(b)
             * -1 : a < b
             * 0 : a == b
             * 1 : a > b
             */
            if (!((createdDate.compareTo(START_DATE) >= 0) && (createdDate.compareTo(END_DATE)<=0 ))) {
                System.out.println(createdDate.getTime() +" is Skipped");
                continue;
            }

            System.out.println(msg.get("ヘッドライン"));

            /**
             * メッセージ既読のJSONデータ処理
             */
            for (Map<String, String> reader : readers) {
                final String sender = (String) msg.get("sender");
                final String readCondition = reader.get("status_name");



                //自分自身を既読を除外
                if (sender.contains(reader.get("user_notes"))) {
                    continue;
                }

                if (readCondition.equals("既読")) {
                    System.out.println("既読 -- " + reader.get("user_notes"));
                    final Calendar readDate = parseCalendar(reader.get("response_result_created"));

                    /**
                     * 既読日時の絞り込み
                     * a.compareTo(b)
                     * -1 : a < b
                     * 0 : a == b
                     * 1 : a > b
                     */
                    if (!(readDate.compareTo(START_DATE) >= 0 ) && ( readDate.compareTo(END_DATE) <= 0 )) {
                        continue;
                    }

                    members.stream()
                            .filter(x -> reader.get("t_device_mng_id").equals(x.getNumber()))
                            .forEach(x -> {
                                x.getCounter().addRead(messageType);
                                x.getCounter().addReceive(messageType);
                            });
                }

                if (readCondition.equals("未読")) {
                    System.out.println("未読 -- "+ reader.get("user_notes"));
                    members.stream()
                            .filter(x -> reader.get("t_device_mng_id").equals(x.getNumber()) )
                            .forEach(x ->
                                x.getCounter().addReceive(messageType)
                            );
                }
            }
        }

        System.out.println(wadaLab.calcGradePercentage("B3"));
        System.out.println(wadaLab.calcGradePercentage("B4"));
        System.out.println(wadaLab.calcGradePercentage("M2"));


    }

}
