import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static void writeFile(String fileName,String []content) {
        FileOutputStream fileOutputStream = null;
        File file = new File(fileName);
        try {
            if(!file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            for(int i=0;i<content.length ;i++) {
                fileOutputStream.write(content[i].getBytes());
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static List<String> readFile(String fileName) {
        File file = new File(fileName );
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //StringBuffer sb = new StringBuffer();
                ArrayList<String> youAnswer = new ArrayList<>();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    //sb.append(text);
                    //System.out.println(text);
                    youAnswer.add(text);
                }

                return youAnswer;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
