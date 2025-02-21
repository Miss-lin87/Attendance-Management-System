package sv.linda.Attendance_Management_System;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sv.linda.Attendance_Management_System.mongo.Mongodb;

import java.util.List;

@Controller
@SessionAttributes("userModel")
@RequestMapping("/m")
public class MainController {
    public Mongodb DB = new Mongodb("Logins", "students,teacher");

    @GetMapping
    public String get(Model mod) {
        List<String> clazzes = DB.getClazzes();
        int i = 0;
        for (String clazz : clazzes) {
            mod.addAttribute("clazz"+i, clazz);
            i ++;
        }
        mod.addAttribute("userModel", new UserModel());
        return "main";
    }

    @PostMapping
    public String post(@ModelAttribute("userModel") UserModel userModel, Model model, RedirectAttributes redirect) {
        redirect.addFlashAttribute("user", userModel);
        return "redirect:/clazzcontroller";
    }
}