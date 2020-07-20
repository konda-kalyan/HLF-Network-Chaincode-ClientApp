package org.hyperledger.fabric.samples.cc;

import java.util.ArrayList;
import java.util.List;

public class DeleteResponse {
	private int numberOfDeletedOrders;
	private int numberOfNotDeletedOrders;
	private List<String> deletedOrderIDs;
	private List<String> notDeletedOrderIDs;
	
	public DeleteResponse() {
		deletedOrderIDs = new ArrayList<>();
		notDeletedOrderIDs = new ArrayList<>();
	}
	
	public DeleteResponse(int noOfOrders) {
		deletedOrderIDs = new ArrayList<>(noOfOrders);
		notDeletedOrderIDs = new ArrayList<>(noOfOrders);
	}
	
	public int getNumberOfDeletedOrders() {
		return numberOfDeletedOrders;
	}
	
	public void setNumberOfDeletedOrders(int numberOfDeletedOrders) {
		this.numberOfDeletedOrders = numberOfDeletedOrders;
	}
	
	public int getNumberOfNotDeletedOrders() {
		return numberOfNotDeletedOrders;
	}
	
	public void setNumberOfNotDeletedOrders(int numberOfNotDeletedOrders) {
		this.numberOfNotDeletedOrders = numberOfNotDeletedOrders;
	}
	
	public List<String> getDeletedOrderIDs() {
		return deletedOrderIDs;
	}
	
	public void addDeletedOrderID(String deletedOrderID) {
		this.deletedOrderIDs.add(deletedOrderID);
	}
	
	public List<String> getNotDeletedOrderIDs() {
		return notDeletedOrderIDs;
	}
	
	public void addNotDeletedOrderID(String notDeletedOrderID) {
		this.notDeletedOrderIDs.add(notDeletedOrderID);
	}
}
