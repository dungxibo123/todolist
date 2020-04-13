package Event;

import java.util.Date;

public class MyEvent {
    private  String content;

    private  Date deadline;
    private  Date startDate;

    public MyEvent(String content) {
        this.content = content;
    }


    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
