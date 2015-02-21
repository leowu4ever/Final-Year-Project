//
// Generated by JTB 1.3.2
//
package timeline;

import java.util.*;
import java.io.*;

/**
 * Dumps the syntax tree to a Writer using the location information in
 * each NodeToken.
 */
public class TreeDumper extends DepthFirstVisitor {
   protected PrintWriter out;
   private int curLine = 1;
   private int curColumn = 1;
   private boolean startAtNextToken = false;
   private boolean printSpecials = true;

   /**
    * The default constructor uses System.out as its output location.
    * You may specify your own Writer or OutputStream using one of the
    * other constructors.
    */
   public TreeDumper()       { out = new PrintWriter(System.out, true); }
   public TreeDumper(Writer o)        { out = new PrintWriter(o, true); }
   public TreeDumper(OutputStream o)  { out = new PrintWriter(o, true); }

   /**
    * Flushes the OutputStream or Writer that this TreeDumper is using.
    */
   public void flushWriter()        { out.flush(); }

   /**
    * Allows you to specify whether or not to print special tokens.
    */
   public void printSpecials(boolean b)   { printSpecials = b; }

   /**
    * Starts the tree dumper on the line containing the next token
    * visited.  For example, if the next token begins on line 50 and the
    * dumper is currently on line 1 of the file, it will set its current
    * line to 50 and continue printing from there, as opposed to
    * printing 49 blank lines and then printing the token.
    */
   public void startAtNextToken()   { startAtNextToken = true; }

   /**
    * Resets the position of the output "cursor" to the first line and
    * column.  When using a dumper on a syntax tree more than once, you
    * either need to call this method or startAtNextToken() between each
    * dump.
    */
   public void resetPosition()      { curLine = curColumn = 1; }

   /**
    * Dumps the current NodeToken to the output stream being used.
    *
    * @throws  IllegalStateException   if the token position is invalid
    *   relative to the current position, i.e. its location places it
    *   before the previous token.
    */
   public void visit(NodeToken n) {
      if ( n.beginLine == -1 || n.beginColumn == -1 ) {
         printToken(n.tokenImage);
         return;
      }

      //
      // Handle special tokens
      //
      if ( printSpecials && n.numSpecials() > 0 )
         for ( Enumeration<NodeToken> e = n.specialTokens.elements(); e.hasMoreElements(); )
            visit(e.nextElement());

      //
      // Handle startAtNextToken option
      //
      if ( startAtNextToken ) {
         curLine = n.beginLine;
         curColumn = 1;
         startAtNextToken = false;

         if ( n.beginColumn < curColumn )
            out.println();
      }

      //
      // Check for invalid token position relative to current position.
      //
      if ( n.beginLine < curLine )
         throw new IllegalStateException("at token \"" + n.tokenImage +
            "\", n.beginLine = " + Integer.toString(n.beginLine) +
            ", curLine = " + Integer.toString(curLine));
      else if ( n.beginLine == curLine && n.beginColumn < curColumn )
         throw new IllegalStateException("at token \"" + n.tokenImage +
            "\", n.beginColumn = " +
            Integer.toString(n.beginColumn) + ", curColumn = " +
            Integer.toString(curColumn));

      //
      // Move output "cursor" to proper location, then print the token
      //
      if ( curLine < n.beginLine ) {
         curColumn = 1;
         for ( ; curLine < n.beginLine; ++curLine )
            out.println();
      }

      for ( ; curColumn < n.beginColumn; ++curColumn )
         out.print(" ");

      printToken(n.tokenImage);
   }

   private void printToken(String s) {
      for ( int i = 0; i < s.length(); ++i ) { 
         if ( s.charAt(i) == '\n' ) {
            ++curLine;
            curColumn = 1;
         }
         else
            curColumn++;

         out.print(s.charAt(i));
      }

      out.flush();
   }
}
