//
// Generated by JTB 1.3.2
//

package timeline;

/**
 * Grammar production:
 * integerLiteral -> IntegerLiteral()
 * nodeToken -> "FPS"
 */
public class PlaybackChoiceFrames implements Node {
   public IntegerLiteral integerLiteral;
   public NodeToken nodeToken;

   public PlaybackChoiceFrames(IntegerLiteral n0, NodeToken n1) {
      integerLiteral = n0;
      nodeToken = n1;
   }

   public PlaybackChoiceFrames(IntegerLiteral n0) {
      integerLiteral = n0;
      nodeToken = new NodeToken("FPS");
   }

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
}

