package de.seven.fate.betslip.service;

import de.seven.fate.betslip.vo.Betslip;

import java.util.List;

/**
 * Betslip Service that provide all CRUD's operations
 */
public interface BetslipService {

    /**
     * @return List of Betslip
     */
    List<Betslip> getBetslips();

    /**
     * @param betslip may not be null
     * @return Database id
     */
    Long createBetslip(Betslip betslip);

    /**
     * @param betslip may not be null
     * @return Database id
     */
    Long updateBetslip(Betslip betslip);

    /**
     * @param id may not be null
     * @return Betslip
     * throws java.util.NoSuchElementException
     */
    Betslip getBetslip(Long id);

    /**
     * Delete Betslip by given id
     *
     * @param id may not be null
     */
    void deleteBetslip(Long id);

    /**
     * Check if Betslip identified by teamName exist
     *
     * @param teamName may not be empty
     * @return Boolean
     */
    boolean existsBetslip(String teamName);
}
