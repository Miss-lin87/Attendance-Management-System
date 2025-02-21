package sv.linda.Attendance_Management_System;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sv.linda.Attendance_Management_System.mongo.Mongodb;
import java.util.List;

@Controller
@SessionAttributes("userModel")
@RequestMapping("/clazzcontroller")
public class ClazzController {
    public Mongodb DB = new Mongodb("Logins", "students");

    @GetMapping
    public String get(@ModelAttribute("user") UserModel clazz, Model model) {
        List<String> students = DB.getStudents(clazz.getuserText().toString());
        System.out.print(clazz.getuserText());
        int i = 0;
        for (String student : students) {
            model.addAttribute("stud"+1, student);
        }
        return "results";
    }

    @PostMapping
    public String post(@ModelAttribute("userModel") UserModel pressent, Model model, RedirectAttributes redirect) {
        redirect.addFlashAttribute("user", pressent);
        System.out.println(pressent.getPressent());
        return "redirect:/Rest";
    }
}
