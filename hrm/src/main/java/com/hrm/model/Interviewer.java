/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuyenDung;

/**
 *
 * @author MSI
 */
public class Interviewer {
    private int Employee_id;
    private int Interview_id;

    public Interviewer(int Employee_id, int Interview_id) {
        this.Employee_id = Employee_id;
        this.Interview_id = Interview_id;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public int getInterview_id() {
        return Interview_id;
    }

    public void setInterview_id(int Interview_id) {
        this.Interview_id = Interview_id;
    }
    
    
}
