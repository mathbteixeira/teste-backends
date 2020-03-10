package service;

import java.util.ArrayList;
import java.util.List;

import dto.ProponentDTO;
import dto.ProposalDTO;
import dto.WarrantyDTO;

public class ValidationService {
	
	private static final String PARANA = "PR";
	private static final String SANTA_CATARINA = "SC";
	private static final String RIO_GRANDE_DO_SUL = "RS";
	
	/**
	 * Valida a mensagem de proposal (proposta de empréstimo) de acordo com as regras predefinidas
	 * 
	 * @param proposal
	 * @return
	 */
	public boolean validateProposal(ProposalDTO proposal) {
		if (!validateLoanValueAndPayment(proposal)) {
			return false;
		}
		
		if ((!validateWarranties(proposal)) || (!validateProponents(proposal))) {
			return false;
		}
		
		return true;
	}

	/**
	 * O valor do empréstimo deve estar entre R$ 30.000,00 e R$ 3.000.000,00
	 * &&
	 * O empréstimo deve ser pago em no mínimo 2 anos e no máximo 15 anos
	 * 
	 * @param proposal
	 * @return
	 */
	private boolean validateLoanValueAndPayment(ProposalDTO proposal) {
		if ((proposal.getProposalLoanValue() < 30000 || proposal.getProposalLoanValue() > 3000000) ||  
			(proposal.getProposalNumberOfMonthlyInstallments() < 24 || proposal.getProposalNumberOfMonthlyInstallments() > 180)) { 
			return false;
		}
		return true;
	}
	
	/**
	 * Valida a mensagem de warranty (garantia de imóvel) de acordo com as regras predefinidas
	 * 
	 * @param proposal
	 * @return
	 */
	private boolean validateWarranties(ProposalDTO proposal) {
		// Dever haver no mínimo 1 garantia de imóvel por proposta
		if (proposal.getWarrantyList().isEmpty()) {
			return false;
		} 
		else {
			double warrantyTotal = 0;
			for (WarrantyDTO dto : proposal.getWarrantyList()) {
				// Se a garantia não foi removida, prossegue para as outras validações
				if (!dto.getEventAction().equalsIgnoreCase("removed")) {
					
					if (!validateWarrantyProvince(dto)) {
						return false;
					}
					
					// Soma o valor da garantia ao total dos valores de garantia 
					warrantyTotal += dto.getWarrantyValue();
				}
			}
			
			// O valor das garantias deve ser maior ou igual ao dobro do valor do empréstimo
			if (!(warrantyTotal >= (proposal.getProposalLoanValue()*2))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * As garantias de imóvel dos estados PR, SC e RS não são aceitas
	 * 
	 * @param dto
	 */
	private boolean validateWarrantyProvince(WarrantyDTO dto) {
		if (dto.getWarrantyProvince().equalsIgnoreCase(PARANA) || dto.getWarrantyProvince().equalsIgnoreCase(RIO_GRANDE_DO_SUL) 
				|| dto.getWarrantyProvince().equalsIgnoreCase(SANTA_CATARINA)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Valida a mensagem de proponent (pessoas envolvidas no contrato) de acordo com as regras predefinidas
	 * 
	 * @param proposal
	 * @return
	 */
	private boolean validateProponents(ProposalDTO proposalDTO) {
		// Deve haver no mínimo 2 proponentes por proposta
		if (proposalDTO.getProponentList().size() < 2) {
			return false;
		} else {
			List<ProponentDTO> mainProponent = new ArrayList<ProponentDTO>();
			for (ProponentDTO dto : proposalDTO.getProponentList()) {
				// Todos os proponentes devem ser maiores de 18 anos
				if (dto.getProponentAge() < 18) {
					return false;
				}
				if (dto.isMainProponent()) {
					mainProponent.add(dto);
				}
			}
			
			// Deve haver exatamente 1 proponente principal por proposta
			if (mainProponent.size() != 1) {
				return false;
			}
			else {
				ProponentDTO proponent = mainProponent.get(0);
				double monthlyPayment = proposalDTO.getProposalLoanValue() / proposalDTO.getProposalNumberOfMonthlyInstallments();
				
				if (!validateProponentIncomeByAge(proponent, monthlyPayment)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * A renda do proponente principal deve ser 4 vezes o valor da parcela do empréstimo, se a idade dele for entre 18 e 24 anos
	 * &&
	 * A renda do proponente principal deve ser 3 vezes o valor da parcela do empréstimo, se a idade dele for entre 24 e 50 anos
	 * &&
	 * A renda do proponente principal deve ser 2 vezes o valor da parcela do empréstimo, se a idade dele for acima de 50 anos
	 * 
	 * @param proponent
	 * @param monthlyPayment
	 */
	private boolean validateProponentIncomeByAge(ProponentDTO proponent, double monthlyPayment) {
		if (proponent.getProponentAge() >= 18 && proponent.getProponentAge() <= 24 && !(proponent.getProponentMonthlyIncome() >= monthlyPayment * 4)) {
			return false;
		}

		if(proponent.getProponentAge() >= 24 && proponent.getProponentAge() <= 50 && !(proponent.getProponentMonthlyIncome() >= monthlyPayment * 3)) {
			return false;
		}

		if(proponent.getProponentAge() > 50 && !(proponent.getProponentMonthlyIncome() >= monthlyPayment * 2)) {
			return false;
		}
		return true;
	}
}
