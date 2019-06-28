package cc.southseast.controller.change.manage;

import cc.southseast.view.ui.manage.admin.ManagePanel;
import cc.southseast.view.ui.manage.admin.managemain.EmpManageMain;
import cc.southseast.view.ui.manage.admin.tableview.EmpTableView;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import static cc.southseast.controller.base.ToGetData.EMP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.getRelCacheData;
import static cc.southseast.view.ui.base.StaticData.NO_SELECCTED_STYLE;
import static cc.southseast.view.ui.base.StaticData.SELECCTED_STYLE;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 9:34 PM
 * @Version 1.0
 * 触发职员管理界面
 */
public class ToEmpManagePanel implements EventHandler<MouseEvent> {

    private ManagePanel managePanel;

    private EmpManageMain empManageMain = new EmpManageMain();

    public ToEmpManagePanel(ManagePanel managePanel) {

        this.managePanel = managePanel;
    }

    @Override
    public void handle(MouseEvent event) {

        managePanel.getEmpManageButton().setStyle(SELECCTED_STYLE);
        managePanel.getDepManageButton().setStyle(NO_SELECCTED_STYLE);
        managePanel.getRelManageButton().setStyle(NO_SELECCTED_STYLE);
        managePanel.getManageMain().getChildren().clear();
        managePanel.getManageMain().getChildren().addAll(empManageMain);

        EmpTableView empTableView = new EmpTableView();
        empTableView = (EmpTableView) empManageMain.getChildren().get(1);
        TableView tableView = (TableView) empTableView.getChildren().get(0);

        EMP_CACHE_DATA = getRelCacheData();
        tableView.setItems(EMP_CACHE_DATA);
    }
}
