import kotlin.math.PI
class Circle(var x: Int, var y: Int, var radius: Int) : Movable, Transforming, Figure(0) {
    // TODO: реализовать интерфейс Transforming
    override fun area(): Float {
        return (PI * radius * radius).toFloat();
    }
    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }
    override fun resize(zoom: Int)
    {
        radius += zoom
    }
    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        val dx = x - centerX
        val dy = y - centerY

        when (direction) {
            RotateDirection.Clockwise -> {
                x = centerX - dy
                y = centerY + dx
            }
            RotateDirection.CounterClockwise -> {
                x = centerX + dy
                y = centerY - dx
            }
        }
    }
}