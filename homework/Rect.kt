
class Rect(var x: Int, var y: Int, var width: Int, var height: Int) : Movable, Transforming, Figure(0) {
    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    // для каждого класса area() определяется по-своему
    override fun area(): Float {
        return (width*height).toFloat() // требуется явное приведение к вещественному числу
    }

    override fun resize(zoom: Int)
    {
        width += zoom; height += zoom
    }
    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        val dx = x - centerX
        val dy = y - centerY

        when (direction) {
            RotateDirection.Clockwise -> {
                x = centerX + dy
                y = centerY - dx
            }
            RotateDirection.CounterClockwise -> {
                x = centerX - dy
                y = centerY + dx
            }
        }
    }
}