package cc.southseast.controller.change.manage;

import cc.southseast.view.ui.manage.admin.ManagePanel;
import cc.southseast.view.ui.manage.admin.managemain.DepManageMain;
import cc.southseast.view.ui.manage.admin.tableview.DepTableView;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import static cc.southseast.controller.base.ToGetData.DEP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.getDepCacheData;
import static cc.southseast.view.ui.base.StaticData.NO_SELECCTED_STYLE;
import static cc.southseast.view.ui.base.StaticData.SELECCTED_STYLE;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 1:34 PM
 * @Version 1.0
 * 触发部门管理界面
 */
public class ToDepManagePanel implements EventHandler<MouseEvent> {

    private ManagePanel managePanel;

    private DepManageMain depManageMain = new DepManageMain();

    public ToDepManagePanel(ManagePanel managePanel) {

        this.managePanel = managePanel;
    }

    @Override
    public void handle(MouseEvent event) {

        managePanel.getEmpManageButton().setStyle(NO_SELECCTED_STYLE);
        managePanel.getDepManageButton().setStyle(SELECCTED_STYLE);
        managePanel.getRelManageButton().setStyle(NO_SELECCTED_STYLE);
        managePanel.getManageMain().getChildren().clear();
        managePanel.getManageMain().getChildren().addAll(depManageMain);

        DepTableView depTableView = (DepTableView) depManageMain.getChildren().get(1);
        TableView tableView = (TableView) depTableView.getChildren().get(0);

        DEP_CACHE_DATA = getDepCacheData();
        tableView.setItems(DEP_CACHE_DATA);

    }
}
