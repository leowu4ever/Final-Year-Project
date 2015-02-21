//
// Generated by JTB 1.3.2
//

package timelinedata.syntaxtree;

/**
 * Grammar production:
 * nodeToken -> <INTEGER_LITERAL>
 */
public class IntegerLiteral implements Node {
   public NodeToken nodeToken;

   public IntegerLiteral(NodeToken n0) {
      nodeToken = n0;
   }

   public void accept(timelinedata.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(timelinedata.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(timelinedata.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(timelinedata.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

