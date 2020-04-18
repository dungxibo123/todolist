package Event;

import java.time.LocalDate;
import java.util.Date;

public class MyEvent {
    private  String content;
    private  Integer id;
    private LocalDate deadline;
    private String deadlineString;
    private  Date startDate;

    public MyEvent(String content, LocalDate ld, Integer id) {
        this.content = content;
        this.deadline = ld;
        this.deadlineString = deadline.toString();
        this.id = id;
    }

    public MyEvent(String content, LocalDate ld) {
        this.content = content;
        this.deadline = ld;
        this.deadlineString = deadline.toString();
    }


    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public String getDeadlineString() {
        return this.deadlineString;
    }

    public void setDeadlineString(String deadlineString) {
        this.deadlineString = deadlineString;
    }

    public Integer getId() {
        return id;
    }

    public void setDeadline(LocalDate value) {
        this.deadline = value;
        this.deadlineString = deadline.toString();

    }
}
