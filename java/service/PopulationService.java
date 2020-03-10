package service;

import java.util.List;

import dto.ProponentDTO;
import dto.ProposalDTO;
import dto.WarrantyDTO;

public class PopulationService {
	
	private static String WARRANTY = "warranty";
	private static String PROPONENT = "proponent";
	private static String COMMA = ",";
	
	/**
	 * Popula ProposalDTO
	 * 
	 * @param proposal
	 * @param messages
	 * */
	public ProposalDTO populateProposalDTO(String proposal, List<String> messages) {
		
		String[] proposalValues = proposal.split(COMMA);
		ProposalDTO proposalDTO = new ProposalDTO(proposalValues);
				
		for (String message: messages) {
			if (message.contains(proposalDTO.getProposalId())) {
				if (message.contains(WARRANTY)) {
					WarrantyDTO warrantyDTO = populateWarrantyDTO(message);
					proposalDTO.getWarrantyList().add(warrantyDTO);
				}
				else if (message.contains(PROPONENT)){
					ProponentDTO proponentDTO = populateProponentDTO(message);
					proposalDTO.getProponentList().add(proponentDTO);
				}
			}
		}
		
		return proposalDTO;
	}
	
	/**
	 * Popula WarrantyDTO
	 * @param String
	 * */
	private WarrantyDTO populateWarrantyDTO(String warranty) {
		String[] warrantyData = warranty.split(COMMA);
		WarrantyDTO warrantyDTO = new WarrantyDTO(warrantyData);
		
		return warrantyDTO;
	}

	/**
	 * Popula ProponentDTO
	 * @param String
	 * */
	private ProponentDTO populateProponentDTO(String proponent) {
		String[] proponentData = proponent.split(COMMA);
		ProponentDTO proponentDTO = new ProponentDTO(proponentData);
		
		return proponentDTO;
	}
}