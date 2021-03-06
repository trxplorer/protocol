package io.trxplorer.protocol.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.trxplorer.protocol.model.serializer.TimestampDeserializer;

public class TransferModel {

	private String hash;
	
	private BigDecimal amount;
	
	private String token;
	
	private String from;
	
	private String to;
	
	private int type;
	
	private boolean confirmed;
	
	@JsonDeserialize(using=TimestampDeserializer.class)
	private long timestamp;
	
	
	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TransferModel [hash=" + hash + ", amount=" + amount + ", token=" + token + ", from=" + from + ", to="
				+ to + ", type=" + type + ", confirmed=" + confirmed + ", timestamp=" + timestamp + "]";
	}
	
	
	
}
