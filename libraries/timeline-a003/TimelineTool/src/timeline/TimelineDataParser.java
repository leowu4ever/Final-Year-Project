/* Generated By:JavaCC: Do not edit this line. TimelineDataParser.java */
package timeline;

import timelinedata.*;
import timelinedata.syntaxtree.*;
import java.util.Vector;


public class TimelineDataParser implements TimelineDataParserConstants {

  static final public Goal Goal() throws ParseException {
   NodeOptional n0 = new NodeOptional();
   OptionList n1;
   NodeListOptional n2 = new NodeListOptional();
   Variable n3;
   NodeToken n4;
   Token n5;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLAYBACK:
      n1 = OptionList();
        n0.addNode(n1);
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VARIABLE:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      n3 = Variable();
        n2.addNode(n3);
    }
     n2.nodes.trimToSize();
    n5 = jj_consume_token(0);
      n5.beginColumn++; n5.endColumn++;
      n4 = JTBToolkit.makeNodeToken(n5);
     {if (true) return new Goal(n0,n2,n4);}
    throw new Error("Missing return statement in function");
  }

  static final public OptionList OptionList() throws ParseException {
   NodeList n0 = new NodeList();
   Option n1;
    label_2:
    while (true) {
      n1 = Option();
        n0.addNode(n1);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLAYBACK:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
    }
     n0.nodes.trimToSize();
     {if (true) return new OptionList(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public Option Option() throws ParseException {
   NodeToken n0;
   Token n1;
   NodeToken n2;
   Token n3;
   PlaybackChoice n4;
    n1 = jj_consume_token(PLAYBACK);
                   n0 = JTBToolkit.makeNodeToken(n1);
    n3 = jj_consume_token(EQUALS);
            n2 = JTBToolkit.makeNodeToken(n3);
    n4 = PlaybackChoice();
     {if (true) return new Option(n0,n2,n4);}
    throw new Error("Missing return statement in function");
  }

  static final public PlaybackChoice PlaybackChoice() throws ParseException {
   NodeChoice n0;
   PlaybackChoiceFrames n1;
   PlaybackChoiceTime n2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      n1 = PlaybackChoiceFrames();
        n0 = new NodeChoice(n1, 0);
      break;
    case REALTIME:
      n2 = PlaybackChoiceTime();
        n0 = new NodeChoice(n2, 1);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return new PlaybackChoice(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public PlaybackChoiceFrames PlaybackChoiceFrames() throws ParseException {
   IntegerLiteral n0;
   NodeToken n1;
   Token n2;
    n0 = IntegerLiteral();
    n2 = jj_consume_token(FPS);
              n1 = JTBToolkit.makeNodeToken(n2);
     {if (true) return new PlaybackChoiceFrames(n0,n1);}
    throw new Error("Missing return statement in function");
  }

  static final public PlaybackChoiceTime PlaybackChoiceTime() throws ParseException {
   NodeToken n0;
   Token n1;
    n1 = jj_consume_token(REALTIME);
                    n0 = JTBToolkit.makeNodeToken(n1);
     {if (true) return new PlaybackChoiceTime(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public Variable Variable() throws ParseException {
   NodeToken n0;
   Token n1;
   Identifier n2;
   NodeList n3 = new NodeList();
   Vertex n4;
    n1 = jj_consume_token(VARIABLE);
                   n0 = JTBToolkit.makeNodeToken(n1);
    n2 = Identifier();
    label_3:
    while (true) {
      n4 = Vertex();
        n3.addNode(n4);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VERTEX:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
    }
     n3.nodes.trimToSize();
     {if (true) return new Variable(n0,n2,n3);}
    throw new Error("Missing return statement in function");
  }

  static final public Vertex Vertex() throws ParseException {
   NodeToken n0;
   Token n1;
   FloatingPointLiteral n2;
   FloatingPointLiteral n3;
   FloatingPointLiteral n4;
   FloatingPointLiteral n5;
   FloatingPointLiteral n6;
   FloatingPointLiteral n7;
    n1 = jj_consume_token(VERTEX);
                 n0 = JTBToolkit.makeNodeToken(n1);
    n2 = FloatingPointLiteral();
    n3 = FloatingPointLiteral();
    n4 = FloatingPointLiteral();
    n5 = FloatingPointLiteral();
    n6 = FloatingPointLiteral();
    n7 = FloatingPointLiteral();
     {if (true) return new Vertex(n0,n2,n3,n4,n5,n6,n7);}
    throw new Error("Missing return statement in function");
  }

  static final public Identifier Identifier() throws ParseException {
   NodeToken n0;
   Token n1;
    n1 = jj_consume_token(IDENTIFIER);
                     n0 = JTBToolkit.makeNodeToken(n1);
     {if (true) return new Identifier(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public FloatingPointLiteral FloatingPointLiteral() throws ParseException {
   NodeToken n0;
   Token n1;
    n1 = jj_consume_token(FLOATING_POINT_LITERAL);
                                 n0 = JTBToolkit.makeNodeToken(n1);
     {if (true) return new FloatingPointLiteral(n0);}
    throw new Error("Missing return statement in function");
  }

  static final public IntegerLiteral IntegerLiteral() throws ParseException {
   NodeToken n0;
   Token n1;
    n1 = jj_consume_token(INTEGER_LITERAL);
                          n0 = JTBToolkit.makeNodeToken(n1);
     {if (true) return new IntegerLiteral(n0);}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public TimelineDataParserTokenManager token_source;
  static JavaCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[5];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1000,0x40,0x1000,0x6000,0x80,};
   }

  /** Constructor with InputStream. */
  public TimelineDataParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public TimelineDataParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new TimelineDataParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public TimelineDataParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new TimelineDataParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public TimelineDataParser(TimelineDataParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(TimelineDataParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[19];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 5; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 19; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}

class JTBToolkit {
   static NodeToken makeNodeToken(Token t) {
      return new NodeToken(t.image.intern(), t.kind, t.beginLine, t.beginColumn, t.endLine, t.endColumn);
   }
}
