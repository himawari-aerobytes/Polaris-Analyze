import lombok.Data;

@Data
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

}
