package cc.southseast.view.ui.hint;

import cc.southseast.view.ui.base.BasePanel;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static cc.southseast.view.ui.base.StaticData.HINT_LABEL_STYLE;

/**
 * @Author: Southseast
 * @Date: 2019/1/3 11:22 PM
 * @Version 1.0
 * 提示面板
 */
public class HintPanel extends HBox {

    // 提示语
    private Label hintLabel = new Label();

    // 根页面
    private BasePanel root = new BasePanel();

    // 新舞台
    private Stage hintStage = new Stage();

    // 场景
    private Scene scene = new Scene(root);

    // 原舞台
    private Stage stage;

    public HintPanel(JFXTextField input, String hint) {

        // 获取原舞台
        stage = (Stage) input.getScene().getWindow();

        // 设置提示语
        hintLabel.setText(hint);
        // 设置样式
        hintLabel.setStyle(HINT_LABEL_STYLE);

        // 设置间隔
        this.setPadding(new Insets(0, 20, 20, 20));
        // 设置子节点
        this.getChildren().addAll(hintLabel);

        // 初始化当前窗体
        root.windowsInit(hintStage);
        // 添加提示面版到窗体
        root.getSubject().getChildren().addAll(this);

        // 设置透明度
        scene.setFill(Color.TRANSPARENT);

        // 设置当前窗体最前
        hintStage.initModality(Modality.WINDOW_MODAL);
        hintStage.initOwner(stage);
        hintStage.setScene(scene);
        hintStage.show();
    }

}
