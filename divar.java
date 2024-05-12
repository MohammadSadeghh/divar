import java.util.ArrayList;
import java.util.Scanner;

public class divar {
    advertise[] ads = new advertise[1000];
    user[] users = new user[1000];
    private int adsNum;
    private int userNum;
    public static Scanner scanner = new Scanner(System.in);

    public divar(){
        setAdsNum(0);
        setUserNum(0);
    }
    public void setAdsNum(int adsNum) {
        this.adsNum = adsNum;
    }
    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }
    public int getUserNum() {
        return userNum;
    }
    public int getAdsNum() {
        return adsNum;
    }

    public int validUser(String username){
        for (int i=0; i<getUserNum(); i++){
            if(users[i].getUsername().equals(username)){
                return 0;
            }
        }
        return 1;
    }

    public int validAd(String title){
        for (int i=0; i<getAdsNum(); i++){
            if(ads[i].getTitle().equals(title)){
                return 0;
            }
        }
        return 1;
    }

    public void register(String username){

        if (validUser(username) == 0){
            System.out.println("invalid username");
        }
        else {
            users[getUserNum()] = new user(username);
            setUserNum(getUserNum()+1);
            System.out.println("registered successfully");
        }
    }

    public void addAd(String username, String title){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        if (validAd(title) == 0){
            System.out.println("invalid title");
            return;
        }
        ads[getAdsNum()] = new advertise(title,username);
        System.out.println("posted successfully");
        setAdsNum(getAdsNum()+1);
    }

    public void addAd(String username, String title,String tag){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        if (validAd(title) == 0){
            System.out.println("invalid title");
            return;
        }
        ads[getAdsNum()] = new advertise(title,username,tag);
        System.out.println("posted successfully");
        setAdsNum(getAdsNum()+1);
    }

    public void remAd(String username, String title){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        if (validAd(title) == 1){
            System.out.println("invalid title");
            return;
        }

        for (advertise advertise : ads){
            if(advertise.getTitle().equals(title)){
                if (advertise.getOwner().equals(username)){
                    advertise.clear();
                    System.out.println("removed successfully");
                    return;
                }
                else {
                    System.out.println("access denied");
                    return;
                }
            }
        }
    }

    public void listAd(String username){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        for(advertise advertise : ads){
            if (advertise == null){
                System.out.println();
                return;
            }
            if(advertise.getOwner().equals(username)){
                System.out.print(advertise.getTitle()+" ");
            }
        }
    }

    public void listAd(String username,String tag){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        for(advertise advertise : ads){
            if(advertise == null){
                System.out.println();
                return;
            }
            if(advertise.getOwner().equals(username) && advertise.getTag().equals(tag)){
                System.out.print(advertise.getTitle()+" ");
            }
        }
    }

    public void addFav(String username, String title){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        if(validAd(title) == 1){
            System.out.println("invalid title");
            return;
        }
        for (advertise advertise : ads){
            if(advertise == null){
                return;
            }
            if(advertise.getTitle().equals(title)){
                if(advertise.getIsFavor().contains(username)){
                    System.out.println("already favorite");
                }
                else {
                    advertise.setFavor(username);
                    System.out.println("added successfully");
                }
            }
        }
    }

    public void remFav(String username, String title){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        if(validAd(title) == 1){
            System.out.println("invalid title");
            return;
        }
        for (advertise advertise : ads){
            if (advertise==null){
                return;
            }
            if(advertise.getTitle().equals(title)){
                if(advertise.getIsFavor().contains(username)){
                    advertise.remFavor(username);
                    System.out.println("removed successfully");
                    return;
                }
                else {
                    System.out.println("already not favorite");
                }
            }
        }
    }

    public void listFav(String username){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        for (advertise advertise : ads){
            if (advertise == null){
                System.out.println();
                return;
            }
            if(advertise.getIsFavor().contains(username)){
                System.out.print(advertise.getTitle()+" ");
            }
        }
    }

    public void listFav(String username, String tag){
        if (validUser(username) == 1){
            System.out.println("invalid username");
            return;
        }
        for (advertise advertise : ads){
            if (advertise == null){
                System.out.println();
                return;
            }
            if(advertise.getIsFavor().contains(username) && advertise.getTag().equals(tag)){
                System.out.print(STR."\{advertise.getTitle()} ");
            }
        }
    }

    public static void main(String[] args){
        divar divar = new divar();
        int orders = scanner.nextInt();
        for (int i=0; i<orders; i++){
            String order = scanner.next();
            if(order.equals("register")){
                divar.register(scanner.next());
            }
            else if(order.equals("add_advertise")){
                String line= scanner.nextLine();
                String[] lineOrders = line.split(" ");
                if (lineOrders.length == 3){
                    divar.addAd(lineOrders[1],lineOrders[2]);
                }
                else {
                    divar.addAd(lineOrders[1],lineOrders[2],lineOrders[3]);
                }
            }
            else if(order.equals("rem_advertise")){
                divar.remAd(scanner.next(), scanner.next());
            }
            else if(order.equals("list_my_advertises")){
                String line= scanner.nextLine();
                String[] lineOrders = line.split(" ");
                if (lineOrders.length == 2){
                    divar.listAd(lineOrders[1]);
                }
                else {
                    divar.listAd(lineOrders[1],lineOrders[2]);
                }
            }
            else if (order.equals("add_favorite")){
                divar.addFav(scanner.next(), scanner.next());
            }
            else if (order.equals("rem_favorite")){
                divar.remFav(scanner.next(), scanner.next());
            }
            else if(order.equals("list_favorite_advertises")){
                String line= scanner.nextLine();
                String[] lineOrders = line.split(" ");
                if (lineOrders.length == 2){
                    divar.listFav(lineOrders[1]);
                }
                else {
                    divar.listFav(lineOrders[1],lineOrders[2]);
                }
            }

        }

    }

}
class advertise{
    private String title;
    private String owner;
    private String tag;
    private final ArrayList<String> isFavor = new ArrayList<>();

    public String getOwner() {
        return owner;
    }
    public ArrayList<String> getIsFavor() {
        return isFavor;
    }
    public String getTitle() {
        return title;
    }
    public String getTag() {
        return tag;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public void setFavor(String username) {
        isFavor.add(username);
    }
    public void remFavor(String username) {
        isFavor.remove(username);
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void clear(){
        setTag("");
        isFavor.clear();
        setOwner("");
        setTitle("");
    }

    public advertise(String title, String owner){
        setOwner(owner);
        setTitle(title);
        setTag("");
    }

    public advertise(String title, String owner, String tag){
        setOwner(owner);
        setTitle(title);
        setTag(tag);
    }
}
class user{
    private String username;
    public user(String username){
        setUsername(username);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
