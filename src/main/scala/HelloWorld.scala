import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.{Display, DisplayMode}

object HelloWorld extends App {
	val displayMode = new DisplayMode(800, 600)
	Display.setTitle("LWJGL Test")
	Display.setDisplayMode(displayMode)
	Display.create()
	fader(0f)
	Display.destroy()
	sys.exit(0)

	def pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState) {
				if (Keyboard.getEventKey == Keyboard.KEY_A) {
					println("A key")
				}
			}
		}
	}

	def fader(color: Float, d: Int = -1) {
		pollInput()
		val mult = if(color > 1f) 1 else if(color < 0f) -1 else d
		val newColor = color - (mult * 0.05f)

		glClearColor(newColor, newColor, newColor, 1f)
		glClear(GL_COLOR_BUFFER_BIT)

		Display.update()
		Thread.sleep(60)
		if(!Display.isCloseRequested) fader(newColor, mult)
	}
}