package analyzer.logic;

import analyzer.propaties.CUI;

import java.util.*;

public class Counter {
    private final String[] CATEGORIES = {"ゼミ入退室連絡", "ゼミ緊急", "GR連絡", "一般", "資料作成連絡", "GR緊急", "共通連絡", "共通緊急","その他"};
    private Map<String,Integer> receive = new HashMap<String,Integer>();
    private Map<String,Integer> read = new HashMap<String,Integer>();
    private int send = 0;

    private static void exceptionMessageType(String messageType){
        CUI.println("WARN:"+messageType+"は既定のカテゴリではありません","color_red");
    }

    /**
     * @constructor
     * カテゴリのカウントを0で初期化します
     */
    public Counter(){
        this.Reset();

    }

    public void Reset(){
        for(String CATEGORY : CATEGORIES){
            this.receive.put(CATEGORY,0);
        }

        for(String CATEGORY : CATEGORIES){
            this.read.put(CATEGORY,0);
        }
        this.send = 0;
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
        try{
            int receive = this.receive.get(messageType);
            this.receive.replace(messageType,++receive);

        }catch (NullPointerException e){
            int receive = this.receive.get("その他");
            this.receive.replace("その他",++receive);
            exceptionMessageType(messageType);

        }


    }

    /**
     * 既読数を追加します
     * @param messageType
     */
    public void addRead(String messageType){
        try{
            int read = this.read.get(messageType);
            this.read.replace(messageType,++read);
        }catch (NullPointerException e){
            int read = this.read.get("その他");
            this.read.replace("その他",++read);
           exceptionMessageType(messageType);
        }

    }

    public void addSend(){
        this.send++;
    }

    public int getSend(){
        return this.send;
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

    public static class ReadCondition {
        private String t_device_id;
        private String status;
        private String status_name;
        private String device_name;
        private String user_notes;

        public void ReadCondition(String t_device_id,String status,String status_name,String device_name,String user_notes){
            this.t_device_id = t_device_id;
            this.status = status;
            this.status_name = status_name;
            this.device_name = device_name;
            this.user_notes = user_notes;
        }

        public String getT_device_id() {
            return t_device_id;
        }

        public String getStatus() {
            return status;
        }

        public String getStatus_name() {
            return status_name;
        }

        public String getDevice_name() {
            return device_name;
        }

        public String getUser_notes() {
            return user_notes;
        }

        public void setT_device_id(String t_device_id) {
            this.t_device_id = t_device_id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public void setUser_notes(String user_notes) {
            this.user_notes = user_notes;
        }
    }
}
