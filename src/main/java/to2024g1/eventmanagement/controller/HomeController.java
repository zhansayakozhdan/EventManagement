package to2024g1.eventmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import to2024g1.eventmanagement.entity.Event;
import to2024g1.eventmanagement.service.EventService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EventService eventService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("vseEventy", events);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
