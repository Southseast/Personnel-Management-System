package cc.southseast.view.ui.manage.admin.tableview;


import cc.southseast.controller.function.delete.ToDeleteEmp;
import cc.southseast.model.dao.Employee;
import cc.southseast.view.ui.command.update.EmpUpdatePanel;
import cc.southseast.view.ui.manage.admin.CheckBox;
import cc.southseast.view.ui.manage.admin.CommandBar;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.Date;

import static cc.southseast.controller.base.ToGetData.EMP_CACHE_DATA;
import static cc.southseast.controller.base.ToGetData.getEmpCacheData;
import static cc.southseast.view.ui.base.StaticData.*;

/**
 * @Author: Southseast
 * @Date: 2019/1/5 9:33 PM
 * @Version 1.0
 * 职工表
 */
public class EmpTableView extends HBox {

    private TableView tableView = new TableView();
    private TableColumn checkBoxColumn = new TableColumn<Employee, Boolean>();
    private TableColumn num = new TableColumn<Employee, Integer>(NUM_NAME);
    private TableColumn empNo = new TableColumn<Employee, Long>(EMP_NO_NAME);
    private TableColumn empName = new TableColumn<Employee, String>(EMP_NAME_NAME);
    private TableColumn empSex = new TableColumn<Employee, String>(EMP_SEX_NAME);
    private TableColumn empBirthday = new TableColumn<Employee, Date>(EMP_BIRTHDAY_NAME);
    private TableColumn depNo = new TableColumn<Employee, String>(DEP_NAME_NAME);
    private TableColumn entryTime = new TableColumn<Employee, String>(ENTRY_TIME_NAME);
    private TableColumn empTelphone = new TableColumn<Employee, String>(EMP_TELPHONE_NAME);
    private TableColumn empEmail = new TableColumn<Employee, String>(EMP_EMAIL_NAME);
    private TableColumn commandbar = new TableColumn<>(COMMAND_BAR_NAME);
    private Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>> cellCheckBox;
    private Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>> cellCommandBar;

    public EmpTableView() {

        // 勾选框
        cellCheckBox = new Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>>() {
            public TableCell<Employee, Boolean> call(TableColumn<Employee, Boolean> param) {
                CheckBox<Employee, Boolean> cell = new CheckBox<>();
                JFXCheckBox jfxCheckBox = (JFXCheckBox) cell.getGraphic();
                jfxCheckBox.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Employee employee = EMP_CACHE_DATA.get(cell.getIndex());
                        if (employee.getCheck()) {
                            employee.setCheck(false);
                        } else {
                            employee.setCheck(true);
                        }
                    }
                });
                return cell;
            }
        };
        checkBoxColumn.setCellFactory(cellCheckBox);
        checkBoxColumn.setMaxWidth(25);

        // 序号框
        num.setCellFactory((col) -> {
            TableCell<Employee, String> cell = new TableCell<Employee, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });

        empNo.setCellValueFactory(new PropertyValueFactory<Employee, Long>(EMP_NO_BEAN_ID));

        empName.setCellValueFactory(new PropertyValueFactory<Employee, String>(EMP_NAME_BEAN_ID));

        empSex.setCellValueFactory(new PropertyValueFactory<Employee, Date>(EMP_SEX_BEAN_ID));

        // 计算年龄
        empBirthday.setCellValueFactory(new PropertyValueFactory<Employee, Date>(EMP_BIRTHDAY_BEAN_ID));
        empBirthday.setCellFactory((col) -> {
            TableCell<Employee, Date> cell = new TableCell<Employee, Date>() {
                @Override
                public void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Employee employee = getTableView().getItems().get(getIndex());
                        long age = ((new java.sql.Date(new Date().getTime())).getTime()
                                - (employee.getEmpBirthday().getTime())) / 1000 / 60 / 60 / 24 / 365;
                        this.setText(String.valueOf(age));
                    }
                }
            };
            return cell;
        });

        depNo.setCellValueFactory(new PropertyValueFactory<Employee, String>(DEP_NO_BEAN_ID));

        // 计算工龄
        entryTime.setCellValueFactory(new PropertyValueFactory<Employee, String>(ENTRY_TIME_BEAN_ID));
        entryTime.setCellFactory((col) -> {
            TableCell<Employee, Date> cell = new TableCell<Employee, Date>() {
                @Override
                public void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Employee employee = getTableView().getItems().get(getIndex());
                        long age = ((new java.sql.Date(new Date().getTime())).getTime()
                                - (employee.getEntryTime().getTime())) / 1000 / 60 / 60 / 24 / 365;
                        this.setText(String.valueOf(age));
                    }
                }
            };
            return cell;
        });

        empTelphone.setCellValueFactory(new PropertyValueFactory<Employee, String>(EMP_TELPHONE_BEAN_ID));

        empEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>(EMP_EMAIL_BEAN_ID));

        // 删除修改操作
        cellCommandBar = new Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>>() {
            public TableCell<Employee, Boolean> call(TableColumn<Employee, Boolean> param) {
                CommandBar<Employee, Boolean> cell = new CommandBar<>();
                cell.getUserEdit().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new EmpUpdatePanel(EMP_CACHE_DATA.get(cell.getIndex()), tableView);
                    }
                });
                cell.getUserDelete().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new ToDeleteEmp(EMP_CACHE_DATA.get(cell.getIndex()).getEmpNo(), tableView);
                    }
                });
                return cell;
            }
        };
        commandbar.setCellFactory(cellCommandBar);
        commandbar.setMinWidth(110);

        tableView.setEditable(true);
        tableView.setId(TABLEVIEW_CSS_ID);
        tableView.getItems().clear();

        EMP_CACHE_DATA = getEmpCacheData();

        tableView.setItems(EMP_CACHE_DATA);
        tableView.setMinSize(884, 680);
        tableView.styleProperty();
        tableView.getColumns().setAll(checkBoxColumn, num, empNo, empName, empSex,
                empBirthday, entryTime, empTelphone, empEmail, commandbar);

        this.getChildren().addAll(tableView);
    }
}
