import java.lang.reflect.Array;
import java.util.*;

public class Counter {
    private final String[] CATEGORIES = {"ゼミ入退室連絡", "ゼミ緊急", "GR連絡", "一般", "資料作成連絡", "GR緊急", "共通連絡", "共通緊急"};
    private Map<String,Integer> receive = new HashMap<String,Integer>();
    private Map<String,Integer> read = new HashMap<String,Integer>();

    public String[] getCATEGORIES() {
        return CATEGORIES;
    }

    public Counter(){
        System.out.println("Counter--Constructor");
        for(String CATEGORY : CATEGORIES){
            this.receive.put(CATEGORY,0);
        }

        for(String CATEGORY : CATEGORIES){
            this.read.put(CATEGORY,0);
        }
    }


    public int getAllReceived(){
        int allReceived=0;
        for(String CATEGORY : CATEGORIES){
            allReceived += this.receive.get(CATEGORY);
        }
        return allReceived;
    }

    public int getAllRead(){
        int allRead=0;
        for(String CATEGORY : CATEGORIES){
            allRead += this.read.get(CATEGORY);
        }
        return allRead;
    }

    public void addReceive(String messageType)
    {
        int receive = this.receive.get(messageType);
        this.receive.replace(messageType,++receive);

    }

    public void addRead(String messageType){
        int read = this.receive.get(messageType);
        this.read.replace(messageType,++read);
    }

    public boolean canCalcPercentage(String messageType){

        if(this.receive.get(messageType) != 0){
            return true;
        }
        return false;
    }

    public double calcPercentage(String messageType){
        if(!canCalcPercentage(messageType)){
            throw new ArithmeticException(messageType + "の受信は１つもありませんでした");
        }

        return (double) this.read.get(messageType) / this.receive.get(messageType);
    }

    public double calcAllPercentage(){
        int receive = 0;
        int read = 0;

        for(String CATEGORY : CATEGORIES){
            read += this.read.get(CATEGORY);
            receive += this.receive.get(CATEGORY);
        }

        if(0==receive){
            return -1;
        }

        return (double) read / receive;
    }




}
