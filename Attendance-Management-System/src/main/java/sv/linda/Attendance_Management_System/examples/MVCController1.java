package sv.linda.Attendance_Management_System.examples;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sv.linda.Attendance_Management_System.UserModel;

@Controller
@SessionAttributes("userModel")
@RequestMapping("/")
public class MVCController1 {

    @GetMapping
    public String get(Model model) {
        model.addAttribute("Controller1","You are in Controller-1");
        model.addAttribute("enterMe", "this is a test");
        model.addAttribute("userModel", new UserModel());
        return "view";
    }

    @PostMapping
    public String post(@ModelAttribute("userModel") UserModel userModel, Model model,RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("user", userModel);
        return "redirect:/MVC-2";
    }
}