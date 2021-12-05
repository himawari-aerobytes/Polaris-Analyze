package analyzer.analyze.logic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown=true)
public class ReadCondition {
    @JsonProperty("t_device_mng_id")
    private String t_device_mng_id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("status_name")
    private String status_name;
    @JsonProperty("response_result_created")
    private String response_result_created;
    @JsonProperty("device_name")
    private String device_name;
    @JsonProperty("user_notes")
    private String user_notes;

    public ReadCondition(String t_device_mng_id, String status, String status_name, String response_result_created, String device_name, String user_notes) {
        this.t_device_mng_id = t_device_mng_id;
        this.status = status;
        this.status_name = status_name;
        this.response_result_created = response_result_created;
        this.device_name = device_name;
        this.user_notes = user_notes;
    }

    public ReadCondition() {}

    public String getT_device_mng_id() {
        return t_device_mng_id;
    }

    public void setT_device_mng_id(String t_device_mng_id) {
        this.t_device_mng_id = t_device_mng_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getResponse_result_created() {
        return response_result_created;
    }

    public void setResponse_result_created(String response_result_created) {
        this.response_result_created = response_result_created;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getUser_notes() {
        return user_notes;
    }

    public void setUser_notes(String user_notes) {
        this.user_notes = user_notes;
    }
}
