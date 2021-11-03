import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Counter {
    private final List<String> CATEGORIES = new ArrayList<>(Arrays.asList("ゼミ入退室連絡", "ゼミ緊急", "GR連絡", "一般", "資料作成連絡", "GR緊急", "共通連絡", "共通緊急"));
    private int***REMOVED******REMOVED*** receive = new int***REMOVED***CATEGORIES.size()***REMOVED***;
    private int***REMOVED******REMOVED*** read = new int***REMOVED***CATEGORIES.size()***REMOVED***;
    private int index = 0;
    public final int MaxIndex = CATEGORIES.size();

    public int classNameToIndex(String className){
        return CATEGORIES.indexOf(className);
  ***REMOVED***

    public int getMaxIndex(){
        return this.MaxIndex;
  ***REMOVED***

    public int getAllReceived(){
        int allReceive = 0;

        for(int receive : this.receive){
            allReceive+=receive;
      ***REMOVED***

        return allReceive;

  ***REMOVED***

    public int getAllRead(){
        int allRead = 0;
        for(int read : this.read){
            allRead += read;
      ***REMOVED***
        return allRead;

  ***REMOVED***
    public void Counter(){
        this.index = 0;
        this.receive = new int***REMOVED***CATEGORIES.size()***REMOVED***;
        this.read = new int***REMOVED***CATEGORIES.size()***REMOVED***;
  ***REMOVED***

    public int getIndex(){
        return this.index;
  ***REMOVED***

    public void setIndex(int index){
        if(index < 0 && CATEGORIES.size() <= index ){
            throw new ArrayIndexOutOfBoundsException("indexがcategoriesの配列の範囲を指定していません．");
      ***REMOVED***
        this.index = index;
  ***REMOVED***

    public void setIndex(String className){
        this.index = CATEGORIES.indexOf(className);
  ***REMOVED***

    public void addReceive(){
        this.receive***REMOVED***this.index***REMOVED***++;
  ***REMOVED***

    public void addRead(){
        this.read***REMOVED***this.index***REMOVED***++;
  ***REMOVED***

    public boolean canCalcPercentage(){
        if(this.receive***REMOVED***this.index***REMOVED*** != 0){
            return true;
      ***REMOVED***
        return false;
  ***REMOVED***
    private boolean canCalcPercentage(int index){
        if(this.receive***REMOVED***index***REMOVED*** != 0){
            return true;
      ***REMOVED***
        return false;
  ***REMOVED***

    public double calcPercentage(){
        if(!canCalcPercentage()){
            throw new ArithmeticException(CATEGORIES.get(this.index)+"の受信は１つもありませんでした");
      ***REMOVED***
        return (double) this.read***REMOVED***this.index***REMOVED*** / this.receive***REMOVED***this.index***REMOVED***;
  ***REMOVED***

    public double calcAllPercentage(){
        int i;
        int receive = 0;
        int read = 0;

        for(i=0;i<CATEGORIES.size();i++){
            receive += this.receive***REMOVED***i***REMOVED***;
            read += this.read***REMOVED***i***REMOVED***;
      ***REMOVED***


        if(0==receive){
            return -1;
      ***REMOVED***

        return (double) read / receive;
  ***REMOVED***




}
