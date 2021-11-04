import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.IOException;

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
    private String readCondition;



    public Message(){}

    public Message(String createdDate, String type,String headline,String APNsSend,String APNsSuccess,String APNsFail,String GCMSend,String GCMSuccess,String GCMFail,String sender ,String user,String root_push_id,String readCondition) throws IOException {
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
        this.readCondition = readCondition;

  ***REMOVED***

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
  ***REMOVED***

    public void setType(String type) {
        this.type = type;
  ***REMOVED***

    public void setHeadline(String headline) {
        this.headline = headline;
  ***REMOVED***

    public void setAPNsSend(String APNsSend) {
        this.APNsSend = APNsSend;
  ***REMOVED***

    public void setAPNsSuccess(String APNsSuccess) {
        this.APNsSuccess = APNsSuccess;
  ***REMOVED***

    public void setAPNsFail(String APNsFail) {
        this.APNsFail = APNsFail;
  ***REMOVED***

    public void setGCMSend(String GCMSend) {
        this.GCMSend = GCMSend;
  ***REMOVED***

    public void setGCMSuccess(String GCMSuccess) {
        this.GCMSuccess = GCMSuccess;
  ***REMOVED***

    public void setGCMFail(String GCMFail) {
        this.GCMFail = GCMFail;
  ***REMOVED***

    public void setSender(String sender) {
        this.sender = sender;
  ***REMOVED***

    public void setUser(String user) {
        this.user = user;
  ***REMOVED***

    public void setRoot_push_id(String root_push_id) {
        this.root_push_id = root_push_id;
  ***REMOVED***

    public void setReadCondition(String readCondition) {
        this.readCondition = readCondition;
  ***REMOVED***

    public String getCreatedDate() {
        return createdDate;
  ***REMOVED***

    public String getType() {
        return type;
  ***REMOVED***

    public String getHeadline() {
        return headline;
  ***REMOVED***

    public String getAPNsSend() {
        return APNsSend;
  ***REMOVED***

    public String getAPNsSuccess() {
        return APNsSuccess;
  ***REMOVED***

    public String getAPNsFail() {
        return APNsFail;
  ***REMOVED***

    public String getGCMSend() {
        return GCMSend;
  ***REMOVED***

    public String getGCMSuccess() {
        return GCMSuccess;
  ***REMOVED***

    public String getGCMFail() {
        return GCMFail;
  ***REMOVED***

    public String getSender() {
        return sender;
  ***REMOVED***

    public String getUser() {
        return user;
  ***REMOVED***

    public String getRoot_push_id() {
        return root_push_id;
  ***REMOVED***

    public String getReadCondition() {
        return readCondition;
  ***REMOVED***
}
