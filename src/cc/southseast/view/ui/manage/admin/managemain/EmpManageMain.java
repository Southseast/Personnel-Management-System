package cc.southseast.view.ui.manage.admin.managemain;

import cc.southseast.controller.change.insert.ToEmpInsertPanel;
import cc.southseast.controller.function.batchdelete.ToBatchDeleteEmp;
import cc.southseast.controller.function.search.ToSearchEmp;
import cc.southseast.view.ui.manage.admin.tableview.EmpTableView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 9:30 AM
 * @Version 1.0
 * 职工管理界面
 */
public class EmpManageMain extends VBox {

    private JFXTextField searchInput = new JFXTextField();

    private JFXButton searchButton = new JFXButton(SEARCH_BUTTON_NAME);

    private JFXButton addButton = new JFXButton(ADD_BUTTON_NAME);

    private JFXButton batchDeletionButton = new JFXButton(BATCH_DELETE_BUTTON_NAME);

    private HBox functionBar = new HBox();

    private EmpTableView empTableView = new EmpTableView();

    public EmpManageMain() {

        // 引入样式文件
        this.getStylesheets().add(MANAGE_MAIN_CSS_ROUTE);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setSpacing(20);

        searchInput.setId(SEARCH_INPUT_CSS_ID);
        searchInput.setPadding(new Insets(0, 0, 0, 10));
        searchInput.setMinHeight(30);
        searchInput.setPromptText(SEARCH_EMP_INPUT_PROMPT_TEXT);

        searchButton.setId(SEARCH_BUTTON_CSS_ID);
        searchButton.setOnAction(new ToSearchEmp(searchInput, empTableView));

        addButton.setId(ADD_BUTTON_CSS_ID);
        addButton.setOnAction(new ToEmpInsertPanel(empTableView));

        batchDeletionButton.setId(BATCH_DELETE_BUTTON_CSS_ID);
        batchDeletionButton.setOnAction(new ToBatchDeleteEmp(empTableView));

        functionBar.setSpacing(20);
        functionBar.getChildren().addAll(searchInput, searchButton, addButton, batchDeletionButton);

        this.getChildren().addAll(functionBar, empTableView);
    }
}