package sv.linda.mongo;

import org.bson.Document;

public class Docdata {
    private String FName;
    private String LName;
    private String roll;
    private String lesions;

    public Docdata(String id) {
        Mongodb db = new Mongodb("Logins", "students,teacher");
        this.FName = db.getData(id, "FName");
        this.LName = db.getData(id, "LName");
        this.roll = db.getData(id, "roll");
        this.lesions = db.getData(id, "Classes");
    }

    /*
    private String getFName(){
        return FName;
    }
    private String getLName(){
        return LName;
    }
    private String getRoll(){
        return roll;
    }
    private String getLesions(){
        return lesions;
    }
    */
    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public void setLesions(String lesions) {
        for (String i : lesions.split(",")) {
            if (!this.lesions.contains(i)) {
                this.lesions = this.lesions.concat(i);
            }
        }
    }

    public void removeLesions(String lesion) {
        String temp = this.lesions;
        this.lesions = temp.substring(temp.indexOf(lesion), lesion.length());
    }

    public Document getDoc() {
        return new Document("FName", this.FName).append("LName", this.LName).append("Classes", this.lesions);
    }
}