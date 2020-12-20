package gui

import freditor.FlexerState
import freditor.FlexerStateBuilder
import freditor.persistent.ChampMap

import freditor.FlexerState.EMPTY
import freditor.FlexerState.THIS

object Flexer : freditor.Flexer() {
    private val SLASH_SLASH = FlexerState('\n', null).setDefault(THIS)
    private val SLASH_ASTERISK___ASTERISK_SLASH = EMPTY.tail()
    private val SLASH_ASTERISK___ASTERISK = FlexerState('*', THIS, '/', SLASH_ASTERISK___ASTERISK_SLASH)
    private val SLASH_ASTERISK = FlexerState('*', SLASH_ASTERISK___ASTERISK).setDefault(THIS)

    init {
        SLASH_ASTERISK___ASTERISK.setDefault(SLASH_ASTERISK)
    }

    private val NUMBER_TAIL = FlexerState("09", THIS)
    private val NUMBER_HEAD = NUMBER_TAIL.head()

    val IDENTIFIER_TAIL = FlexerState("09AZ__az", THIS)
    private val IDENTIFIER_HEAD = IDENTIFIER_TAIL.head()

    private val START = FlexerStateBuilder()
            .set('(', OPENING_PAREN)
            .set(')', CLOSING_PAREN)
            .set('{', OPENING_BRACE)
            .set('}', CLOSING_BRACE)
            .set('\n', NEWLINE)
            .set(' ', SPACE_HEAD)
            .set('/', FlexerState('*', SLASH_ASTERISK, '/', SLASH_SLASH).head())
            .set("09", NUMBER_HEAD)
            .set("AZ__az", IDENTIFIER_HEAD)
            .build()
            .verbatim(IDENTIFIER_TAIL, "else", "false", "if", "repeat", "true", "void", "while")
            .verbatim(EMPTY, "!", "&&", ";", "||")
            .setDefault(ERROR)

    override fun start(): FlexerState = START

    override fun pickColorForLexeme(previousState: FlexerState, endState: FlexerState): Int {
        return lexemeColors[endState] ?: 0x6491B2
    }

    private val lexemeColors = ChampMap.of(ERROR, 0x808080)
            .put(START.read("/", "&", "|"), 0xFFFFFF)
            .put(START.read(";"), 0xA2A8AF)
            .put(SLASH_SLASH, SLASH_ASTERISK, SLASH_ASTERISK___ASTERISK, SLASH_ASTERISK___ASTERISK_SLASH, 0x70705F)
            .put(NUMBER_HEAD, NUMBER_TAIL, 0x0FA010)
            .put(START.read("else", "false", "if", "repeat", "true", "while"), 0xCC7832)
            .put(START.read("void"), 0xCC7832)
            .put(START.read("(", ")", "{", "}"), 0xA2A8AF)
            .put(START.read("!", "&&", "||"), 0xA2A8AB)
}
