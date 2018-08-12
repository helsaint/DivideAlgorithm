/************************************************************************************
* To divide without using the division operator we will use the binary shift operator
* and integer subtraction. The algorithm used is simple binary longdivision.
* First we will convert negative numbers to positive. This allows for the easy use of 
* the subtraction operator. Java stores all numbers as signed in 2's complement form.
************************************************************************************/
class Divide {
    public int divide(int dividend, int divisor) {
       	int tempDivisor;
        int tempDividend;
        int count = 0;
	/***************************************************************************
	*The booleans are used to ensure that the result is given the correct sign
	*when calculations are completed. 
	***************************************************************************/
        boolean isDividendNeg = false;
        boolean isDivisorNeg = false;
        String strResult = ""; //holds the binary result as characters in a string
        int lengthOfQ = Integer.toBinaryString(dividend).length()-1;
	//The array below is used to store the integers of the binary. Will be added
	//to the strResult later.
        int[] arrQuotient = new int[Integer.toBinaryString(dividend).length()];
        
	//Will be used to identify if sign changes are required.
        if(divisor < 0){
            isDivisorNeg = true;
            divisor = convertToPositive(divisor);
        }
	//How much to the left do we need to shift the divisor for its MSB to be in
	//the same column as the MSB of the dividend
        int leftShift = Integer.toBinaryString(dividend).length() - Integer.toBinaryString(divisor).length();        
        if(dividend < 0){
            leftShift--;
            isDividendNeg = true;
            dividend = convertToPositive(dividend);
        }
        
	//In the long division algorithm the MSB of the divisor is shifted to be in the same column as the MSB
	//of the dividend
        tempDivisor = divisor << leftShift;
        tempDividend = dividend;
        //Long division algorithm       
        for(int i = 0; i < leftShift+1; i++){
            count++;
            if(tempDivisor <= tempDividend){
                tempDividend = tempDividend - tempDivisor;
                arrQuotient[i] = 1;
            }else{
                arrQuotient[i] = 0;
            }
            tempDivisor = tempDivisor >> 1;
        }
        
        for(int i = 0; i < count; i++) strResult = strResult + arrQuotient[i];
        if(strResult == "") strResult = "0";
        if(isDividendNeg ^ isDivisorNeg){
            return convertToNegative(Integer.parseInt(strResult, 2));
        }
        return Integer.parseInt(strResult, 2);       
    }
    
    //Use 2's Complement to convert negative numbers to positive
    private int convertToPositive(int number){
        if(number == Integer.MIN_VALUE) return number -1;
        int intReturn = number - 1;
        intReturn = ~intReturn;
        return intReturn;
    }
    
    //Use 2's Complement to convert positive numbers to negative
    private int convertToNegative(int number){
        int intReturn = number;
        intReturn = ~intReturn + 1;
        return intReturn;
    }
     
    
}
