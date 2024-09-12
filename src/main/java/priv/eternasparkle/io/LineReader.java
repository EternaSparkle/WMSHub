package priv.eternasparkle.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LineReader {
    public static List<String> read(String path)
        throws IOException {
        BufferedReader BR =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(path), "UTF-8"
                    )
                );
        String line = null;
        List<String> list = new ArrayList();
        while ( (line=BR.readLine())!=null ){
            line = line.trim();
            if( line.length()>0 && !line.startsWith("#") ){
                System.out.println( line );
                list.add( line );
            }
        }
        BR.close();
        return list;
    }
}
