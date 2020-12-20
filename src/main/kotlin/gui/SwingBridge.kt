package gui

import java.awt.Color
import java.awt.Component
import java.awt.Graphics
import java.awt.event.*

import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

open class HorizontalBoxPanel(vararg components: Component) : JPanel() {
    init {
        layout = BoxLayout(this, BoxLayout.X_AXIS)
        components.forEach { add(it) }
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        g?.color = Color(0x313335)
        g?.fillRect(0, 0, width, height)
    }
}

open class VerticalBoxPanel(vararg components: Component) : JPanel() {
    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        components.forEach { add(it) }
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        g?.color = Color(0x313335)
        g?.fillRect(0, 0, width, height)
    }
}

fun JComponent.onMouseClicked(handler: (MouseEvent) -> Unit) {
    addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(event: MouseEvent) {
            handler(event)
        }
    })
}

fun JComponent.onKeyPressed(handler: (KeyEvent) -> Unit) {
    addKeyListener(object : KeyAdapter() {
        override fun keyPressed(event: KeyEvent) {
            handler(event)
        }
    })
}

fun JComponent.setEmptyBorder(size: Int) {
    border = EmptyBorder(size, size, size, size)
}

fun JFrame.onWindowClosing(handler: () -> Unit) {
    addWindowListener(object : WindowAdapter() {
        override fun windowClosing(event: WindowEvent) {
            handler()
        }
    })
}
