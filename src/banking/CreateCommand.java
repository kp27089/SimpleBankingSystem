package banking;


import java.util.concurrent.ThreadLocalRandom;

public class CreateCommand implements Command{

    final String prefix = "400000";
    Receiver receiver = new Receiver();
    String identifier;
    Account account;
    LuhnAlg luhn = new LuhnAlg();

    public String getIdentifier() {
        return identifier;
    }

    public Account getAccount() {
        return account;
    }

    public void execute(){
        account = new Account(generateCardNumber(),pin());
        int len = account.getCardNumber().length();
        identifier = account.getCardNumber().substring(6,len-1);


    }

    private String generateCardNumber(){
        int num = 0;
        String result;
        int last;
        do{

            int number;
            for(int i = 0; i < 9; i++){
                ThreadLocalRandom random = ThreadLocalRandom.current();
                number = random.nextInt(1,10);
                num=num*10+number;
            }
            last =luhn.lastDigit(prefix+num);

            result = prefix+num+last;
        }while(receiver.getAccounts().containsKey(num));

        return result;
    }

    private String pin(){
        int num = ThreadLocalRandom.current().nextInt(9999);
        String formatted = String.format("%04d", num);
        System.out.println(formatted);

        return formatted;
    }



}
