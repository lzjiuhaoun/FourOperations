package cn.lzj66.util;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {
    public static List<String> in (String textName,String path){
        List<String> list = null;
        try{
            File file = ResourceUtils.getFile("classpath:text.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            list = new ArrayList<String>();
            while ((line=bufferedReader.readLine())!=null){
                list.add(line);
            }
            bufferedReader.close();
            fileReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }
}
