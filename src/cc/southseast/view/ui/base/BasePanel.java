package cc.southseast.view.ui.base;

import cc.southseast.controller.base.ToClose;
import cc.southseast.controller.base.ToDrag;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 8:32 AM
 * @Version 1.0
 * 基本界面
 */
public class BasePanel extends HBox {

    // 根节点
    private VBox root = new VBox();

    // 标题栏
    private AnchorPane title = new AnchorPane();

    // 关闭按钮
    private Button closeWindowsButton = new Button();

    // 主窗体
    private VBox subject = new VBox();

    public BasePanel() {

        // 引入样式
        this.getStylesheets().add(BASE_PANEL_CSS_ROUTE);
        // 引入CSSID
        this.setId(BASE_PANEL_CSS_ID);
        // 阴影范围
        this.setPadding(new Insets(10, 10, 10, 10));

        // 设置关闭按钮大小
        closeWindowsButton.setPrefSize(20, 20);
        // 设置CSSID
        closeWindowsButton.setId(CLOSE_WINDOWS_BUTTON_CSS_ID);

        // 设置关闭按钮为标题栏子级
        title.getChildren().add(closeWindowsButton);
        // 设置标题栏绝对停靠右部位置
        AnchorPane.setRightAnchor(closeWindowsButton, 5.0);
        // 设置标题栏绝对停靠顶部位置
        AnchorPane.setTopAnchor(closeWindowsButton, 5.0);

        // 设置CSSID
        root.setId(ROOT_CSS_ID);
        // 设置子节点
        root.getChildren().addAll(title, subject);

        // 外部透明框，为做阴影效果
        this.getChildren().addAll(root);
    }

    // 窗体初始化
    public void windowsInit(Stage primaryStage) {

        // 隐藏原窗体
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        // 设置全局拖动
        ToDrag toDrag = new ToDrag(primaryStage);
        toDrag.enableDrag(this);

        // 关闭功能
        closeWindowsButton.setOnAction(new ToClose(primaryStage));
    }

    public VBox getSubject() {
        return subject;
    }

}
