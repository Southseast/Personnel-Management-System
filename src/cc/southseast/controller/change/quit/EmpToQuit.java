package cc.southseast.controller.change.quit;

import cc.southseast.view.ui.base.BasePanel;
import cc.southseast.view.ui.login.LoginPanel;
import cc.southseast.view.ui.manage.emp.EmpManagePanel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 11:09 AM
 * @Version 1.0
 * 触发普通职工退出界面
 */
public class EmpToQuit implements EventHandler<MouseEvent> {

    private EmpManagePanel empManagePanel;

    public EmpToQuit(EmpManagePanel empManagePanel) {
        this.empManagePanel = empManagePanel;
    }

    @Override
    public void handle(MouseEvent event) {

        //创建管理面板
        LoginPanel loginPanel = new LoginPanel();

        BasePanel root = (BasePanel) empManagePanel.getParent().getParent().getParent();

        root.getSubject().getChildren().clear();
        root.getSubject().getChildren().addAll(loginPanel);

    }
}
