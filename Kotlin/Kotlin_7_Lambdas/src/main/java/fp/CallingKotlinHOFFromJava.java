package fp;

public class CallingKotlinHOFFromJava {

    public static void main(String[] args) {

        /* provided later
        FList<String> words = FList.Companion.flistOf("Kotlin", "from", "Java");

        FList<Integer> wordLengths = words.filter(word -> word.length() > 0).map(word -> word.length());

        System.out.println(wordLengths);
        wordLengths =
              words.filter(new kotlin.jvm.functions.Function1<String, Boolean>() {
                        @Override
                        public Boolean invoke(String word) {
                            return word.length() > 0;
                        }
                    })
                    .map(new kotlin.jvm.functions.Function1<String, Integer>() {
                        @Override
                        public Integer invoke(String word) {
                            return word.length();
                    }
        });

        words.forEach(
            word -> {
                System.out.println(word);
                return kotlin.Unit.INSTANCE;
            }
        );

    */
    }

}
