package cc.southseast.controller.function.update;

import cc.southseast.controller.verification.sm3.SM3Digest;
import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.hint.HintPanel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.ZoneId;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.EMP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.getEmpCacheData;
import static cc.southseast.view.ui.base.StaticData.EMAIL_INSERT_ERROR_HINT;
import static com.xiaoleilu.hutool.lang.Validator.isEmail;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 11:34 PM
 * @Version 1.0
 * 职工更新
 */
public class ToUpdateEmp implements EventHandler<ActionEvent> {

    private TableView tableView;
    private Label empNoInput;
    private JFXTextField empNameInput;
    private JFXTextField empPasswordInput;
    private JFXComboBox empSexInput;
    private DatePicker empBirthdayInput;
    private DatePicker entryTimeInput;
    private JFXTextField empTelphoneInput;
    private JFXTextField empEmailInput;
    private Stage editStage;

    public ToUpdateEmp(TableView tableView, Label empNoInput, JFXTextField empNameInput,
                       JFXTextField empPasswordInput, JFXComboBox empSexInput,
                       DatePicker empBirthdayInput, DatePicker entryTimeInput,
                       JFXTextField empTelphoneInput, JFXTextField empEmailInput, Stage editStage) {
        this.tableView = tableView;
        this.empNoInput = empNoInput;
        this.empNameInput = empNameInput;
        this.empPasswordInput = empPasswordInput;
        this.empSexInput = empSexInput;
        this.empBirthdayInput = empBirthdayInput;
        this.entryTimeInput = entryTimeInput;
        this.empTelphoneInput = empTelphoneInput;
        this.empEmailInput = empEmailInput;
        this.editStage = editStage;
    }

    @Override
    public void handle(ActionEvent event) {


        String email = empEmailInput.getText().trim();

        if (isEmail(email)) {

            Employee employee = dao.fetch(Employee.class, Long.parseLong(empNoInput.getText()));
            employee.setEmpName(empNameInput.getText());
            if (!employee.getEmpPassword().equals(empPasswordInput.getText())) {
                employee.setEmpPassword(SM3Digest.encode(empPasswordInput.getText()));
            }
            employee.setEmpSex((String) empSexInput.getValue());
            if (empBirthdayInput.getValue() != null) {
                employee.setEmpBirthday(
                        new Date(Date.from(empBirthdayInput.getValue().atStartOfDay()
                                .atZone(ZoneId.systemDefault()).toInstant()).getTime()));
            }
            if (entryTimeInput.getValue() != null) {
                employee.setEntryTime(
                        new Date(Date.from(entryTimeInput.getValue().atStartOfDay()
                                .atZone(ZoneId.systemDefault()).toInstant()).getTime()));
            }
            employee.setEmpTelphone(empTelphoneInput.getText());
            employee.setEmpEmail(empEmailInput.getText());
            employee.setCheck(false);
            employee.setAdmin(false);
            dao.update(employee);

            EMP_CACHE_DATA = getEmpCacheData();

            tableView.getItems().clear();
            tableView.setItems(EMP_CACHE_DATA);
            tableView.refresh();
            editStage.close();
        } else {

            // 错误提示
            new HintPanel(empNameInput, EMAIL_INSERT_ERROR_HINT);
        }

    }
}
