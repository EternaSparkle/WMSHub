package priv.eternasparkle.util;


import org.springframework.stereotype.Component;
import priv.eternasparkle.entity.Menu;
import priv.eternasparkle.io.LineReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(value="dataTemplate")
public class DataTemplate {
    final String BASE = "E:\\DevelopmentFiles\\Web\\vue-learning\\static\\";
    public List<Menu> getConsoleRoutes() {
        String path = BASE + "console.txt";
        List<Menu> retList = new ArrayList<>();
        try {
            List<String> list = LineReader.read(path);
            list.forEach((x)->{
                Menu menu = new Menu(x);
                System.out.println( menu );
                retList.add( menu );
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文本数据失败。");
        }
        return retList;
    }
}
