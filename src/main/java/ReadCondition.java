public class ReadCondition {
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
