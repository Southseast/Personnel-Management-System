package cc.southseast.controller.function.insert;

import cc.southseast.controller.verification.sm3.SM3Digest;
import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.hint.HintPanel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.ZoneId;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.EMP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.getEmpCacheData;
import static cc.southseast.view.ui.base.StaticData.EMAIL_INSERT_ERROR_HINT;
import static cc.southseast.view.ui.base.StaticData.EMPNO_INSERT_ERROR_HINT;
import static com.xiaoleilu.hutool.lang.Validator.isEmail;
import static com.xiaoleilu.hutool.util.NumberUtil.isInteger;

/**
 * @Author: Southseast
 * @Date: 2019/1/4 11:56 PM
 * @Version 1.0
 * 添加职工信息
 */
public class ToInsertEmp implements EventHandler<ActionEvent> {

    private TableView tableView;
    private JFXTextField empNoInput;
    private JFXTextField empNameInput;
    private JFXTextField empPasswordInput;
    private JFXComboBox empSexInput;
    private DatePicker empBirthdayInput;
    private DatePicker entryTimeInput;
    private JFXTextField empTelphoneInput;
    private JFXTextField empEmailInput;
    private Stage addStage;
    private Employee employee = new Employee();

    public ToInsertEmp(TableView tableView, JFXTextField empNoInput, JFXTextField empNameInput,
                       JFXTextField empPasswordInput, JFXComboBox empSexInput,
                       DatePicker empBirthdayInput, DatePicker entryTimeInput,
                       JFXTextField empTelphoneInput, JFXTextField empEmailInput, Stage addStage) {
        this.tableView = tableView;
        this.empNoInput = empNoInput;
        this.empNameInput = empNameInput;
        this.empPasswordInput = empPasswordInput;
        this.empSexInput = empSexInput;
        this.empBirthdayInput = empBirthdayInput;
        this.entryTimeInput = entryTimeInput;
        this.empTelphoneInput = empTelphoneInput;
        this.empEmailInput = empEmailInput;
        this.addStage = addStage;
    }

    @Override
    public void handle(ActionEvent event) {

        String empNo = empNoInput.getText().trim();

        // 判断主键是是数字以及长度
        if (empNo.length() < 16 && isInteger(empNo)) {

            // 判断主键是否重复
            if (dao.fetch(Employee.class, Long.parseLong(empNo)) == null) {

                String email = empEmailInput.getText().trim();

                if (isEmail(email)) {

                    employee.setEmpNo(Long.parseLong(empNoInput.getText()));
                    employee.setEmpName(empNameInput.getText());
                    employee.setEmpPassword(SM3Digest.encode(empPasswordInput.getText()));
                    employee.setEmpSex((String) empSexInput.getValue());

                    if (empBirthdayInput.getValue() == null) {
                        employee.setEmpBirthday(new Date(new java.util.Date().getTime()));
                    } else {
                        employee.setEmpBirthday(
                                new Date(Date.from(empBirthdayInput.getValue().atStartOfDay()
                                        .atZone(ZoneId.systemDefault()).toInstant()).getTime()));
                    }

                    if (entryTimeInput.getValue() == null) {
                        employee.setEntryTime(new Date(new java.util.Date().getTime()));
                    } else {
                        employee.setEntryTime(
                                new Date(Date.from(entryTimeInput.getValue().atStartOfDay()
                                        .atZone(ZoneId.systemDefault()).toInstant()).getTime()));
                    }

                    employee.setEmpTelphone(empTelphoneInput.getText());
                    employee.setEmpEmail(empEmailInput.getText());
                    employee.setCheck(false);
                    employee.setAdmin(false);
                    dao.create(Employee.class, false);
                    dao.insert(employee);

                    EMP_CACHE_DATA = getEmpCacheData();

                    tableView.getItems().clear();
                    tableView.setItems(EMP_CACHE_DATA);
                    tableView.refresh();
                    addStage.close();
                } else {

                    // 错误提示
                    new HintPanel(empNoInput, EMAIL_INSERT_ERROR_HINT);
                }
            } else {

                // 错误提示
                new HintPanel(empNoInput, EMPNO_INSERT_ERROR_HINT);
            }

        } else {

            // 错误提示
            new HintPanel(empNoInput, EMPNO_INSERT_ERROR_HINT);
        }
    }

}
