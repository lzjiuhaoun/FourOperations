package cn.lzj66.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Output {
    public static boolean out(String textName, String path,List<String> question,List<String> answer){
        byte [] quesBuff = new byte[]{};
        byte [] ansBuff = new byte[]{};
        int questionLen = question.size();
        int answerLen = answer.size();
        FileOutputStream outputStream;
        if(questionLen != answerLen ){
            return false;
        }
        try {
            for (int i = 0; i < questionLen; i++) {
                String str = (String)question.get(i);
                String str2 = (String)answer.get(i);
                quesBuff = str.getBytes();
                ansBuff = str2.getBytes();
                if(i == 0){
                    //第一次覆盖
                    File file = ResourceUtils.getFile("classpath:text.txt");
                    outputStream = new FileOutputStream(file,false);
                }else{
                     File file = ResourceUtils.getFile("classpath:text.txt");
                     outputStream = new FileOutputStream(file,true);
                }
                String tmp = String.valueOf(i+1)+",";
                outputStream.write(tmp.getBytes());
                outputStream.write(quesBuff);
                outputStream.write(",".getBytes());
                outputStream.write(ansBuff);
                outputStream.write("\r\n".getBytes());
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
