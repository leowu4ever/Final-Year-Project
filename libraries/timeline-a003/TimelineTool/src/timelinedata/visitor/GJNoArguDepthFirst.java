//
// Generated by JTB 1.3.2
//

package timelinedata.visitor;
import timelinedata.syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJNoArguDepthFirst<R> implements GJNoArguVisitor<R> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public R visit(NodeList n) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n) {
      if ( n.present() )
         return n.node.accept(this);
      else
         return null;
   }

   public R visit(NodeSequence n) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n) { return null; }

   //
   // User-generated visitor methods below
   //

   /**
    * nodeOptional -> ( OptionList() )?
    * nodeListOptional -> ( Variable() )*
    * nodeToken -> <EOF>
    */
   public R visit(Goal n) {
      R _ret=null;
      n.nodeOptional.accept(this);
      n.nodeListOptional.accept(this);
      n.nodeToken.accept(this);
      return _ret;
   }

   /**
    * nodeList -> ( Option() )+
    */
   public R visit(OptionList n) {
      R _ret=null;
      n.nodeList.accept(this);
      return _ret;
   }

   /**
    * nodeToken -> "Playback"
    * nodeToken1 -> "="
    * playbackChoice -> PlaybackChoice()
    */
   public R visit(Option n) {
      R _ret=null;
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.playbackChoice.accept(this);
      return _ret;
   }

   /**
    * nodeChoice -> PlaybackChoiceFrames()
    *       | PlaybackChoiceTime()
    */
   public R visit(PlaybackChoice n) {
      R _ret=null;
      n.nodeChoice.accept(this);
      return _ret;
   }

   /**
    * integerLiteral -> IntegerLiteral()
    * nodeToken -> "FPS"
    */
   public R visit(PlaybackChoiceFrames n) {
      R _ret=null;
      n.integerLiteral.accept(this);
      n.nodeToken.accept(this);
      return _ret;
   }

   /**
    * nodeToken -> "Real-time"
    */
   public R visit(PlaybackChoiceTime n) {
      R _ret=null;
      n.nodeToken.accept(this);
      return _ret;
   }

   /**
    * nodeToken -> "Variable"
    * identifier -> Identifier()
    * nodeList -> ( Vertex() )+
    */
   public R visit(Variable n) {
      R _ret=null;
      n.nodeToken.accept(this);
      n.identifier.accept(this);
      n.nodeList.accept(this);
      return _ret;
   }

   /**
    * nodeToken -> "Vertex"
    * floatingPointLiteral -> FloatingPointLiteral()
    * floatingPointLiteral1 -> FloatingPointLiteral()
    * floatingPointLiteral2 -> FloatingPointLiteral()
    * floatingPointLiteral3 -> FloatingPointLiteral()
    * floatingPointLiteral4 -> FloatingPointLiteral()
    * floatingPointLiteral5 -> FloatingPointLiteral()
    */
   public R visit(Vertex n) {
      R _ret=null;
      n.nodeToken.accept(this);
      n.floatingPointLiteral.accept(this);
      n.floatingPointLiteral1.accept(this);
      n.floatingPointLiteral2.accept(this);
      n.floatingPointLiteral3.accept(this);
      n.floatingPointLiteral4.accept(this);
      n.floatingPointLiteral5.accept(this);
      return _ret;
   }

   /**
    * nodeToken -> <IDENTIFIER>
    */
   public R visit(Identifier n) {
      R _ret=null;
      n.nodeToken.accept(this);
      return _ret;
   }

   /**
    * nodeToken -> <FLOATING_POINT_LITERAL>
    */
   public R visit(FloatingPointLiteral n) {
      R _ret=null;
      n.nodeToken.accept(this);
      return _ret;
   }

   /**
    * nodeToken -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n) {
      R _ret=null;
      n.nodeToken.accept(this);
      return _ret;
   }

}
