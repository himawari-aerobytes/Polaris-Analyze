package analyzer;

import analyzer.propaties.JPCalendar;

import java.io.IOException;
import java.util.Calendar;

public class Main {
    public static void main(String args[]){
        final String filename = args[0];
        final String StrStartDate = "2021-10-11 00:00:00";
        final String StrEndDate = "2021-12-10 23:59:59";

        final Calendar startDate = JPCalendar.parseCalendar(StrStartDate);
        final Calendar endDate = JPCalendar.parseCalendar(StrEndDate);

        Calendar nextStart = (Calendar) startDate.clone();
        Calendar nextEnd = JPCalendar.getWeekend(startDate,"SATURDAY");




        while(nextStart.before(endDate)){
            nextStart.set(Calendar.HOUR,0);
            nextStart.set(Calendar.MINUTE,0);
            nextStart.set(Calendar.SECOND,0);
            nextStart.set(Calendar.AM_PM,Calendar.AM);

            nextEnd.set(Calendar.HOUR,23);
            nextEnd.set(Calendar.MINUTE,59);
            nextEnd.set(Calendar.SECOND,59);
            nextEnd.set(Calendar.AM_PM,Calendar.PM);

            final String strNextStart= JPCalendar.toJPString(nextStart);
            final String strNextEnd = JPCalendar.toJPString(nextEnd);

            //メイン処理

            try{
                Analyzer.analyze(args[0],strNextStart,strNextEnd);

            }catch (IOException e){
                e.printStackTrace();
            }

            //次回変数セット
            //開始日セット(現在終了日+1)
            nextStart.setTime(nextEnd.getTime());
            nextStart.add(Calendar.DAY_OF_MONTH,1);


            //終了日セット(1週間後)
            nextEnd.add(Calendar.DAY_OF_MONTH,7);


            //debug
            System.out.println(strNextStart);
            System.out.println(strNextEnd);






        }

 /*       try {
            Analyzer.analyze(filename,StrStartDate,StrEndDate);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


}
