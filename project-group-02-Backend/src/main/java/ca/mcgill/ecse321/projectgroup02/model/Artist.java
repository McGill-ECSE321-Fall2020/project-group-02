package ca.mcgill.ecse321.projectgroup02.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Artist extends UserRole{
   private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}

@Column(nullable = false) //Tell JPA users must be non-null
@ElementCollection //Resolves "Failed to load ApplicationContext"
private Set<Item> item;


@OneToMany
@NotFound(action = NotFoundAction.IGNORE)
public Set<Item> getItem() {
   return this.item;
}

public void setItem(Set<Item> items) {
   this.item = items;
   
}
}
