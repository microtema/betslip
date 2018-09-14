package de.seven.fate.betslip.repository;

import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.betslip.entity.EntityProperty;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static de.seven.fate.betslip.constants.Constants.MAY_NOT_BE_NULL;

@Repository
public interface BetslipRepository extends ExtendedCrudRepository<BetslipEntity, Long> {

    /**
     * @param teamName may not be null or empty
     * @return List of BetslipEntity
     */
    List<BetslipEntity> findAllByTeamNameContainingIgnoreCase(@NotNull String teamName);

    default List<BetslipEntity> findAllSorted() {

        return findAll(new Sort(Sort.Direction.ASC, "teamName"));
    }

    @Override
    default List<EntityProperty> update(BetslipEntity recent, BetslipEntity current) {
        Validate.notNull(recent, MAY_NOT_BE_NULL, "recent");
        Validate.notNull(current, MAY_NOT_BE_NULL, "current");

        List<EntityProperty> properties = new ArrayList<>();

        if (!Objects.equals(recent.getTeamName(), current.getTeamName())) {

            properties.add(new EntityProperty<>("teamName", current.getTeamName(), recent.getTeamName()));

            recent.setTeamName(current.getTeamName());
        }

        if (!Objects.equals(recent.getAmount(), current.getAmount())) {

            properties.add(new EntityProperty<>("amount", current.getAmount(), recent.getAmount()));

            recent.setAmount(current.getAmount());
        }

        return properties;
    }

    @Override
    default Class<BetslipEntity> getEntityType() {

        return BetslipEntity.class;
    }
}
