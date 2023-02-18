public class Palindrome{

    public Deque<Character> wordToDeque(String word){
        Deque<Character> letters=new LinkedListDeque<>();
        for(int i=0;i<word.length();++i){
            letters.addLast(word.charAt(i));
        }
        return letters;
    }

    private boolean helperIsPelindrome(Deque<Character> letters){
        if(letters.size()<2){
            return true;
        }
        char firstChar= letters.removeFirst();
        char lastChar=letters.removeLast();
        if(firstChar!=lastChar){
            return false;
        }
        return helperIsPelindrome(letters);
    }

    public boolean isPalindrome(String word){
        Deque<Character> letters=wordToDeque(word);
        return helperIsPelindrome(letters);
    }

    private boolean helperIsPelindrome(Deque<Character> letters,CharacterComparator cc){
        if(letters.size()<2){
            return true;
        }
        char firstChar= letters.removeFirst();
        char lastChar=letters.removeLast();
        if(!cc.equalChars(firstChar,lastChar)){
            return false;
        }
        return helperIsPelindrome(letters,cc);
    }

    public boolean isPalindrome(String word,CharacterComparator cc){
        Deque<Character> letters=wordToDeque(word);
        return helperIsPelindrome(letters,cc);
    }
}