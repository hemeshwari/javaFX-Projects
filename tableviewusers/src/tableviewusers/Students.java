/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewusers;

/**
 *
 * @author Dell
 */
public class Students {
    Integer studentId;
    String studentName,studentEmail,studentPassword,studentCourse;

    public Students(Integer studentId, String studentName, String studentEmail, String studentPassword, String studentCourse) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPassword = studentPassword;
        this.studentCourse = studentCourse;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }
    
    
    
}
