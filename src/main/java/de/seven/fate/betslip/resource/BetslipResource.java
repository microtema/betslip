package de.seven.fate.betslip.resource;

import de.seven.fate.betslip.facade.BetslipFacade;
import de.seven.fate.betslip.vo.Betslip;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/betslip", produces = MediaType.APPLICATION_JSON_VALUE)
public class BetslipResource {

    private final BetslipFacade betslipFacade;

    public BetslipResource(BetslipFacade betslipFacade) {
        this.betslipFacade = betslipFacade;
    }

    @GetMapping
    public ResponseEntity query() {

        return ResponseEntity.ok(betslipFacade.getBetslips());
    }

    @PostMapping
    public ResponseEntity createBetslip(@Valid @RequestBody Betslip betslip, BindingResult result) {

        return ResponseEntity.status(HttpStatus.CREATED).body(betslipFacade.createBetslip(betslip));
    }

    @PutMapping
    public ResponseEntity updateBetslip(@RequestBody Betslip betslip, BindingResult result) {

        return ResponseEntity.ok(betslipFacade.getBetslip(betslip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBetslip(@PathVariable Long id) {

        betslipFacade.deleteBetslip(id);

        return ResponseEntity.accepted().build();
    }
}
