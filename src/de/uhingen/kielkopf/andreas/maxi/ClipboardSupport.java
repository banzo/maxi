package de.uhingen.kielkopf.andreas.maxi;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.Console;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClipboardSupport {
   final static CopyOnWriteArrayList<String> clip=new CopyOnWriteArrayList<>();
   static String                             line="";
   public void clipln(String s) {
      clip.add(s.replaceAll(InfoLine.ANY_ESC, ""));
   }
   public void println(String s) {
      clipln(s);
      System.out.println(s);
   }
   public void println() {
      println(line);
      line="";
   }
   public void println(InfoLine info) {
      println(info.toString());
   }
   public void printonly(String s) {
      System.out.println(s);
   }
   public void print(String s) {
      line+=s;
   }
   public void transfer() {
      if (!clip.isEmpty()) {
         String          a =String.join(System.lineSeparator(), clip);
         Clipboard       b =Toolkit.getDefaultToolkit().getSystemClipboard();
         StringSelection ss=new StringSelection(a);
         b.setContents(ss, ss);
         Console c=System.console();
         if (c != null) {
            printonly("\nPress ENTER to proceed.\n");
            c.readLine();
         }
         clip.clear();
      }
   }
}