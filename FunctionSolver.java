import java.util.Scanner;
import java.util.regex.*;
import java.lang.Math;
public class FunctionSolver {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter your quadratic function: ");
    String func = sc.nextLine();

    solveFunction(func);

    sc.close();
   }


  public static void solveFunction(String func)
  {
    double x1;
    double x2;
    func = func.replaceAll(" ","");
    func = func.replaceAll("X","x");
     int a = 0;
     int b = 0;
     int c = 0;
     String aStr = "0";
     String bStr = "0";
     String cStr = "0";

     int xSquareIndex = -1;
     int xIndex = -1;
     int equalIndex = -1;

       xSquareIndex = func.indexOf("x^2");
       if(xSquareIndex == -1)
       {
         System.out.println("It is not a quadratic function.");
         System.exit(0);
       }
       equalIndex = func.indexOf("=");

      while(xSquareIndex != -1)
      {
        int i;
        for(i = xSquareIndex; i>0;i=i-1)
        {
          if(func.charAt(i) == '-' || func.charAt(i) == '+' || func.charAt(i-1) == '=')
          {
            break;
          }
        }  
        aStr = func.substring(i,xSquareIndex);
        if(aStr.replaceAll("[^0-9-]", "").matches("[-]?")) aStr = aStr + "1";
        if(xSquareIndex > equalIndex && equalIndex != -1) aStr = "-" + aStr;
        if(aStr.matches("[-][-]\\d+")) aStr = aStr.substring(2,aStr.length());
        a = a + Integer.parseInt(aStr.replaceAll("[^0-9-]", ""));

          int nn = xSquareIndex+3;

          if(nn > func.length())
          {
            nn = func.length();
          }
          func = func.substring(0,i) + func.substring(nn,func.length());        
          xSquareIndex = func.indexOf("x^2");
          equalIndex = func.indexOf("=");
      }

    if(a == 0)
    {
        System.out.println("It is not a quadratic function.");
        System.exit(0);
    }


      xIndex = func.indexOf("x");
      while(xIndex != -1)
      {
        int j;
        for(j = xIndex; j>0;j=j-1)
        {
          if(func.charAt(j) == '-' || func.charAt(j) == '+' || func.charAt(j-1) == '=')
          {
            break;
          }
        }  
        bStr = func.substring(j,xIndex);
        if(bStr.replaceAll("[^0-9-]", "").matches("[-]?")) bStr = bStr + "1";
        if(xIndex > equalIndex && equalIndex != -1) bStr = "-" + bStr;
        if(bStr.matches("[-][-]\\d+")) bStr = bStr.substring(2,bStr.length());
        b = b + Integer.parseInt(bStr.replaceAll("[^0-9-]", ""));

         int nn = xIndex+1;

          if(nn > func.length())
          {
            nn = func.length();
          }
          func = func.substring(0,j) + func.substring(nn,func.length());        
          xIndex = func.indexOf("x");
          equalIndex = func.indexOf("=");
      }



    while(func.matches(".*\\d+.*"))
    {
      Pattern pattern = Pattern.compile("[-]?\\d+");
      Matcher matcher = pattern.matcher(func);
      matcher.find();
      int indexOfNumber = func.indexOf(matcher.group());
      cStr = matcher.group();

      if(indexOfNumber > equalIndex && equalIndex != -1) cStr = "-" + cStr;
      if(cStr.matches("[-][-]\\d+")) cStr = cStr.substring(2,cStr.length());
       c = c + Integer.parseInt(cStr);



      func = func.substring(0,indexOfNumber) + func.substring(indexOfNumber + matcher.group().length(), func.length());

      equalIndex = func.indexOf("=");
    }

    //System.out.println("func: " + func);
    //System.out.println("a: "+ a);
    //System.out.println("b: "+ b);
    //System.out.println("c: "+ c);

    double aa = Double.valueOf(a);
    double bb = Double.valueOf(b);
    double cc = Double.valueOf(c);

    double D = bb*bb - 4*aa*cc;

    if(D<0)
    {
      System.out.println("No solutions");
    }
    else if(D==0)
    {
      x1 = -bb / 2*aa;
      System.out.println("x = "+x1);
    }
    else if(D>0)
    {

      x1 = (-bb + Math.sqrt(D))/(2f*aa);
      x2 = (-bb - Math.sqrt(D))/(2f*aa);
      System.out.println("x = " + x1);
      System.out.println("x = " + x2);
    }


  }
}

