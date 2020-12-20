package gui

import freditor.FlexerState
import freditor.FlexerStateBuilder
import freditor.persistent.ChampMap

import freditor.FlexerState.EMPTY
import freditor.FlexerState.THIS

object BytecodeFlexer : freditor.Flexer() {
    private val NUMBER_TAIL = FlexerState("09af", THIS)
    private val NUMBER_HEAD = NUMBER_TAIL.head()

    private val START = FlexerStateBuilder()
            .set('\n', NEWLINE)
            .set(' ', SPACE_HEAD)
            .set("09af", NUMBER_HEAD)
            .build()
            .verbatim(EMPTY, "@", "CODE", "MNEMONIC",
                    "RET",
                    "MOVE", "TRNL", "TRNA", "TRNR", "PICK", "DROP",
                    "BEEP", "HEAD", "LCLR", "FCLR", "RCLR",
                    "NOT", "AND", "OR", "XOR",
                    "PUSH", "FALSE", "TRUE", "LOOP", "CALL", "JUMP", "ELSE", "THEN")
            .setDefault(ERROR)

    override fun start(): FlexerState = START

    override fun pickColorForLexeme(previousState: FlexerState, endState: FlexerState): Int {
        val colors = if (previousState === NEWLINE) afterNewline else lexemeColors
        return colors[endState] ?: 0x12B352
    }

    private val lexemeColors = ChampMap.of(ERROR, 0x808080)
            .put(NUMBER_HEAD, NUMBER_TAIL, 0xA38302)
            .put(START.read("@", "CODE", "MNEMONIC"), 0xA2A1A0)
            .put(START.read("BEEP", "HEAD", "LCLR", "FCLR", "RCLR", "NOT", "AND", "OR", "XOR", "PUSH", "FALSE", "TRUE"), 0x1E9AA6)
            .put(START.read("RET", "LOOP", "CALL", "JUMP", "ELSE", "THEN"), 0x1E62A6)

    private val afterNewline = lexemeColors
            .put(NUMBER_HEAD, NUMBER_TAIL, 0xA2A1A0)
}
