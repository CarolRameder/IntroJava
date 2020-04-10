package com.company;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Main lab1 = new Main();
        lab1.compulsory();
        lab1.optional(args);
    }

    static int computedN() {
        int n = (int) (Math.random() * 1_000_000);
        n = n * 3;
        n = n + 0b10101;
        n = n + 0xff;
        n = n * 6;
        return n;
    }

    static int recSum(int n) {
        int sum = 0;
        do {
            sum = 0;
            while (n != 0) {
                sum = sum + n % 10;
                n = n / 10;
            }
            System.out.println(sum);
            System.out.println('\n');
            n = sum;
        } while (n > 10);
        return n;
    }

    void compulsory() {
        System.out.println("Hello World!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int result = computedN();
        result = recSum(result);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result] + '.');
    }

    public String[] generate(int n, int k, char[] alphabet) {
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < k; j++) {
                int pos = (int) (Math.random() * (alphabet.length + 1)) - 1;
                if (pos < 0) {
                    pos = 0;
                }
                sb.append(alphabet[pos]);
            }
            words[i] = sb.toString();
        }
        return words;
    }
    public boolean checkArg(String[] myArg,int a,int b) {
        if (myArg.length < 3) return false;
        for (int i = 2; i < myArg.length; i++) {
            char c = myArg[i].charAt(0);
            int isLit = 0;
            if (Character.toString(c).matches("[A-Z?]"))
                isLit = 1;
            if (isLit == 0) return false;
        }
        return (a > 0) && (b > 0);
    }
    public int check(String[] words,int i,int j,int n)
    {
        for (int p1=0;p1<n;p1++)
            for(int p2=0;p2<n;p2++)
                if( (int)words[i].charAt(p1)==(int)words[j].charAt(p2)) return 1;
        return 0;
    }
    public int countNeigh(int[][] matrix,int row,int maxim)
    {
        int sum=0;
        for (int i=0;i<maxim;i++)
        {
            sum=sum+matrix[row][i];
        }
        return sum;
    }

    void optional(String[] args) {
        int wordLg = Integer.parseInt(args[1]);
        int nbWords = Integer.parseInt(args[0]); //how many words to generate
         if(!checkArg(args,wordLg,nbWords)){
            System.out.println("Wrong parameters!");
            System.exit(-1);
        } else {

            final int alphabetSize = args.length - 2; //how many characters has the alphabet
            char[] myAlphabet = new char[alphabetSize]; //create the alphabet array

            int l,c;
            int i;
            for (i = 0; i < alphabetSize; i++) //
                myAlphabet[i] = args[i + 2].charAt(0);//converteste in char stringul gasit la poz curenta
            System.out.println("That is your alphabet : ");
            System.out.println(myAlphabet);
            String[] words = generate(nbWords, wordLg, myAlphabet);
            System.out.println("These are your random generated words : ");
            for( i=0;i< nbWords;i++){
                System.out.println(words[i]);
            }

            int[][] isAd = new int[nbWords][nbWords];
            for (l = 0; l < nbWords; l++)
                for (c = 0; c < nbWords; c++)
                    isAd[l][c] = check(words, l,c, wordLg);

            int []neighNo=new int[nbWords];
            boolean sameNo=true;
            int maxN=0;
            int minN=nbWords;
            int answMax=0;
            int answMin=0;
            int initial=countNeigh(isAd,0,nbWords);
            for(i=1;i<nbWords;i++) {
                neighNo[i] = countNeigh(isAd, i,nbWords);
                if(neighNo[i]!=initial)
                    sameNo=false;
                if(neighNo[i]>maxN) {
                    maxN=neighNo[i];
                    answMax=i;
                }
                if(neighNo[i]<minN) {
                    minN = neighNo[i];
                    answMin = i;
                }
            }
            if (sameNo) System.out.println("All genereted words have the same number of neighbours");
             System.out.println("This word has the minimum number of neighbours : ");
             System.out.println(words[answMin]);
             System.out.println("This word has the maximum number of neighbours : ");
            System.out.println(words[answMax]);
        }
    }

}
