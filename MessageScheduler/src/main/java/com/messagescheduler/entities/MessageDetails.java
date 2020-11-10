package com.messagescheduler.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messagedetails")
public class MessageDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "message")
	private String message;

	@Column(name = "date")
	private String date;

	@Column(name = "time")
	private String time;

	@Column(name = "timezone")
	private String timezone;

	@Column(name = "delivered")
	private String delivered;

	public MessageDetails() {
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getDelivered() {
		return delivered;
	}

	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	@Override
	public String toString() {
		return "MessageDetails [id=" + id + ", message=" + message + ", date=" + date + ", time=" + time + ", timezone="
				+ timezone + ", delivered=" + delivered + "]";
	}

}
