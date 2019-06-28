package cc.southseast.controller.change.insert;

import cc.southseast.view.ui.base.BasePanel;
import cc.southseast.view.ui.command.insert.DepInsertPanel;
import cc.southseast.view.ui.manage.admin.tableview.DepTableView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 7:09 PM
 * @Version 1.0
 * 触发部门添加界面
 */
public class ToDepInsertPanel implements EventHandler<ActionEvent> {

    private TableView tableView = new TableView();

    private DepTableView depTableView;

    public ToDepInsertPanel(DepTableView depTableView) {
        this.depTableView = depTableView;
    }

    @Override
    public void handle(ActionEvent event) {

        tableView = (TableView) depTableView.getChildren().get(0);

        Stage addStage = new Stage();

        DepInsertPanel depInsertPanel = new DepInsertPanel(tableView, addStage);

        BasePanel root = new BasePanel();

        root.windowsInit(addStage);

        root.getSubject().getChildren().addAll(depInsertPanel);

        Scene scene = new Scene(root);

        Stage stage = (Stage) tableView.getScene().getWindow();

        // 设置透明度
        scene.setFill(Color.TRANSPARENT);

        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(stage);
        addStage.setScene(scene);
        addStage.show();

    }
}
