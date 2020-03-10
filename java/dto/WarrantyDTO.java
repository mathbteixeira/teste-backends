package dto;

public class WarrantyDTO {

	private String eventID;
	private String eventSchema;
	private String eventAction;
	private String eventTimestamp;
	private String proposalId;
	private String warrantyId;
	private double warrantyValue;
	private String warrantyProvince;
	
	private static String REMOVED = "removed";
	
	public WarrantyDTO(String[] warrantyData) {
		this.setEventID(warrantyData[0]);
		this.setEventSchema(warrantyData[1]);
		this.setEventAction(warrantyData[2]);
		this.setEventTimestamp(warrantyData[3]);
		this.setProposalId(warrantyData[4]);
		this.setWarrantyId(warrantyData[5]);
		// Se a garantia foi removida, a mensagem não contém warrantyValue nem warrantyProvince
		if (!warrantyData[2].toString().contains(REMOVED)) {
			this.setWarrantyValue(Double.parseDouble(warrantyData[6]));
			this.setWarrantyProvince(warrantyData[7]);
		}
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
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

	public String getWarrantyId() {
		return warrantyId;
	}

	public void setWarrantyId(String warrantyId) {
		this.warrantyId = warrantyId;
	}

	public double getWarrantyValue() {
		return warrantyValue;
	}

	public void setWarrantyValue(double warrantyValue) {
		this.warrantyValue = warrantyValue;
	}

	public String getWarrantyProvince() {
		return warrantyProvince;
	}

	public void setWarrantyProvince(String warrantyProvince) {
		this.warrantyProvince = warrantyProvince;
	}
	
}
