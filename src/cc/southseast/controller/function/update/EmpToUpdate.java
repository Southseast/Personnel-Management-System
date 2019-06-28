package cc.southseast.controller.function.update;

import cc.southseast.controller.verification.sm3.SM3Digest;
import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.manage.emp.EmpManagePanel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import org.nutz.dao.Cnd;

import java.sql.Date;
import java.time.ZoneId;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.USER;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 11:34 PM
 * @Version 1.0
 * 职工更新
 */
public class EmpToUpdate implements EventHandler<ActionEvent> {

    private JFXComboBox empSexInput;
    private DatePicker empBirthdayInput;
    private JFXTextField empTelphoneInput;
    private JFXTextField empEmailInput;
    private JFXTextField empPasswordInput;

    public EmpToUpdate(JFXComboBox empSexInput, DatePicker empBirthdayInput,
                       JFXTextField empTelphoneInput, JFXTextField empEmailInput, JFXTextField empPasswordInput) {
        this.empSexInput = empSexInput;
        this.empBirthdayInput = empBirthdayInput;
        this.empTelphoneInput = empTelphoneInput;
        this.empEmailInput = empEmailInput;
        this.empPasswordInput = empPasswordInput;
    }

    @Override
    public void handle(ActionEvent event) {

        Employee employee = dao.fetch(Employee.class, USER.getEmpNo());
        if (!employee.getEmpPassword().equals(empPasswordInput.getText())) {
            employee.setEmpPassword(SM3Digest.encode(empPasswordInput.getText()));
        }
        employee.setEmpSex((String) empSexInput.getValue());
        if (empBirthdayInput.getValue() != null) {
            employee.setEmpBirthday(
                    new Date(Date.from(empBirthdayInput.getValue().atStartOfDay()
                            .atZone(ZoneId.systemDefault()).toInstant()).getTime()));
        }
        employee.setEmpTelphone(empTelphoneInput.getText());
        employee.setEmpEmail(empEmailInput.getText());
        employee.setAdmin(false);
        dao.update(employee);

        System.out.println(empSexInput.getParent().getParent().getParent().getParent());
        VBox root = (VBox) empSexInput.getParent().getParent().getParent().getParent();

        // 获取用户信息
        USER = dao.fetchByJoin(Employee.class, null, Cnd.where("empNo", "=", USER.getEmpNo()));

        //创建管理面板
        EmpManagePanel empManagePanel = new EmpManagePanel();

        // 清除所有子级
        root.getChildren().clear();
        // 添加管理面板
        root.getChildren().addAll(empManagePanel);
    }
}
