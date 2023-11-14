package patterns.singleton;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// 创建一个单例注册阶段类
public class SingleRegisterStage {
    // 懒汉单例模式
    private static Stage registerInstance;

    private SingleRegisterStage() {}

    public static Stage getInstance() {
        if(registerInstance == null) {
            // 创建一个FXMLLoader对象，用于加载register.fxml文件
            FXMLLoader Loader = new FXMLLoader(SingleRegisterStage.class.getResource("/views/register.fxml"));
            Parent parent = null;
            try {
                // 加载register.fxml文件
                parent = Loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 创建一个Stage对象
            Stage stage = new Stage();
            // 设置标题
            stage.setTitle("注册界面");
            // 设置场景
            stage.setScene(new Scene(parent, 500, 400));
            // 设置不可改变大小
            stage.setResizable(false);
            // 设置单例
            registerInstance = stage;
        }
        return registerInstance;
    }
}
