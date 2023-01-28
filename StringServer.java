import java.io.IOException;
import java.net.URI;
import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String[] wordsArray = new String[20];
    for(int i = 0; i < 20; i++){
        wordsArray[i] = "";
    }
    // sized 20 just in case
    @Override
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            //not optimal at all because I don't know java that well but 
            // I am going to loop everything
            String to_return = "";
            for(int i = 0; i < 20 ;i++){
                if (wordsArray[i] != ""){
                    to_return += wordsArray[i] + "\n";
                }
            }
            return to_return;
        } 
         else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("count")) {
                    num += Integer.parseInt(parameters[1]);
                    return String.format("Number increased by %s! It's now %d", parameters[1], num);
                }
            }
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
