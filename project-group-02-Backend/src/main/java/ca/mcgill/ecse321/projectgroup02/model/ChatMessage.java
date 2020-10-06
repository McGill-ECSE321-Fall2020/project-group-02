import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ChatMessage{
private String chatMessageId;
   
   public void setChatMessageId(String value) {
this.chatMessageId = value;
    }
@Id
public String getChatMessageId() {
return this.chatMessageId;
    }
private String sentDate;

public void setSentDate(String value) {
this.sentDate = value;
    }
public String getSentDate() {
return this.sentDate;
    }
private String message;

public void setMessage(String value) {
this.message = value;
    }
public String getMessage() {
return this.message;
    }
private Conversation conversation;

@ManyToOne(optional=false)
public Conversation getConversation() {
   return this.conversation;
}

public void setConversation(Conversation conversation) {
   this.conversation = conversation;
}

private User user;

@OneToOne(mappedBy="chatMessage", optional=false)
public User getUser() {
   return this.user;
}

public void setUser(User user) {
   this.user = user;
}

}
