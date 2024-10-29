package com.hrm.model;

import java.time.LocalDate;

public class Employee {

    public enum Gender {
        male,
        female
    }

    public enum Status {
        on,
        off
    }

    public enum Work_type {
        full_time,
        part_time,
        internship,
        on_board,
        work_from_home
    }

    private int id;
    private String name;
    private LocalDate dob;
    private int position_id;       // ID của vị trí
    private int department_id;     // ID của phòng ban
    private Position position;     // Đối tượng Position
    private Department department; // Đối tượng Department
    private String previous_position;
    private Gender gender;
    private String phone_mumber;
    private String address;
    private String email;
    private LocalDate hire_date;
    private Status status;
    private int account_bank;
    private int indentify_card;
    private int tax_code;
    private int social_insurance_code;
    private Work_type work_type;

    // Constructor không đối số
    public Employee() {
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    

    // Constructor với position_id và department_id
    public Employee(int id, String name, LocalDate dob, int position_id, int department_id, String previous_position,
                    Gender gender, String phone_mumber, String address, String email, LocalDate hire_date, Status status,
                    int account_bank, int indentify_card, int tax_code, int social_insurance_code, Work_type work_type) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.position_id = position_id;
        this.department_id = department_id;
        this.previous_position = previous_position;
        this.gender = gender;
        this.phone_mumber = phone_mumber;
        this.address = address;
        this.email = email;
        this.hire_date = hire_date;
        this.status = status;
        this.account_bank = account_bank;
        this.indentify_card = indentify_card;
        this.tax_code = tax_code;
        this.social_insurance_code = social_insurance_code;
        this.work_type = work_type;
    }

    // Constructor mới với đối tượng Position và Department
    public Employee(int id, String name, LocalDate dob, Position position, Department department, String previous_position,
                    Gender gender, String phone_mumber, String address, String email, LocalDate hire_date, Status status,
                    int account_bank, int indentify_card, int tax_code, int social_insurance_code, Work_type work_type) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.position = position;
        this.department = department;
        this.position_id = position.getId();  // Lấy id từ Position object
        this.department_id = department.getId();  // Lấy id từ Department object
        this.previous_position = previous_position;
        this.gender = gender;
        this.phone_mumber = phone_mumber;
        this.address = address;
        this.email = email;
        this.hire_date = hire_date;
        this.status = status;
        this.account_bank = account_bank;
        this.indentify_card = indentify_card;
        this.tax_code = tax_code;
        this.social_insurance_code = social_insurance_code;
        this.work_type = work_type;
    }

    // Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        this.position_id = position.getId(); // Cập nhật position_id khi thay đổi Position
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        this.department_id = department.getId(); // Cập nhật department_id khi thay đổi Department
    }

    public String getPrevious_position() {
        return previous_position;
    }

    public void setPrevious_position(String previous_position) {
        this.previous_position = previous_position;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone_mumber() {
        return phone_mumber;
    }

    public void setPhone_mumber(String phone_mumber) {
        this.phone_mumber = phone_mumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAccount_bank() {
        return account_bank;
    }

    public void setAccount_bank(int account_bank) {
        this.account_bank = account_bank;
    }

    public int getIndentify_card() {
        return indentify_card;
    }

    public void setIndentify_card(int indentify_card) {
        this.indentify_card = indentify_card;
    }

    public int getTax_code() {
        return tax_code;
    }

    public void setTax_code(int tax_code) {
        this.tax_code = tax_code;
    }

    public int getSocial_insurance_code() {
        return social_insurance_code;
    }

    public void setSocial_insurance_code(int social_insurance_code) {
        this.social_insurance_code = social_insurance_code;
    }

    public Work_type getWork_type() {
        return work_type;
    }

    public void setWork_type(Work_type work_type) {
        this.work_type = work_type;
    }

    // Override phương thức toString() để hiển thị thông tin Employee
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + ", position_id=" + position_id
                + ", department_id=" + department_id + ", previous_position=" + previous_position + ", gender=" + gender
                + ", phone_mumber=" + phone_mumber + ", address=" + address + ", email=" + email + ", hire_date="
                + hire_date + ", status=" + status + ", account_bank=" + account_bank + ", indentify_card=" + indentify_card
                + ", tax_code=" + tax_code + ", social_insurance_code=" + social_insurance_code + ", work_type=" + work_type + "]";
    }
}
