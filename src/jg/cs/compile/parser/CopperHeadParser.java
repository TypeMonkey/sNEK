/*
 * CopperHeadParser.java
 *
 * THIS FILE HAS BEEN GENERATED AUTOMATICALLY. DO NOT EDIT!
 */

package jg.cs.compile.parser;

import java.io.Reader;

import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ProductionPattern;
import net.percederberg.grammatica.parser.ProductionPatternAlternative;
import net.percederberg.grammatica.parser.RecursiveDescentParser;
import net.percederberg.grammatica.parser.Tokenizer;

/**
 * A token stream parser.
 *
 *
 */
public class CopperHeadParser extends RecursiveDescentParser {

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_1 = 3001;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_2 = 3002;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_3 = 3003;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_4 = 3004;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_5 = 3005;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_6 = 3006;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_7 = 3007;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_8 = 3008;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_9 = 3009;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_10 = 3010;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_11 = 3011;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_12 = 3012;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_13 = 3013;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_14 = 3014;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_15 = 3015;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_16 = 3016;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_17 = 3017;

    /**
     * Creates a new parser with a default analyzer.
     *
     * @param in             the input stream to read from
     *
     * @throws ParserCreationException if the parser couldn't be
     *             initialized correctly
     */
    public CopperHeadParser(Reader in) throws ParserCreationException {
        super(in);
        createPatterns();
    }

    /**
     * Creates a new parser.
     *
     * @param in             the input stream to read from
     * @param analyzer       the analyzer to use while parsing
     *
     * @throws ParserCreationException if the parser couldn't be
     *             initialized correctly
     */
    public CopperHeadParser(Reader in, CopperHeadAnalyzer analyzer)
        throws ParserCreationException {

        super(in, analyzer);
        createPatterns();
    }

    /**
     * Creates a new tokenizer for this parser. Can be overridden by a
     * subclass to provide a custom implementation.
     *
     * @param in             the input stream to read from
     *
     * @return the tokenizer created
     *
     * @throws ParserCreationException if the tokenizer couldn't be
     *             initialized correctly
     */
    protected Tokenizer newTokenizer(Reader in)
        throws ParserCreationException {

        return new CopperHeadTokenizer(in);
    }

    /**
     * Initializes the parser by creating all the production patterns.
     *
     * @throws ParserCreationException if the parser couldn't be
     *             initialized correctly
     */
    private void createPatterns() throws ParserCreationException {
        ProductionPattern             pattern;
        ProductionPatternAlternative  alt;

        pattern = new ProductionPattern(CopperHeadConstants.PROGRAM,
                                        "program");
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_1, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.EXPR,
                                        "expr");
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_9, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.FUNC_DEF,
                                        "funcDef");
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.DEF, 1, 1);
        alt.addToken(CopperHeadConstants.NAME, 1, 1);
        alt.addProduction(SUBPRODUCTION_11, 1, -1);
        alt.addToken(CopperHeadConstants.COLON, 1, 1);
        alt.addProduction(CopperHeadConstants.TYPE, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, -1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.LET_DEC,
                                        "letDec");
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.LET, 1, 1);
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(SUBPRODUCTION_13, 1, -1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, -1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.IF_EXPR,
                                        "ifExpr");
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.IF, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.BIN_OP,
                                        "binOp");
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_14, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.SET_VAR,
                                        "setVar");
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.SET, 1, 1);
        alt.addToken(CopperHeadConstants.NAME, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.FUNC_CALL,
                                        "funcCall");
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.NAME, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, -1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.WHILE_LOOP,
                                        "whileLoop");
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.WHILE, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 0, -1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.TYPE,
                                        "type");
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_15, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(CopperHeadConstants.ATOM,
                                        "atom");
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_17, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_1,
                                        "Subproduction1");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addProduction(CopperHeadConstants.EXPR, 1, -1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_2,
                                        "Subproduction2");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(CopperHeadConstants.FUNC_DEF, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_3,
                                        "Subproduction3");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(CopperHeadConstants.LET_DEC, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_4,
                                        "Subproduction4");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(CopperHeadConstants.IF_EXPR, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_5,
                                        "Subproduction5");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(CopperHeadConstants.BIN_OP, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_6,
                                        "Subproduction6");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(CopperHeadConstants.SET_VAR, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_7,
                                        "Subproduction7");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(CopperHeadConstants.FUNC_CALL, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_8,
                                        "Subproduction8");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(CopperHeadConstants.WHILE_LOOP, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_9,
                                        "Subproduction9");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addProduction(CopperHeadConstants.ATOM, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_2, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_3, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_4, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_5, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_6, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_7, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_8, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_10,
                                        "Subproduction10");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.NAME, 1, 1);
        alt.addToken(CopperHeadConstants.COLON, 1, 1);
        alt.addProduction(CopperHeadConstants.TYPE, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_11,
                                        "Subproduction11");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(SUBPRODUCTION_10, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_12,
                                        "Subproduction12");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.NAME, 1, 1);
        alt.addToken(CopperHeadConstants.COLON, 1, 1);
        alt.addProduction(CopperHeadConstants.TYPE, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_13,
                                        "Subproduction13");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.OP_PAREN, 1, 1);
        alt.addProduction(SUBPRODUCTION_12, 1, 1);
        alt.addProduction(CopperHeadConstants.EXPR, 1, 1);
        alt.addToken(CopperHeadConstants.CL_PAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_14,
                                        "Subproduction14");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.PLUS, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.MINUS, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.MULT, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.LESS, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.GREAT, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.EQUAL, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_15,
                                        "Subproduction15");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.INT, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.BOOL, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.STR, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_16,
                                        "Subproduction16");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.MINUS, 0, 1);
        alt.addToken(CopperHeadConstants.INTEGER, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_17,
                                        "Subproduction17");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_16, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.FALSE, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.TRUE, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.STRING, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(CopperHeadConstants.NAME, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);
    }
}
