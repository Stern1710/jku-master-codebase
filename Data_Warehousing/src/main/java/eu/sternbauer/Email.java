package eu.sternbauer;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Data class object for Emails. Consist of up to six fields id, date, from, recipients, subject and body
 */
public class Email {
    private String id;
    private Timestamp date;
    private String from;
    private List<String> recipients;
    private String subject;
    private String body;

    /**
     * Creates a new Email object
     * @param id ID of the mail
     * @param date Datetime object with the time the Email was sent
     * @param from Who sent the mail
     * @param recipients A string list of all recipients of this email
     * @param subject The subject line, can be null
     * @param body The body of the mail, can be null as well
     */
    public Email(String id, Timestamp date,
                 String from, List<String> recipients,
                 String subject, String body) {
        this.id = id;
        this.date = date;
        this.from = from;
        this.recipients = recipients;
        this.subject = subject;
        this.body = body;
    }

    /* ----------- Getter and Setter ----------- */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /* ----------- Overwritten methods ----------- */
    @Override
    public String toString() {
        return "Email{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", from='" + from + '\'' +
                ", recipients=" + recipients +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return id.equals(email.id) && date.equals(email.date)
                && from.equals(email.from) && recipients.equals(email.recipients)
                && Objects.equals(subject, email.subject) && Objects.equals(body, email.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, from, recipients, subject, body);
    }
}
