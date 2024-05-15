import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket; //ネットワーク関連のパッケージを利用する
import java.util.Scanner;

public class MailTCPClient {

    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in, "SHIFT_JIS");
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            System.out.println("メールを送ります");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("件名を入力してください ↓");
            String title = scanner.next();
            System.out.println("メールの本文を入力してください ↓");
            String message = scanner.next();
            scanner.close();

            Mail mail = new Mail();
            mail.setTitle(title);
            mail.setMessage(message);

            oos.writeObject(mail);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Mail okaeshiMail = (Mail) ois.readObject();

            String replayTitle = okaeshiMail.getTitle();
            System.out.println("サーバからメールの返信がきました\n" + replayTitle);
            String replayMessage = okaeshiMail.getMessage();
            System.out.println(replayMessage);

            ois.close();
            oos.close();
            socket.close();

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
