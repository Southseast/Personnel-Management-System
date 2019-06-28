package cc.southseast.view.ui.manage.admin;

import cc.southseast.controller.base.ToHelp;
import cc.southseast.controller.change.manage.ToDepManagePanel;
import cc.southseast.controller.change.manage.ToEmpManagePanel;
import cc.southseast.controller.change.manage.ToRelManagePanel;
import cc.southseast.controller.change.quit.AdminToQuit;
import cc.southseast.view.ui.clock.AdminClockPanel;
import cc.southseast.view.ui.manage.admin.managemain.EmpManageMain;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/2 9:00 AM
 * @Version 1.0
 * 管理界面
 */
public class ManagePanel extends HBox {

    private BorderPane chooseMain = new BorderPane();

    private HBox manageMain = new HBox();

    private EmpManageMain empManageMain = new EmpManageMain();

    private JFXButton empManageButton = new JFXButton(EMP_MANAGE_NAME);

    private JFXButton depManageButton = new JFXButton(DEP_MANAGE_NAME);

    private JFXButton relManageButton = new JFXButton(REL_MANAGE_NAME);

    private JFXButton quitButton = new JFXButton(QUIT_BUTTON_NAME);

    private JFXButton helpButton = new JFXButton(HELP_BUTTON_NAME);

    private AdminClockPanel adminClockPanel = new AdminClockPanel();

    private VBox menuBar = new VBox();

    private VBox bottomBar = new VBox();

    public ManagePanel() {

        // 引入样式文件
        this.getStylesheets().add(MANAGE_PANEL_CSS_ROUTE);
        this.setId(MANAGE_PANEL_CSS_ID);

        helpButton.setId(HELP_BUTTON_CSS_ID);
        helpButton.setOnAction(new ToHelp());
        helpButton.setMinSize(100, 50);
        chooseMain.setId(CHOOSE_MAIN_CSS_ID);

        empManageButton.setId(EMP_MANAGE_BUTTON_CSS_ID);
        empManageButton.setMinSize(100, 50);
        empManageButton.setStyle(SELECCTED_STYLE);
        empManageButton.setOnMousePressed(new ToEmpManagePanel(this));

        depManageButton.setId(DEP_MANAGE_BUTTON_CSS_ID);
        depManageButton.setMinSize(100, 50);
        depManageButton.setOnMousePressed(new ToDepManagePanel(this));

        relManageButton.setId(REL_MANAGE_BUTTON_CSS_ID);
        relManageButton.setMinSize(100, 50);
        relManageButton.setOnMousePressed(new ToRelManagePanel(this));

        quitButton.setId(QUIT_BUTTON_CSS_ID);
        quitButton.setMinSize(100, 50);
        quitButton.setOnMousePressed(new AdminToQuit(this));

        menuBar.getChildren().addAll(empManageButton, depManageButton, relManageButton, helpButton);

        bottomBar.setSpacing(10);
        bottomBar.getChildren().addAll(adminClockPanel, quitButton);

        chooseMain.setTop(menuBar);
        chooseMain.setBottom(bottomBar);
        chooseMain.setMinSize(100, 768);

        manageMain.setMinSize(884, 768);
        manageMain.getChildren().addAll(empManageMain);

        this.getChildren().addAll(chooseMain, manageMain);
    }

    public JFXButton getEmpManageButton() {
        return empManageButton;
    }

    public JFXButton getDepManageButton() {
        return depManageButton;
    }

    public JFXButton getRelManageButton() {
        return relManageButton;
    }

    public HBox getManageMain() {
        return manageMain;
    }

}
