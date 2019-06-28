package cc.southseast.view.ui.manage.admin.managemain;

import cc.southseast.controller.change.insert.ToDepInsertPanel;
import cc.southseast.controller.function.batchdelete.ToBatchDeleteDep;
import cc.southseast.controller.function.search.ToSearchDep;
import cc.southseast.view.ui.manage.admin.tableview.DepTableView;
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
 * 部门管理界面
 */
public class DepManageMain extends VBox {

    private JFXTextField searchInput = new JFXTextField();

    private JFXButton searchButton = new JFXButton(SEARCH_BUTTON_NAME);

    private JFXButton addButton = new JFXButton(ADD_BUTTON_NAME);

    private JFXButton batchDeleteButton = new JFXButton(BATCH_DELETE_BUTTON_NAME);

    private HBox functionBar = new HBox();

    private DepTableView depTableView = new DepTableView();

    public DepManageMain() {

        // 引入样式文件
        this.getStylesheets().add(MANAGE_MAIN_CSS_ROUTE);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setSpacing(20);

        searchInput.setId(SEARCH_INPUT_CSS_ID);
        searchInput.setPadding(new Insets(0, 0, 0, 10));
        searchInput.setMinHeight(30);
        searchInput.setPromptText(SEARCH_DEP_INPUT_PROMPT_TEXT);

        searchButton.setId(SEARCH_BUTTON_CSS_ID);
        searchButton.setOnAction(new ToSearchDep(searchInput, depTableView));

        addButton.setId(ADD_BUTTON_CSS_ID);
        addButton.setOnAction(new ToDepInsertPanel(depTableView));

        batchDeleteButton.setId(BATCH_DELETE_BUTTON_CSS_ID);
        batchDeleteButton.setOnAction(new ToBatchDeleteDep(depTableView));

        functionBar.setSpacing(20);
        functionBar.getChildren().addAll(searchInput, searchButton, addButton, batchDeleteButton);

        this.getChildren().addAll(functionBar, depTableView);
    }
}