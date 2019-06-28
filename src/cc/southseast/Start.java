package cc.southseast;

import cc.southseast.view.ui.base.BasePanel;
import cc.southseast.view.ui.manage.admin.ManagePanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 8:30 AM
 * @Version 1.0
 * 启动项
 */
public class Start extends Application {

    // 根页面
    private BasePanel root = new BasePanel();

    // 登录界面
    private ManagePanel loginPanel = new ManagePanel();

    // 场景
    private Scene scene = new Scene(root);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // 添加登陆界面
        root.windowsInit(stage);
        root.getSubject().getChildren().addAll(loginPanel);

        // 设置透明度
        scene.setFill(Color.TRANSPARENT);

        // 添加舞台
        stage.setScene(scene);
        // 显示舞台
        stage.show();
    }
}