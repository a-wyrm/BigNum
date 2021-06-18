public class BigNum {

    // node class
    private class Node {
        int value;
        Node next = null;
    }

    // these booleans are used in multiply specifically
    private boolean isnegative1;
    private boolean isnegative2;

    // boolean used in subtract
    private boolean BiggerIs;

    // this private method reverses a list, this is specifically used in add
    private Node reverse(Node r) {
        // we will be returning current, which is our reversed linked list
        Node current = null;
        // keeps track of the previous node
        Node prev = null;
        // our original node
        Node OurNode = r;

        while (OurNode != null) {
            // sets to the current node
            prev = current;
            // sets current to our original node
            current = OurNode;

            // sets our original node to its next value
            OurNode = current.next;

            // sticks the previous node into current, effectively "reversing" the list
            current.next = prev;
        }
        // returns the reverse linked list
        return current;
    }

    // this private method finds the larger of the two values in subtract, used to calculate the negative
    private Boolean findlarger(String x, String y){

        // we take the two string lengths...
        int xstring = x.length();
        int ystring = y.length();

        // we test to see if one is either bigger or smaller than the other
        if (xstring > ystring) {
            return true;
        }
        else if (ystring > xstring) {
            return false;
        }

        // if they're of equal size, then we move on to the individual values
        for (int i = 0; i < xstring; i++) {

            if (Character.getNumericValue(x.charAt(i)) > Character.getNumericValue(y.charAt(i))) {
                return true;
            }

            else if (Character.getNumericValue(x.charAt(i)) < Character.getNumericValue(y.charAt(i))){
                return false;
            }
        }

        return true;
    }

    // this private method removes the leading zeroes
    private String removezeroes(String x){
        String xnum = x;
        while( xnum.length() > 0 && xnum.charAt(0) == '0'){
            xnum = xnum.substring(1);
        }
        if(xnum.length() == 0){
            return("0");
        }
        else{
            return xnum;
        }
    }

    // Empty node to use in the code. This will be filled up with various data
    Node start = null;

    // Construct an empty BigNum
    // Empty, since I don't need to construct anything.
    public BigNum() {
    }

    // Construct a BigNum from an int.

    public BigNum(int n) {

        // makes two empty nodes, one for the current and one for the previous value
        Node current = null;
        Node prev = null;

        // makes a string of the integer
        String nstring = "" + n;

        // turns the string n into a character array
        char[] digits = nstring.toCharArray();


        // for loop iterates through values in the array, sticking them into each node
        for (int i = 0; i < digits.length; i++) {


            // new node is made
            Node BigNumIntNode = new Node();
            // sets the next node to null
            BigNumIntNode.next = null;

            // saves the current node's value as the digit in the character array
            BigNumIntNode.value = Integer.parseInt(String.valueOf(digits[i]));

            // for the first iteration, sets the start value to BigNum value; this is used in the toString method
            // the previous one is also the BigNum value to start that linked list
            if (start == null) {
                start = BigNumIntNode;
                prev = BigNumIntNode;

            } else {
                // current now points to the new node's value
                // the next previous' value points to the current value as well, used to put values in the loop
                current = BigNumIntNode;
                prev.next = current;
                prev = current;
            }

        }

    }

    // Construct a BigNum from a String.

    public BigNum(String s) {
        // makes two empty nodes, one for the current and one for the previous value
        Node current = null;
        Node prev = null;

        if(s.length() > 2) {
            if (s.charAt(0) == '0' || s.charAt(1) == ('0')) {
                if (s.charAt(0) == '-' && s.charAt(1) == '0') {
                    s = removezeroes(s.substring(1));
                    s = "-" + s;
                } else {
                    s = removezeroes(s);
                }
            }
        }

        // turns the string s into a character array
        char[] stringcharacters = s.toCharArray();

        // index used for the while loop
        int i = 0;
        // while loop iterates through the char array length
        while(i != stringcharacters.length) {
            int a;
            Node BigNumString;

            // if statement checks to see if there's a negative
            if (String.valueOf(stringcharacters[i]).equals("-")) {

                // we get the - and the value of the next number in the array
                a = Integer.parseInt(String.valueOf("-"+stringcharacters[i+1]));
                // we clear the next two nodes
                BigNumString = new Node();
                BigNumString.next = null;
                BigNumString = new Node();
                BigNumString.next = null;
                BigNumString.value = a;
                i = i+2;
            }
            else{
                // finds the integer value of the string value
                // sets the new node to have the value of a
                a = Integer.parseInt(String.valueOf(stringcharacters[i]));
                BigNumString = new Node();
                BigNumString.next = null;
                BigNumString.value = a;
                i++;
            }
            // for the first iteration, sets the start value to BigNum value; this is used in the toString method
            // the previous one is also the BigNum value to start that linked list
            if (start == null) {
                start = BigNumString;
                prev = BigNumString;
            } else {
                // current now points to the new node's value
                // the next previous' value points to the current value as well
                current = BigNumString;
                prev.next = current;
                prev = current;
            }
        }

    }

    // Convert this BigNum to a String
    public String toString() {

        // bignum string
        String bignum = "";

        // sets a new node current to a start value
        Node current = start;

        // while the current does not equal null, add the values into the bignum string
        // while also go ahead in the linked list by taking the next value
        while (current != null) {
            bignum += "" + current.value;
            current = current.next;
        }

        bignum = removezeroes(bignum);

        // returns bignum
        return bignum;
    }

    // Compare this BigNum to another BigNum, returning 0 if they are equal,
    // a value > 0 if this > other, or a value < 0 if this < other.
    public int compareTo(BigNum other) {

        // gets the two starting values of the two linked lists we are comparing
        Node BigNumhead = this.start;
        Node Otherhead = other.start;
        String BigNumString = this.toString();
        String OtherNumString = other.toString();

        if(BigNumString.charAt(0) == '-'|| OtherNumString.charAt(0) == '-'){
            if(BigNumString.charAt(0) == '-' && OtherNumString.charAt(0) != '-'){
                return -1;
            }
        }

        if(findlarger(BigNumString, OtherNumString) == true){
            return 1;
        }
        else if(findlarger(BigNumString, OtherNumString) == false){
            return -1;
        }

        // This while loop will continue taking the next values until either linked list reaches null
        while (Otherhead != null || BigNumhead != null) {


            if (BigNumhead != null && Otherhead == null) {
                return 1;
            }

            if (BigNumhead == null && Otherhead != null) {
                return -1;
            }

            BigNumhead = BigNumhead.next;
            Otherhead = Otherhead.next;

        }

        // returns 0 otherwise
        return 0;
    }


    // Add this BigNum to another BigNum, returning a new BigNum which contains the sum of the two
    public BigNum add(BigNum other) {

        // this is the string value we will be returning.
        // we specifically want to return a string because we can easily manipulate it
        String number = "";

        // boolean that checks for the negative
        boolean addnegative = false;

        // the carry
        int carry = 0;

        // again, strings are easier to manipulate
        String ThisString = this.toString();
        String OtherString = other.toString();

        // these if statements check for a negative, substringing the value and activating the boolean
        if(ThisString.charAt(0) == '-'){
            if(OtherString.charAt(0) == '-') {
                ThisString = ThisString.substring(1);
                OtherString = OtherString.substring(1);
                addnegative = true;
            }
            else{
                ThisString = ThisString.substring(1);
                BigNum This = new BigNum(ThisString);
                isnegative1 = true;
                return other.subtract(This);

            }
        }
        if(OtherString.charAt(0) == '-'){
            OtherString = OtherString.substring(1);
            BigNum Other = new BigNum(OtherString);
            isnegative2 = true;
            return this.subtract(Other);
        }

        // makes a new bignum, specifically so we can reverse it
        BigNum This = new BigNum(ThisString);
        BigNum Other = new BigNum(OtherString);
        Node BigNumhead = This.start;
        Node Otherhead = Other.start;
        BigNumhead = reverse(BigNumhead);
        Otherhead = reverse(Otherhead);

        // this while loop continues until either of our nodes are null, or carry does not equal 0
        while (BigNumhead != null || Otherhead != null || carry != 0) {

            // these if statements check to see if either one of our nodes is not null
            // gets the next value if they are not null, and adds the current value to carry
            if (Otherhead != null) {
                carry += Otherhead.value;
                Otherhead = Otherhead.next;
            }
            if (BigNumhead != null) {
                carry += BigNumhead.value;
                BigNumhead = BigNumhead.next;
            }

            // our answer is updated
            number = carry % 10 + number;

            // our carry is updated
            carry = carry / 10;
        }

        number = removezeroes(number);

        // if the negative boolean is true, we add the negative sign to it
        if(addnegative == true){
            return new BigNum("-" + number);
        }

        // returns a new bignum with our answer as its value
        return new BigNum(number);
    }

    // Subtract BigNum other from this, returning
    // a new BigNum which contains the difference of the two
    public BigNum subtract(BigNum other) {

        // boolean used to determine if we need a negative
        boolean isbigger;
        Node BigNumhead;
        Node Otherhead;

        // answer we're returning
        String number = "";
        // carry
        int carry = 0;

        // strings are easy to manipulate
        String larger = this.toString();
        String smaller = other.toString();

        // these if statements check for a negative, substringing the value and activating the boolean
        if(larger.charAt(0) == '-'){
            if(smaller.charAt(0) == '-') {
                smaller = smaller.substring(1);
                larger = larger.substring(1);
                BigNum small = new BigNum(smaller);
                BigNum large = new BigNum(larger);

                return (small.subtract(large));
            }
            else{
                larger = larger.substring(1);
                BigNum This = new BigNum(larger);
                isnegative1 = true;
                BigNum added = other.add(This);
                return new BigNum("-"+added);

            }
        }
        if(smaller.charAt(0) == '-'){
            smaller = smaller.substring(1);
            BigNum Other = new BigNum(smaller);
            isnegative2 = true;

            BigNum added = this.add(Other);
            return added;
        }

        // we find the bigger number, taking the negative into account
        isbigger = findlarger(larger, smaller);
        if(isbigger == true)  {
            BigNumhead = this.start;
            Otherhead = other.start;
            BigNumhead = reverse(BigNumhead);
            Otherhead = reverse(Otherhead);
        }
        else{
            BiggerIs = true;
            BigNumhead = other.start;
            BigNumhead = reverse(BigNumhead);
            Otherhead = this.start;
            Otherhead = reverse(Otherhead);
        }

        // this while loop performs the actual subtracting operations on Otherhead
        while(Otherhead != null){
            int subtract = (BigNumhead.value)-(Otherhead.value) - carry;

            // if subtract is less than 0, we borrow 10, just like in elementary school
            // carry becomes 1
            if (subtract < 0){
                subtract += 10;
                carry = 1;
            }
            else{
                carry = 0;
            }

            number += subtract + "";
            BigNumhead = BigNumhead.next;
            Otherhead = Otherhead.next;
        }

        // this while loop performs the actual subtracting operations on BigNumhead, adding it to the number total
        while(BigNumhead != null){
            int subtract = (BigNumhead.value) - carry;

            if (subtract < 0) {
                subtract += 10;
                carry = 1;
            }
            else{
                carry = 0;
            }

            number += subtract + "";
            BigNumhead = BigNumhead.next;
        }

        // reverses the string using StringBuilder, using method here:
        // https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
        String numberrev = new StringBuilder(number).reverse().toString();

        // we remove the zeroes
        numberrev = removezeroes(numberrev);

        // if the boolean is true, it's negative; so we add a - sign.
        if(BiggerIs == true){
            numberrev = "-" + numberrev;
        }

        return new BigNum (numberrev);
    }

    // Multiply one digit d times another BigNum other, and return a BigNum
    // containing the product
    public BigNum multOneDigit(int d, BigNum other) {

        // used in the multiplication array
        int e = (other.toString().length())+Integer.toString(d).length();
        // p is the index of the multiplication array
        int p = 0;
        // this is the answer we will be returning
        String answer = "";

        // we reverse the BigNum to make operations easier
        Node Other = other.start;
        Other = reverse(Other);

        // we want to be able to manipulate the values when we multiply; the easiest way to do so is in an array
        int[] manipulate = new int[e];

        // while other doesn't equal null, we take the values in other and multiply them by d
        while(Other != null){
            manipulate[p] += (Other.value * d);
            p++;
            Other = Other.next;
        }

        // this calculates the carry, and adds onto the string answer
        for(int i = 0; i < manipulate.length; i++) {
            int mod = manipulate[i] % 10;
            int carry = manipulate[i] / 10;

            // we add on the carry
            if (i + 1 < manipulate.length) {
                manipulate[i + 1] += carry;
            }
            // and we add onto the answer
            answer = (mod + "") + answer;
        }

        answer = removezeroes(answer);

        return new BigNum(answer);
    }

    // Multiply this BigNum by another BigNum, returning the product as a BigNum
    public BigNum multiply(BigNum other) {

        // initializing the carry
        int carry;
        // index used in the keeptrack array
        int o = 0;

        // numbers used in the string
        int indexi = -1;
        int indexj = -1;

        // this is the answer we will be returning
        String number = "";

        // converts the bignums to strings
        String ThisString = this.toString();
        String OtherString = other.toString();

        // these if statements check to see if there's a negative
        if(ThisString.charAt(0) == '-'){
            ThisString = ThisString.substring(1);
            isnegative1 = true;
        }
        if(OtherString.charAt(0) == '-'){
            OtherString = OtherString.substring(1);
            isnegative2 = true;
        }

        // we want to be able to manipulate the values when we multiply; the easiest way to do so is in an array
        int keeptrack[] = new int[ThisString.length() + OtherString.length()];

        // double for-loop will multiply each number
        for(int i = 0; i < ThisString.length(); i++){

            // sets the values for these variables
            carry = 0;
            indexj = -1;
            o = 0;

            // value we will be multiplying
            int use1 = Character.getNumericValue(ThisString.charAt(ThisString.length() + indexi));
            int int1 = use1;

            for(int j = 0; j < OtherString.length(); j++){

                // gets the value of string #2
                int use2 = Character.getNumericValue(OtherString.charAt(OtherString.length() + indexj));
                int int2 = use2;

                // calculates the sum of those two values, adding the values at index i+j and carry
                int num = (int1 * int2) + keeptrack[i + j] + carry;

                // calculates the carry
                carry = num / 10;
                // the actual calculated value in the array
                keeptrack[i + j] = num % 10;

                // we subtract from indexj to get the next value in the string
                indexj--;
                // we add to o for the if statement in the first loop
                o++;
            }

            // we subtract from indexi to get the next number in the string
            indexi--;
            // if the carry is still bigger than 0, we add the carry into the value of the array
            if (carry > 0){
                keeptrack[i+o] += carry;
            }
        }

        // puts the values in keeptrack into the number array
        for (int i = keeptrack.length-1; i >= 0; i--) {
            number = number + keeptrack[i];
        }

        number = removezeroes(number);

        // these if/else statements check to see if the number is negative or not
        if(isnegative1 == true){
            if(isnegative2 == true){
                return new BigNum(number);
            }
            else{
                return new BigNum("-" + number);
            }
        }
        if(isnegative2 == true) {
            if (isnegative1 == true) {
                return new BigNum(number);
            }
            else {
                return new BigNum("-" + number);
            }
        }
        else{
            return new BigNum(number);
        }

    }


    // Use this to test your other BigNum methods
    private static void main(String[] args) {

    }
}