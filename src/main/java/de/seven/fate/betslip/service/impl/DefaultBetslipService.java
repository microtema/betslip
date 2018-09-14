package de.seven.fate.betslip.service.impl;

import de.seven.fate.betslip.converter.Betslip2BetslipEntityConverter;
import de.seven.fate.betslip.converter.BetslipEntity2BetslipConverter;
import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.betslip.repository.BetslipRepository;
import de.seven.fate.betslip.service.BetslipService;
import de.seven.fate.betslip.vo.Betslip;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.seven.fate.betslip.constants.Constants.DUPLICATE_ENTITY;
import static de.seven.fate.betslip.constants.Constants.MAY_NOT_BE_EMPTY;
import static de.seven.fate.betslip.constants.Constants.MAY_NOT_BE_NULL;
import static de.seven.fate.betslip.constants.Constants.MAY_NOT_EXISTS;

@Log4j2
@Service
public class DefaultBetslipService implements BetslipService {

    private final BetslipRepository betslipRepository;
    private final Betslip2BetslipEntityConverter betslip2BetslipEntityConverter;
    private final BetslipEntity2BetslipConverter betslipEntity2BetslipConverter;

    public DefaultBetslipService(BetslipRepository betslipRepository,
                                 Betslip2BetslipEntityConverter betslip2BetslipEntityConverter,
                                 BetslipEntity2BetslipConverter betslipEntity2BetslipConverter) {
        this.betslipRepository = betslipRepository;
        this.betslip2BetslipEntityConverter = betslip2BetslipEntityConverter;
        this.betslipEntity2BetslipConverter = betslipEntity2BetslipConverter;
    }

    @Override
    public List<Betslip> getBetslips() {

        return betslipEntity2BetslipConverter.convertToList(betslipRepository.findAllSorted());
    }

    @Override
    public Long createBetslip(Betslip betslip) {
        Validate.notNull(betslip, MAY_NOT_BE_NULL, "betslip");

        BetslipEntity betslipEntity = betslip2BetslipEntityConverter.convert(betslip);

        Validate.isTrue(!betslipRepository.exists(betslipEntity), DUPLICATE_ENTITY, betslip.getTeamName());

        Validate.isTrue(!existsBetslip(betslip.getTeamName()), DUPLICATE_ENTITY, betslip.getTeamName());

        log.debug(() -> "Create new Betslip: " + betslip.getTeamName());

        return betslipRepository.save(betslipEntity).getId();
    }

    @Override
    public Long updateBetslip(Betslip betslip) {
        Validate.notNull(betslip, MAY_NOT_BE_NULL, "betslip");

        BetslipEntity betslipEntity = betslip2BetslipEntityConverter.convert(betslip);

        Validate.isTrue(betslipRepository.exists(betslipEntity), MAY_NOT_EXISTS, betslip.getTeamName());

        log.debug(() -> "Update Betslip: " + betslip.getTeamName());

        return betslipRepository.saveOrUpdate(betslipEntity).getId();
    }

    @Override
    public Betslip getBetslip(Long id) {
        Validate.notNull(id, MAY_NOT_BE_NULL, "id");

        BetslipEntity betslipEntity = betslipRepository.getOne(id);

        log.debug(() -> "Get Betslip by Id: " + id);

        return betslipEntity2BetslipConverter.convert(betslipEntity);
    }

    @Override
    public void deleteBetslip(Long id) {
        Validate.notNull(id, MAY_NOT_BE_NULL, "id");

        log.debug(() -> "Delete Betslip by Id: " + id);

        betslipRepository.deleteById(id);
    }

    @Override
    public boolean existsBetslip(String teamName) {
        Validate.notNull(teamName, MAY_NOT_BE_EMPTY, "teamName");

        return !betslipRepository.findAllByTeamNameContainingIgnoreCase(teamName).isEmpty();
    }
}
