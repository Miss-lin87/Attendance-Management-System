package sv.linda.Attendance_Management_System.examples;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sv.linda.Attendance_Management_System.UserModel;

@Controller
@SessionAttributes("userModel")
@RequestMapping("/MVC-2")
public class MVCController2 {

    @GetMapping
    public String get(@ModelAttribute("user") UserModel message, Model model) {
        model.addAttribute("Controller2","You are in Controller-2");
        model.addAttribute("message", message.getuserText());
        model.addAttribute("userModel", new UserModel());
        return "view";
    }

    @PostMapping
    public String post(@ModelAttribute("userModel") UserModel userModel, Model model,RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", userModel);
        return "redirect:/Rest";
    }
}
