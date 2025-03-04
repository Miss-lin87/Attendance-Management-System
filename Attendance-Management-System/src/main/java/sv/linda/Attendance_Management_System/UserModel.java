package sv.linda.Attendance_Management_System;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserModel {
    public Object userText;
    public boolean pressent;

    public void setuserText(String userText) {
        this.userText = userText;
    }

    public Object getuserText() {
        return userText;
    }

    public boolean getPressent() {
        return pressent;
    }

    public void setPressent(boolean input) {
        this.pressent = input;
    }
}
