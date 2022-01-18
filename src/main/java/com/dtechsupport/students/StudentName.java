package com.dtechsupport.students;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class StudentName {
    @Column(name = "first_name")
    private String fName;

    @Column(name = "last_name")
    private String lName;

    public StudentName(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public StudentName() {
    }


}
