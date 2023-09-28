import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws InterruptedException {
        //输入-n 10 -r 10

        if(args[0].equals("-n")&&args[2].equals("-r")){

            //初始化
            int n = Integer.valueOf(args[1]);
            int r = Integer.valueOf(args[3]);

            Create create = new Create();
            Reverse reverse = new Reverse();
            String[] fuHao = {"+", "-", "*", "÷"};

            //把题目写到文件里
            String[] strExe = new String[n];//表达式
            String[] strFile = new String[n];//文件内容
            create.create(r, n, fuHao, strExe);//主业务：生成题目
            for(int i = 0; i<n; i++) {
                int num=i+1;
                strFile[i]=num+"." + strExe[i];
            }
            IOFile.writeFile("Exercises.txt",strFile );

            //把答案写到文件里
            String[] Answers = new String[n];
            for(int i = 0; i<n; i++) {
                String result = reverse.cal(strExe[i]);
                Answers[i]=result+"\n";
            }
            IOFile.writeFile("Answers.txt",Answers );

        }

        //输入 -e <exercisefile>.txt -a <answerfile>.txt
        else if(args[0].equals("-e")&&args[2].equals("-a")){
            //读取两个文件，获得内容
            String fileExeName=args[1];
            String fileYouAnsName=args[3];
            Reverse reverse = new Reverse();
            List<String> exes = IOFile.readFile(fileExeName);//表达式list
            List<String> youAnss = IOFile.readFile(fileYouAnsName);//值list
            List<String> Anss = IOFile.readFile("Answers.txt");//值list

            //处理，并且比较答案，拼接Correct:(1,2,3,4) wrong(5,6,7,8)
            StringBuffer correct = new StringBuffer("Correct:");
            StringBuffer wrong = new StringBuffer("Wrong:");
            List<Integer> correctNums=new ArrayList<>();
            List<Integer> wrongNums=new ArrayList<>();

            for (int i=0;i<youAnss.size();i++){
                BigDecimal result = new BigDecimal(youAnss.get(i));
                BigDecimal result2 = new BigDecimal(Anss.get(i));
                if(result2.equals(result)){
                    //正确
                    correctNums.add(i);
                }
                else {
                    //失败
                    wrongNums.add(i);
                }
            }
            correct.append(correctNums.size());
            wrong.append(wrongNums.size());
            correct.append("(");
            wrong.append("(");

            for(Integer correctNum:correctNums){
                correct.append(correctNum+1);
                if(correctNums.size() != correctNum + 1){
                    correct.append(",");
                }
            }
            correct.append(")");
            for(Integer wrongNum:wrongNums){
                wrong.append(wrongNum+1);
                if(wrongNums.size() != wrongNum + 1){
                    correct.append(",");
                }
            }
            wrong.append(")");
            IOFile.writeFile("Grade.txt", new String[]{correct.toString(), "\n", wrong.toString()});
            System.out.println("输出成功");
        }
    }
}
