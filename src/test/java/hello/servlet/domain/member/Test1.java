package hello.servlet.domain.member;

public class Test1 {

    public static void main(String[] args) {
        String s = "abDcAdeZP";
        String answer = "";
        char[] tempArr = s.toCharArray();

        for(int i=0; i<tempArr.length; i++) {
            for(int j=i+1; j<tempArr.length; j++) {
                if(tempArr[i] < tempArr[j]) { //내림차순
                    char tmp = tempArr[i];
                    tempArr[i] = tempArr[j];
                    tempArr[j] = tmp;
                }
            }
        }
        answer = tempArr.toString();
        for (char c : tempArr) {
            System.out.println(c);
        }


    }


}
