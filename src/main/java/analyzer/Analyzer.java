package analyzer;

import analyzer.propaties.CUIColor;
import analyzer.propaties.JPCalendar;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.*;



public class Analyzer {

    public static Map<String, AnalyzeResult> analyze(String startDate, String endDate, History history) throws IOException {
        //解析期間を指定．
        final Calendar START_DATE = JPCalendar.parseCalendar(startDate);
        final Calendar END_DATE = JPCalendar.parseCalendar(endDate);
        final ObjectMapper mapper = new ObjectMapper();
        final Lab lab = history.getLab();
        final List<Member> members= history.getMembers();

        /**
         * 全てのJSONデータ処理
         */

        for (Map<String, Object> msg : history.getHistory()) {
            final Calendar createdDate = JPCalendar.parseCalendar((String) msg.get("登録日時"));
            final List<Map<String, String>> readers = mapper.readValue((String) msg.get("既読状態"), new TypeReference<List<Map<String, String>>>(){});
            final String messageType = (String) msg.get("形態");
            final String sender = (String) msg.get("sender");

            /**
             * 送信日時の絞り込み
             * a.compareTo(b)
             * -1 : a < b
             * 0 : a == b
             * 1 : a > b
             */
            if (!((createdDate.compareTo(START_DATE) >= 0) && (createdDate.compareTo(END_DATE) <= 0))) {
                continue;
            }

            CUIColor.println(msg.get("登録日時")+" "+msg.get("形態")+" "+msg.get("sender"),"bg_red");

            lab.searchMember(sender)
                    .ifPresent(x -> x.getCounter().addSend());

            /**
             * メッセージ既読のJSONデータ処理
             */
            for (Map<String, String> reader : readers) {
                final String readCondition = reader.get("status_name");

                //自分自身を既読を除外
                if (sender.contains(reader.get("user_notes"))) {
                    continue;
                }

                if (readCondition.equals("既読")) {

                    final Calendar readDate = JPCalendar.parseCalendar(reader.get("response_result_created"));

                    /**
                     * 既読日時の絞り込み
                     * a.compareTo(b)
                     * -1 : a < b
                     * 0 : a == b
                     * 1 : a > b
                     */
                    if (!((readDate.compareTo(START_DATE) >= 0) && (readDate.compareTo(END_DATE) <= 0))) {
                        members.stream()
                                .filter(x -> x.getNumber().equals(reader.get("t_device_mng_id")))
                                .forEach(x -> {
                                    x.getCounter().addReceive(messageType);
                                    System.out.println("未読: "+x.getName());
                                });
                    }else{
                        members.stream()
                                .filter(x -> reader.get("t_device_mng_id").equals(x.getNumber()))
                                .forEach(x -> {
                                    System.out.println("既読: "+x.getName()+" "+reader.get("response_result_created"));
                                    x.getCounter().addRead(messageType);
                                    x.getCounter().addReceive(messageType);
                                });

                    }

                }

                if (readCondition.equals("未読")) {
                    members.stream()
                            .filter(x -> x.getNumber().equals(reader.get("t_device_mng_id")))
                            .forEach(x -> {
                                x.getCounter().addReceive(messageType);
                                System.out.println("未読: "+x.getName());
                            });
                }
            }
        }

        Map<String,AnalyzeResult> result = new HashMap<>();
        List<String> grade = new ArrayList<>();
        grade.add("B3");
        grade.add("B4");
        grade.add("M2");
        for(String g:grade){
            //順序依存
            final Double percentage = lab.calcGradePercentage(g);
            final int allRead = lab.getAllRead().get(g);
            final int allReceive = lab.getAllReceive().get(g);
            final String start = (START_DATE.get(Calendar.MONTH)+1)+"月" + START_DATE.get(Calendar.DAY_OF_MONTH)+"日";
            final String end = (END_DATE.get(Calendar.MONTH)+1)+"月" + END_DATE.get(Calendar.DAY_OF_MONTH)+"日";
            result.put(g,new AnalyzeResult(start,end,g,allRead,allReceive,percentage));
        }


        //System.out.println(lab.calcGradePercentage("B3"));
        //System.out.println(lab.calcGradePercentage("B4"));
        //System.out.println(lab.calcGradePercentage("M2"));

        return result;

    }
}
