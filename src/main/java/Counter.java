import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Counter {
    private final List<String> CATEGORIES = new ArrayList<>(Arrays.asList("ゼミ入退室連絡", "ゼミ緊急", "GR連絡", "一般", "資料作成連絡", "GR緊急", "共通連絡", "共通緊急"));
    private int[] receive = new int[CATEGORIES.size()];
    private int[] read = new int[CATEGORIES.size()];
    private int index = 0;
    public final int MaxIndex = CATEGORIES.size();

    public int classNameToIndex(String className){
        return CATEGORIES.indexOf(className);
    }

    public int getMaxIndex(){
        return this.MaxIndex;
    }

    public int getAllReceived(){
        int allReceive = 0;

        for(int receive : this.receive){
            allReceive+=receive;
        }

        return allReceive;

    }

    public int getAllRead(){
        int allRead = 0;
        for(int read : this.read){
            allRead += read;
        }
        return allRead;

    }
    public void Counter(){
        this.index = 0;
        this.receive = new int[CATEGORIES.size()];
        this.read = new int[CATEGORIES.size()];
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int index){
        if(index < 0 && CATEGORIES.size() <= index ){
            throw new ArrayIndexOutOfBoundsException("indexがcategoriesの配列の範囲を指定していません．");
        }
        this.index = index;
    }

    public void setIndex(String className){
        this.index = CATEGORIES.indexOf(className);
    }

    public void addReceive(){
        this.receive[this.index]++;
    }

    public void addRead(){
        this.read[this.index]++;
    }

    public boolean canCalcPercentage(){
        if(this.receive[this.index] != 0){
            return true;
        }
        return false;
    }
    private boolean canCalcPercentage(int index){
        if(this.receive[index] != 0){
            return true;
        }
        return false;
    }

    public double calcPercentage(){
        if(!canCalcPercentage()){
            throw new ArithmeticException(CATEGORIES.get(this.index)+"の受信は１つもありませんでした");
        }
        return (double) this.read[this.index] / this.receive[this.index];
    }

    public double calcAllPercentage(){
        int i;
        int receive = 0;
        int read = 0;

        for(i=0;i<CATEGORIES.size();i++){
            receive += this.receive[i];
            read += this.read[i];
        }


        if(0==receive){
            return -1;
        }

        return (double) read / receive;
    }




}
