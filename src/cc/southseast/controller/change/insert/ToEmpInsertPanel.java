package cc.southseast.controller.change.insert;

import cc.southseast.view.ui.base.BasePanel;
import cc.southseast.view.ui.command.insert.EmpInsertPanel;
import cc.southseast.view.ui.manage.admin.tableview.EmpTableView;
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
 * 触发职员添加界面
 */
public class ToEmpInsertPanel implements EventHandler<ActionEvent> {

    private TableView tableView = new TableView();

    private EmpTableView empTableView;

    public ToEmpInsertPanel(EmpTableView empTableView) {
        this.empTableView = empTableView;
    }

    @Override
    public void handle(ActionEvent event) {

        tableView = (TableView) empTableView.getChildren().get(0);

        Stage addStage = new Stage();

        EmpInsertPanel empInsertPanel = new EmpInsertPanel(tableView, addStage);

        BasePanel root = new BasePanel();

        root.windowsInit(addStage);

        root.getSubject().getChildren().addAll(empInsertPanel);

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
