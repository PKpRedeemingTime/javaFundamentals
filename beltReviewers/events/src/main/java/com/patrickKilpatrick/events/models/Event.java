package com.patrickKilpatrick.events.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.patrickKilpatrick.events.models.User;
import com.patrickKilpatrick.events.models.Message;

@Entity
@Table(name="events")
public class Event {
	@Id
    @GeneratedValue
    private Long id;
	@Size(min=1)
	private String name;
	private String owner;
	@NotNull
	@Future
	private Date eventDate;
	@Size(min=1)
	private String locationCity;
	@Size(min=2, max=2)
	private String locationState;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany(mappedBy = "events", fetch=FetchType.LAZY)
    private List<User> users = new ArrayList<User>();
    @OneToMany(mappedBy = "event", fetch=FetchType.LAZY)
    private List<Message> messages = new ArrayList<Message>();
    
    public Event() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLocationState() {
		return locationState;
	}

	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
		user.getEvents().add(this);
	}
	
	public void removeUser(User user) {
		this.users.remove(user);
		user.getEvents().remove(this);
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
    
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}
