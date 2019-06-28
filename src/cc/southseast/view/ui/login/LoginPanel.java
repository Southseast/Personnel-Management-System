package cc.southseast.view.ui.login;

import cc.southseast.view.ui.title.TitlePanel;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 8:33 AM
 * @Version 1.0
 * 登陆界面
 */
public class LoginPanel extends VBox {

    // 标题
    private TitlePanel titlePanel = new TitlePanel(LOGIN_LITTLE_TITLE);

    // 登录界面背景
    private HBox backgroundImage = new HBox();

    // 登录组件整合
    private LoginMain loginMain = new LoginMain();

    private HBox subject = new HBox();

    public LoginPanel() {

        // 引入样式文件
        this.getStylesheets().add(LOGIN_PANEL_CSS_ROUTE);
        // 设置CSSID
        this.setId(LOGIN_PANEL_CSS_ID);
        // 设置窗口大小
        this.setMinSize(1024, 768);
        this.setMaxSize(1024, 768);
        // 设置间隔
        this.setPadding(new Insets(50, 50, 0, 50));
        this.setSpacing(205);

        // 设置背景图片
        backgroundImage.setMinSize(300, 330);
        backgroundImage.setId(BACKGROUND_IMAGE_CSS_ID);

        subject.getChildren().addAll(backgroundImage, loginMain);
        subject.setSpacing(50);

        // 添加为子节点
        this.getChildren().addAll(titlePanel, subject);
    }
}
