import javax.persistence.Entity;
import javax.persistence.Id;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Conversation{
private String conversationId;
   
   public void setConversationId(String value) {
this.conversationId = value;
    }
@Id
public String getConversationId() {
return this.conversationId;
    }
private ArtGallerySystem artGallerySystem;

@ManyToOne(optional=false)
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}

private Set<ChatMessage> messages;

@OneToMany(mappedBy="conversation", cascade={CascadeType.ALL})
public Set<ChatMessage> getMessages() {
   return this.messages;
}

public void setMessages(Set<ChatMessage> messagess) {
   this.messages = messagess;
}

private Set<User> user;

@OneToMany(mappedBy="conversation")
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

}
