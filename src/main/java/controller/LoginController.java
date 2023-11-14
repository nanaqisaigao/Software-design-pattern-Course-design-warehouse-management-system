package controller;

import dao.IUserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;
import patterns.memento.Memento;
import patterns.singleton.SingleRegisterStage;
import utils.DialogUtil;
import utils.MybatisUtil;

import java.io.IOException;

public class LoginController {

    // 获取FXML文件中的根节点
    @FXML
    private AnchorPane rootPane;

    // 获取FXML文件中的用户id输入框
    @FXML
    private TextField userid;

    // 获取FXML文件中的用户密码输入框
    @FXML
    private PasswordField userpwd;

    // 获取SqlSession对象
    private SqlSession sqlSession = MybatisUtil.getSession();
    // 创建userDao接口，然后通过反射来获取代理对象
    private IUserDao userDao = sqlSession.getMapper(IUserDao.class);

    // 点击登录按钮
    @FXML
    void loginClick() throws IOException {
        // 获取输入框中的用户id和密码
        String id = userid.getText();
        String pwd = userpwd.getText();
        // 判断输入是否为空
        if (id == null || pwd == null || id.equals("") || pwd.equals("")) {
            // 如果为空，则弹出提示框
            DialogUtil.showDialog("ERROR", "输入不能为空！");
        } else {
            // 判断用户是否存在
            int cnt = userDao.selectHasUser(id);
            if (cnt == 0) {
                // 如果用户不存在，则弹出提示框
                DialogUtil.showDialog("WARNING", "用户不存在，请先注册！");
            } else {
                // 如果用户存在，则获取用户信息
                Memento user = userDao.findUserById(id);
                String mypwd = user.getUserpwd();
                // 判断密码是否正确
                if (mypwd.equals(pwd)) {
                    // 如果密码正确，则跳转到主界面
                    // 跳转到主界面
                   // 创建一个FXMLLoader对象，用于加载mainPage.fxml文件
  FXMLLoader Loader = new FXMLLoader(getClass().getResource("/views/mainPage.fxml"));
                    // 使用Loader加载fxml文件，并返回父类
                    Parent parent = Loader.load();
                    // 获取Controller对象
                    MainPageController mocl = Loader.getController();
                    // 初始化PersonData
                    mocl.initPersonData(id);
                    // 创建一个Stage对象
                    Stage stage = new Stage();
                    // 设置Stage的标题
                    stage.setTitle("小型仓库管理系统");
                    // 设置Stage的 Scene
                    stage.setScene(new Scene(parent, 900, 700));
                    // 设置Stage的大小不可改变
                    stage.setResizable(false);
                    // 显示Stage
                    stage.show();
                    // 关闭登录窗口，并且关闭会话资源
                    sqlSession.close();
                    ((Stage) rootPane.getScene().getWindow()).close();
                } else {
                    // 如果密码不正确，则弹出提示框
                    DialogUtil.showDialog("ERROR", "密码错误！");
                }
            }
        }
    }

    // 点击注册按钮
    @FXML
    void registerClick() throws IOException {
        // 单例模式生成一个注册界面
        SingleRegisterStage.getInstance().show();
    }
}