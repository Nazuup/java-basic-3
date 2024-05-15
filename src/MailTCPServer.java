import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MailTCPServer {

    public static void main(String arg[]) {
        try {
            /* 通信の準備をする */
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            // scanner.close();
            System.out.println("localhostの" + port + "番ポートで待機します");
            ServerSocket server = new ServerSocket(port); // ポート番号を指定し、クライアントとの接続の準備を行う

            Socket socket = server.accept(); // クライアントからの接続要求を待ち、
            // 要求があればソケットを取得し接続を行う
            System.out.println("接続しました。相手の入力を待っています......");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            while (true) {

                Mail mail = (Mail) ois.readObject();// Integerクラスでキャスト。

                System.out.println("メールが届きました。");
                String mailTitle = mail.getTitle();
                System.out.println("メールの件名は" + mailTitle);
                String messageFromClient = mail.getMessage();
                System.out.println("メールの内容は" + messageFromClient);

                String responceTitle = "Re. " + mail.title;
                System.out.println("メールの返信内容を入力して下さい ↓");
                String message = scanner.next();

                Mail response = new Mail();
                response.setTitle(responceTitle);
                response.setMessage(message);

                oos.writeObject(response);
                oos.flush();

                System.out.println("メールを返信しました。クライアントからの応答をお待ちください");

                Mail re = (Mail) ois.readObject();
                if(!re.getTitle().equals("y")){
                    System.out.println("接続が切断されました");
                    scanner.close();
                    // close処理
                    ois.close();
                    oos.close();
                    // socketの終了。
                    socket.close();
                    server.close();
                    break;
                }

            }

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.out.println("ポート番号が不正、ポートが使用中です");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
