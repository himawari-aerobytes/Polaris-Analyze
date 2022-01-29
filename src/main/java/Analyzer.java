import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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
        //解析期間を指定．
        final Calendar START_DATE = parseCalendar("2021-10-1 00:00:00");
        final Calendar END_DATE = parseCalendar("2021-12-31 23:59:59");
        final ObjectMapper mapper = new ObjectMapper();
        final String PolarisJSON = CSVDeserializer.ReadCSV(args[0]);

       final String cyan   = "\u001b[00;46m";
       final String end    = "\u001b[00m";
       final String red    = "\u001b[00;41m";

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
        List<Map<String,Object>> messages_csv = new ArrayList<>();

        for (Map<String, Object> msg : polarisJSON) {
            final Calendar createdDate = parseCalendar((String) msg.get("登録日時"));
            final List<Map<String, String>> readers = mapper.readValue((String) msg.get("既読状態"), new TypeReference<List<Map<String, String>>>(){});
            final String messageType = (String) msg.get("形態");
            final String sender = (String) msg.get("sender");

            int read = 0;
            int receive = 0;

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
            System.out.println( red + msg.get("登録日時")+" "+msg.get("形態")+"-"+ msg.get("sender") + end);
            System.out.println(msg.get("ヘッドライン"));

            wadaLab.searchMember(sender).ifPresent(x -> {
                x.getCounter().addSend();
            });

            /**
             * メッセージ既読のJSONデータ処理
             */
            for (Map<String, String> reader : readers) {
                final String readCondition = reader.get("status_name");

                //自分自身を既読を除外
                if (sender.contains(reader.get("user_notes"))) {
                    continue;
                }

                receive++;
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
                                    System.out.println("既読: "+x.getName());
                                    x.getCounter().addRead(messageType);
                                    x.getCounter().addReceive(messageType);

                                });
                        read +=
                        members.stream()
                                .filter(x -> reader.get("t_device_mng_id").equals(x.getNumber()))
                                .count();
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

           msg.put("read_count",String.valueOf(read));
           msg.put("receive_count",String.valueOf(receive));

           messages_csv.add(msg);

        }
        List<String> csv = new ArrayList<>();
        final String[] headers = {
                "登録日時",
                "形態",
                "ヘッドライン",
                "APNs:送信数",
                "APNs:成功",
                "APNs:失敗",
                "GCM:送信数",
                "GCM:成功",
                "GCM:失敗",
                "sender",
                "user",
                "root_push_id",
                "read_count",
                "receive_count",
                "既読状態"
        };
        csv.add(String.join(",",headers));
        String keep ="";

        for(Map<String,Object> message: messages_csv){
            String line ="";
            for(String header : headers){
                if(header=="既読状態"||header=="ヘッドライン"||header=="登録日時"){
                    keep= (String) message.get(header);
                    if(header=="既読状態"){
                        keep= "\""+ message.get(header)+"\"";
                    }

                    line+="\""+keep+"\"";
                }else{
                    line+=message.get(header);
                }

                if(header!=headers[headers.length-1]){
                    line+=",";
                }

            }
            csv.add(line);
        }

        final String writeData =  String.join("\n",csv);
        try{
            File file = new File("test.csv");
            FileWriter filewriter = new FileWriter(file);

            filewriter.write(writeData);

            filewriter.close();
        }catch(IOException e){
            System.out.println(e);
        }






        System.out.println(wadaLab.calcGradePercentage("B3"));
        System.out.println(wadaLab.calcGradePercentage("B4"));
        System.out.println(wadaLab.calcGradePercentage("M2"));
        System.out.println(wadaLab.calcAllPercentage());

    }



}
