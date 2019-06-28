package cc.southseast.controller.change.manage;

import cc.southseast.view.ui.manage.admin.ManagePanel;
import cc.southseast.view.ui.manage.admin.managemain.RelManageMain;
import cc.southseast.view.ui.manage.admin.tableview.RelTableView;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import static cc.southseast.controller.base.ToGetData.getRelCacheData;
import static cc.southseast.controller.base.ToGetData.REL_CACHE_DATA;
import static cc.southseast.view.ui.base.StaticData.NO_SELECCTED_STYLE;
import static cc.southseast.view.ui.base.StaticData.SELECCTED_STYLE;

/**
 * @Author: Southseast
 * @Date: 2019/1/4 1:34 PM
 * @Version 1.0
 * 触发职员部门管理界面
 */
public class ToRelManagePanel implements EventHandler<MouseEvent> {

    private ManagePanel managePanel;

    private RelManageMain relManageMain = new RelManageMain();

    public ToRelManagePanel(ManagePanel managePanel) {

        this.managePanel = managePanel;
    }

    @Override
    public void handle(MouseEvent event) {

        managePanel.getEmpManageButton().setStyle(NO_SELECCTED_STYLE);
        managePanel.getDepManageButton().setStyle(NO_SELECCTED_STYLE);
        managePanel.getRelManageButton().setStyle(SELECCTED_STYLE);
        managePanel.getManageMain().getChildren().clear();
        managePanel.getManageMain().getChildren().addAll(relManageMain);

        RelTableView relTableView = (RelTableView) relManageMain.getChildren().get(1);
        TableView tableView = (TableView) relTableView.getChildren().get(0);

        REL_CACHE_DATA = getRelCacheData();
        tableView.setItems(REL_CACHE_DATA);
    }
}
