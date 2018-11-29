package com.patrickKilpatrick.dojoOverflow.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min=1, message="Question field must be present")
	private String question;
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	@OneToMany(mappedBy="question", fetch=FetchType.LAZY)
	private List<Answer> answers = new ArrayList<Answer>();
	@ManyToMany
	@JoinTable(
		name = "questions_tags"	,
		joinColumns = { @JoinColumn(name="question_id") },
		inverseJoinColumns = { @JoinColumn(name="tag_id") }
	)
	private List<Tag> tags = new ArrayList<Tag>();
	
	public Question() {
		
	}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
		tag.getQuestions().add(this);
	}
	
	public void removeCategory(Tag tag) {
		this.tags.remove(tag);
		tag.getQuestions().remove(this);
	}
	
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}
	
	public void removeAnswer(Answer answer) {
		this.answers.remove(answer);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}
}
