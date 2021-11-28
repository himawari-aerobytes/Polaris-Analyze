package analyzer;

import analyzer.io.CSVDeserializer;
import analyzer.propaties.CUIColor;
import analyzer.propaties.JPCalendar;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;



public class Analyzer {


    public static void main(String args[]) throws IOException {
        //解析期間を指定．
        final Calendar START_DATE = JPCalendar.parseCalendar("2021-11-14 00:00:00");
        final Calendar END_DATE = JPCalendar.parseCalendar("2021-11-20 23:59:59");
        final ObjectMapper mapper = new ObjectMapper();
        final String PolarisJSON = CSVDeserializer.ReadCSV(args[0]);


        FileInputStream MembersFile = new FileInputStream("Members.json");

        List<Map<String, String>> MembersJSON = mapper.readValue(MembersFile, new TypeReference<List<Map<String, String>>>() {
        });


        //jsonファイルを直接読むときはコメントアウト解除
        //final File PolarisJSON = new File(args[0]);

        List<Member> members = new ArrayList<>();


        for(Map<String,String> member : MembersJSON ){
            final String number = member.get("number");
            final String grade = member.get("grade");
            final String name = member.get("name");
            members.add(new Member(name,number,grade));
        }

        /**
         * 研究室メンバーの追加
         * 名前･番号(t_device_mng_id)･学年
         * jsonfileの既読状態のt_device_mng_idを参照してidを確認してください．
         * ここの番号付与アルゴリズムは変更になるかもしれません．
         */

        //研究室全体をクラス化．
        final Lab wadaLab = new Lab(members);


        //csvからJSONに変換されたものが入っています．
        List<Map<String, Object>> polarisJSON = mapper.readValue(PolarisJSON, new TypeReference<List<Map<String, Object>>>() {
        });

        /**
         * 全てのJSONデータ処理
         */
        for (Map<String, Object> msg : polarisJSON) {
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

            wadaLab.searchMember(sender)
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

        System.out.println(wadaLab.calcGradePercentage("B3"));
        System.out.println(wadaLab.calcGradePercentage("B4"));
        System.out.println(wadaLab.calcGradePercentage("M2"));

    }
}
