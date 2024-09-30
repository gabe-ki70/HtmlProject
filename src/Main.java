import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
public class Main {


        public static void main(String[] args) {
            Main html = new Main();
        }

        public Main() {

            try {
                System.out.println();
                System.out.print("hello \n");
                Scanner linkholder = new Scanner(System.in);
                System.out.println("Enter Link: ");
                String userLink = linkholder.nextLine();
                Scanner keyword = new Scanner(System.in);
                System.out.println("Search for Keyword: ");
                String userKeyword = linkholder.nextLine();
                URL url = new URL(userLink);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream())
                );
                String line;
                while ( (line = reader.readLine()) != null ) {
                    if (line.contains("href")) {
                        int start = line.indexOf("href=")+6;
                        String link = line.substring(start);

                        int end = link.indexOf("\"");
                        if (end==-1){
                            end = link.indexOf("'");
                        }
                        if (end==-1){
                            end = link.indexOf("--");
                        }
                        link = line.substring(start, start+end);
                        if (link.contains(userKeyword)){
                        System.out.println(start + "," + end);
                        //System.out.println(link);
                        System.out.println(line.substring(start, start+end));
                        }
                    }
                }
                reader.close();
            } catch(Exception ex) {
                System.out.println(ex);
            }

        }

    }

