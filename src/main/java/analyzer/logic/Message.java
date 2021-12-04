package analyzer.logic;

import analyzer.propaties.Cal;
import analyzer.propaties.Graph;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//アノテーションでcsvの順序とキー名を対応させています
@JsonPropertyOrder({
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
        "既読状態"
})
public class Message {
    @JsonProperty("登録日時")
    private String createdDate;
    @JsonProperty("形態")
    private String type;
    @JsonProperty("ヘッドライン")
    private String headline;
    @JsonProperty("APNs:送信数")
    private String APNsSend;
    @JsonProperty("APNs:成功")
    private String APNsSuccess;
    @JsonProperty("APNs:失敗")
    private String APNsFail;
    @JsonProperty("GCM:送信数")
    private String GCMSend;
    @JsonProperty("GCM:成功")
    private String GCMSuccess;
    @JsonProperty("GCM:失敗")
    private String GCMFail;
    @JsonProperty("sender")
    private String sender;
    @JsonProperty("user")
    private String user;
    @JsonProperty("root_push_id")
    private String root_push_id;
    @JsonProperty("既読状態")
    private List<ReadCondition> readCondition;
    private Map<String,Object> extensions;
    @JsonIgnore
    private Double readRatio;
    @JsonIgnore
    private List<String> response_body = new ArrayList<>();
    @JsonIgnore
    private String id;
    @JsonIgnore
    private Graph<String,Integer> graph = new Graph<>();
    public Message(){}

    @JsonCreator
    public Message(@JsonProperty("登録日時") String createdDate,
                   @JsonProperty("形態") String type,
                   @JsonProperty("ヘッドライン") String headline,
                   @JsonProperty("APNs:送信数") String APNsSend,
                   @JsonProperty("APNs:成功") String APNsSuccess,
                   @JsonProperty("APNs:失敗") String APNsFail,
                   @JsonProperty("GCM:送信数") String GCMSend,
                   @JsonProperty("GCM:成功") String GCMSuccess,
                   @JsonProperty("GCM:失敗") String GCMFail,
                   @JsonProperty("sender") String sender ,
                   @JsonProperty("user") String user,
                   @JsonProperty("root_push_id") String root_push_id,
                   @JsonProperty("既読状態") String readCondition) throws IOException {
        this.createdDate = createdDate;
        this.type = type;
        this.headline = headline;
        this.APNsSend = APNsSend;
        this.APNsSuccess = APNsSuccess;
        this.APNsFail = APNsFail;
        this.GCMSend = GCMSend;
        this.GCMSuccess = GCMSuccess;
        this.GCMFail = GCMFail;
        this.sender = sender;
        this.createdDate = createdDate;
        this.user = user;
        this.root_push_id = root_push_id;
        final ObjectMapper mapper = new ObjectMapper();
        this.readCondition =  mapper.readValue(readCondition,new TypeReference<List<ReadCondition>>() {});
        this.id = root_push_id+this.createdDate.replace(':','0').replaceAll(" ","").replace('-','0');
        this.graph.addKey("1分以内");
        this.graph.addKey("5分以内");
        this.graph.addKey("10分以内");
        this.graph.addKey("15分以内");
        this.graph.addKey("20分以内");
        this.graph.addKey("25分以内");
        this.graph.addKey("30分以内");
        this.graph.addKey("60分以内");
        this.graph.addKey("60分より長い");

        for(int i=0;i<9;i++){
            this.graph.addValue(0);
        }

    }

