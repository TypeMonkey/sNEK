/*
 * CopperHeadTokenizer.java
 *
 * THIS FILE HAS BEEN GENERATED AUTOMATICALLY. DO NOT EDIT!
 */

package jg.cs.compile.parser;

import java.io.Reader;

import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.TokenPattern;
import net.percederberg.grammatica.parser.Tokenizer;

/**
 * A character stream tokenizer.
 *
 *
 */
public class CopperHeadTokenizer extends Tokenizer {

    /**
     * Creates a new tokenizer for the specified input stream.
     *
     * @param input          the input stream to read
     *
     * @throws ParserCreationException if the tokenizer couldn't be
     *             initialized correctly
     */
    public CopperHeadTokenizer(Reader input)
        throws ParserCreationException {

        super(input, false);
        createPatterns();
    }

    /**
     * Initializes the tokenizer by creating all the token patterns.
     *
     * @throws ParserCreationException if the tokenizer couldn't be
     *             initialized correctly
     */
    private void createPatterns() throws ParserCreationException {
        TokenPattern  pattern;

        pattern = new TokenPattern(CopperHeadConstants.WHITESPACE,
                                   "WHITESPACE",
                                   TokenPattern.REGEXP_TYPE,
                                   "[ \\t\\n\\r]+");
        pattern.setIgnore();
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.STRING,
                                   "STRING",
                                   TokenPattern.REGEXP_TYPE,
                                   "\\'.*?\\'");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.INTEGER,
                                   "INTEGER",
                                   TokenPattern.REGEXP_TYPE,
                                   "([-|+]?[0-9]+)");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.TRUE,
                                   "TRUE",
                                   TokenPattern.STRING_TYPE,
                                   "true");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.FALSE,
                                   "FALSE",
                                   TokenPattern.STRING_TYPE,
                                   "false");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.WHILE,
                                   "WHILE",
                                   TokenPattern.STRING_TYPE,
                                   "while");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.IF,
                                   "IF",
                                   TokenPattern.STRING_TYPE,
                                   "if");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.INT,
                                   "INT",
                                   TokenPattern.STRING_TYPE,
                                   "int");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.STR,
                                   "STR",
                                   TokenPattern.STRING_TYPE,
                                   "string");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.BOOL,
                                   "BOOL",
                                   TokenPattern.STRING_TYPE,
                                   "bool");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.LET,
                                   "LET",
                                   TokenPattern.STRING_TYPE,
                                   "let");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.DEF,
                                   "DEF",
                                   TokenPattern.STRING_TYPE,
                                   "def");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.SET,
                                   "SET",
                                   TokenPattern.STRING_TYPE,
                                   "set");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.VOID,
                                   "VOID",
                                   TokenPattern.STRING_TYPE,
                                   "void");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.NAME,
                                   "NAME",
                                   TokenPattern.REGEXP_TYPE,
                                   "[a-zA-Z][a-zA-Z0-9_]*");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.PLUS,
                                   "PLUS",
                                   TokenPattern.STRING_TYPE,
                                   "+");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.MINUS,
                                   "MINUS",
                                   TokenPattern.STRING_TYPE,
                                   "-");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.MULT,
                                   "MULT",
                                   TokenPattern.STRING_TYPE,
                                   "*");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.LESS,
                                   "LESS",
                                   TokenPattern.STRING_TYPE,
                                   "<");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.GREAT,
                                   "GREAT",
                                   TokenPattern.STRING_TYPE,
                                   ">");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.EQUAL,
                                   "EQUAL",
                                   TokenPattern.STRING_TYPE,
                                   "=");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.NOT_EQ,
                                   "NOT_EQ",
                                   TokenPattern.STRING_TYPE,
                                   "!=");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.GR_EQ,
                                   "GR_EQ",
                                   TokenPattern.STRING_TYPE,
                                   ">=");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.LS_EQ,
                                   "LS_EQ",
                                   TokenPattern.STRING_TYPE,
                                   "<=");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.COLON,
                                   "COLON",
                                   TokenPattern.STRING_TYPE,
                                   ":");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.EXPONENT,
                                   "EXPONENT",
                                   TokenPattern.STRING_TYPE,
                                   "^");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.OP_PAREN,
                                   "OP_PAREN",
                                   TokenPattern.STRING_TYPE,
                                   "(");
        addPattern(pattern);

        pattern = new TokenPattern(CopperHeadConstants.CL_PAREN,
                                   "CL_PAREN",
                                   TokenPattern.STRING_TYPE,
                                   ")");
        addPattern(pattern);
    }
}
