import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Create {
    //选择生成整数还是分数
    public String select (int r){
      String s;
      int x,y;
      switch ((int)(Math.random()*2+1)){
          case 1:s=Integer.toString((int) (Math.random() * r + 1));break;//生成整数
          case 2://生成真分数
                do{
                    x=(int)(Math.random()*r+1);
                }while(x == 0);
                 y=(int)(Math.random()*r+1);
                 if(x<y) { s=x+"/"+y; }
                 else
                     s=y+"/"+x;
                 break;

          default:
              throw new IllegalStateException("Unexpected value: " + (int) (Math.random() + 1));
      }
       return s;
    }

    //根据中缀生成后缀
    public static String creatBackExp(String midExp){
        List<String> list = new ArrayList<>();
        char[] arr = midExp.toCharArray();
        //存放数字临时变量
        StringBuffer tmpStr = new StringBuffer();
        for (char c : arr) {
            //如果是数字或小数点，添加到临时变量中
            if (c>='0' && c<='9') {
                tmpStr.append(c);
            }
            else if(c=='.') {
                tmpStr.append(c);
            }
            //如果是加减乘除或者括号，将数字临时变量和运算符依次放入List中
            else if (c=='+' || c=='-' || c=='*' || c=='/' || c=='(' || c==')') {
                if (tmpStr.length() > 0) {
                    list.add(tmpStr.toString());
                    tmpStr.setLength(0);
                }
                list.add(c + "");
            }
            else if (c==' ') {
                continue;
            }
        }
        if (tmpStr.length() > 0) {
            list.add(tmpStr.toString());
        }
        //初始化后缀表达式
        List<String> strList = new ArrayList<>();
        //运算过程中，使用了两次栈结构，
        //第一次是将中缀表达式转换成后缀表达式，第二次是计算后缀表达式的值
        Stack<String> stack = new Stack<>();
        //声明临时变量，存放栈元素
        String tmp;
        //将中缀表达式转换成后缀表达式
        for (String s : list) {
            //如果是左括号直接入栈
            if (s.equals("(")) {
                stack.push(s);
            }
            //如果是右括号，执行出栈操作，依次添加到后缀表达式中，直到出栈元素为左括号，左括号和右括号都不添加到后缀表达式中
            else if (s.equals(")")) {
                while (!(tmp = stack.pop()).equals("(")) {
                    strList.add(tmp);
                }
            }
            //如果是加减乘除，弹出所遇优先级大于或等于该运算符的栈顶元素（栈中肯定没有右括号，认为左括号的优先级最低），然后将该运算符入栈
            else if (s.equals("*") || s.equals("/")) {
                while(!stack.isEmpty()) {
                    //取出栈顶元素
                    tmp = stack.peek();//取出但不移除
                    if (tmp.equals("*") || tmp.equals("/")) {
                        stack.pop();
                        strList.add(tmp);
                    }
                    else {
                        break;
                    }
                }
                stack.push(s);
            }
            else if (s.equals("+") || s.equals("-")) {
                while(!stack.isEmpty()) {
                    //取出栈顶元素
                    tmp = stack.peek();
                    if (!tmp.equals("(")) {
                        stack.pop();
                        strList.add(tmp);
                    }
                    else {
                        break;
                    }
                }
                stack.push(s);
            }
            //如果是数字，直接添加到后缀表达式中
            else {
                strList.add(s);
            }
        }
        //最后依次出栈，放入后缀表达式中
        while (!stack.isEmpty()) {
            strList.add(stack.pop());
        }

        //List<String>转换成String
        StringBuffer backExp=new StringBuffer();
        //转化成字符串
        for (String s : strList){
            backExp.append(s);
        }

        return backExp.toString();
    }

    //r:范围  n：题目数  fuao：+-*/   strArryay:式子数组
    //生成不重复的式子
    public String[] create(int r, int n, String[] fuHao, String[] strArray) {
        String str = "";
        String a,b,c,d;
        //随机生成n个式子
        for (int i = 0; i < n; i++) {
            str = "";
            int fuhaoSum=(int)(Math.random()*3+1);
            if(fuhaoSum ==1){//只有一个符号
                a =select(r) ;
                b =select(r);
                str=str+a+fuHao[(int)(Math.random()*4)]+b;
            }
            if(fuhaoSum ==2){//两个符号
                a =select(r) ;
                b =select(r) ;
                c =select(r) ;
                int s = (int) (Math.random() * 2);
                switch (s) {
                    case 0:
                        str = str + a + fuHao[(int) (Math.random() * 4)] + b + fuHao[(int) (Math.random() * 4)] + c;//a+b+c型
                        break;
                    case 1:
                        str = "(" + a + fuHao[(int) (Math.random() * 4)] + b + ")" + fuHao[(int) (Math.random() * 4)] + c;//(a+b)+c型
                        break;
                    case 2:
                        str = str + a + fuHao[(int) (Math.random() * 4)] + "(" + b + fuHao[(int) (Math.random() * 4)] + c;//a+(b+c)型
                        break;
                }
            }
            if(fuhaoSum ==3){//三个符号
               a =select(r) ;
               b =select(r) ;
               c =select(r) ;
               d =select(r) ;
               int s = (int) (Math.random() * 10);
               switch(s){
                   case 0:str=str + a + fuHao[(int) (Math.random() * 4)] + b + fuHao[(int) (Math.random() * 4)] + c+fuHao[(int) (Math.random() * 4)]+d;//a+b+c+型
                          break;
                   case 1:str="("+a+fuHao[(int) (Math.random() * 4)]+b+")"+fuHao[(int) (Math.random() * 4)]+c+fuHao[(int) (Math.random() * 4)]+d;//(a+b)+c+a型
                          break;
                   case 2:str=str+a+fuHao[(int) (Math.random() * 4)]+"("+b+fuHao[(int) (Math.random() * 4)]+c+")"+fuHao[(int) (Math.random() * 4)]+d;//a+(b+c)+d型
                          break;
                   case 3:str=str+a+fuHao[(int) (Math.random() * 4)]+b+fuHao[(int) (Math.random() * 4)]+"("+c+fuHao[(int) (Math.random() * 4)]+d+")";//a+b+(c+d)型
                          break;
                   case 4:str="("+a+fuHao[(int) (Math.random() * 4)]+b+fuHao[(int) (Math.random() * 4)]+c+")"+fuHao[(int) (Math.random() * 4)]+d;//(a+b+c)+d型
                          break;
                   case 5:str="("+"("+a+fuHao[(int) (Math.random() * 4)]+b+")"+fuHao[(int) (Math.random() * 4)]+c+")"+fuHao[(int) (Math.random() * 4)]+d;//((a+b)+c)+d型
                          break;
                   case 6:str="("+a+fuHao[(int) (Math.random() * 4)]+"("+b+fuHao[(int) (Math.random() * 4)]+c+")"+")"+fuHao[(int) (Math.random() * 4)]+d;//(a+(b+c))+d型
                          break;
                   case 7:str=str+a+fuHao[(int) (Math.random() * 4)]+"("+b+fuHao[(int) (Math.random() * 4)]+c+fuHao[(int) (Math.random() * 4)]+d+")";//a+(b+c+d)型
                          break;
                   case 8:str=str+a+fuHao[(int) (Math.random() * 4)]+"("+"("+b+fuHao[(int) (Math.random() * 4)]+c+")"+fuHao[(int) (Math.random() * 4)]+d+")";//a+((b+c)+d)型
                          break;
                   case 9:str=str+a+fuHao[(int) (Math.random() * 4)]+"("+b+fuHao[(int) (Math.random() * 4)]+"("+c+fuHao[(int) (Math.random() * 4)]+d+")"+")";//a+(b+(c+d))型
                          break;
                   case 10:str="("+a+fuHao[(int) (Math.random() * 4)]+b+")"+fuHao[(int) (Math.random() * 4)]+"("+c+fuHao[(int) (Math.random() * 4)]+d+")";//(a+b)+(c+d)型
               }
            }

            //判断是否重复,如果重复删除题目
            String midExp=str;
            String backExp=creatBackExp(midExp);
            if(i==0){
                strArray[i] = str + "\n";
            }
            for(int j=0;j<i;j++){
                    if(midExp.equals(strArray[i])){
                        i--;
                    break;
                }
                else {
                    //将式子存储在数组中
                    strArray[i] = str + "\n";
                }
            }
        }
        return strArray;
    }




}

