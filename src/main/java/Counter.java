import java.lang.reflect.Array;
import java.util.*;

public class Counter {
    private final String[] CATEGORIES = {"ゼミ入退室連絡", "ゼミ緊急", "GR連絡", "一般", "資料作成連絡", "GR緊急", "共通連絡", "共通緊急"};
    private Map<String,Integer> receive = new HashMap<String,Integer>();
    private Map<String,Integer> read = new HashMap<String,Integer>();

    /**
     * @constructor
     * カテゴリのカウントを0で初期化します
     */
    public Counter(){
        for(String CATEGORY : CATEGORIES){
            this.receive.put(CATEGORY,0);
        }

        for(String CATEGORY : CATEGORIES){
            this.read.put(CATEGORY,0);
        }
    }

    /**
     * 全カテゴリ合計の受信数です．
     * @return 全受信数
     */
    public int getAllReceived(){
        int allReceived=0;
        for(String CATEGORY : CATEGORIES){
            allReceived += this.receive.get(CATEGORY);
        }

        return allReceived;
    }

    /**
     * 全カテゴリ合計の既読数です．
     * @return 全既読数
     */
    public int getAllRead(){
        int allRead=0;
        for(String CATEGORY : CATEGORIES){
            allRead += this.read.get(CATEGORY);
        }

        return allRead;
    }

    /**
     * 受信数を追加します
     * @param messageType
     */
    public void addReceive(String messageType)
    {
        int receive = this.receive.get(messageType);
        this.receive.replace(messageType,++receive);

    }

    /**
     * 既読数を追加します
     * @param messageType
     */
    public void addRead(String messageType){
        int read = this.receive.get(messageType);
        this.read.replace(messageType,++read);
    }

    /**
     *
     * @param messageType
     * @return 百分率が計算可能かどうか
     */
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
