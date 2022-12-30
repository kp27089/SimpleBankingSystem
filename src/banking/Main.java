package banking;

public class Main {
    public static void main(String[] args) {
        Client client;
        if(args.length > 0){
            client = new Client(args[1]);
        }else{
            client = new Client();
        }
        client.execute();


    }
}