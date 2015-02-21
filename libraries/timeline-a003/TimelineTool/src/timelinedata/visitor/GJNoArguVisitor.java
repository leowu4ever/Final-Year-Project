//
// Generated by JTB 1.3.2
//

package timelinedata.visitor;
import timelinedata.syntaxtree.*;
import java.util.*;

/**
 * All GJ visitors with no argument must implement this interface.
 */

public interface GJNoArguVisitor<R> {

   //
   // GJ Auto class visitors with no argument
   //

   public R visit(NodeList n);
   public R visit(NodeListOptional n);
   public R visit(NodeOptional n);
   public R visit(NodeSequence n);
   public R visit(NodeToken n);

   //
   // User-generated visitor methods below
   //

   /**
    * nodeOptional -> ( OptionList() )?
    * nodeListOptional -> ( Variable() )*
    * nodeToken -> <EOF>
    */
   public R visit(Goal n);

   /**
    * nodeList -> ( Option() )+
    */
   public R visit(OptionList n);

   /**
    * nodeToken -> "Playback"
    * nodeToken1 -> "="
    * playbackChoice -> PlaybackChoice()
    */
   public R visit(Option n);

   /**
    * nodeChoice -> PlaybackChoiceFrames()
    *       | PlaybackChoiceTime()
    */
   public R visit(PlaybackChoice n);

   /**
    * integerLiteral -> IntegerLiteral()
    * nodeToken -> "FPS"
    */
   public R visit(PlaybackChoiceFrames n);

   /**
    * nodeToken -> "Real-time"
    */
   public R visit(PlaybackChoiceTime n);

   /**
    * nodeToken -> "Variable"
    * identifier -> Identifier()
    * nodeList -> ( Vertex() )+
    */
   public R visit(Variable n);

   /**
    * nodeToken -> "Vertex"
    * floatingPointLiteral -> FloatingPointLiteral()
    * floatingPointLiteral1 -> FloatingPointLiteral()
    * floatingPointLiteral2 -> FloatingPointLiteral()
    * floatingPointLiteral3 -> FloatingPointLiteral()
    * floatingPointLiteral4 -> FloatingPointLiteral()
    * floatingPointLiteral5 -> FloatingPointLiteral()
    */
   public R visit(Vertex n);

   /**
    * nodeToken -> <IDENTIFIER>
    */
   public R visit(Identifier n);

   /**
    * nodeToken -> <FLOATING_POINT_LITERAL>
    */
   public R visit(FloatingPointLiteral n);

   /**
    * nodeToken -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n);

}

