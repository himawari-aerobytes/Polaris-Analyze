package analyzer.analyze;

import analyzer.logic.*;
import analyzer.propaties.CUI;
import analyzer.propaties.Cal;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Analyzer {

    public static List<Result> analyze(LocalDateTime startDate, LocalDateTime endDate, History history) throws IOException {
        final List<Member> members= history.getMembers();
        final List list = new ArrayList();
        Map<String,Integer> allRead=new HashMap<>();
        Map<String,Integer> allReceive= new HashMap<>();

        /**
         * 全てのJSONデータ処理
         */

        for (Message msg: history.getHistory()) {
            final LocalDateTime createdDate = Cal.toLocalDateTime(msg.getCreatedDate());
            final List<ReadCondition> readers = msg.getReadCondition();
            final String messageType = msg.getType();
            final String sender = msg.getSender();

            if (!((createdDate.compareTo(startDate)>=0) && (createdDate.compareTo(endDate)<=0))) {
                continue;
            }

            CUI.println(msg.getCreatedDate()+" "+msg.getType()+" "+msg.getSender(),"bg_red");

            //senderカウント
            members.stream()
                    .filter(x -> sender.contains(x.getName()))
                    .findFirst()
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

                if(!isMemberExists(members,reader.getT_device_mng_id())){
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
            final Double percentage = calcGradePercentage(g,members,allRead,allReceive);
            final String start = startDate.getMonthValue()+"月" + startDate.getDayOfMonth()+"日";
            final String end = endDate.getMonthValue()+"月" + endDate.getDayOfMonth()+"日";
            final String date = start+ " ~ " + end;
            list.add(new Result(date,g,percentage));
        }

        return list;

    }

    private static boolean isMemberExists(List<Member> members, String t_device_mng_id) {
        Optional<Member> member = members.stream()
                .filter(x -> x.getNumber().equals(t_device_mng_id))
                .findFirst();

        if(member.isPresent()){
            return true;
        }
        return false;
    }

    private static Double calcGradePercentage(String grade,List<Member> members,Map<String,Integer> allRead,Map<String,Integer> allReceive){
        int Read = 0;
        int Receive = 0;

        System.out.println("--- 既読率 - "+grade+" ---");

        for (Member member : members) {
            if(grade.equals(member.getGrade())){
                Receive += member.getCounter().getAllReceived();
                Read += member.getCounter().getAllRead();
                System.out.println(member.getName()+"::"+member.getCounter().getAllRead()+"||"+member.getCounter().getAllReceived()+"--"+member.getCounter().getSend());
            }

        }

        int percentage =(int) ((double) Read / Receive*10000);
        System.out.println(grade + "|"+Read+"|"+Receive);

        allRead.put(grade,Read);
        allReceive.put(grade,Receive);

        if(0==Receive){
            return null;
        }

        final double value = (double)percentage / 10000 *100;
        System.out.println(value);

        return Double.valueOf(value);

    }

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



}
