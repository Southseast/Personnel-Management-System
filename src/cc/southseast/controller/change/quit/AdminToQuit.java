package cc.southseast.controller.change.quit;

import cc.southseast.view.ui.base.BasePanel;
import cc.southseast.view.ui.login.LoginPanel;
import cc.southseast.view.ui.manage.admin.ManagePanel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 11:09 AM
 * @Version 1.0
 * 触发管理员退出界面
 */
public class AdminToQuit implements EventHandler<MouseEvent> {

    private ManagePanel managePanel;

    public AdminToQuit(ManagePanel managePanel) {
        this.managePanel = managePanel;
    }

    @Override
    public void handle(MouseEvent event) {

        //创建管理面板
        LoginPanel loginPanel = new LoginPanel();

        BasePanel root = (BasePanel) managePanel.getParent().getParent().getParent();

        root.getSubject().getChildren().clear();
        root.getSubject().getChildren().addAll(loginPanel);

    }
}