    @JsonAnySetter
    public void setExtensions(String key,Object value){
        this.extensions.put(key,value);
    }
    @JsonAnyGetter
    public Map<String,Object> getExtensions(){
        return this.extensions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //以下，自動生成
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setAPNsSend(String APNsSend) {
        this.APNsSend = APNsSend;
    }

    public void setAPNsSuccess(String APNsSuccess) {
        this.APNsSuccess = APNsSuccess;
    }

    public void setAPNsFail(String APNsFail) {
        this.APNsFail = APNsFail;
    }

    public void setGCMSend(String GCMSend) {
        this.GCMSend = GCMSend;
    }

    public void setGCMSuccess(String GCMSuccess) {
        this.GCMSuccess = GCMSuccess;
    }

    public void setGCMFail(String GCMFail) {
        this.GCMFail = GCMFail;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRoot_push_id(String root_push_id) {
        this.root_push_id = root_push_id;
    }



    public String getCreatedDate() {
        return createdDate;
    }

    public String getType() {
        return type;
    }

    public String getHeadline() {
        return headline;
    }

    public String getAPNsSend() {
        return APNsSend;
    }

    public String getAPNsSuccess() {
        return APNsSuccess;
    }

    public String getAPNsFail() {
        return APNsFail;
    }

    public String getGCMSend() {
        return GCMSend;
    }

    public String getGCMSuccess() {
        return GCMSuccess;
    }

    public String getGCMFail() {
        return GCMFail;
    }

    public String getSender() {
        return sender;
    }

    public String getUser() {
        return user;
    }

    public String getRoot_push_id() {
        return root_push_id;
    }

    public List<ReadCondition> getReadCondition() {
        return readCondition;
    }

    public void setReadCondition(List<ReadCondition> readCondition) {
        this.readCondition = readCondition;
    }

    public Double getReadRatio() {
        return readRatio;
    }

    public void setReadRatio(Double readRatio) {
        this.readRatio = readRatio;
    }

    public List<String> getResponse_body() {
        return response_body;
    }

    public void setResponse_body(List<String> response_body) {
        this.response_body = response_body;
    }

    public void addResponse_body(String response_body){
        this.response_body.add(response_body);
    }

    public Graph<String, Integer> getGraph() {
        return graph;
    }

    public void setGraph(Graph<String, Integer> graph) {
        this.graph = graph;
    }

    public void addGraphValue(int val){
        this.graph.addValue(val);
    }

    @Override
    public boolean equals(Object obj){

        if(obj == this){
            return true;
        }
        if(!(obj instanceof Member)){
            return false;
        }
        Message message = (Message) obj;

        return message.getId() == this.getId();
    }


    @Override
    public int hashCode(){
        return Integer.parseInt(this.id);
    }

    public void addCalcReadTime(String condition,ReadCondition reader){
            if(reader.getResponse_result_created().equals("")){
                return;
            }
            final LocalDateTime st = Cal.toLocalDateTime(this.getCreatedDate());
            final LocalDateTime rt = Cal.toLocalDateTime(reader.getResponse_result_created());
            final long minute = ChronoUnit.MINUTES.between(st,rt);
            int plus;

            if(minute<=1){
                plus = 1+this.graph.getValue().get(0);
                this.graph.getValue().set(0,plus);
            }else if (minute>1 && minute<=5){
                plus = this.graph.getValue().get(1)+1;
                this.graph.getValue().set(1,plus);
            }else if (minute>5 && minute<=10){
                plus = this.graph.getValue().get(2)+1;
                this.graph.getValue().set(2,plus);
            }else if(minute>10 && minute<=15){
                plus = this.graph.getValue().get(3)+1;
                this.graph.getValue().set(3,plus);
            }else if(minute >15 && minute<=20){
                plus = this.graph.getValue().get(4)+1;
                this.graph.getValue().set(4,plus);
            }else if(minute>20 && minute<=25){
                plus = this.graph.getValue().get(5)+1;
                this.graph.getValue().set(5,plus);
            }else if(minute>25 && minute <=30){
                plus = this.graph.getValue().get(6)+1;
                this.graph.getValue().set(6,plus);
            }
            else if(minute>30 && minute<=60){
                plus = this.graph.getValue().get(7)+1;
                this.graph.getValue().set(7,plus);
            }else if(minute>60){
                plus = this.graph.getValue().get(8)+1;
                this.graph.getValue().set(8,plus);
            }


    }


}
