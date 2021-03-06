package io.trxplorer.protocol.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.trxplorer.protocol.model.serializer.TimestampDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockModel {

	private long height;
	
	private String hash;
	
	@JsonIgnore
	private String parentHash;
	
	private String witness;
	
	@JsonIgnore
	private List<TransactionModel> transactions;
	
	private int txCount;
	
	@JsonDeserialize(using=TimestampDeserializer.class)
	private long timestamp;
	
	private int size;
	
	private boolean confirmed;
	
	public BlockModel() {
		this.transactions = new ArrayList<>();
	}
	
		
	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTxCount() {
		return txCount;
	}

	public void setTxCount(int txCount) {
		this.txCount = txCount;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getParentHash() {
		return parentHash;
	}

	public void setParentHash(String parentHash) {
		this.parentHash = parentHash;
	}

	public String getWitness() {
		return witness;
	}

	public void setWitness(String witness) {
		this.witness = witness;
	}

	public List<TransactionModel> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionModel> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransaction(TransactionModel transaction) {
		this.transactions.add(transaction);
	}
	
	
	
}
