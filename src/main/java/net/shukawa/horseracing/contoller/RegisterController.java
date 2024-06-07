package net.shukawa.horseracing.contoller;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import net.shukawa.horseracing.domain.RaceDetailForm;
import net.shukawa.horseracing.domain.TicketDetailForm;
import net.shukawa.horseracing.entity.PurchaseEntity;
import net.shukawa.horseracing.entity.TicketEntity;
import net.shukawa.horseracing.repository.PurchaseRepository;
import net.shukawa.horseracing.repository.TicketRepository;
import net.shukawa.horseracing.service.PurchaseMapper;
import net.shukawa.horseracing.service.TicketMapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@RestController
public class RegisterController {
  /** 購入情報 */
  private final PurchaseRepository purchaseRepository;

  /** 馬券情報 */
  private final TicketRepository ticketRepository;

  /** 購入情報マッパー */
  private final PurchaseMapper purchaseMapper;

  /** 馬券情報マッパー */
  private final TicketMapper ticketMapper;

  @GetMapping("/register")
  public ModelAndView getRegister(ModelAndView mav) {
    mav.addObject("raceDetailForm", new RaceDetailForm());
    mav.addObject("ticketDetailForm", new TicketDetailForm());
    mav.setViewName("register");
    return mav;
  }

  @PostMapping("/register")
  @Transactional
  public ModelAndView postRegister(
      @Validated RaceDetailForm raceDetailForm,
      BindingResult resultRace,
      @Validated TicketDetailForm ticketDetailForm,
      BindingResult resultTicket,
      ModelAndView mav) {
    if (resultRace.hasErrors() || resultTicket.hasErrors()) {
      return mav;
    }
    PurchaseEntity pe = purchaseMapper.convert(raceDetailForm);
    purchaseRepository.saveAndFlush(pe);
    List<TicketEntity> teList = ticketMapper.convert(ticketDetailForm, pe);
    ticketRepository.saveAllAndFlush(teList);

    mav.setViewName("register");
    return mav;
  }
}
