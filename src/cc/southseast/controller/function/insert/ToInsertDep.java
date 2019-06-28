package cc.southseast.controller.function.insert;

import cc.southseast.model.dao.Department;
import cc.southseast.view.ui.hint.HintPanel;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import static cc.southseast.controller.base.ToConnect.dao;
import static cc.southseast.controller.base.ToGetData.DEP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.getDepCacheData;
import static cc.southseast.view.ui.base.StaticData.DEP_INSERT_ERROR_HINT;
import static com.xiaoleilu.hutool.util.NumberUtil.isInteger;

/**
 * @Author: Southseast
 * @Date: 2019/1/4 11:56 PM
 * @Version 1.0
 * 添加部门信息
 */
public class ToInsertDep implements EventHandler<ActionEvent> {

    private TableView tableView;

    private JFXTextField depNoInput;

    private JFXTextField depNameInput;

    private JFXTextField depAddressInput;

    private JFXTextField depPhoneInput;

    private Department department = new Department();

    private Stage addStage;

    public ToInsertDep(TableView tableView, JFXTextField depNoInput, JFXTextField depNameInput, JFXTextField depAddressInput, JFXTextField depPhoneInput, Stage addStage) {
        this.tableView = tableView;
        this.depNoInput = depNoInput;
        this.depNameInput = depNameInput;
        this.depAddressInput = depAddressInput;
        this.depPhoneInput = depPhoneInput;
        this.addStage = addStage;
    }

    @Override
    public void handle(ActionEvent event) {

        String depNo = depNoInput.getText().trim();

        // 判断主键是是数字以及长度
        if (depNo.length() < 16 && isInteger(depNo)) {
            // 判断主键是否重复
            if (dao.fetch(Department.class, Long.parseLong(depNo)) == null) {

                department.setDepNo(Long.parseLong(depNo));
                department.setDepName(depNameInput.getText().trim());
                department.setDepAddress(depAddressInput.getText().trim());
                department.setDepPhone(depPhoneInput.getText().trim());
                dao.insert(department);

                DEP_CACHE_DATA = getDepCacheData();

                tableView.getItems().clear();
                tableView.setItems(DEP_CACHE_DATA);
                tableView.refresh();
                addStage.close();
            } else {

                // 错误提示
                new HintPanel(depNoInput, DEP_INSERT_ERROR_HINT);
            }
        } else {

            // 错误提示
            new HintPanel(depNoInput, DEP_INSERT_ERROR_HINT);
        }
    }

}
