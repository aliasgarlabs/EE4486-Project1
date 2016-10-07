import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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

    public static void main(String[] args) {
        float x;
        int a, b, t, i = 0;

        Project1A project1A = new Project1A();
        Project1B project1B = new Project1B();
        Project1TrueAnswer project1TrueAnswer = new Project1TrueAnswer();

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        t = scanner.nextInt();

        System.out.println("Case #        Algorithm 1     Algorithm 2     Actual Answer");
        while (i++ < t) {
            x = scanner.nextFloat();
            a = scanner.nextInt();
            b = scanner.nextInt();

            project1A.init(x, a, b);
            project1A.calculate();

            project1B.init(x, a, b);
            project1B.calculate();


            project1TrueAnswer.init(x, a, b);
            project1TrueAnswer.calculate();


            System.out.println("Case #" + i + ":      " + project1A.getResult() + "       " + project1B.getResult()+"           "+project1TrueAnswer.getResult());


        }


    }


    public static class Project1A {

        float x, result;
        int a, b, neg = -1;

        public void init(float x, int a, int b) {
            this.x = x;
            this.a = a;
            this.b = b;

        }

        public void calculate() {

            if (x < 0) {
                neg = 1;
                x = Math.abs(x);
            }

            result = pickMethod(x, a, b);

            result = balanceNegativity(result, neg);
        }

        public float getResult() {
            return result;
        }

        private float balanceNegativity(float result, int neg) {
            return neg == 1 ? 0 - result : result;
        }

        public float pickMethod(float x, int a, int b) {
            if (a % b == 0)
                return myPow(x, a / b);

            return myPow(findRoot(0, x, b, x), a);
        }


        public float findRoot(float low, float high, int root, float x) {
            float mid = (low + high) / 2;
            float num = myPow(mid, root);
            if (round(num) == x)
                return mid;
            else if(mid == low || mid == high)
                return mid;
            return num < x ? findRoot(mid, high, root, x) : findRoot(low, mid, root, x);
        }

        public float myPow(float x, int a) {
            float num = x;
            while (a-- > 1)
                num *= x;
            return num;
        }

        public float round(float num) {
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            return Float.parseFloat(df.format(num));
        }
    }

    public static class Project1TrueAnswer {

        float x, result;
        int a, b, neg = -1;

        public void init(float x, int a, int b) {
            this.x = x;
            this.a = a;
            this.b = b;

        }

        public void calculate() {

            if (x < 0) {
                neg = 1;
                x = Math.abs(x);
            }

            result = (float) Math.log(x);
            result = result * ((float) (a) / (float) b);
            result = (float) Math.exp(result);
            result = balanceNegativity(result, neg);

        }

        private float balanceNegativity(float result, int neg) {
            return neg == 1 ? 0 - result : result;
        }

        public float getResult() {
            return result;
        }
    }

    public static class Project1B {
        int arrayBase[];
        String arrayMedian[];
        double x;
        BigDecimal result;
        int searchIndex;
        int meanIndex;
        int a, b,neg=-1;

        /*
               Here we are using the Log Property
               We first find log of x and multiply it by the exponent power.
               Then we find the log inverse of the product obtained from above multiplication.
               Then this result is then analysed and formatted to the actual answer.
         */


        public void calculate() {

            if (x < 0) {
                neg = 1;
                x = Math.abs(x);
            }

            x = findLog(x);
            x = multiplyLogWithFraction(x);

            getSearchIndex();
            result = BigDecimal.valueOf(arrayBase[searchIndex]+ getMeanValue());


            //First digit is found out to find the position of the decimal.
            int firstDigit = Integer.parseInt(Double.toString(x).substring(0, 1));
            result = shiftPointer(result, firstDigit);
            result = balanceNegativity(result, neg);
        }

        private BigDecimal shiftPointer(BigDecimal result, int firstDigit) {
            if (firstDigit > 4)
                return result.movePointRight((firstDigit + 1) - 4);
            else if (firstDigit < 4)
                return result.movePointLeft(4 - (firstDigit + 1));
            else
                return result;
        }

        public double findLog(double x) {
            double log = Math.log10(x);
            return log;
        }

        public double multiplyLogWithFraction(double x) {
            return x * ((float) a / (float) b);
        }

        public double getResult() {
            return result.doubleValue();
        }

        public void init(float x, int a, int b) {
            arrayBase = new int[1000];
            arrayMedian = new String[100];
            this.x = x;
            this.a = a;
            this.b = b;

            try {
                loadArray();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        public int getMeanValue()
        {
            return Integer.valueOf(arrayMedian[searchIndex%100].substring(meanIndex*2, (meanIndex*2 )+2));
        }

        public void loadArray() throws IOException {

            BufferedReader br = new BufferedReader(new FileReader("table.txt"));

            String line;

            for (int i = 0; i < 1000; i++) {
                line = br.readLine();

                if (line != null)
                    arrayBase[i] = Integer.valueOf(line);
            }

            for(int i=1000;i<1100;i++)
            {
                line = br.readLine();

                if (line != null)
                    arrayMedian[i-1000] = line;
            }

        }

        public void getSearchIndex() {
            double postDecimal = x - (int) x;
            postDecimal *= 10000;
            int postDecimalInt = (int) postDecimal;
            String postDecimalString = String.format("%04d", postDecimalInt);
            searchIndex = Integer.valueOf(postDecimalString.substring(2, 3)) * 100 + Integer.valueOf(postDecimalString.substring(0, 2));
            meanIndex = Integer.valueOf(postDecimalString.substring(3, 4));
        }

        private BigDecimal balanceNegativity(BigDecimal result, int neg) {
            return neg == 1 ? result.negate() : result;
        }
    }
}
