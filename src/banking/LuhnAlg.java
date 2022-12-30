package banking;

import java.util.Arrays;

public class LuhnAlg {
    String number;
    Boolean result;
    public LuhnAlg(){

    }

    public LuhnAlg(String numToCheck){
        this.result = isCorrect(numToCheck);
    }


    boolean isCorrect(String numToCheck) {

        String reversed = new StringBuilder(numToCheck).reverse().toString();

        int sum = 0;

        for (int i = 0; i < reversed.length(); i += 2) {

            int digit = Character.getNumericValue(reversed.charAt(i));

            sum += digit;
        }

        for (int i = 1; i < reversed.length(); i += 2) {

            int digit = Character.getNumericValue(reversed.charAt(i));

            int doubled = digit * 2;

            if (doubled > 9) {
                doubled -= 9;
            }
            sum += doubled;
        }
        return sum % 10 == 0;
    }

    int lastDigit(String num){
        String numToCheck = "4000002944628795";
        int len = num.length();
        int[] numArr = new int[len];
        int sum = 0;
        int lastDigit = 0;
        for(int i = 0;i < len; i++){
            numArr[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
            if(i%2==0){
                numArr[i]*=2;
                if(numArr[i] > 9){
                    numArr[i]-=9;
                }
            }

            sum+=numArr[i];
        }
        if(sum < 100){
            lastDigit = (100-(sum))%10;
        }else if(sum==100){
            lastDigit = 0;
        }else{
            lastDigit = (100%sum)%10;
        }
        int[] result = new int[len+1];
        for(int i = 0; i < len+1; i++){
            if(i==len){
                result[i] = lastDigit;
            }else {
                result[i]=numArr[i];
            }
        }
        String[] arrs = numToCheck.split("");
        String resultString=(Arrays.toString(result)).replace(",","").replace("[","").replace("]","").replace(" ","");
        char c = resultString.charAt(result.length-1);
        int k = c-48;
        return k;
    }

    public Boolean getResult() {
        return result;
    }
}
