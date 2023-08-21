import java.util.Scanner;

public class RailFenceCipher {

    public static String encrypt(String message, int rails) {
        char[][] railMatrix = new char[rails][message.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < message.length(); j++) {
                railMatrix[i][j] = '.';
            }
        }

        int row = 0;
        int col = 0;
        boolean directionDown = false;

        for (int i = 0; i < message.length(); i++) {
            if (row == 0 || row == rails - 1) {
                directionDown = !directionDown;
            }

            railMatrix[row][col] = message.charAt(i);

            if (directionDown) {
                row++;
            } else {
                row--;
                col++;
            }
        }

        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < message.length(); j++) {
                if (railMatrix[i][j] != '.') {
                    encryptedMessage.append(railMatrix[i][j]);
                }
            }
        }

        return encryptedMessage.toString();
    }

    public static String decrypt(String encryptedMessage, int rails) {
        char[][] railMatrix = new char[rails][encryptedMessage.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < encryptedMessage.length(); j++) {
                railMatrix[i][j] = '.';
            }
        }

        int row = 0;
        int col = 0;
        boolean directionDown = false;

        for (int i = 0; i < encryptedMessage.length(); i++) {
            if (row == 0 || row == rails - 1) {
                directionDown = !directionDown;
            }

            railMatrix[row][col] = '*';

            if (directionDown) {
                row++;
            } else {
                row--;
                col++;
            }
        }

        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < encryptedMessage.length(); j++) {
                if (railMatrix[i][j] == '*' && index < encryptedMessage.length()) {
                    railMatrix[i][j] = encryptedMessage.charAt(index++);
                }
            }
        }

        StringBuilder decryptedMessage = new StringBuilder();
        row = 0;
        col = 0;
        for (int i = 0; i < encryptedMessage.length(); i++) {
            if (row == 0 || row == rails - 1) {
                directionDown = !directionDown;
            }

            if (railMatrix[row][col] != '*') {
                decryptedMessage.append(railMatrix[row][col]);
            }

            if (directionDown) {
                row++;
            } else {
                row--;
                col++;
            }
        }

        return decryptedMessage.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();

        System.out.print("Enter the number of rails: ");
        int rails = scanner.nextInt();

        String encryptedMessage = encrypt(message, rails);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, rails);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }
}
