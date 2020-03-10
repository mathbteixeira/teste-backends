package dto;

import java.util.ArrayList;
import java.util.List;

public class ProposalDTO {

	private String eventId;
	private String eventSchema;
	private String eventAction;
	private String eventTimestamp;
	private String proposalId;
	private double proposalLoanValue;
	private int proposalNumberOfMonthlyInstallments;
	private List<WarrantyDTO> warrantyList = new ArrayList<>();
	private List<ProponentDTO> proponentList = new ArrayList<>();
	
	public ProposalDTO(String[] proposalData) {
		this.setEventId(proposalData[0]);
		this.setEventSchema(proposalData[1]);
		this.setEventAction(proposalData[2]);
		this.setEventTimestamp(proposalData[3]);
		this.setProposalId(proposalData[4]);
		this.setProposalLoanValue(Double.parseDouble(proposalData[5]));
		this.setProposalNumberOfMonthlyInstallments(Integer.parseInt(proposalData[6]));
	}
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventSchema() {
		return eventSchema;
	}

	public void setEventSchema(String eventSchema) {
		this.eventSchema = eventSchema;
	}

	public String getEventAction() {
		return eventAction;
	}

	public void setEventAction(String eventAction) {
		this.eventAction = eventAction;
	}

	public String getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(String eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public String getProposalId() {
		return proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}

	public double getProposalLoanValue() {
		return proposalLoanValue;
	}

	public void setProposalLoanValue(double proposalLoanValue) {
		this.proposalLoanValue = proposalLoanValue;
	}

	public int getProposalNumberOfMonthlyInstallments() {
		return proposalNumberOfMonthlyInstallments;
	}

	public void setProposalNumberOfMonthlyInstallments(int proposalNumberOfMonthlyInstallments) {
		this.proposalNumberOfMonthlyInstallments = proposalNumberOfMonthlyInstallments;
	}

	public List<WarrantyDTO> getWarrantyList() {
		return warrantyList;
	}

	public void setWarrantyList(List<WarrantyDTO> warrantyList) {
		this.warrantyList = warrantyList;
	}

	public List<ProponentDTO> getProponentList() {
		return proponentList;
	}

	public void setProponentList(List<ProponentDTO> proponentList) {
		this.proponentList = proponentList;
	}
	
}
