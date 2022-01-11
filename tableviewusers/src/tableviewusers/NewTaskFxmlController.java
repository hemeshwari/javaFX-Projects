package tableviewusers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class NewTaskFxmlController implements Initializable {

    @FXML
    private TableView<Students> tbl_student;
    @FXML
    private TableColumn<Students, Integer> col_st_id;
    @FXML
    private TableColumn<Students, String> col_st_name;
    @FXML
    private TableColumn<Students, String> col_st_email;
    @FXML
    private TableColumn<Students, String> col_st_password;
    @FXML
    private TableColumn<Students, String> col_st_course;
    @FXML
    private TextField txt_st_id;
    @FXML
    private TextField txt_st_name;
    @FXML
    private TextField txt_st_email;
    @FXML
    private PasswordField txt_st_password;
    @FXML
    private TextField txt_st_course;

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    ObservableList<Students> listM;
    ObservableList<Students> dataList;

    int index = -1;
    @FXML
    private TextField txt_st_search;

    /**
     * Initializes the controller class.
     */
    private void tableStudents() {
        col_st_id.setCellValueFactory(new PropertyValueFactory<Students, Integer>("studentId"));
        col_st_name.setCellValueFactory(new PropertyValueFactory<Students, String>("studentName"));
        col_st_email.setCellValueFactory(new PropertyValueFactory<Students, String>("studentEmail"));
        col_st_password.setCellValueFactory(new PropertyValueFactory<Students, String>("studentPassword"));
        col_st_course.setCellValueFactory(new PropertyValueFactory<Students, String>("studentCourse"));

        listM = mysqlconnect.getDataStudents();
        tbl_student.setItems(listM);

    }

    public void clearTextField() {
        txt_st_id.clear();
        txt_st_name.clear();
        txt_st_email.clear();
        txt_st_password.clear();
        txt_st_course.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //txt_st_course.getItems().addAll("BE","B.Tech","ME","M.Tech","B.Com","M.Com","BA","MA","PHD");

        tableStudents();
        search_students();

    }

    @FXML
    private void add_students(ActionEvent event) {
        con = mysqlconnect.ConnectDb();
        String sql = "insert into Students (student_name,student_password,student_course_name,student_email) values (?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, txt_st_name.getText());
            ps.setString(2, txt_st_password.getText());
            ps.setString(3, txt_st_course.getText());
            ps.setString(4, txt_st_email.getText());
            ps.execute();
            System.out.println("add students successfull!");
            tableStudents();

            clearTextField();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void get_selected(MouseEvent event) {
        index = tbl_student.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txt_st_id.setText(col_st_id.getCellData(index).toString());
        txt_st_name.setText(col_st_name.getCellData(index).toString());
        txt_st_password.setText(col_st_password.getCellData(index).toString());
        txt_st_email.setText(col_st_email.getCellData(index).toString());
        txt_st_course.setText(col_st_course.getCellData(index).toString());
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        con = mysqlconnect.ConnectDb();
        String query = "delete from students where student_id = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, txt_st_id.getText());
            System.out.println(query);
            System.out.println(query);
            ps.execute();

            tableStudents();

            clearTextField();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void edit_students(ActionEvent event) {
        try {
            con = mysqlconnect.ConnectDb();

            String v1 = txt_st_id.getText();
            String v2 = txt_st_name.getText();
            String v3 = txt_st_password.getText();
            String v4 = txt_st_email.getText();
            String v5 = txt_st_course.getText();

            String query = "update students set student_id='" + v1 + "',student_name='" + v2 + "',student_password='"
                    + v3 + "',student_email='" + v4 + "',student_course_name='" + v5 + "'where student_id='" + v1 + "'";

            ps = con.prepareStatement(query);

            ps.execute();

            System.out.println("students edited successfully");

            tableStudents();
            clearTextField();
        } catch (Exception e) {
            System.out.println("students edited unsuccessfull");
            e.printStackTrace();
        }
    }

    private void search_students() {
        col_st_id.setCellValueFactory(new PropertyValueFactory<Students, Integer>("studentId"));
        col_st_name.setCellValueFactory(new PropertyValueFactory<Students, String>("studentName"));
        col_st_email.setCellValueFactory(new PropertyValueFactory<Students, String>("studentEmail"));
        col_st_password.setCellValueFactory(new PropertyValueFactory<Students, String>("studentPassword"));
        col_st_course.setCellValueFactory(new PropertyValueFactory<Students, String>("studentCourse"));

        dataList = mysqlconnect.getDataStudents();
        tbl_student.setItems(dataList);

        FilteredList<Students> filteredData = new FilteredList<>(dataList, b -> true);
        txt_st_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercaseFilter = newValue.toLowerCase();

                if (student.getStudentName().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else if (student.getStudentPassword().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else if (student.getStudentEmail().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else if (student.getStudentCourse().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        SortedList<Students> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbl_student.comparatorProperty());
        tbl_student.setItems(sortedData);

    }

}
