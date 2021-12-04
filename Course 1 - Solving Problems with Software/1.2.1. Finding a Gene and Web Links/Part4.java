import edu.duke.URLResource;

public class Part4 {
    
    public void youtubeURL(){
        
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        URLResource file = new URLResource(url);

        for (String item : file.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {

                /*
                int beg = itemLower.lastIndexOf("\"", pos);
                int end = itemLower.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));
                */

                /*
                int beg = item.lastIndexOf("\"",pos);
                int end = item.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));
                */

                int beg = itemLower.indexOf("\"",pos-9);
                int end = itemLower.indexOf("\"", pos+1);
                System.out.println(itemLower.substring(beg+1,end));



            }
        }
    }

    public static void main (String[] args) {
        Part4 p4 = new Part4();
        p4.youtubeURL();
    }
}