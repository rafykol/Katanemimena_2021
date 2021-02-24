package gr.hua.group10.forms;

import javax.persistence.Column;
import javax.validation.Valid;

import gr.hua.group10.entities.Offer;

public class OfferForm {

	@Valid
	private Offer newoffer;
	
	@Column
	private int profID;
	
	@Column 
	private int stdID;

	
	public OfferForm() {}
	
	
	public OfferForm(Offer newoffer, int profID, int stdID) {
		super();
		this.newoffer = newoffer;
		this.profID = profID;
		this.stdID = stdID;
	}

	public Offer getNewoffer() {
		return newoffer;
	}

	public void setNewoffer(Offer newoffer) {
		this.newoffer = newoffer;
	}

	public int getProfID() {
		return profID;
	}

	public void setProfID(int profID) {
		this.profID = profID;
	}

	public int getStdID() {
		return stdID;
	}

	public void setStdID(int stdID) {
		this.stdID = stdID;
	}
	
	
	
	
}
