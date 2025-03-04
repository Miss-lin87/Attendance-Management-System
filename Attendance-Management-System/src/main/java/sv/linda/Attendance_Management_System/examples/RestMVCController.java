package sv.linda.Attendance_Management_System.examples;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sv.linda.Attendance_Management_System.UserModel;

@RestController
@SessionAttributes("userModel")
@RequestMapping(path="/rest", produces="application/json")
@CrossOrigin(origins="*")
public class RestMVCController {

    @GetMapping
    public UserModel get(@ModelAttribute("user") UserModel user, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return user;
    }
}
