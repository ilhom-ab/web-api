package com.example.webapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.webapi.model.audit.DateAudit;

@Entity
@Table(name = "campaigns")
public class Campaign extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4908762060794670008L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 45)
	private String title;

	@NotBlank
	@Size(max = 45)
	@Email
	private String email;

	@NotBlank
	@Size(max = 255)
	private String content;

	@NotBlank
	@Size(max = 40)
	private String status;
	
	//@NotBlank
	private byte[] imgfile;
	
	
	public Campaign() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getImgfile() {
		return imgfile;
	}

	public void setImgfile(byte[] imgfile) {
		this.imgfile = imgfile;
	}
}
