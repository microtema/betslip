package de.seven.fate.betslip.facade;

import de.seven.fate.betslip.service.BetslipService;
import de.seven.fate.betslip.vo.Betslip;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BetslipFacade {

    private final BetslipService betslipService;

    public BetslipFacade(BetslipService betslipService) {
        this.betslipService = betslipService;
    }


    public List<Betslip> getBetslips() {

        return betslipService.getBetslips();
    }


    public Long createBetslip(Betslip betslip) {

        return betslipService.createBetslip(betslip);
    }


    public Long getBetslip(Betslip betslip) {

        return betslipService.updateBetslip(betslip);
    }


    public Betslip getBetslip(Long id) {

        return betslipService.getBetslip(id);
    }


    public void deleteBetslip(Long id) {

        betslipService.deleteBetslip(id);
    }
}
