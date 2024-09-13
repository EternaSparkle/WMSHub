package priv.eternasparkle.templates;


import org.springframework.stereotype.Component;
import priv.eternasparkle.entity.Menu;
import priv.eternasparkle.io.LineReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestTemplate {
    final String BASE = "E:\\DevelopmentFiles\\Web\\vue-learning\\static\\";
    public List<Menu> getMenus() throws IOException {
        String path = BASE + "menus.txt";
        List<String> list = LineReader.read(path);
        List<Menu> retList = new ArrayList<>();
        list.forEach((x)->{
            Menu menu = new Menu(x);
            System.out.println( menu );
            retList.add( menu );
        });
        return retList;
    }
    public List<String> getPerms() throws IOException {
        String path = BASE + "perms.txt";
        List<String> list = LineReader.read(path);
        return list;
    }
}
