//
// Generated by JTB 1.3.2
//

package timeline;

import java.util.*;

/**
 * Represents a sequence of nodes nested within a choice, list,
 * optional list, or optional, e.g. ( A B )+ or [ C D E ]
 */
public class NodeSequence implements NodeListInterface {
   public NodeSequence(int n) {
      nodes = new Vector<Node>(n);
   }

   public NodeSequence(Node firstNode) {
      nodes = new Vector<Node>();
      addNode(firstNode);
   }

   public void addNode(Node n) {
      nodes.addElement(n);
   }

   public Node elementAt(int i)  { return nodes.elementAt(i); }
   public Enumeration<Node> elements() { return nodes.elements(); }
   public int size()             { return nodes.size(); }
   public void accept(timeline.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(timeline.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(timeline.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(timeline.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }

   public Vector<Node> nodes;
}

