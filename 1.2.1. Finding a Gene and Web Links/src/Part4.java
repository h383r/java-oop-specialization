import edu.duke.URLResource;

public class Part4 {
    
    public String youtubeURL(){
        
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        URLResource urlResource = new URLResource(url);
        String result = "";

        // For each word in urlResource
        for (String word : urlResource.words()) {
            
            // Set to lowercase without modify the original String
            String lcWord = word.toLowerCase();
            
            int index = lcWord.indexOf("youtube.com");
            
            // If "youtube.com" appears
            if (index != -1) {

                // Index of left "
                int beginIndex = lcWord.lastIndexOf("\"", index);

                // Index of right "
                int endIndex = lcWord.indexOf("\"", index);

                // Substring between left " and right " is the url
                String url = word.substring(beginIndex + 1, endIndex);
                
                result = result + url + "\n";
            }
        }
        return result;
    }

    public static void main (String[] args) {
        Part4 p4 = new Part4();
        System.out.println("Youtube URL:\n" + p4.youtubeURL());
    }
}