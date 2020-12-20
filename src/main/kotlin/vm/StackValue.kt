package vm

interface StackValue {
    val color: Int
}

class ReturnAddress(val value: Int) : StackValue {
    override fun toString(): String {
        return "%4x".format(value)
    }

    override val color: Int
        get() = 0xA38302
}

class LoopCounter(val value: Int) : StackValue {
    override fun toString(): String {
        return "%4d".format(value)
    }

    override val color: Int
        get() = 0x1E9AA6
}

enum class Bool : StackValue {
    FALSE {
        override fun toString(): String {
            return "false"
        }

        override val color: Int
            get() = 0xff0000
    },
    TRUE {
        override fun toString(): String {
            return "true"
        }

        override val color: Int
            get() = 0x008000
    }
}
