package Event;

import java.time.LocalDate;
import java.util.Date;

public class MyEvent {
    private  String content;
    private  String id;
    private LocalDate deadline;
    private String deadlineString;
    private  Date startDate;
    private String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    public MyEvent(String content, LocalDate ld) {
        this.content = content;
        this.deadline = ld;
        this.deadlineString = deadline.toString();
        this.id = this.getAlphaNumericString(20);
    }

    public MyEvent(String id, String content, String deadlineString) {
        this.id = id;
        this.content =content;
        this.deadlineString = deadlineString;
        this.deadline = LocalDate.parse(deadlineString);
    }

    public MyEvent(String content, String stringLocalDate) {
        this.deadlineString = stringLocalDate;
        this.content = content;
        this.deadline = LocalDate.parse(stringLocalDate);
        this.id = this.getAlphaNumericString(20);
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

    public String  getId() {
        return id;
    }

    public void setDeadline(LocalDate value) {
        this.deadline = value;
        this.deadlineString = deadline.toString();

    }
}
