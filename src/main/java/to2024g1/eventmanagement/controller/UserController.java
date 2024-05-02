package to2024g1.eventmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import to2024g1.eventmanagement.dto.UserCreate;
import to2024g1.eventmanagement.dto.UserDto;
import to2024g1.eventmanagement.dto.UserUpdate;
import to2024g1.eventmanagement.entity.Booking;
import to2024g1.eventmanagement.entity.User;
import to2024g1.eventmanagement.service.BookingService;
import to2024g1.eventmanagement.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BookingService bookingService;

    @PostMapping("/user/create")
    public String createUser(UserCreate userCreate){
        String statusMessage = userService.createUser(userCreate);
        return "redirect:/login?statusMessage="+statusMessage;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/")
    public String profilePage(Model model){
        User currentUser = userService.getCurrentUser();
        User user = userService.getUserById(currentUser.getId());
        model.addAttribute("currentUser", user);

        List<Booking> myBookingList = bookingService.getAllBookingsByUserId(user.getId());
        model.addAttribute("myBookings", myBookingList);

        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/user/")
    public String editUser(UserUpdate userUpdate){
        userService.editUser(userUpdate);
        return "redirect:/profile/";
    }

    @PostMapping("/cancel/booking/")
    public String cancelBookingById(@RequestParam Long id){
        bookingService.cancelBookingById(id);
        return "redirect:/profile/";
    }
}
