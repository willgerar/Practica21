package gerardo.com.urv.practica21.Models;

import android.support.annotation.NonNull;

import java.util.Date;

public class Slot {
    private  int id;
    private int company_number;
    private String name;
    private String slot_color;
    private String slot_state;
    private String slot_type;
    private Date state_change_date;

    public Slot (int id, int company_number, String name, String slot_color, String slot_state, String slot_type, Date state_change_date) {
        this.id = id;
        this.company_number = company_number;
        this.name = name;
        this.slot_color = slot_color;
        this.slot_state = slot_state;
        this.slot_type = slot_type;
        this.state_change_date = state_change_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_number() {
        return company_number;
    }

    public void setCompany_number(int company_number) {
        this.company_number = company_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlot_color() {
        return slot_color;
    }

    public void setSlot_color(String slot_color) {
        this.slot_color = slot_color;
    }

    public String getSlot_state() {
        return slot_state;
    }

    public void setSlot_state(String slot_state) {
        this.slot_state = slot_state;
    }

    public String getSlot_type() {
        return slot_type;
    }

    public void setSlot_type(String slot_type) {
        this.slot_type = slot_type;
    }

    public Date getState_change_date() {
        return state_change_date;
    }

    public void setState_change_date(Date state_change_date) {
        this.state_change_date = state_change_date;
    }

    @NonNull
    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", company_number=" + company_number +
                ", name='" + name + '\'' +
                ", slot_color='" + slot_color + '\'' +
                ", slot_state='" + slot_state + '\'' +
                ", slot_type='" + slot_type + '\'' +
                ", state_change_date=" + state_change_date +
                '}';
    }
}
