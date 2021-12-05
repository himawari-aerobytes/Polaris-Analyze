package analyzer.analyze;

import analyzer.analyze.logic.*;
import analyzer.propaties.CUI;
import analyzer.propaties.Cal;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Analyzer {

    private static void addResponse(List<Member> members, String status, String messageType, ReadCondition reader, Message message){
        members.stream()
                .filter(x -> reader.getT_device_mng_id().equals(x.getNumber()))
                .forEach(x -> {
                    x.getCounter().addReceive(messageType);
                    if(status.equals("既読")){
                        message.addCalcReadTime("既読",reader);
                        message.addResponse_body("既読: "+x.getName()+" "+reader.getResponse_result_created());
                        System.out.println("既読: "+x.getName()+" "+reader.getResponse_result_created());
                        x.getCounter().addRead(messageType);
                    }else{
                        message.addCalcReadTime("未読",reader);
                        message.addResponse_body("未読: "+x.getName());
                        System.out.println("未読: "+x.getName());
                    }
                });

    }

    private static Double calcReadRatio(int read,int receive){
        final double readRatio = (double) read/receive;
        System.out.println(read+"/"+ receive);
        System.out.println(readRatio);
        return readRatio;

    }


    public static List<Result> analyze(LocalDateTime startDate, LocalDateTime endDate, History history) throws IOException {
        //解析期間を指定．
        final Lab lab = history.getLab();
        final List<Member> members= history.getMembers();
        final List list = new ArrayList();

        /**
         * 全てのJSONデータ処理
         */

        for (Message msg: history.getHistory()) {
            final LocalDateTime createdDate = Cal.toLocalDateTime(msg.getCreatedDate());
            final List<ReadCondition> readers = msg.getReadCondition();
            final String messageType = msg.getType();
            final String sender = msg.getSender();

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

            CUI.println(msg.getCreatedDate()+" "+msg.getType()+" "+msg.getSender(),"bg_red");

            lab.searchMember(sender)
                    .ifPresent(x -> x.getCounter().addSend());

            /**
             * メッセージ既読のJSONデータ処理
             */
            for (ReadCondition reader : readers) {
                final String readCondition = reader.getStatus_name();
                //自分自身を既読を除外
                if (sender.contains(reader.getUser_notes())) {
                    continue;
                }

                if(!lab.isExits(reader.getT_device_mng_id())){
                    continue;
                }

                boolean isMatchMngNumber = false;

                for(Member member : members){
                    if(member.getNumber().equals(reader.getT_device_mng_id())){
                        isMatchMngNumber = true;
                        break;
                    }
                }

                if(!isMatchMngNumber){
                    continue;
                }


                if (readCondition.equals("既読")) {
                    final LocalDateTime readDate = Cal.toLocalDateTime(reader.getResponse_result_created());
                    /**
                     * 既読日時の絞り込み
                     * a.compareTo(b)
                     * -1 : a < b
                     * 0 : a == b
                     * 1 : a > b
                     */
                    if (!((readDate.compareTo(startDate) >= 0) && (readDate.compareTo(endDate) <= 0))) {
                        addResponse(members,"未読",messageType,reader,msg);

                    }else{
                        addResponse(members,"既読",messageType,reader,msg);
                    }
                }else if (readCondition.equals("未読")) {
                    addResponse(members,"未読",messageType,reader,msg);
                }else{
                    continue;
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
