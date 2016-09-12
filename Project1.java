
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Author: Aliasgar Murtaza
 * Matric No: N1604189L
 * Course: EE4483 Artificial Intelligence & Data Mining
 * Project: 1
 * Professor: Prof. Junsong Yuan
 * Date: 12 September 2016
 */

public class Project1 {

    public static void main (String[] args)
    {
        float x;
        int a, b, t, i=0;

        Project1A project1A = new Project1A();
        Project1B project1B = new Project1B();

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        t = scanner.nextInt();

        System.out.println("Case #        Algorithm 1     Algorithm 2");
        while (i++<t)
        {
            x = scanner.nextFloat();
            a = scanner.nextInt();
            b = scanner.nextInt();

            project1A.init(x,a,b);
            project1A.calculate();

            project1B.init(x,a,b);
            project1B.calculate();

            System.out.println("Case #"+ i +":      " + project1A.getResult() + "       "+ project1B.getResult());
        }
    }

    public static class Project1A{

        float x, result;
        int a, b, neg = -1;

        public void init(float x, int a, int b)
        {
            this.x = x;
            this.a = a;
            this.b = b;
        }

        public void calculate() {

            if(x<0)
            {
                neg = 1;
                x = Math.abs(x);
            }

            result =  pickMethod(x,a,b);

            result = balanceNegativity(result, neg);
        }

        public float getResult()
        {
            return result;
        }

        private float balanceNegativity(float result, int neg) {
            return neg==1?0-result:result;
        }

        public float pickMethod (float x, int a, int b)
        {
            if(a%b ==0)
                return myPow(x,a/b);

            return myPow(findRoot(0,x,b,x),a);
        }


        public float findRoot(float low, float high, int root, float x)
        {
            float mid = (low+high)/2;
            float num = myPow(mid,root);
            if(round(num) == x)
                return mid;
            return num<x ? findRoot(mid,high,root,x):findRoot(low,mid,root,x);
        }

        public float myPow(float x, int a)
        {
            float num = x;
            while (a-->1)
                num*=x;
            return num;
        }

        public float round(float num)
        {
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            return Float.parseFloat(df.format(num));

        }
    }

    public static class Project1B
    {

        float x, result;
        int a, b, neg = -1;

        public void init(float x, int a, int b)
        {
            this.x = x;
            this.a = a;
            this.b = b;
        }

        public void calculate() {

            if(x<0)
            {
                neg = 1;
                x = Math.abs(x);
            }

            result = (float) Math.log(x);
            result = result * ((float)(a)/(float)b);
            result = (float) Math.exp(result);
            result = balanceNegativity(result,neg);
        }

        private float balanceNegativity(float result, int neg) {
            return neg==1?0-result:result;
        }

        public float getResult()
        {
            return result;
        }
    }
}
