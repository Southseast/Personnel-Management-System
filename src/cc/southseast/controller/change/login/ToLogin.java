package cc.southseast.controller.change.login;

import cc.southseast.controller.verification.sm3.SM3Digest;
import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.hint.HintPanel;
import cc.southseast.view.ui.manage.admin.ManagePanel;
import cc.southseast.view.ui.manage.emp.EmpManagePanel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import org.nutz.dao.Cnd;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.USER;
import static cc.southseast.view.ui.base.StaticData.LOGIN_ERROR_HINT;
import static com.xiaoleilu.hutool.util.NumberUtil.isInteger;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 8:50 AM
 * @Version 1.0
 * 触发管理界面
 */
public class ToLogin implements EventHandler<ActionEvent> {

    // 账号输入
    private JFXTextField idInput;
    // 密码输入
    private JFXPasswordField passwordInput;

    public ToLogin(JFXTextField idInput, JFXPasswordField passwordInput) {

        this.idInput = idInput;
        this.passwordInput = passwordInput;
    }

    @Override
    public void handle(ActionEvent event) {

        // 获取账号
        String id = idInput.getText().trim();
        // 获取密码
        String password = passwordInput.getText().trim();

        // 判断输入是否为空以及账号长度
        if (id.length() < 16 && isInteger(id)) {

            // 加密
            password = SM3Digest.encode(password);

            // 判断账号是否存在
            if (dao.fetch(Employee.class, Long.parseLong(id)) != null) {

                // 获取用户信息
                USER = dao.fetchByJoin(Employee.class, null, Cnd.where("empNo", "=", id));

                // 判断密码
                if (USER.getEmpPassword().equals(password)) {

                    // 是管理
                    if (USER.isAdmin()) {

                        // 获取父级
                        VBox root = (VBox) idInput.getParent().getParent().getParent().getParent();

                        //创建管理面板
                        ManagePanel managePanel = new ManagePanel();

                        // 清除所有子级
                        root.getChildren().clear();
                        // 添加管理面板
                        root.getChildren().addAll(managePanel);
                    }
                    // 不是管理
                    else {

                        // 获取父级
                        VBox root = (VBox) idInput.getParent().getParent().getParent().getParent();

                        // 创建管理面板
                        EmpManagePanel empManagePanel = new EmpManagePanel();

                        // 清除所有子级
                        root.getChildren().clear();
                        // 添加管理面板
                        root.getChildren().addAll(empManagePanel);
                    }
                } else {

                    // 错误提示
                    new HintPanel(idInput, LOGIN_ERROR_HINT);
                }
            } else {

                // 错误提示
                new HintPanel(idInput, LOGIN_ERROR_HINT);
            }
        } else {

            // 错误提示
            new HintPanel(idInput, LOGIN_ERROR_HINT);
        }
    }
}