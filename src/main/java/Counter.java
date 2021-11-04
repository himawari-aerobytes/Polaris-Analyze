import java.lang.reflect.Array;
import java.util.*;

public class Counter {
    private final String***REMOVED******REMOVED*** CATEGORIES = {"ゼミ入退室連絡", "ゼミ緊急", "GR連絡", "一般", "資料作成連絡", "GR緊急", "共通連絡", "共通緊急"};
    private Map<String,Integer> receive = new HashMap<String,Integer>();
    private Map<String,Integer> read = new HashMap<String,Integer>();

    public String***REMOVED******REMOVED*** getCATEGORIES() {
        return CATEGORIES;
  ***REMOVED***

    public Counter(){
        System.out.println("Counter--Constructor");
        for(String CATEGORY : CATEGORIES){
            this.receive.put(CATEGORY,0);
      ***REMOVED***

        for(String CATEGORY : CATEGORIES){
            this.read.put(CATEGORY,0);
      ***REMOVED***
  ***REMOVED***


    public int getAllReceived(){
        int allReceived=0;
        for(String CATEGORY : CATEGORIES){
            allReceived += this.receive.get(CATEGORY);
      ***REMOVED***
        return allReceived;
  ***REMOVED***

    public int getAllRead(){
        int allRead=0;
        for(String CATEGORY : CATEGORIES){
            allRead += this.read.get(CATEGORY);
      ***REMOVED***
        return allRead;
  ***REMOVED***

    public void addReceive(String messageType)
  ***REMOVED***
        int receive = this.receive.get(messageType);
        this.receive.replace(messageType,++receive);

  ***REMOVED***

    public void addRead(String messageType){
        int read = this.receive.get(messageType);
        this.read.replace(messageType,++read);
  ***REMOVED***

    public boolean canCalcPercentage(String messageType){

        if(this.receive.get(messageType) != 0){
            return true;
      ***REMOVED***
        return false;
  ***REMOVED***

    public double calcPercentage(String messageType){
        if(!canCalcPercentage(messageType)){
            throw new ArithmeticException(messageType + "の受信は１つもありませんでした");
      ***REMOVED***

        return (double) this.read.get(messageType) / this.receive.get(messageType);
  ***REMOVED***

    public double calcAllPercentage(){
        int receive = 0;
        int read = 0;

        for(String CATEGORY : CATEGORIES){
            read += this.read.get(CATEGORY);
            receive += this.receive.get(CATEGORY);
      ***REMOVED***

        if(0==receive){
            return -1;
      ***REMOVED***

        return (double) read / receive;
  ***REMOVED***




}
