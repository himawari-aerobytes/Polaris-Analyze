package analyzer.analyze;

import analyzer.History;
import analyzer.Lab;
import analyzer.Member;
import analyzer.propaties.CUIColor;
import analyzer.propaties.Cal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;



public class Analyzer {

    public static List<Result> analyze(LocalDateTime startDate, LocalDateTime endDate, History history) throws IOException {
        //解析期間を指定．
        final ObjectMapper mapper = new ObjectMapper();
        final Lab lab = history.getLab();
        final List<Member> members= history.getMembers();

        final List list = new ArrayList();




        /**
         * 全てのJSONデータ処理
         */

        for (Map<String, Object> msg : history.getHistory()) {
            final LocalDateTime createdDate = Cal.toLocalDateTime((String) msg.get("登録日時"));
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
            if (!((createdDate.compareTo(startDate)>=0) && (createdDate.compareTo(endDate)<=0))) {
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

                    final LocalDateTime readDate = Cal.toLocalDateTime(reader.get("response_result_created"));

                    /**
                     * 既読日時の絞り込み
                     * a.compareTo(b)
                     * -1 : a < b
                     * 0 : a == b
                     * 1 : a > b
                     */
                    if (!((readDate.compareTo(startDate) >= 0) && (readDate.compareTo(endDate) <= 0))) {
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

        List<String> grade = new ArrayList<>();
        String[] arrayGrade = {"B3","B4","M2"};
        Collections.addAll(grade,arrayGrade);

        for(String g:grade){
            //順序依存
            final Double percentage = lab.calcGradePercentage(g);
            final String start = startDate.getMonthValue()+"月" + startDate.getDayOfMonth()+"日";
            final String end = endDate.getMonthValue()+"月" + endDate.getDayOfMonth()+"日";
            final String date = start+ " ~ " + end;

            list.add(new Result(date,g,percentage));

        }

        return list;

    }
}
